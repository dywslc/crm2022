package com.pn.service.impl;

import com.pn.entity.Brand;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.pn.mapper.BrandMapper;
import com.pn.service.BrandService;

import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/10 17:15
 * @PackageName:com.pn.service.impl
 * @ClassName: BrandServiceImpl
 * @Description: TODO
 * @Version 1.0
 */

@Service
public class BrandServiceImpl implements BrandService{

    @Resource
    private BrandMapper brandMapper;

    @Override
    public List<Brand> queryBrandAll() {
        return brandMapper.selectBrandAll();

    }
}
