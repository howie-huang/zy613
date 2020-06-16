package com.exam.zy613.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.exam.zy613.util.AuthReaml;
import com.exam.zy613.util.CustomCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置类
 * @author 31515
 */
@SpringBootConfiguration
public class ShiroConfig {
    /**
     *声明lifecycleBeanPostProcessor类
     *  作用是将shiro的相关类的生命周期交给Spring
     * @return
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        LifecycleBeanPostProcessor processor = new LifecycleBeanPostProcessor();
        return processor;
    }

    /**
     * 密码比较器，里边设置有密码的加密规则——自定义加密规则
     * @return
     */
    @Bean(name = "customCredentialsMatcher")
    public CustomCredentialsMatcher customCredentialsMatcher() {
        CustomCredentialsMatcher matcher = new CustomCredentialsMatcher();
        return matcher;
    }

    /**
     * 声明一个缓存对象——后期一般情况下缓存都是redis整合
     * mybatis的缓存  还有shiro的缓存扥跟一般都是给redis
     * @return
     */
    @Bean(name = "ehCacheManager")
    @DependsOn("lifecycleBeanPostProcessor")
    public EhCacheManager ehCacheManager() {
        return new EhCacheManager();
    }

    /**
     * 自定义realm对象
     * @return
     */
    @Bean(name = "authReaml")
    @DependsOn("lifecycleBeanPostProcessor")
    public AuthReaml authReaml() {
        AuthReaml authReaml = new AuthReaml();
        authReaml.setCredentialsMatcher(customCredentialsMatcher());
        authReaml.setCacheManager(ehCacheManager());
        return authReaml;
    }
    /**
     * 创建一个securityManager管理类
     *  管理了所有的reaml  subject对象等.......管理认证和授权
     * @return
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(authReaml());
        securityManager.setCacheManager(ehCacheManager());
        return securityManager;
    }

    /**
     * shiro自带的过滤器
     * @return
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();
        filter.setSecurityManager(securityManager());
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        /*
            所有路径全部都是必须认证方可请求：authc必须认证方可请求
            user：认证才可以请求，但是记住自己的这个对象也可以请求。remberMe,,此时user

            角色相关：
                /admin/**=roles[admin] 必须是用户具备admin角色的时候才可以访问
                /admin/**=perms[admin:add]必须用户具备admin:add权限的时候可以访问
         */
        filterChainDefinitionMap.put("/logout", "logout");
        //anon指的是匿名，其实就是不拦截，一般情况下所有的静态文件都不链接
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        //放行jq
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/login/login", "anon");
        filterChainDefinitionMap.put("/**", "authc");
        filterChainDefinitionMap.put("/login","anon");
        //登录页面，如果拦截了之后都会到/login
        filter.setLoginUrl("/login");
        //成功后的
        filter.setSuccessUrl("/hello");
        //没有权限
        filter.setUnauthorizedUrl("/hello");
        filter.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return filter;
    }

    /**
     * 生成代理——就是让注解生效
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor aASA = new AuthorizationAttributeSourceAdvisor();
        aASA.setSecurityManager(securityManager());
        return aASA;
    }

    /**
     * 目的是在thymeleaf中使用shiro的标签
     **/
    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}
