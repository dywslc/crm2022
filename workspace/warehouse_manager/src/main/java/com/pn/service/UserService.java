package com.pn.service;

import com.pn.dto.AssignRoleDto;
import com.pn.entity.Result;
import com.pn.entity.Role;
import com.pn.entity.User;
import com.pn.page.Page;

import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/8 10:28
 * @PackageName:com.pn.service
 * @ClassName: Uservice
 * @Description: TODO
 * @Version 1.0
 */
public interface UserService {

//    根据账号查询用户的业务方法
    public User queryUserByCode(String userCode);
//    分页查询用户的业务方法
    public Page queryUserByPage(Page page,User user);
//    新增用户的业务方法
    public Result savaUser(User user);
//   根据userId修改数据的业务方法
    public Result modifyUserState(User user);
//   根据userId查询用户的业务方法
    public Result queryRoleByUId(Integer userId);
//   根据AssignRoleDto对user_role表删除和增加
    public Result modifyUserRoleByDto(AssignRoleDto assignRoleDto);
//   根据userId删除用户
    public Result reomveUserByList(List<Integer> userIdList);
//    根据userId修改userName、
    public Result modifyUserNameByUid(User user,String token);
//    根据userId修改userPwd
    public Result modifyUserPwdByUid(Integer userId);
//    根据userId查询角色
    public List<Role> queryRoleByUid(Integer userId);



}
