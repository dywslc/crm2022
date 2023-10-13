package com.pn.mapper;

import com.pn.entity.Auth;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/9 23:38
 * @PackageName:com.pn.mapper
 * @ClassName: RoleAuthMapper
 * @Description: TODO
 * @Version 1.0
 */

public interface RoleAuthMapper {

//    通过roleid删除role_auth中间表
    @Delete("delete t2 from role t1 join role_auth t2 on t1.role_id = t2.role_id where t1.role_id=#{roleId}")
    public int deleteRoleAuthByRid(Integer roleId);
//    通过roleId获取权限
    public List<Auth> selectAuthByRid(Integer roleId);

}