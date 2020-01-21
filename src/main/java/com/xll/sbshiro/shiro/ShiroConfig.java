package com.xll.sbshiro.shiro;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public SecurityManager getSecurityManager(UserRealm userRealm, SessionManager sessionManager) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(userRealm);
//        manager.setSessionManager(sessionManager);
        manager.setCacheManager(getEhCacheManager());
        return manager;
    }


    /* 会话管理  SessionManager */
    @Bean("sessionManager")
    public SessionManager SessionManager() {
        return new DefaultWebSessionManager();
    }

    /*  使用缓存   授权一次  再次请求就不会数据库查询 */
    @Bean
    public EhCacheManager getEhCacheManager(){
        EhCacheManager ehcacheManager = new EhCacheManager();
        // 即使没有配置文件，默认会使用ehcache配置，不会有，缓存依然生效
        // jar包中已经有  org/apache/shiro/cache/ehcache/ehcache.xml 配置文件

//        ehcacheManager.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
        return ehcacheManager;
    }


    // 3. filter
    @Bean
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
        bean.setLoginUrl("/login");  // todo  可以手动管理
//        bean.setSuccessUrl("/home");  // todo 手动管理
        bean.setUnauthorizedUrl("/login");  // todo  可以手动管理
        bean.setFilterChainDefinitionMap(filterMap);
        return bean;
    }

}
