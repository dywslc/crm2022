package com.pn.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 自由的骏马
 * @Date 2023/10/10 17:15
 * @PackageName:com.pn.entity
 * @ClassName: Brand
 * @Description: TODO
 * @Version 1.0
 */

/**
    * 品牌
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brand implements Serializable {
    private Integer brandId;

    private String brandName;

    private String brandLeter;

    private String brandDesc;

    private static final long serialVersionUID = 1L;
}