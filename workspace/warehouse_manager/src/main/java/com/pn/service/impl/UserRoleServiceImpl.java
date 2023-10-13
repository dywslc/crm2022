package com.pn.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.pn.mapper.UserRoleMapper;
import com.pn.service.UserRoleService;
/**
 * @Author 自由的骏马
 * @Date 2023/10/10 17:19
 * @PackageName:com.pn.service.impl
 * @ClassName: UserRoleServiceImpl
 * @Description: TODO
 * @Version 1.0
 */

@Service
public class UserRoleServiceImpl implements UserRoleService{

    @Resource
    private UserRoleMapper userRoleMapper;

}
