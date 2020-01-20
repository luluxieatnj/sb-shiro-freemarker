package com.xll.sbshiro.shiro;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 *  shiro 配置类
 */
@Configuration
public class ShiroConfiguration {

    // 第一步   注入userRealm
    @Bean("userRealm")
    public UserRealm getUserRealm(){
        UserRealm userRealm = new UserRealm();
        return userRealm;
    }

    // 第二步  securityManager
    @Bean(name = "securityManager")
    @ConditionalOnMissingBean
    public SecurityManager securityManager(UserRealm userRealm) {
        DefaultSecurityManager sm = new DefaultWebSecurityManager();
        // 交给默认的安全管理器
        sm.setRealm(userRealm);
        return sm;
    }

    // 第三步  过滤器
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        // 最后要返回的bean
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();

        Map<String, String> filterChainDefinitionMap = new HashMap<>();

        // 静态资源不拦截
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/html/**", "anon");

        // 登陆页面   不能拦截
        filterChainDefinitionMap.put("/user/login", "anon");

        // 退出登录  显示的页面
        filterChainDefinitionMap.put("/user/logout", "logout");

        //  authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
        filterChainDefinitionMap.put("/**", "authc");


        // 设置默认的安全管理器  1  2  3  4  5
        bean.setSecurityManager(securityManager);
        bean.setLoginUrl("/login");
        bean.setSuccessUrl("/home");
        bean.setUnauthorizedUrl("/login");
        bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return bean;
    }
}
