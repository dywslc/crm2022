package com.pn.service.impl;

import com.pn.entity.Result;
import com.pn.entity.Role;
import com.pn.page.Page;
import com.pn.service.RoleService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.pn.mapper.RoleMapper;

import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/9 10:05
 * @PackageName:com.pn.service.impl
 * @ClassName: RoleServiceImpl
 * @Description: TODO
 * @Version 1.0
 */
//2.指定缓存的名称（数据保存到redis中的键的前缀，一般给标注@CacheConfig注解类的全路径）
@CacheConfig(cacheNames = "com.pn.service.impl.RoleServiceImpl")
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Cacheable(key = "'all:role'")//3.查询方法上标注@Cacheable指定缓存的键
    @Override
    public List<Role> findRoleAll() {
        List<Role> roleList = roleMapper.selectRoleAll();
        return roleList;

    }



    @Override
    public Page findRolePage(Page page, Role role) {
//        查询角色行数
        Integer count = roleMapper.selectRoleRowCount(role);
//        查询角色列表
        List<Role> roleList = roleMapper.selectRolePage(page, role);
//        组装page信息，组装page总页数
        page.setTotalNum(count);
//        组装page查询到的list集合
        page.setResultList(roleList);
        return page;

    }

    @CacheEvict(key = "'all:role'")//清除缓存
    @Override
    public Result saveRole(Role role) {
        int count = roleMapper.insertRole(role);
        if (count ==0) return Result.err(Result.CODE_ERR_BUSINESS,"添加角色失败！");
        return Result.ok("添加角色成功");
    }

    @CacheEvict(key = "'all:role'")
    @Override
    public Result modifyRolyState(Role role) {
        int count = roleMapper.updateRStateByRid(role);
        if (count == 0)return Result.err(Result.CODE_ERR_BUSINESS,"修改角色状态失败!");
        return Result.ok("修改用户状态成功");
    }

    @Override
    public Role findRoleByNameOrCode(String Rname,String Rcode) {
        Role role = roleMapper.selectRoleByNameOrCode(Rname,Rcode);
        return role;

    }

    @CacheEvict(key = "'all:role'")
    @Override
    public Result removeRoleByRid(Integer roleId) {
        int count = roleMapper.deleteRoleByRid(roleId);
        if (count == 0)return Result.err(Result.CODE_ERR_BUSINESS,"删除角色失败!");
        return Result.ok("删除角色成功");
    }

    @Override
    public Result modifyRnameByRid(Role role) {
        int count = roleMapper.updateRnameByRid(role);
        return count==0?Result.err(Result.CODE_ERR_BUSINESS,"修改角色描述失败！"):Result.ok("修改角色描述成功");
    }


}
