package com.pn.controller;

import com.pn.dto.AssignRoleDto;
import com.pn.dto.AuthGrantDto;
import com.pn.entity.*;
import com.pn.page.Page;
import com.pn.service.AuthService;
import com.pn.service.RoleAuthService;
import com.pn.service.impl.UserServiceImpl;
import com.pn.utils.TokenUtils;
import com.pn.utils.WarehouseConstants;
import org.apache.ibatis.io.ResolverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/8 17:54
 * @PackageName:com.pn.controller
 * @ClassName: UserController
 * @Description: TODO
 * @Version 1.0
 */

@RequestMapping("/user")
@RestController
public class UserController {
//    注入userService
    @Autowired
    private UserServiceImpl userService;

//   分页查询用户的url接口/user/user-list
     @RequestMapping("/user-list")
    public Result userList(Page page, User user){
         page = userService.queryUserByPage(page, user);
         return Result.ok(page);
     }
/*
    添加用户的url接口/user/addUser
    参数@RequestBody user --接收并封装前端传递的json数据的用户信息

 */
    @Autowired
    private TokenUtils tokenUtils;
    @PostMapping("/addUser")
    public Result addUser(@RequestBody User user, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME)String token){
//        向user中添加创建者creatBy
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int creatBy = currentUser.getUserId();
        user.setCreateBy(creatBy);
//        执行业务
        Result result = userService.savaUser(user);
//        响应
        return result;
    }

/*
    添加用户的url接口 /user/updateState
     禁用启用用户状态
 */
    @RequestMapping("/updateState")
    public Result updateState(@RequestBody User user){
        Result result = userService.modifyUserState(user);
        return result;
    }

    /*
        添加用户的url接口/user-role-list/{userId}
        根据用户id查询已分配角色

    * */
    @GetMapping("user-role-list/{userId}")
    public Result urList(@PathVariable(name = "userId") Integer userId){
        Result result = userService.queryRoleByUId(userId);
        return result;
    }

    /*
        添加用户的url接口 /user/assignRole
        根据userId对user_role表进行删除和增加
    * */
    @RequestMapping("/assignRole")
    public Result assignRole(@RequestBody AssignRoleDto assignRoleDto){
        Result result = userService.modifyUserRoleByDto(assignRoleDto);
        return result;
    }

    /*
    *   添加用户的url接口 user/deleteUserList
    *   根据userIdList删除用户
    * */

    @RequestMapping("/deleteUserList")
    public Result deleteUserList(@RequestBody List<Integer> userIdList){
        return userService.reomveUserByList(userIdList);

    }
    /*
    *  添加用户url接口 user/deleteUser/{userId}
    *  根据用户userId删除用户
    * */

    @RequestMapping("deleteUser/{userId}")
    public Result deleteUser(@PathVariable Integer userId){
//        List<Integer> userIdList = new ArrayList<>();
//        userIdList.add(userId);
        return userService.reomveUserByList(Arrays.asList(userId));
    }

    /*
    *  添加用户url接口 user/updateUser
    *   根据userId更改userName;
    * */
    @RequestMapping("/updateUser")
    public Result updateUser(@RequestBody User user,@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME)String token){
        return userService.modifyUserNameByUid(user,token);
    }
    /*
    * 添加用户url接口user/updatePwd/{userId}
    * 通过userId更改userPwd
    * */
    @RequestMapping("/updatePwd/{userId}")
    public Result updatePwd(@PathVariable Integer userId){
        return userService.modifyUserPwdByUid(userId);
    }


//    提供角色的url接口 user/user-auth?userId=35
//    通过userId获取角色权限
    @Autowired
    private RoleAuthService roleAuthService;
    @GetMapping("/user-auth")
    public Result userAuth(Integer userId){
        List<Role> roles = userService.queryRoleByUid(userId);
        List<Auth> auths = new ArrayList<>();
        List<Integer> authIds = new ArrayList<>();
        for (Role role : roles) {
            for (Auth auth : roleAuthService.findAuthByRid(role.getRoleId())) {
                auths.add(auth);
            }
        }
        for (Auth auth : auths) {
            authIds.add(auth.getAuthId());
        }
        return Result.ok(authIds);
    }

    //  导出数据
    @GetMapping("/exportTable")
    public Result exportTable(Page page,User user){
        page = userService.queryUserByPage(page, user);
        List<?> resultList = page.getResultList();
        return Result.ok(resultList);
    }
}
