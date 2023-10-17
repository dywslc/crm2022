package com.pn.service.impl;

import com.pn.dto.AssignRoleDto;
import com.pn.entity.Result;
import com.pn.entity.Role;
import com.pn.entity.User;
import com.pn.mapper.UserMapper;
import com.pn.page.Page;
import com.pn.service.UserService;
import com.pn.utils.DigestUtil;
import com.pn.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/8 10:31
 * @PackageName:com.pn.service.impl
 * @ClassName: UserService
 * @Description: TODO
 * @Version 1.0
 */

@Service
public class UserServiceImpl implements UserService {

    //注入UserMapper
    @Autowired
    private UserMapper userMapper;

//    根据账号查找用户业务方法
    @Override
    public User queryUserByCode(String userCode) {
        User user = userMapper.findUserByCode(userCode);
        return user;
    }

//    分页查询用户的业务方法
    @Override
    public Page queryUserByPage(Page page, User user) {
//        查询用户行数
        Integer count = userMapper.findUserRowCount(user);
//        分页查询用户
        List<User> userList = userMapper.findUserByPage(page, user);
//        组装所有分页信息
        page.setTotalNum(count);
        page.setResultList(userList);
        return page;
    }

    @Override
    public Result savaUser(User user) {
//        判断账号不能重复
        if (userMapper.findUserByCode(user.getUserCode()) != null) return Result.err(Result.CODE_ERR_BUSINESS,"用户名已存在");
        //对密码进行加密
        String userPwd = DigestUtil.hmacSign(user.getUserPwd());
        user.setUserPwd(userPwd);
//        执行添加
        int count = userMapper.insertUser(user);
        if(count ==1 )return Result.ok("添加用户成功");
        return Result.err(Result.CODE_ERR_BUSINESS,"用户添加失败！");
    }

    @Override
    public Result modifyUserState(User user) {
        int count = userMapper.updataUserById(user);
        if(count ==1){
            return Result.ok("修改成功");
        }
        return Result.err(Result.CODE_ERR_BUSINESS,"修改失败");
    }

    @Override
    public Result queryRoleByUId(Integer userId) {
        List<Role> roleList = userMapper.selectRoleByUId(userId);
        return Result.ok(roleList);
    }

    @Transactional//事务处理
    @Override
    public Result modifyUserRoleByDto(AssignRoleDto assignRoleDto) {
//        根据userID删除user_role
        userMapper.deleteUserRoleByUId(assignRoleDto.getUserId());
//        根据userId和roleId添加user_role
        List<String> roleCheckList = assignRoleDto.getRoleCheckList();
        for (String roleName : roleCheckList) {
//            通过roleName查询到roleId
            Integer roleId = userMapper.selectRoleByRname(roleName);
//            新增user_role
            int count = userMapper.insertUserRole(assignRoleDto.getUserId(), roleId);
            if (count == 0)return Result.err(Result.CODE_ERR_BUSINESS,"分配角色失败！");
        }
        return Result.ok("分配角色成功");
    }

    @Override
    public Result reomveUserByList(List<Integer> userIdList) {
        for (Integer userId : userIdList) {
            int count = userMapper.setIsDeleteByUid(userId);
            if(count == 0)return Result.err(Result.CODE_ERR_BUSINESS,"删除用户失败！");
        }
        return Result.ok("删除用户成功");


    }

    @Autowired
    private TokenUtils tokenUtils;
    @Override
    public Result modifyUserNameByUid(User user,String token) {
        int userId = tokenUtils.getCurrentUser( token).getUserId();
        user.setUpdateBy(userId);
        int count = userMapper.setUserNameByUid(user);
        if (count == 0)return Result.err(Result.CODE_ERR_BUSINESS,"修改用户名失败！");
        return Result.ok("修改用户名成功！");
    }

    @Override
    public Result modifyUserPwdByUid(Integer userId) {
        int count = userMapper.setUserPwdByUid(userId);
        if (count == 0)return Result.err(Result.CODE_ERR_BUSINESS,"重置密码失败！");
        return Result.ok("重置密码成功");
    }

    @Override
    public List<Role> queryRoleByUid(Integer userId) {
        return userMapper.selectRoleByUId(userId);
    }


}
