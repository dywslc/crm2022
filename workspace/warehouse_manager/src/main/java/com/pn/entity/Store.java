package com.pn.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 自由的骏马
 * @Date 2023/10/10 17:18
 * @PackageName:com.pn.entity
 * @ClassName: Store
 * @Description: TODO
 * @Version 1.0
 */

/**
    * 仓库表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Store implements Serializable {
    private Integer storeId;

    private String storeName;

    private String storeNum;

    private String storeAddress;

    private String concat;

    private String phone;

}