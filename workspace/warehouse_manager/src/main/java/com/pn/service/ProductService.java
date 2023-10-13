package com.pn.service;

import com.pn.entity.Product;
import com.pn.entity.ProductType;
import com.pn.entity.Result;
import com.pn.page.Page;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/10 17:18
 * @PackageName:com.pn.service
 * @ClassName: ProductService
 * @Description: TODO
 * @Version 1.0
 */

public interface ProductService{

//    分页查询商品
    public Page queryProductPage(Page page, Product product);
//    添加商品
    public Result savaProduct(Product product);
//    修改商品上下架状态
    public Result modifyProductState(Product product);
//    删除商品
    public Result removeProduct(Integer productId);
//    修改商品
    public Result modifyProduct(Product product);
//    批量删除商品
    public Result removeProducts(List<Integer> productIds);

}
