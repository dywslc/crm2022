package com.pn.mapper;

import com.pn.entity.OutStore;
import com.pn.page.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/11 19:03
 * @PackageName:com.pn.mapper
 * @ClassName: OutStoreMapper
 * @Description: TODO
 * @Version 1.0
 */

public interface OutStoreMapper {
//    添加出库单
    public int insertOutStore(OutStore outStore);
//    出库单列表行数
    public Integer selectOutStoreCount(OutStore outStore);
//    出库分页列表
    public List<OutStore> selectOutStorePage(@Param("page") Page page, @Param("outStore") OutStore outStore);
//    确认出库单
    @Update("update out_store set is_out = 1 ,tally_id=#{param1} where outs_id = #{param2}")
    public int updateOutStoreIsOut(Integer tallyId,Integer outsId);

}