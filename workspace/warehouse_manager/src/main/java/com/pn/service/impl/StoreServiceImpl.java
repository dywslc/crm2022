package com.pn.service.impl;

import com.pn.entity.Result;
import com.pn.entity.Store;
import com.pn.page.Page;
import com.pn.vo.StoreCountVo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.pn.mapper.StoreMapper;
import com.pn.service.StoreService;

import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/10 17:18
 * @PackageName:com.pn.service.impl
 * @ClassName: StoreServiceImpl
 * @Description: TODO
 * @Version 1.0
 */
@CacheConfig(cacheNames = "com.pn.service.impl.StoreServiceImpl")
@Service
public class StoreServiceImpl implements StoreService{

    @Resource
    private StoreMapper storeMapper;

    @Override
    public List<Store> queryStoreAll() {
        return storeMapper.selectStoreAll();
    }

    @Override
    public List<StoreCountVo> queryStoreCount() {

        return storeMapper.selectStoreCount();
    }

    @Override
//    @Cacheable(key = "'all:store'")
    public Page queryStorePage(Page page,Store store) {
        Integer count = storeMapper.findtStoreCount(store);
        List<Store> storeList = storeMapper.findStorePage(page, store);
        page.setTotalNum(count);
        page.setResultList(storeList);
        return page;
    }

    @Override
    @CacheEvict(key = "'all:store'")
    public Store queryStoreNum(String storeNum) {return storeMapper.selectStoreNum(storeNum);
    }

    @Override
    @CacheEvict(key = "'all:store'")
    public Result saveStore(Store store) {
        int count = storeMapper.insertStore(store);
        if (count == 0)return Result.err(Result.CODE_ERR_BUSINESS,"添加仓库失败!");
        return Result.ok("添加仓库成功");
    }

    @Override
    public Result modifyStore(Store store) {
        int count = storeMapper.updateStore(store);
        if (count == 0)return Result.err(Result.CODE_ERR_BUSINESS,"修改仓库失败！");
        return Result.ok("修改仓库成功");
    }

    @Override
    public Result removeStore(Integer storeId) {
        int count = storeMapper.deleteStore(storeId);
        if (count == 0)return Result.err(Result.CODE_ERR_BUSINESS,"删除仓库失败");
        return Result.ok("删除仓库成功");
    }
}
