package com.pn.service;

import com.pn.entity.Result;
import com.pn.entity.Role;
import com.pn.page.Page;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/9 10:05
 * @PackageName:com.pn.service
 * @ClassName: RoleService
 * @Description: TODO
 * @Version 1.0
 */

public interface RoleService{
//  查询所有角色的业务方法
    public List<Role> findRoleAll();

//  分页查询角色方法
    public Page findRolePage(Page page, Role role);
//  新增角色
    public Result saveRole(Role role);
//    根据roleId修改roleState
    public Result modifyRolyState(Role role);
//  通过roleName或者roleCode获取role;
    public Role findRoleByNameOrCode(String Rname,String Rcode);
//    根据roleId删除role
    public Result removeRoleByRid(Integer roleId);
//    根据roleId修改roleName
    public Result modifyRnameByRid(Role role);





}
