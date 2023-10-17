package com.pn.mapper;

import com.pn.entity.Brand;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/10 17:15
 * @PackageName:com.pn.mapper
 * @ClassName: BrandMapper
 * @Description: TODO
 * @Version 1.0
 */

public interface BrandMapper {
    //    查询所有的brand
    @Select("select * from brand")
    public List<Brand> selectBrandAll();
}