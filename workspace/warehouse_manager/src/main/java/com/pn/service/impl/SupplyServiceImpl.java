package com.pn.service.impl;

import com.pn.entity.Supply;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.pn.mapper.SupplyMapper;
import com.pn.service.SupplyService;

import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/10 17:18
 * @PackageName:com.pn.service.impl
 * @ClassName: SupplyServiceImpl
 * @Description: TODO
 * @Version 1.0
 */

@Service
public class SupplyServiceImpl implements SupplyService{

    @Resource
    private SupplyMapper supplyMapper;

    @Override
    public List<Supply> querySupplyAll() {
        return supplyMapper.selectSupplyAll();
    }
}
