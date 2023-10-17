package com.pn.service;

import com.pn.entity.ProductType;
import com.pn.entity.Result;

import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/11 9:19
 * @PackageName:com.pn.service
 * @ClassName: ProductTypeService
 * @Description: TODO
 * @Version 1.0
 */

public interface ProductTypeService{
    //    查询商品类别树
    public List<ProductType> categoryTree();

//    查询商品类别
    public ProductType queryType(String typeCode);
//    添加商品类别
    public Result saveType(ProductType productType);
//    修改商品类别
    public Result modifyType(ProductType productType);
//    删除商品类别
    public Result removeType(Integer typeId);

}
