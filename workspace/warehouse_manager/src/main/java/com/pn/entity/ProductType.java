package com.pn.entity;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 自由的骏马
 * @Date 2023/10/11 9:19
 * @PackageName:com.pn.entity
 * @ClassName: ProductType
 * @Description: TODO
 * @Version 1.0
 */

/**
    * 商品分类表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductType implements Serializable {
    private Integer typeId;

    private Integer parentId;

    private String typeCode;

    private String typeName;

    private String typeDesc;

    private List<ProductType> childProductCategory;

}