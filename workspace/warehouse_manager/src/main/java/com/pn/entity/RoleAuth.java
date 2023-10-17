package com.pn.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 自由的骏马
 * @Date 2023/10/9 23:38
 * @PackageName:com.pn.entity
 * @ClassName: RoleAuth
 * @Description: TODO
 * @Version 1.0
 */

/**
    * 角色权限表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleAuth implements Serializable {
    private Integer roleAuthId;

    private Integer roleId;

    private Integer authId;

}