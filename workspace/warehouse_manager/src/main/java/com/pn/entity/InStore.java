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
 * @Date 2023/10/12 10:11
 * @PackageName:com.pn.entity
 * @ClassName: InStore
 * @Description: TODO
 * @Version 1.0
 */

/**
    * 入库单
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InStore implements Serializable {
    private Integer insId;

    private Integer storeId;

    private Integer productId;

    private Integer inNum;

    private Integer createBy;
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String isIn;

//    ===========追加属性=======
    private Integer buyId;
    private Integer factBuyNum;
    private String productName;
    private String startTime;
    private String endTime;
    private String storeName;
    private BigDecimal inPrice;
    private String userCode;


}