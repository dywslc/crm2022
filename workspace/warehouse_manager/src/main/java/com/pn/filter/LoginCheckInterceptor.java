package com.pn.filter;

import com.alibaba.fastjson.JSON;
import com.pn.entity.Result;
import com.pn.utils.WarehouseConstants;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @Author 自由的骏马
 * @Date 2023/10/9 8:45
 * @PackageName:com.pn.filter
 * @ClassName: LoginCheckHandler
 * @Description: TODO
 * @Version 1.0
 */
public class LoginCheckInterceptor implements HandlerInterceptor {

    private RedisTemplate redisTemplate;

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
//      校验请求都校验是否携带token，以及redis中是否有对象token的键
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
            return false;
        }
//      有则放行
        return true;
    }
}
