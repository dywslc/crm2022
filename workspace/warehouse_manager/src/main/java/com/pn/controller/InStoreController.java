package com.pn.controller;

import ch.qos.logback.core.pattern.util.RestrictedEscapeUtil;
import com.pn.entity.InStore;
import com.pn.entity.Result;
import com.pn.entity.Store;
import com.pn.page.Page;
import com.pn.service.InStoreService;
import com.pn.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/12 11:15
 * @PackageName:com.pn.controller
 * @ClassName: InStore
 * @Description: TODO
 * @Version 1.0
 */
@RestController
@RequestMapping("/instore")
public class InStoreController {

    @Autowired
    private StoreService storeService;

    @Autowired
    private InStoreService instoreService;

//    入库列表仓库的url接口instore/store-list
    @GetMapping("/store-list")
    public Result storeList(){
        List<Store> stores = storeService.queryStoreAll();
        return Result.ok(stores);
    }

//  分页入库列表的url接口instore/instore-page-list
    @GetMapping("/instore-page-list")
    public Result instorePage(Page page, InStore inStore){
        page = instoreService.inStorePage(page, inStore);
        return Result.ok(page);
    }

//  确定入库的url接口instore/instore-confirm
    @PutMapping("/instore-confirm")
    public Result instoreConfirm(@RequestBody InStore inStore){
        return instoreService.modifyInStoreIsIn(inStore);
    }

    //  导出数据
    @GetMapping("/exportTable")
    public Result exportTable(Page page,InStore inStore){
        page = instoreService.inStorePage(page, inStore);
        List<?> resultList = page.getResultList();
        return Result.ok(resultList);
    }
}
