package com.pn.config;

import com.pn.filter.LoginCheckFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @Author 自由的骏马
 * @Date 2023/10/8 11:41
 * @PackageName:com.pn.config
 * @ClassName: ServletConfig
 * @Description: 原生servlet配置类
 * @Version 1.0
 */
@Deprecated
//@Configuration
public class ServletConfig  {

    @Autowired
    private StringRedisTemplate redisTemplate;

//  配置filterRegistrationBean的bean对象， -- 注册原生Servlet中的过滤器
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
//        创建FilterRegistrationBean的bean对象
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        创建自定义的过滤器
        LoginCheckFilter loginCheckFilter = new LoginCheckFilter();
//        手动注入注入redis模版
        loginCheckFilter.setRedisTemplate(redisTemplate);
//        将自定义的过滤器注册到FilterRegistrationBean中
        filterRegistrationBean.setFilter(loginCheckFilter);
//        给过滤器指定拦截的请求
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;

    }
}
