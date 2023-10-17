package com.pn.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 自由的骏马
 * @Date 2023/10/9 10:05
 * @PackageName:com.pn.entity
 * @ClassName: Role
 * @Description: TODO
 * @Version 1.0
 */

/**
    * 角色表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {
    private Integer roleId;

    private String roleName;

    private String roleDesc;

    private String roleCode;

    private String roleState;

    private Integer createBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private Integer updateBy;

    private Date updateTime;

//    ===========角色创建人=====================
    private String getCode;


}