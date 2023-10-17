package com.pn.mapper;

import com.pn.entity.InStore;
import com.pn.entity.Store;
import com.pn.page.Page;
import com.pn.vo.StoreCountVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/10 17:18
 * @PackageName:com.pn.mapper
 * @ClassName: StoreMapper
 * @Description: TODO
 * @Version 1.0
 */

public interface StoreMapper {
//    查询所有store
    @Select("select * from store ")
    public List<Store> selectStoreAll();
//    查询每个仓库中的所有商品
    public List<StoreCountVo> selectStoreCount();
//    查询所有仓库列数
    public Integer findtStoreCount(Store store);
//    分页查询仓库
    public List<Store> findStorePage(@Param("page") Page page,@Param("store") Store store);
//    校验仓库编号
    public Store selectStoreNum(String storeNum);
//    新增仓库
    @Insert("insert into store values(null,#{storeName},#{storeName},#{storeAddress},#{concat},#{phone})")
    public int insertStore(Store store);
//    修改仓库
    public int updateStore(Store store);
//    删除仓库
    @Delete("delete from store where store_id=#{storeId}")
    public int deleteStore(Integer storeId);
}