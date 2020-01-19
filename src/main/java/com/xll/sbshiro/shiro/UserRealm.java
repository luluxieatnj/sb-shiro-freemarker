package com.xll.sbshiro.shiro;

import com.xll.sbshiro.entity.User;
import com.xll.sbshiro.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;

    /*
     授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /*
        认证信息，主要针对用户登录，
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        // 获取token
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        char[] chars = token.getPassword();
        String s = new String(chars);
        User user = null;

        if (user == null) {
            throw new AccountException("帐号或密码不正确！");
        } else {
            user.setLastlogin(new Date());
            userService.updateById(user);
        }
        return new SimpleAuthenticationInfo(user, user.getPwd(), getName());
    }
}
