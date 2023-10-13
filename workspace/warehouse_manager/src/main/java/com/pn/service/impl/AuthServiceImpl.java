package com.pn.service.impl;

import com.alibaba.fastjson.JSON;
import com.pn.dto.AuthGrantDto;
import com.pn.entity.Auth;
import com.pn.entity.Result;
import com.pn.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.KeyBoundCursor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import com.pn.mapper.AuthMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/8 14:51
 * @PackageName:com.pn.service.impl
 * @ClassName: AuthInfoServiceImpl
 * @Description: 菜单树
 * @Version 1.0
 */
@CacheConfig(cacheNames = "com.pn.service.impl.AuthServiceImpl")
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthMapper authMapper;

//    注入redis模版
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public List<Auth> authTreeByUId(Integer userId) {

//        先从redis中查询缓存的用户菜单树
        String authTreeJson = redisTemplate.opsForValue().get("authTree:" + userId);
        if (StringUtils.hasText(authTreeJson)) {
//            将authTreeJson转换为authTreeList并返回
            List<Auth> authTreeList = JSON.parseArray(authTreeJson, Auth.class);
            return authTreeList;
        }
        /*
          缓存中没有用户菜单
        * */
//      查询用户权限下的所有菜单
        List<Auth> allAuthList = authMapper.findAuthByUId(userId);
//        将所有菜单List<Auth>转换为菜单树List<Auth>
        List<Auth> authTreeList = allAuthToAuthTree(allAuthList,0);
//        向redis中缓存菜单树List<Auth>
        redisTemplate.opsForValue().set("authTree:" + userId, JSON.toJSONString(authTreeList));
        return authTreeList;
    }

    //    将所有菜单List<Auth>转换为菜单树List<Auth>的递归算法
    private List<Auth> allAuthToAuthTree(List<Auth> allAuthList, Integer pid) {
//        查询出所有一级菜单
        ArrayList<Auth> firstLevelAuthList = new ArrayList<>();
        for (Auth auth : allAuthList) {
            if (pid.equals(auth.getParentId())) {
                firstLevelAuthList.add(auth);
            }
        }

//        拿到每个一级菜单的所有二级菜单
        for (Auth firstAuth : firstLevelAuthList) {
            List<Auth> secondLevelAuthList = allAuthToAuthTree(allAuthList, firstAuth.getAuthId());
            firstAuth.setChildAuth(secondLevelAuthList);
        }
        return firstLevelAuthList;
    }

    @Cacheable(key = "'all:authTree'")
    @Override
    public List<Auth> authTreeAll() {
//        查询角色所有权限
        List<Auth> authAll = authMapper.findAuthAll();
//        将角色所有权限转换为菜单树
        List<Auth> authTreeList = allAuthToAuthTree(authAll, 0);
        return authTreeList;
    }

    @Override
    @Transactional
    @CacheEvict(key = "'all:authTree'")
    public Result modifyRoleAuthByDto(AuthGrantDto authGrantDto) {
//        删除角色之前的权限
        authMapper.deleteRoleAuthByRid(authGrantDto.getRoleId());
        int count = authMapper.insertRoleAuthByDto(authGrantDto);
        if (count == 0)return Result.err(Result.CODE_ERR_BUSINESS,"修改角色权限失败");
        return Result.ok("修改角色权限成功");
    }

}
