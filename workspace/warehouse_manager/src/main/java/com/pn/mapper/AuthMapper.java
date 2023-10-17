package com.pn.mapper;

import com.pn.dto.AuthGrantDto;
import com.pn.entity.Auth;
import lombok.Data;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/8 14:51
 * @PackageName:com.pn.mapper
 * @ClassName: AuthInfoMapper
 * @Description: TODO
 * @Version 1.0
 */

public interface AuthMapper {
//    根据userId查询用户权限下的所有菜单的方法
    public List<Auth> findAuthByUId(Integer userId);
//    查询所有权限菜单的方法
    @Select("select * from auth_info where auth_state = 1 and auth_type != 3")
    public List<Auth> findAuthAll();
//    通过role_id删除role_auth
    @Delete("delete from role_auth where role_id=#{roleId}")
    public int deleteRoleAuthByRid(Integer roleId);
//    通过dto增加role_auth
    public int insertRoleAuthByDto(AuthGrantDto authGrantDto);

}