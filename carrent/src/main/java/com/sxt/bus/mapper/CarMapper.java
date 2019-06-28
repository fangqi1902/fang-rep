package com.sxt.bus.mapper;

import java.util.List;
import java.util.Map;

import com.sxt.bus.domain.Car;

public interface CarMapper {
    int deleteByPrimaryKey(String carnumber);

    int insert(Car record);

    int insertSelective(Car record);

    Car selectByPrimaryKey(String carnumber);

    int updateByPrimaryKeySelective(Car record);

    int updateByPrimaryKey(Car record);
    
    //查询所有汽车,包括模糊查询
    List<Car> queryAllCars(Car car);
    
    //车辆类型统计
    List<String> queryType();
    
    Integer queryCountType(String carType);
    
    //车辆颜色统计
    List<String> queryColor();
    
    Integer queryCountColor(String color);
}
