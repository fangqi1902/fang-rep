package com.sxt.bus.mapper;

import java.util.List;

import com.sxt.bus.domain.Rent;
import com.sxt.bus.vo.RentVo;

public interface RentMapper {
    int deleteByPrimaryKey(String rentid);

    int insert(Rent record);

    int insertSelective(Rent record);

    Rent selectByPrimaryKey(String rentid);

    int updateByPrimaryKeySelective(Rent record);

    int updateByPrimaryKey(Rent record);
    
    //查询所有出租单
    List<Rent> queryAllRent(Rent rent);
}