package com.xll.sbshiro.config.shiro;

import com.xll.sbshiro.entity.Permission;
import com.xll.sbshiro.service.IPermissionService;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
@Import(ShiroManager.class)
public class ShiroConfig {

    // 权限 service
    @Resource
    private IPermissionService resourceService;


    @Bean(name = "realm")
    @DependsOn("lifecycleBeanPostProcessor")
    @ConditionalOnMissingBean
    public Realm realm() {
        return new MyRealm();
    }

    /**
     * 用户授权信息Cache
     */
    @Bean(name = "shiroCacheManager")
    @ConditionalOnMissingBean
    public CacheManager cacheManager() {
        return new MemoryConstrainedCacheManager();
    }

    @Bean(name = "securityManager")
    @ConditionalOnMissingBean
    public DefaultSecurityManager securityManager() {
        DefaultSecurityManager sm = new DefaultWebSecurityManager();
        sm.setCacheManager(cacheManager());
        return sm;
    }

    /**
     *  shiro过滤器    重要
     */
    @Bean(name = "shiroFilter")
    @DependsOn("securityManager")
    @ConditionalOnMissingBean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultSecurityManager securityManager, Realm realm) {
        securityManager.setRealm(realm);

        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        shiroFilter.setLoginUrl("/admin/login");
        shiroFilter.setSuccessUrl("/admin/index");
        shiroFilter.setUnauthorizedUrl("/previlige/no");
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/assets/**", "anon");

        filterChainDefinitionMap.put("/admin/login", "anon");

        List<Permission> list = resourceService.findAll();
        for (Permission resource : list) {
            filterChainDefinitionMap.put(resource.getSourceUrl(), "perms[" + resource.getSourceKey() + "]");
        }

        filterChainDefinitionMap.put("/admin/**", "authc");
        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilter;
    }

}
