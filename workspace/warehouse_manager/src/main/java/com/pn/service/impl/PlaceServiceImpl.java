package com.pn.service.impl;

import com.pn.entity.Place;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.pn.mapper.PlaceMapper;
import com.pn.service.PlaceService;

import java.util.List;

/**
 * @Author 自由的骏马
 * @Date 2023/10/10 17:17
 * @PackageName:com.pn.service.impl
 * @ClassName: PlaceServiceImpl
 * @Description: TODO
 * @Version 1.0
 */

@Service
public class PlaceServiceImpl implements PlaceService{

    @Resource
    private PlaceMapper placeMapper;

    @Override
    public List<Place> queryPlaceAll(){
        return placeMapper.selectPlaceAll();
    }
}
