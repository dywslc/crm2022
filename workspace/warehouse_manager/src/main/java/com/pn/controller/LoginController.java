package com.pn.controller;

import com.google.code.kaptcha.Producer;
import com.pn.entity.*;
import com.pn.service.AuthService;
import com.pn.service.impl.UserServiceImpl;
import com.pn.utils.DigestUtil;
import com.pn.utils.TokenUtils;
import com.pn.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author 自由的骏马
 * @Date 2023/10/7 22:24
 * @PackageName:com.pn.controller
 * @ClassName: LoginController
 * @Description: TODO
 * @Version 1.0
 */
@RestController
public class LoginController {

//    注入DefaultKaptcha的bean对象--生成验证码图片
    @Resource(name = "captchaProducer")
    private Producer producer;
//    注入redis模版
    @Autowired
    private StringRedisTemplate redisTemplate;

//    注入UserService
    @Autowired
    private UserServiceImpl userService;
//    注入TokenUtils
    @Autowired
    private TokenUtils tokenUtils;

//  注入AuthService
    @Autowired
    private AuthService authService;

    /**
     * 生成验证码
     * @param response
     */
    @RequestMapping("/captcha/captchaImage")
    public void captchaImage(HttpServletResponse response){
        ServletOutputStream out = null;
        try {
//        生成验证码图片的文本
            String text = producer.createText();
//       使用验证码文本生成验证码图片 -- 在内存中
            BufferedImage image = producer.createImage(text);
//        将验证码文本作为键保存到redis中 -- 键过期时间1800s，单位是秒
            redisTemplate.opsForValue().set(text,"",60*30, TimeUnit.SECONDS);
        /*
            将验证码响应给前端
        */
//        设置响应正文 image/jpeg
            response.setContentType("image/jpeg");
//            将验证码写给前端
            out = response.getOutputStream();
            ImageIO.write(image,"jpg",out);//使用响应对象的字节输出流写入验证码图片
//          刷新
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }


    /**
     * 登录的url接口/login
     * @param loginUser --表示接收并封装前端传递的登录的用户信息的json数据
     * @return Result  -- 表示向前端响应结果Result对象转的json串，包含响应状态码，成功失败响应，响应信息，相应数据
     */

    @RequestMapping("/login")
    public Result login(@RequestBody LoginUser loginUser,HttpServletResponse response) {
        /**
         * 校验登录
         */
//        拿到用户验证码
        String code = loginUser.getVerificationCode();
        if (!redisTemplate.hasKey(code)) return Result.err(Result.CODE_ERR_BUSINESS,"验证码错误！");
//          根据账号查询用户
        User user = userService.queryUserByCode(loginUser.getUserCode());
//         账户不存在
        if (user == null) return Result.err(Result.CODE_ERR_BUSINESS,"账户不存在");
//        用户未审核
        if (user.getUserState().equals(WarehouseConstants.USER_STATE_NOT_PASS)) return Result.err(Result.CODE_ERR_BUSINESS,"用户未审核");
//          拿到用户录入的密码
        String userPwd = loginUser.getUserPwd();
//          进行MD5加密
            userPwd = DigestUtil.hmacSign(userPwd);
//         密码错误
            if (!userPwd.equals(user.getUserPwd()) ) return Result.err(Result.CODE_ERR_BUSINESS,"密码错误！");
        /**
         * 校验完成
         */
//        生成currentUser
        CurrentUser currentUser = new CurrentUser(user.getUserId(), user.getUserCode(), user.getUserName());
//        生成token
        String token = tokenUtils.loginSign(currentUser, userPwd);
//        向客户端响应jwt token
        return Result.ok("登录成功",token);
        }

    /**
     * 欢迎信息
     * @param token
     * @return
     */
    @RequestMapping("/curr-user")
    public Result curruser(@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
//        解析token，拿到封装了当前登录用户信息的currentUser对象
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
//        响应
        return Result.ok(currentUser);
    }

    /**
     * 菜单树列表
     * @param token
     * @return
     */
    @RequestMapping("/user/auth-list")
    public Result loadAuthTree(@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
//        拿到当前登录的用户id
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int userId = currentUser.getUserId();
//       执行业务
        List<Auth> authTreeList = authService.authTreeByUId(userId);
//        响应
        return Result.ok(authTreeList);

    }

    @RequestMapping("/logout")
    public Result logout(@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME) String token){
        redisTemplate.delete(token);
        return Result.ok("退出登录成功");
    }
}
