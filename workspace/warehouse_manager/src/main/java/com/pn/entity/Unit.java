package com.pn.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 自由的骏马
 * @Date 2023/10/10 17:19
 * @PackageName:com.pn.entity
 * @ClassName: Unit
 * @Description: TODO
 * @Version 1.0
 */

/**
    * 规格单位表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Unit implements Serializable {
    private Integer unitId;

    private String unitName;

    private String unitDesc;

    private static final long serialVersionUID = 1L;
}