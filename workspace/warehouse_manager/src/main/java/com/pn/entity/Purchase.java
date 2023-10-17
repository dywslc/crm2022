package com.pn.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 自由的骏马
 * @Date 2023/10/11 17:26
 * @PackageName:com.pn.entity
 * @ClassName: BuyList
 * @Description: TODO
 * @Version 1.0
 */

/**
    * 采购单
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Purchase implements Serializable {
    private Integer buyId;

    private Integer productId;

    private Integer storeId;

    private Integer buyNum;

    private Integer factBuyNum;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date buyTime;

    private Integer supplyId;

    private Integer placeId;

    private String buyUser;

    private String phone;

    private String isIn;

//   =============追加数据============
    private String startTime;
    private String endTime;
    private String productName;
    private String storeName;

}