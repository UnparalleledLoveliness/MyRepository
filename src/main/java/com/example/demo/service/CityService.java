package com.example.demo.service;

import com.example.demo.bean.City;
import com.example.demo.mapper.CityMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {
    @Autowired
    CityMapper cityMapper;


    public City getById(Long id)
    {
        return cityMapper.getById(id);
    }

    public void insert(City city)
    {
        cityMapper.insert(city);
    }
}
