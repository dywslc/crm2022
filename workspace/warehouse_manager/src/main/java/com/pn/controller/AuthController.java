package com.pn.controller;

import com.pn.entity.Auth;
import com.pn.entity.Result;
import com.pn.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/10 13:41
 * @PackageName:com.pn.controller
 * @ClassName: AuthController
 * @Description: TODO
 * @Version 1.0
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

//    注入AuthService
    @Autowired
    private AuthService authService;
//    提供auth的url接口 auth/auth-tree
//    获取auth的全部权限
    @GetMapping("/auth-tree")
    public Result authTree(){
        List<Auth> authTree = authService.authTreeAll();
        return Result.ok(authTree);
    }

}
