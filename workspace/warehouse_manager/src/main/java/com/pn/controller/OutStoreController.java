package com.pn.controller;

import com.pn.entity.OutStore;
import com.pn.entity.Result;
import com.pn.entity.Store;
import com.pn.mapper.OutStoreMapper;
import com.pn.page.Page;
import com.pn.service.OutStoreService;
import com.pn.service.StoreService;
import com.pn.utils.TokenUtils;
import com.pn.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/11 19:04
 * @PackageName:com.pn.controller
 * @ClassName: OutstoreController
 * @Description: TODO
 * @Version 1.0
 */

@RestController
@RequestMapping("/outstore")
public class OutStoreController {

    @Autowired
    private OutStoreService outStoreService;

    @Autowired
    private StoreService storeService;

    @Autowired
    private TokenUtils tokenUtils;

    //    出库单的url接口outstore/outstore-add
    @PostMapping("/outstore-add")
    public Result addOutstore(@RequestBody OutStore outstore ,@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME)String token){
        outstore.setCreateBy(tokenUtils.getCurrentUser(token).getUserId());
        return outStoreService.savaOutStore(outstore);

    }

    //  出库的仓库列表的url接口outstore/store-list
    @GetMapping("/store-list")
    public Result storeList(){
        List<Store> stores = storeService.queryStoreAll();
        return Result.ok(stores);
    }

    //  出库的分页列表的url接口outstore/outstore-page-list
    @GetMapping("/outstore-page-list")
    public Result outStorePage(Page page, OutStore outStore){
        page = outStoreService.outStorePage(page, outStore);
        return Result.ok(page);
    }

    // 确认出库的url接口outstore/outstore-confirm
    @PutMapping("/outstore-confirm")
    public Result outStoreConfirm(@RequestBody OutStore outStore,@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME)String token){
//        添加理货人
        outStore.setTallyId(tokenUtils.getCurrentUser(token).getUserId());
        return outStoreService.confimeOutStore(outStore);
    }

    //  导出数据
    @GetMapping("/exportTable")
    public Result exportTable(Page page,OutStore outStore){
        page = outStoreService.outStorePage(page,outStore);
        List<?> resultList = page.getResultList();
        return Result.ok(resultList);
    }

}
