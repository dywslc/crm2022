package com.pn.service;

import com.pn.entity.Result;
import com.pn.entity.Store;
import com.pn.page.Page;
import com.pn.vo.StoreCountVo;

import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/10 17:18
 * @PackageName:com.pn.service
 * @ClassName: StoreService
 * @Description: TODO
 * @Version 1.0
 */

public interface StoreService{
//    查询所有store的业务方法
    public List<Store> queryStoreAll();
//    查询仓库所有商品
    public List<StoreCountVo> queryStoreCount();
//    分页查询仓库
    public Page queryStorePage(Page page,Store store);
//    校验仓库编号
    public Store queryStoreNum(String store_num);
//    新增仓库
    public Result saveStore(Store store);
//    修改仓库
    public Result modifyStore(Store store);
//    删除仓库
    public Result removeStore(Integer storeId);

}
