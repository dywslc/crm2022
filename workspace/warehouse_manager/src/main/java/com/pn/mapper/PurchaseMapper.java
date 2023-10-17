package com.pn.mapper;

import com.pn.entity.Purchase;
import com.pn.page.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import javax.annotation.PreDestroy;
import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/11 17:26
 * @PackageName:com.pn.mapper
 * @ClassName: BuyListMapper
 * @Description: TODO
 * @Version 1.0
 */

public interface PurchaseMapper {
    //添加采购单
    public int insertPurchase(Purchase purchase);
    //查询采购单行数
    public Integer selectPurchaseCount(Purchase purchase);
    //分页查询采购单
    public List<Purchase> selectPurchasePage(@Param("page") Page page,@Param("purchase") Purchase purchase);
    //删除采购单
    @Delete("delete from buy_list where buy_id=#{buyId}")
    public int deletePurchase(Integer buyId);
    //修改采购单
    public int updatePurchase(Purchase purchase);
    //修改采购单入库状态
    @Update("update buy_list set is_in = 1 where buy_id=#{purchaseId} ")
    public int updatePurchaseIsIn(Integer purchaseId);
}