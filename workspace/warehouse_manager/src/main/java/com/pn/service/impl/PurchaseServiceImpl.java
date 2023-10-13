package com.pn.service.impl;

import com.pn.entity.Purchase;
import com.pn.entity.Result;
import com.pn.page.Page;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.pn.mapper.PurchaseMapper;
import com.pn.service.PurchaseService;

import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/11 17:26
 * @PackageName:com.pn.service.impl
 * @ClassName: BuyListServiceImpl
 * @Description: TODO
 * @Version 1.0
 */

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Resource
    private PurchaseMapper purchaseMapper;

    @Override
    public Result savePurchase(Purchase purchase) {
//      补充字段，将预采购值赋给实际采购值
//        purchase.setFactBuyNum(purchase.getBuyNum());
//      执行业务
        int count = purchaseMapper.insertPurchase(purchase);
        if (count == 0)return Result.err(Result.CODE_ERR_BUSINESS,"添加采购单失败！");
        return Result.ok("添加采购单成功");

    }

    @Override
    public Page purchasePage(Page page, Purchase purchase) {
        Integer count = purchaseMapper.selectPurchaseCount(purchase);
        List<Purchase> purchases = purchaseMapper.selectPurchasePage(page, purchase);
        page.setTotalNum(count);
        page.setResultList(purchases);
        return page;
    }

    @Override
    public Result removePurchase(Integer buyId) {
        int count = purchaseMapper.deletePurchase(buyId);
        if (count == 0) return Result.err(Result.CODE_ERR_BUSINESS,"删除采购单失败！");
        return Result.ok("删除采购单成功");
    }

    @Override
    public Result modifyPurchase(Purchase purchase) {
        int count = purchaseMapper.updatePurchase(purchase);
        if (count == 0) return Result.err(Result.CODE_ERR_BUSINESS,"修改采购单失败！");
        return Result.ok("修改采购单成功");
    }
}
