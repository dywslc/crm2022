package com.pn.mapper;

import com.pn.entity.Supply;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/10 17:18
 * @PackageName:com.pn.mapper
 * @ClassName: SupplyMapper
 * @Description: TODO
 * @Version 1.0
 */

public interface SupplyMapper {
    //    查询所有的supply供应商
    @Select("select * from supply where is_delete=0")
    public List<Supply> selectSupplyAll();
}