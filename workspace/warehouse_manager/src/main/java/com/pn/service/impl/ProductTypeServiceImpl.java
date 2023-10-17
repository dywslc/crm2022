package com.pn.service.impl;

import com.pn.entity.ProductType;
import com.pn.entity.Result;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.pn.mapper.ProductTypeMapper;
import com.pn.service.ProductTypeService;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/11 9:19
 * @PackageName:com.pn.service.impl
 * @ClassName: ProductTypeServiceImpl
 * @Description: TODO
 * @Version 1.0
 */
@CacheConfig(cacheNames = "com.pn.service.impl.ProductTypeServiceImpl")
@Service
public class ProductTypeServiceImpl implements ProductTypeService{

    @Resource
    private ProductTypeMapper productTypeMapper;


    @Cacheable(key = "'all:productTypeTree'")
    @Override
    public List<ProductType> categoryTree() {
        List<ProductType> productTypes = productTypeMapper.categoryTree();
        List<ProductType> productTypeTree = productTypeTree(productTypes, 0);
        return productTypeTree;
    }


    //    将所有菜单List<Auth>转换为菜单树List<Auth>的递归算法
    private List<ProductType> productTypeTree(List<ProductType> prductTypes, Integer pid) {
//        查询出所有一级菜单
        ArrayList<ProductType> firstLevelProcuctTypeList = new ArrayList<>();
        for ( ProductType productType : prductTypes) {
            if (pid.equals(productType.getParentId())) {
                firstLevelProcuctTypeList.add(productType);
            }
        }

//        拿到每个一级菜单的所有二级菜单
        for (ProductType productType : firstLevelProcuctTypeList) {
            List<ProductType> secondLevelAuthList = productTypeTree(prductTypes,productType.getTypeId());
            productType.setChildProductCategory(secondLevelAuthList);
        }
        return firstLevelProcuctTypeList;
    }

    @Override
    public ProductType queryType(String typeCode) {
        ProductType productType = productTypeMapper.selectTypeByCode(typeCode);
        return productType;
    }

    @Override
    @CacheEvict(key = "'all:productTypeTree'")
    public Result saveType(ProductType productType) {
        int count = productTypeMapper.insertType(productType);
        if (count == 0) return Result.err(Result.CODE_ERR_BUSINESS,"添加商品类别失败！");
        return Result.ok("添加商品类别成功");
    }

    @Override
    @CacheEvict(key ="'all:productTypeTree'")
    public Result modifyType(ProductType productType) {
        int count = productTypeMapper.updateType(productType);
        if (count == 0) return Result.err(Result.CODE_ERR_BUSINESS,"修改商品类别失败！");
        return Result.ok("修改商品类别成功");
    }

    @Override
    @CacheEvict(key ="'all:productTypeTree'")
    public Result removeType(Integer typeId) {
        int count = productTypeMapper.deleteType(typeId);
        if (count==0) return Result.err(Result.CODE_ERR_BUSINESS,"删除商品类别失败");
        return Result.ok("删除商品类别成功");
    }


}
