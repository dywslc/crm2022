package com.pn.service.impl;

import com.pn.entity.Auth;
import com.pn.service.RoleAuthService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.pn.mapper.RoleAuthMapper;

import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/9 23:38
 * @PackageName:com.pn.service.impl
 * @ClassName: RoleAuthServiceImpl
 * @Description: TODO
 * @Version 1.0
 */

@Service
public class RoleAuthServiceImpl implements RoleAuthService {

    @Resource
    private RoleAuthMapper roleAuthMapper;

    @Override
    public void removeRoleAuthByRid(Integer roleId) {
        int count = roleAuthMapper.deleteRoleAuthByRid(roleId);
    }

    @Override
    public List<Auth> findAuthByRid(Integer roleId) {
        List<Auth> authList = roleAuthMapper.selectAuthByRid(roleId);
        return authList;
    }
}
