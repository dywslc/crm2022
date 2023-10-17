package com.pn.service;

import com.pn.entity.InStore;
import com.pn.entity.Result;
import com.pn.page.Page;

/**
 * @Author 自由的骏马
 * @Date 2023/10/12 10:11
 * @PackageName:com.pn.service
 * @ClassName: InStoreService
 * @Description: TODO
 * @Version 1.0
 */

public interface InStoreService{

//    添加入库单
    public Result saveInStore(InStore inStore);
//    分页查询入库列表
    public Page inStorePage(Page page, InStore inStore);
//    修改入库单入库状态
    public Result modifyInStoreIsIn(InStore inStore);



}
