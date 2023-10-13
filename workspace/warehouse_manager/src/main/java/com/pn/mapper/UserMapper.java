package com.pn.mapper;

import com.pn.dto.AssignRoleDto;
import com.pn.entity.Role;
import com.pn.entity.User;
import com.pn.page.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/8 10:17
 * @PackageName:com.pn.mapper
 * @ClassName: UserMapper
 * @Description: user_info表的mapper接口
 * @Version 1.0
 */
public interface UserMapper {

//    根据账号查询用户信息的方法
    User findUserByCode(String userCode);

    //    查询用户行数的方法
    public Integer findUserRowCount(User user);

    //    分页查询用户的方法[多参数需要使用@Param]
    public List<User> findUserByPage(@Param("page") Page page, @Param("user") User user);

//    添加用户的方法
    public int insertUser(User user);

//    更具用户id修改数据
    public int updataUserById(User user);

//    根据用户id查找用户已分配角色
    public List<Role> selectRoleByUId(Integer userId);
//    根据userId删除user_role
    public int deleteUserRoleByUId(Integer userId);
//    新增user_role
    public int insertUserRole(@Param("userId") Integer userId,@Param("roleId") Integer roleId);
//    根据roleName查询roleId
    public Integer selectRoleByRname(String Rname);
//    根据userId删除user
    public int deleteUserByUId(Integer userId);
//    根据userId修改user.is_delete
    public int setIsDeleteByUid(Integer userId);
//    根据userId更改UserName
    public int setUserNameByUid(@Param("user") User user);
//    根据userId修改userPwd为123456
    public int setUserPwdByUid(Integer userId);




}
