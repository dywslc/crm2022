package com.pn.controller;

import com.pn.entity.InStore;
import com.pn.entity.Purchase;
import com.pn.entity.Result;
import com.pn.entity.Store;
import com.pn.page.Page;
import com.pn.service.InStoreService;
import com.pn.service.ProductService;
import com.pn.service.PurchaseService;
import com.pn.service.StoreService;
import com.pn.utils.TokenUtils;
import com.pn.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.annotation.W3CDomHandler;
import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/11 17:49
 * @PackageName:com.pn.controller
 * @ClassName: PurchaseController
 * @Description: TODO
 * @Version 1.0
 */

@RequestMapping("/purchase")
@RestController
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private StoreService storeService;
//    采购单的url接口purchase/purchase-add
    @PostMapping("/purchase-add")
    public Result addPurchase(@RequestBody Purchase purchase){
        return purchaseService.savePurchase(purchase);
    }

//    获取仓库列表的url接口purchase/store-list
    @GetMapping("/store-list")
    public Result queryStoreList(){
        List<Store> stores = storeService.queryStoreAll();
        return Result.ok(stores);
    }

//    采购列表分页查询的url接口purchase/purchase-page-list
    @GetMapping("/purchase-page-list")
    public Result purchasePage(Page page, Purchase purchase){
         page = purchaseService.purchasePage(page, purchase);
        return Result.ok(page);
    }

//    删除采购单的url接口purchase/purchase-delete/51
    @DeleteMapping("/purchase-delete/{buyId}")
    public Result removePurchase(@PathVariable Integer buyId){
        return purchaseService.removePurchase(buyId);
    }

//    修改采购单的url接口purchase/purchase-update
    @PutMapping("/purchase-update")
    public Result modifyPurchase(@RequestBody Purchase purchase){
        return purchaseService.modifyPurchase(purchase);
    }

    @Autowired
    private InStoreService inStoreService;
    @Autowired
    private TokenUtils tokenUtils;
//    生成入库单的url接口purchase/in-warehouse-record-add
    @PostMapping("/in-warehouse-record-add")
    public Result saveInStore (@RequestBody InStore inStore, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME)String token){
//        将token中用户赋给创建入库单的人
        inStore.setCreateBy(tokenUtils.getCurrentUser(token).getUserId());
        return inStoreService.saveInStore(inStore);
    }

    //  导出数据
    @GetMapping("/exportTable")
    public Result exportTable(Page page,Purchase purchase){
        page = purchaseService.purchasePage(page, purchase);
        List<?> resultList = page.getResultList();
        return Result.ok(resultList);
    }



}
