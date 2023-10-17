package com.pn.mapper;

import com.pn.entity.Unit;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/10 17:19
 * @PackageName:com.pn.mapper
 * @ClassName: UnitMapper
 * @Description: TODO
 * @Version 1.0
 */

public interface UnitMapper {
    //    查询所有的unit单位
    @Select("select * from unit")
    public List<Unit> selectUnitALL();
}