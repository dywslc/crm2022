package com.pn.config;

import com.pn.filter.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author 自由的骏马
 * @Date 2023/10/9 8:48
 * @PackageName:com.pn.config
 * @ClassName: HandlerConfig
 * @Description: TODO
 * @Version 1.0
 */

@Configuration
public class HandlerConfig implements WebMvcConfigurer {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        创建拦截器对象
        LoginCheckInterceptor interceptor = new LoginCheckInterceptor();
//        注入redis模版
        interceptor.setRedisTemplate(redisTemplate);
//        声明拦截url和白名单
        String[] path = {"/*"};
        String[] expath = {"/login","/logout","/captcha/captchaImage"};
        registry.addInterceptor(interceptor).addPathPatterns(path).excludePathPatterns(expath);


    }
}
