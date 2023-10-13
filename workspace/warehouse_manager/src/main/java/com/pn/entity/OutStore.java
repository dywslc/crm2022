package com.pn.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 自由的骏马
 * @Date 2023/10/11 19:03
 * @PackageName:com.pn.entity
 * @ClassName: OutStore
 * @Description: TODO
 * @Version 1.0
 */

/**
    * 出库单
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutStore implements Serializable {
    private Integer outsId;

    private Integer productId;

    private Integer storeId;

    private Integer tallyId;

    private BigDecimal outPrice;

    private Integer outNum;

    private Integer createBy;
//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String isOut;

//    ===============追加属性============
    private BigDecimal salePrice;//接收商品的售价
    private String productName;
    private String startTime;
    private String endTime;
    private String storeName;
    private String userCode;
    private String tallyCode;
}