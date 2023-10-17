package com.pn.service;

import com.pn.entity.Purchase;
import com.pn.entity.Result;
import com.pn.page.Page;
import org.apache.ibatis.io.ResolverUtil;

/**
 * @Author 自由的骏马
 * @Date 2023/10/11 17:26
 * @PackageName:com.pn.service
 * @ClassName: BuyListService
 * @Description: TODO
 * @Version 1.0
 */

public interface PurchaseService {
//    添加采购单的方法
    public Result savePurchase(Purchase purchase);

//   分页查询采购单的方法
    public Page purchasePage(Page page,Purchase purchase);
//   删除采购单的方法
    public Result removePurchase(Integer buyId);
//   修改采购单的方法
    public Result modifyPurchase(Purchase purchase);
}
