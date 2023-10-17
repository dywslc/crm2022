package com.pn.controller;

import com.pn.entity.ProductType;
import com.pn.entity.Result;
import com.pn.service.ProductService;
import com.pn.service.ProductTypeService;
import org.apache.ibatis.io.ResolverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/11 19:48
 * @PackageName:com.pn.controller
 * @ClassName: ProductTypeController
 * @Description: TODO
 * @Version 1.0
 */
@RestController
@RequestMapping("/productCategory")
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    //商品分类的url接口/productCategory/product-category-tree
    @GetMapping("/product-category-tree")
    public Result typeTree(){
        List<ProductType> productTypes = productTypeService.categoryTree();
        return Result.ok(productTypes);
    }

    //校验商品分类是否重复的url接口productCategory/verify-type-code
    @GetMapping("/verify-type-code")
    public Result verifyTypeCode(String typeCode){
        ProductType productType = productTypeService.queryType(typeCode);
        return Result.ok(productType == null);
    }

    //添加商品分类的url接口productCategory/type-add
    @PostMapping("/type-add")
    public Result addType(@RequestBody ProductType productType){
        return productTypeService.saveType(productType);
    }

    //修改商品类别的url接口productCategory/type-update
    @PutMapping("/type-update")
    public Result modifyUpdate(@RequestBody ProductType productType){
        return productTypeService.modifyType(productType);
    }

    //删除商品类别的url接口productCategory/type-delete/16
    @DeleteMapping("/type-delete/{typeId}")
    public Result removeType(@PathVariable Integer typeId){
        return productTypeService.removeType(typeId);
    }

}
