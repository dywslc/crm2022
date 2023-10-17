package com.pn.service;

import com.pn.entity.OutStore;
import com.pn.entity.Result;
import com.pn.page.Page;

/**
 * @Author 自由的骏马
 * @Date 2023/10/11 19:03
 * @PackageName:com.pn.service
 * @ClassName: OutStoreService
 * @Description: TODO
 * @Version 1.0
 */

public interface OutStoreService{
//    添加出库表
    public Result savaOutStore(OutStore outStore);
//   分页查询出库单
    public Page outStorePage(Page page, OutStore outStore);
//    确认出库单
    public Result confimeOutStore(OutStore outStore);

}
