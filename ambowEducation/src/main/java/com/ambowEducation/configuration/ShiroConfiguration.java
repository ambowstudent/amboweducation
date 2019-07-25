package com.ambowEducation.configuration;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {

    @Autowired
    private RedisConfig redisConfig;

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){

        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);


        shiroFilterFactoryBean.setLoginUrl("/api/pub/need_login");

        shiroFilterFactoryBean.setUnauthorizedUrl("/api/pub/no_permission");
        Map<String , Filter> map = new LinkedHashMap<>();
        map.put("customRoles", new CustomAuthorizationFilter());
        //自定义的过滤器解决跨域
        map.put("corsFilter", new AuthorizationInterceptor());

        shiroFilterFactoryBean.setFilters(map);

        Map<String,String > filterMap=new LinkedHashMap<>();
       filterMap.put("/logout", "logout");

        filterMap.put("/api/pub/**", "anon");

        filterMap.put("/api/v1/tutor/**", "customRoles[tutor,admin]");
        filterMap.put("/api/v1/student/**", "customRoles[student,admin]");
        filterMap.put("/api/v1/technical_teacher/**", "customRoles[teacher,admin]");
        filterMap.put("/api/v1/admin/**", "customRoles[admin]");
        filterMap.put("/**", "corsFilter");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }
    //安全管理器
    @Bean
    public SecurityManager securityManager(RedisCacheManager redisCacheManager){
        DefaultWebSecurityManager manager=new DefaultWebSecurityManager();
        //设置回话管理
        manager.setSessionManager(customSessionManager());
        //设置realm
        //设置缓存
        manager.setCacheManager(redisCacheManager);

        manager.setRealm(customRealm());
        return manager;
    }
//自定义realm
    @Bean
    public CustomRealm customRealm(){
        CustomRealm customRealm=new CustomRealm();
        //customRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return customRealm;
    }
    //自定义session管理器
    @Bean
    public CustomSessionManager customSessionManager(){
        CustomSessionManager customSessionManager=new CustomSessionManager();
        return customSessionManager;
    }


    @Bean
    public RedisManager redisManager(){
        RedisManager redisManager=new RedisManager();
        redisManager.setDatabase(redisConfig.getDatabase());
        redisManager.setHost(redisConfig.getHost());
        redisManager.setPort(redisConfig.getPort());
        redisManager.setPassword(redisConfig.getPassword());
        return redisManager;
    }
   // cache管理器
    @Bean
    public RedisCacheManager redisCacheManager(RedisManager redisManager){
        RedisCacheManager redisCacheManager=new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager);
        //设置过期时间，单位是秒
        redisCacheManager.setExpire(60*30);
        return redisCacheManager;
    }

}
