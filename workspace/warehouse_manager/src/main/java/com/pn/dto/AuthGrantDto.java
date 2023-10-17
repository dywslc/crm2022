package com.pn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/10 14:48
 * @PackageName:com.pn.dto
 * @ClassName: AuthGrantDto
 * @Description: TODO
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthGrantDto {
    private Integer roleId;
    private List<?> authIds;
}
