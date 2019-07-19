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

        //设置如果未登录时需要跳转的url
        shiroFilterFactoryBean.setLoginUrl("/api/pub/need_login");
        //设置登陆成功后跳转的界面或者url，如果是前后端分离，则可以不写该选项
        //shiroFilterFactoryBean.setSuccessUrl("/");
        //登录成功后，但是未授权，则调到未授权的url
        shiroFilterFactoryBean.setUnauthorizedUrl("/api/pub/no_permission");
        //将自定义的角色过滤器放到map里面
        Map<String , Filter> map = new LinkedHashMap<>();
        //这里配置好角色过滤器时，拦截路径时的前缀要写成key的值
        map.put("customRoles", new CustomAuthorizationFilter());
        //自定义的过滤器解决跨域
        map.put("corsFilter", new AuthorizationInterceptor());
        //将自定义的角色过滤器放到shiroFilterFactoryBean里面去
        shiroFilterFactoryBean.setFilters(map);
        //拦截器路径，同一拦截，注意要使用linkedHashMap保证过滤器的顺序
        Map<String,String > filterMap=new LinkedHashMap<>();
        //key是需要拦截的路径，value采用哪种过滤器，或者那种角色或权限
        //处理跨域的路径
        //登出过滤器
       filterMap.put("/logout", "logout");
        //匿名访问，也就是说无需的登录即可访问
        filterMap.put("/api/pub/**", "anon");
        //有相应角色才能访问的,例如管理员才能访问
        filterMap.put("/api/v1/**", "corsFilter");
        filterMap.put("/api/v1/tutor/**", "customRoles[tutor,admin]");
        filterMap.put("/api/v1/student/**", "customRoles[student,admin]");
        filterMap.put("/api/v1/technical_teacher/**", "customRoles[teacher,admin]");
        //需要登录才能访问的
        //全局拦截，避免遗漏哪些路径，放到最下面
        filterMap.put("/**", "authc");
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
    //密码匹配器
//    @Bean
//    public HashedCredentialsMatcher hashedCredentialsMatcher(){
//        HashedCredentialsMatcher hashedCredentialsMatcher=new HashedCredentialsMatcher();
//        hashedCredentialsMatcher.setHashAlgorithmName("md5");
//        hashedCredentialsMatcher.setHashIterations(2);
//        return hashedCredentialsMatcher;
//    }
    //redis管理器,这个redis是跟shiro整合的，对于权限来说查询后会自动放入缓存，不需要在service层
    //手动放入

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
