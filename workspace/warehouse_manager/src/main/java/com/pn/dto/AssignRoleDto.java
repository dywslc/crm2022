package com.pn.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/9 12:43
 * @PackageName:com.pn.dto
 * @ClassName: AssignRoleDto
 * @Description: TODO
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignRoleDto {
    private List<String> roleCheckList;
    private Integer userId;
}
