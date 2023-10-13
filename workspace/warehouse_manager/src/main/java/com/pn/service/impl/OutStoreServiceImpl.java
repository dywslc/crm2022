package com.pn.service.impl;

import com.pn.entity.OutStore;
import com.pn.entity.Product;
import com.pn.entity.Result;
import com.pn.mapper.ProductMapper;
import com.pn.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.pn.mapper.OutStoreMapper;
import com.pn.service.OutStoreService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/11 19:03
 * @PackageName:com.pn.service.impl
 * @ClassName: OutStoreServiceImpl
 * @Description: TODO
 * @Version 1.0
 */

@Service
public class OutStoreServiceImpl implements OutStoreService{

    @Resource
    private OutStoreMapper outStoreMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public Result savaOutStore(OutStore outStore) {
        int count = outStoreMapper.insertOutStore(outStore);
        if (count == 0) return Result.err(Result.CODE_ERR_BUSINESS,"出库单添加失败");
        return Result.ok("出库单添加成功");
    }

    @Override
    public Page outStorePage(Page page, OutStore outStore) {
        Integer count = outStoreMapper.selectOutStoreCount(outStore);
        List<OutStore> outStores = outStoreMapper.selectOutStorePage(page, outStore);
        page.setTotalNum(count);
        page.setResultList(outStores);
        return page;
    }

    @Override
    @Transactional
    public Result confimeOutStore(OutStore outStore) {
//        判断库存余额是否足够
        Integer invent = productMapper.selectProductInvent(outStore.getProductId());
        if (outStore.getOutNum() > invent) return Result.err(Result.CODE_ERR_BUSINESS,"商品库存不足！");
//        更新出库单状态和理货人
        int count = outStoreMapper.updateOutStoreIsOut(outStore.getTallyId(),outStore.getOutsId());
        if (count == 0)return Result.err(Result.CODE_ERR_BUSINESS,"确认出库失败！");
//        更新商品库存数量
        count = productMapper.updateProductInvent(-outStore.getOutNum(), outStore.getProductId());
        if (count == 0)return Result.err(Result.CODE_ERR_BUSINESS,"确认出库失败！");
        return Result.ok("确认出库成功");
    }
}
