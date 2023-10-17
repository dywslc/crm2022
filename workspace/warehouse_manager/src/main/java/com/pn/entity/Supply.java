package com.pn.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 自由的骏马
 * @Date 2023/10/10 17:18
 * @PackageName:com.pn.entity
 * @ClassName: Supply
 * @Description: TODO
 * @Version 1.0
 */

/**
    * 供货商
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Supply implements Serializable {
    private Integer supplyId;

    private String supplyNum;

    private String supplyName;

    private String supplyIntroduce;

    private String concat;

    private String phone;

    private String address;

    /**
    * 0:可用  1:不可用
    */
    private String isDelete;

    private static final long serialVersionUID = 1L;
}