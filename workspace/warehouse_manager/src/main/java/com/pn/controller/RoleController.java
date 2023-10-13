package com.pn.controller;

import com.pn.dto.AuthGrantDto;
import com.pn.entity.*;
import com.pn.page.Page;
import com.pn.service.AuthService;
import com.pn.service.RoleAuthService;
import com.pn.service.RoleService;
import com.pn.utils.TokenUtils;
import com.pn.utils.WarehouseConstants;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.executor.ResultExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Priority;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/9 10:07
 * @PackageName:com.pn.controller
 * @ClassName: RoleController
 * @Description: TODO
 * @Version 1.0
 */

@RestController
@RequestMapping("/role")
public class RoleController {
//    controller注入service service注入mapper
    @Autowired
    private RoleService roleService;

//    注入RoleAuthService
    @Autowired
    private RoleAuthService roleAuthService;

//    注入authService
    @Autowired
    private AuthService authService;

//    查询所有角色url接口 /role/role-list
    @RequestMapping("/role-list")
    public Result roleList(){
        List<Role> roleList = roleService.findRoleAll();
        return Result.ok(roleList);
    }

//    url = role/role-page-list?roleName=&roleCode=&roleState=&pageSize=5&pageNum=1&totalNum=0
//    根据roleName，roleCode，roleState来查询所有角色


    @RequestMapping("/role-page-list")
    public Result rolePageList(Page page,Role role){
        page = roleService.findRolePage(page, role);
        return Result.ok(page);
    }
//    role的url接口 role/role-add
//    添加角色
    @Autowired
    private TokenUtils tokenUtils;
    @PostMapping("/role-add")
    public Result roleAdd(@RequestBody Role role, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME)String token){
//        判断角色是否已存在
        if (roleService.findRoleByNameOrCode(role.getRoleName(), role.getRoleCode()) != null) {
            return Result.err(Result.CODE_ERR_BUSINESS,"角色已存在");
        }
//        角色不存在，执行业务
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int userId = currentUser.getUserId();
        role.setCreateBy(userId);
        return roleService.saveRole(role);
    }
//    role的url接口 role/role-state-update
    @PutMapping("/role-state-update")
    public Result roleState(@RequestBody Role role){
        return roleService.modifyRolyState(role);
    }

//    role的url接口role/role-delete/{roleId}
//    根据roleId删除role
    @RequestMapping("/role-delete/{roleId}")
    public Result roleDete(@PathVariable Integer roleId){
        roleAuthService.removeRoleAuthByRid(roleId);
        return roleService.removeRoleByRid(roleId);
    }

//    role的url接口 role/role-update
//    根据roleId修改roleName
    @PutMapping("/role-update")
    public Result roleUpdate(@RequestBody Role role){
        return roleService.modifyRnameByRid(role);
    }
//    role的rul接口role/role-auth?roleId=16
//    通过roleId获取当前角色权限
    @GetMapping("/role-auth")
    public Result roleAuth(Integer roleId){
        List<Auth> authList = roleAuthService.findAuthByRid(roleId);
        List<Integer> authIdList = new ArrayList<>();
        for (Auth auth : authList) {
            authIdList.add(auth.getAuthId());
        }
        return Result.ok(authIdList);
    }

//    role的url接口role/auth-grant
//    通过rolegrantdto更改角色权限
    @PutMapping("/auth-grant")
    public Result authGrantByDto(@RequestBody AuthGrantDto authGrantDto){
        return authService.modifyRoleAuthByDto(authGrantDto);
    }

    //  导出数据
    @GetMapping("/exportTable")
    public Result exportTable(Page page,Role role){
        page = roleService.findRolePage(page, role);
        List<?> resultList = page.getResultList();
        return Result.ok(resultList);
    }
}
