package com.pn.mapper;

import com.pn.entity.Place;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/10 17:17
 * @PackageName:com.pn.mapper
 * @ClassName: PlaceMapper
 * @Description: TODO
 * @Version 1.0
 */

public interface PlaceMapper {
    //    查询所有的的place地址
    @Select("select * from place")
    public List<Place> selectPlaceAll();
}