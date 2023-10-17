package com.pn.mapper;

import com.pn.entity.ProductType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/11 9:19
 * @PackageName:com.pn.mapper
 * @ClassName: ProductTypeMapper
 * @Description: TODO
 * @Version 1.0
 */

public interface ProductTypeMapper {
    //    查询商品类别树
    @Select("select * from product_type")
    public List<ProductType> categoryTree();
    //    查询商品类别
    @Select("select * from  product_type where type_code=#{typeCode}")
    public ProductType selectTypeByCode(String typeCode);
    //    添加商品类型
    @Insert("insert into product_type value (null,#{parentId},#{typeCode},#{typeName},#{typeDesc})")
    public int insertType(ProductType productType);
    //    修改商品类别
    public int updateType(ProductType productType);
    //    删除商品类别
    @Delete("delete from product_type where type_id = #{typeId}")
    public int deleteType(Integer typeId);
}