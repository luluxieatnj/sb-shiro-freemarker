package com.xll.sbshiro.shiro;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xll.sbshiro.entity.User;
import com.xll.sbshiro.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.Objects;

@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
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
