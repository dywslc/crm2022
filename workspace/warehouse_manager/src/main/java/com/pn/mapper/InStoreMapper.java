package com.pn.mapper;

import com.google.protobuf.Internal;
import com.pn.entity.InStore;
import com.pn.page.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/12 10:11
 * @PackageName:com.pn.mapper
 * @ClassName: InStoreMapper
 * @Description: TODO
 * @Version 1.0
 */

public interface InStoreMapper {
//    添加采购单
    public int insetInStore(InStore inStore);
//    查询采购单列数
    public Integer selectInStoreCount(InStore inStore);
//    分页查询采购单
    public List<InStore> selectInStorePage(@Param("page") Page page, @Param("inStore") InStore inStore);
//    修改入库状态为已入库
    @Update("update in_store set is_in=1 where ins_id=#{insId}")
    public int updateInStoreIsIn(Integer insId);
}