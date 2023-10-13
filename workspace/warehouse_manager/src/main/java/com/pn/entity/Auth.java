package com.pn.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 自由的骏马
 * @Date 2023/10/8 14:51
 * @PackageName:com.pn.entity
 * @ClassName: AuthInfo
 * @Description: TODO
 * @Version 1.0
 */

/**
    * 权限表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Auth implements Serializable {
    private Integer authId;

    private Integer parentId;

    private String authName;

    private String authDesc;

    private Integer authGrade;

    private String authType;

    private String authUrl;

    private String authCode;

    private Integer authOrder;

    private String authState;

    private Integer createBy;

    private Date createTime;

    private Integer updateBy;

    private Date updateTime;

//    ---------追加的属性--------
//    存放当前菜单下所有的子集菜单
    private List<Auth> childAuth;




}