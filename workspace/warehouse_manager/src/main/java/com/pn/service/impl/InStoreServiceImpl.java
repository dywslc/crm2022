package com.pn.service.impl;

import com.pn.entity.InStore;
import com.pn.entity.Product;
import com.pn.entity.Purchase;
import com.pn.entity.Result;
import com.pn.mapper.ProductMapper;
import com.pn.mapper.PurchaseMapper;
import com.pn.page.Page;
import com.pn.service.ProductService;
import com.pn.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.pn.mapper.InStoreMapper;
import com.pn.service.InStoreService;
import sun.dc.pr.PRError;

import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/12 10:11
 * @PackageName:com.pn.service.impl
 * @ClassName: InStoreServiceImpl
 * @Description: TODO
 * @Version 1.0
 */

@Service
public class InStoreServiceImpl implements InStoreService{

    @Resource
    private InStoreMapper inStoreMapper;
    @Autowired
    private PurchaseMapper purchaseMapper;

    @Override
    public Result saveInStore(InStore inStore) {
//        将采购单入库状态更新
        purchaseMapper.updatePurchaseIsIn(inStore.getBuyId());
//        将采购单实际采购值赋给入库值
        inStore.setInNum(inStore.getFactBuyNum());
        int count = inStoreMapper.insetInStore(inStore);
        if (count==0)return Result.err(Result.CODE_ERR_BUSINESS,"添加入库单失败！");
        return Result.ok("添加入库单成功");
    }

    @Override
    public Page inStorePage(Page page, InStore inStore) {
//        获取instore行数
         int count = inStoreMapper.selectInStoreCount(inStore);
//        获取分页列表
        List<InStore> inStoreList = inStoreMapper.selectInStorePage(page, inStore);
//        拼接page信息
        page.setTotalNum(count);
        page.setResultList(inStoreList);
        return page;

    }

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Result modifyInStoreIsIn(InStore inStore) {
//        修改商品入库后数量
        productMapper.updateProductInvent(inStore.getInNum(), inStore.getProductId());
//        修改入库单入库状态
        int count = inStoreMapper.updateInStoreIsIn(inStore.getInsId());
        if (count==0)return Result.err(Result.CODE_ERR_BUSINESS,"确认入库失败!");
        return Result.ok("确认入库成功");
    }
}
