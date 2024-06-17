package com.example.demo.mapper;

import com.example.demo.bean.City;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

//@Mapper
public interface CityMapper {
    // select * from user where id = #{id}
    @Select(" select * from city where id = #{id}")
    public City getById(long id);


    public void insert(City city);

    @Insert("insert into city(name,country) values (#{name},#{country})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    public void insert2(City city);
}
