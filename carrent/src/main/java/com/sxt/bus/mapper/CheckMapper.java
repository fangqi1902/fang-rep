package com.sxt.bus.mapper;

import java.util.List;

import com.sxt.bus.domain.Check;
import com.sxt.bus.vo.CheckVo;
import com.sxt.sys.utils.DataGridView;

public interface CheckMapper {
    int deleteByPrimaryKey(String checkid);

    int insert(Check record);

    int insertSelective(Check record);

    Check selectByPrimaryKey(String checkid);

    int updateByPrimaryKeySelective(Check record);

    int updateByPrimaryKey(Check record);
    
    //查询所有检查单
    List<Check> queryAllCheck(CheckVo checkVo);
    
}