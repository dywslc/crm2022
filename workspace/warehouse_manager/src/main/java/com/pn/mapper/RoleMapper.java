package com.pn.mapper;

import com.pn.entity.Result;
import com.pn.entity.Role;
import com.pn.page.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/9 10:05
 * @PackageName:com.pn.mapper
 * @ClassName: RoleMapper
 * @Description: TODO
 * @Version 1.0
 */

public interface RoleMapper {
    public List<Role> selectRoleAll();

//   根据roleName，roleCode，roleState来查询角色行数
    public Integer selectRoleRowCount(Role role);
//    分页查询角色的方法
    public List<Role> selectRolePage(@Param("page") Page page,@Param("role") Role role);
//    添加角色的方法
    public int insertRole(Role role);
//    通过role_id修改role_state
    public int updateRStateByRid(Role role);

    // 根据角色名或者角色code查询角色
    @Select("select * from role where role_name=#{roleName} or role_code=#{roleCode}")
    public Role selectRoleByNameOrCode(@Param("roleName") String roleName,@Param("roleCode") String roleCode);
//    通过角色Id删除角色
    @Delete("delete from role where role_id=#{roleId}")
    public int deleteRoleByRid(Integer roleId);

//    根据roleId修改roleName
    @Update("update role set role_desc=#{roleDesc} where role_id=#{roleId}")
    public int updateRnameByRid(Role role);

}