package com.pn.controller;

import com.pn.entity.InStore;
import com.pn.entity.Result;
import com.pn.entity.Store;
import com.pn.mapper.StoreMapper;
import com.pn.page.Page;
import com.pn.service.StoreService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/12 19:34
 * @PackageName:com.pn.controller
 * @ClassName: StoreController
 * @Description: TODO
 * @Version 1.0
 */
@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreService storeService;
//    仓库管理列表的url接口store/store-page-list
    @GetMapping("/store-page-list")
    public Result storePage(Page page, Store store){
        page = storeService.queryStorePage(page, store);
        return Result.ok(page);
    }

//    校验仓库编号的url接口store/store-num-check
    @GetMapping("/store-num-check")
    public Result storeNumCheck(String storeNum){
        Store store = storeService.queryStoreNum(storeNum);
        return Result.ok(store == null);
    }

//    添加仓库的url接口store/store-add
    @PostMapping("/store-add")
    public Result saveStore(@RequestBody Store store){
        return storeService.saveStore(store);
    }

//    修改仓库的url接口store/store-update
    @PutMapping("/store-update")
    public Result modifyStore(@RequestBody Store store){
        return storeService.modifyStore(store);
    }

//   删除仓库的url接口store/store-delete/5
    @DeleteMapping("/store-delete/{storeId}")
    public Result removeStore(@PathVariable Integer storeId){
        return storeService.removeStore(storeId);
    }

//  导出数据
    @GetMapping("/exportTable")
    public Result exportTable(Page page,Store store){
        page = storeService.queryStorePage(page, store);
        List<?> resultList = page.getResultList();
        return Result.ok(resultList);
    }

}
