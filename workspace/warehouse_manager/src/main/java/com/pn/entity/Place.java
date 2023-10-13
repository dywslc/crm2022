package com.pn.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 自由的骏马
 * @Date 2023/10/10 17:17
 * @PackageName:com.pn.entity
 * @ClassName: Place
 * @Description: TODO
 * @Version 1.0
 */

/**
    * 产地
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Place implements Serializable {
    private Integer placeId;

    private String placeName;

    private String placeNum;

    private String introduce;

    /**
    * 0:可用  1:不可用
    */
    private String isDelete;

    private static final long serialVersionUID = 1L;
}