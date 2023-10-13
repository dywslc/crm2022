package com.pn.filter;

import com.alibaba.fastjson.JSON;
import com.pn.entity.Result;
import com.pn.utils.WarehouseConstants;
import io.netty.util.internal.StringUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author 自由的骏马
 * @Date 2023/10/8 11:39
 * @PackageName:com.pn.fileter
 * @ClassName: LoginCheckFilter
 * @Description: 自定义的登录过滤器
 * @Version 1.0
 */
@Deprecated
public class LoginCheckFilter implements Filter {

    private RedisTemplate redisTemplate;

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    //过滤器拦截到请求执行的方法
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
//        1.白名单放行
        ArrayList<String> urlList = new ArrayList<>();
        urlList.add("/login");
        urlList.add("/captcha/captchaImage");
//        过滤器拦截到的当前请求的url
        String url = request.getServletPath();
        if (urlList.contains(url)) {//白名单请求
            chain.doFilter(request,response);
            return;
        }
//        2.其他请求都校验是否携带token，以及redis中是否有对象token的键
        String token = request.getHeader(WarehouseConstants.HEADER_TOKEN_NAME);
        //        2.1无则不放行，说明未登录或者token过期
        if (!StringUtils.hasText(token) || !redisTemplate.hasKey(token)) {
            Result result = Result.err(Result.CODE_ERR_UNLOGINED, "您尚未登录");
            String jsonStr = JSON.toJSONString(result);
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println(jsonStr);
            out.flush();
            out.close();
            return;
        }
//        2.2有则放行
        chain.doFilter(request,response);



    }
}
