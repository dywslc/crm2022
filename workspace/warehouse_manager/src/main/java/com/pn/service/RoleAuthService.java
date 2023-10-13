package com.pn.service;

import com.pn.entity.Auth;

import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/9 23:38
 * @PackageName:com.pn.service
 * @ClassName: RoleAuthService
 * @Description: TODO
 * @Version 1.0
 */

public interface RoleAuthService{

//    通过roleid删除role_auth中间表
    public void removeRoleAuthByRid(Integer roleId);
//    通过roleId获取权限
    public List<Auth> findAuthByRid(Integer roleId);

}
