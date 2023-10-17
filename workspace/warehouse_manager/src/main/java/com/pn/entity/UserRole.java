package com.pn.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 自由的骏马
 * @Date 2023/10/10 17:19
 * @PackageName:com.pn.entity
 * @ClassName: UserRole
 * @Description: TODO
 * @Version 1.0
 */

/**
    * 用户角色表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole implements Serializable {
    private Integer userRoleId;

    private Integer roleId;

    private Integer userId;

    private static final long serialVersionUID = 1L;
}