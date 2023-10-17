package com.pn.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pn.page.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 自由的骏马
 * @Date 2023/10/10 17:18
 * @PackageName:com.pn.entity
 * @ClassName: Product
 * @Description: TODO
 * @Version 1.0
 */

/**
    * 商品表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    private Integer productId;

    private Integer storeId;

    private Integer brandId;

    private String productName;

    private String productNum;

    private Integer productInvent;

    private Integer typeId;

    private Integer supplyId;

    private Integer placeId;

    private Integer unitId;

    private String introduce;

    private String upDownState;

    private BigDecimal inPrice;

    private BigDecimal salePrice;

    private BigDecimal memPrice;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

//    @JsonFormat(pattern = "yyyy-MM-dd ")
    private Date updateTime;

    private Integer createBy;

    private Integer updateBy;

    private String imgs;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date productDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date suppDate;

// =============追加数据================
    private String brandName;//  品牌名字

    private String typeName;//  类型名

    private String supplyName;//  供应商名

    private String placeName;//  产地名

    private String isOverDate;//  是否过期

    private String storeName;//  仓库名

    private String unitName;//  单位名








}