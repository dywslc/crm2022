package com.pn.service.impl;

import com.pn.entity.Auth;
import com.pn.entity.Product;
import com.pn.entity.ProductType;
import com.pn.entity.Result;
import com.pn.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.pn.mapper.ProductMapper;
import com.pn.service.ProductService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/10 17:18
 * @PackageName:com.pn.service.impl
 * @ClassName: ProductServiceImpl
 * @Description: TODO
 * @Version 1.0
 */

@Service
public class ProductServiceImpl implements ProductService{

    @Resource
    private ProductMapper productMapper;

    @Override
    public Page queryProductPage(Page page, Product product) {
//       查詢商品行數
        Integer count = productMapper.selectProductRowCount(product);
//       分頁查詢商品
        List<Product> products = productMapper.selectProductPage(page, product);
//       组装page
        page.setTotalNum(count);
        page.setResultList(products);
        return page;
    }

    @Value("${file.access-path}")
    private String fileAccessPath;
    @Override
    @Transactional
    public Result savaProduct(Product product) {
        product.setImgs(fileAccessPath+"/"+product.getImgs());
        int count = productMapper.insertProduct(product);
        if(count == 0)return Result.err(Result.CODE_ERR_BUSINESS,"添加用户失败！");
        return Result.ok("添加用户成功");

    }

    @Override
    public Result modifyProductState(Product product) {
        int count = productMapper.updateProductState(product.getUpDownState(), product.getProductId());
        if (count == 0)return Result.err(Result.CODE_ERR_BUSINESS,"修改商品状态失败!");
        return Result.ok("修改商品状态成功");


    }

    @Override
    public Result removeProduct(Integer productId) {
        int count = productMapper.deleteProduct(productId);
        if (count == 0)return Result.err(Result.CODE_ERR_BUSINESS,"删除商品状态失败!");
        return Result.ok("删除商品状态成功");
    }

    @Override
    public Result modifyProduct(Product product) {
        if (product.getImgs()!=null){
            if (product.getImgs().indexOf(fileAccessPath)==-1){
                product.setImgs(fileAccessPath+"/"+product.getImgs());
            }
        }
        int count = productMapper.updateProduct(product);
        if (count == 0)return Result.err(Result.CODE_ERR_BUSINESS,"修改商品失败!");
        return Result.ok("修改商品成功");
    }

    @Override
    public Result removeProducts(List<Integer> productIds) {
        int count = productMapper.deleteProducts(productIds);
        if (count == 0)return Result.err(Result.CODE_ERR_BUSINESS,"删除商品状态失败!");
        return Result.ok("删除商品状态成功");
    }

}
