package com.pn.mapper;

import com.pn.entity.Product;
import com.pn.entity.ProductType;
import com.pn.page.Page;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/10 17:18
 * @PackageName:com.pn.mapper
 * @ClassName: ProductMapper
 * @Description: TODO
 * @Version 1.0
 */

public interface ProductMapper {

//    查询商品总行数
    public Integer selectProductRowCount(Product product);
//    分页查询商品
    public List<Product> selectProductPage(@Param("page") Page page,@Param("product") Product product);
//    添加商品
    public int insertProduct(Product product);
//    修改商品上下架状态
    @Update("update product set up_down_state=#{param1} where product_id=#{param2}")
    public int updateProductState(String upDownState,Integer productId);
//    删除商品
    @Delete("delete from product where product_id = #{productId}")
    public int deleteProduct(Integer productId);
//    修改商品
    public int updateProduct(Product product);
//    批量删除商品
    public int deleteProducts(@Param("productIds") List<Integer> productIds);
//    商品入库/商品出库
    public int updateProductInvent(Integer invent,Integer productId);
//    查看商品库存
    @Select("select product_invent from product where product_id=#{productId}")
    public Integer selectProductInvent(Integer productId);

}