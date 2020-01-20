package com.xll.sbshiro.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    // 1.userRealm
    @Bean("userRealm")
    public UserRealm getUserRealm() {
        return new UserRealm();
    }

    // 2.SecurityManager
    @Bean
    public SecurityManager getSecurityManager(UserRealm userRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(userRealm);
        return manager;
    }

    // 3. filter
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager manager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();

        //
        Map<String, String> filterMap = new LinkedHashMap<>();
        // filterMap 需要添加3种过滤规则
        /* 1.静态资源不拦截 */
        filterMap.put("/css/**", "anon");
        filterMap.put("/fonts/**", "anon");
        filterMap.put("/img/**", "anon");
        filterMap.put("/js/**", "anon");
        filterMap.put("/html/**", "anon");
        /* 2.登陆页面请求不拦截 */
        filterMap.put("/user/login", "anon");
        /* 3.其余请求都拦截 */
        filterMap.put("/**", "authc");

        // bean需要设置的5个对象
        // 1 2 3 4 5
        bean.setSecurityManager(manager);
        bean.setLoginUrl("");
        bean.setSuccessUrl("");
        bean.setUnauthorizedUrl("");
        bean.setFilterChainDefinitionMap(filterMap);
        return bean;
    }

}
