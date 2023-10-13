package com.pn.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 自由的骏马
 * @Date 2023/10/12 18:45
 * @PackageName:com.pn
 * @ClassName: StoreCountVo
 * @Description: TODO
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreCountVo {
    private Integer storeId;
    private String storeName;
    private Integer totalInvent;
}
