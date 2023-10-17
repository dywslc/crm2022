package com.pn.controller;

import com.pn.entity.Result;
import com.pn.service.StoreService;
import com.pn.vo.StoreCountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/12 18:39
 * @PackageName:com.pn.controller
 * @ClassName: StatisticsController
 * @Description: TODO
 * @Version 1.0
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private StoreService storeService;
    @GetMapping("/store-invent")
    public Result storeInvent(){
        List<StoreCountVo> storeCountVos = storeService.queryStoreCount();
        return Result.ok(storeCountVos);
    }
}
