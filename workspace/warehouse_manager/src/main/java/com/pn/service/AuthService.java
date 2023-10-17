package com.pn.service;

import com.pn.dto.AuthGrantDto;
import com.pn.entity.Auth;
import com.pn.entity.Result;

import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/8 14:51
 * @PackageName:com.pn.service
 * @ClassName: AuthInfoService
 * @Description: TODO
 * @Version 1.0
 */

public interface AuthService {
//    查询用户菜单树的业务方法
    public List<Auth> authTreeByUId(Integer userId);

//    查询角色权限的业务方法
    public List<Auth> authTreeAll();

//    通过dto更改角色权限的方法
    public Result modifyRoleAuthByDto(AuthGrantDto authGrantDto);


}
