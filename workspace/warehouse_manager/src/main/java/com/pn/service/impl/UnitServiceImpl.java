package com.pn.service.impl;

import com.pn.entity.Unit;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.pn.mapper.UnitMapper;
import com.pn.service.UnitService;

import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/10 17:19
 * @PackageName:com.pn.service.impl
 * @ClassName: UnitServiceImpl
 * @Description: TODO
 * @Version 1.0
 */

@Service
public class UnitServiceImpl implements UnitService{

    @Resource
    private UnitMapper unitMapper;

    @Override
    public List<Unit> queryUnitAll() {
        return unitMapper.selectUnitALL();
    }
}
