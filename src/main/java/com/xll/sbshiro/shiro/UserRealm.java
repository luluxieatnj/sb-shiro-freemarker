package com.xll.sbshiro.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xll.sbshiro.entity.Permission;
import com.xll.sbshiro.entity.Role;
import com.xll.sbshiro.entity.User;
import com.xll.sbshiro.service.IPermissionService;
import com.xll.sbshiro.service.IRoleService;
import com.xll.sbshiro.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPermissionService permissionService;


    /*
      授权  三种情况会进入该方法
          1、subject.hasRole(“admin”) 或 subject.isPermitted(“admin”)：
              自己去调用这个是否有什么角色或者是否有什么权限的时候；
          2、@RequiresRoles("admin") ：在方法上加注解的时候；
          3、[@shiro.hasPermission name = "admin"][/@shiro.hasPermission]：
               在页面上加shiro标签的时候，即进这个页面的时候扫描到有这个标签的时候。
   */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        String username = (String) principalCollection.getPrimaryPrincipal();
        log.info("UserRealm AuthorizationInfo username = {}", username);
//      不能取User对象   只能取出用户名
//        User user = (User) principalCollection.getPrimaryPrincipal();
//        log.info("UserRealm AuthorizationInfo user = {}", user);

        QueryWrapper<User> wrapper = new QueryWrapper<User>().eq("uname", username);
        User user = userService.getOne(wrapper);
        Integer rid = user.getRid();
        // 根据rid查询角色代码
        Role role = roleService.getById(rid);
        info.addRole(role.getCode());

        Collection<Permission> perList = permissionService.getListByRid(rid);
        List<String> list = perList.stream().map(p -> p.getCode()).collect(Collectors.toList());
        info.addStringPermissions(list);

        Set<String> roles = info.getRoles();
        Set<String> stringPermissions = info.getStringPermissions();
        log.info("roles = {} ", roles);
        log.info("stringPermissions = {} ", stringPermissions);

        return info;
    }

    /**
     *  登陆认证
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 从token取出用户名和密码  注意密码经过转换
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        // 根据用户名查询用户   或者   使用用户名和密码一起查询user信息
        QueryWrapper<User> wrapper = new QueryWrapper<User>().eq("uname", username);
        User user = userService.getOne(wrapper);

        if (Objects.isNull(user) || Objects.isNull(password)) {
            throw new UnknownAccountException("用户名或密码错误");
        }
        String pwd = user.getPwd();

        // 密码不对
        if (!password.equals(pwd)) {
            throw new UnknownAccountException("用户名或密码错误");
        }

        // 登陆成功 更新最后登陆时间
        try {
            user.setLastlogin(new Date());
            userService.updateById(user);
            log.info("更新最后登陆时间成功 user = {}", user);
        } catch (Exception e) {
            log.error("更新最后登陆时间失败 user = {}", user);
        }

        // 看看realName是什么
        String realName = getName();
        log.info("realName = {}", realName);
        return new SimpleAuthenticationInfo(username, pwd, realName);
    }
}
