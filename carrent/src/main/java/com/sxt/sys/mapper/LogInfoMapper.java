package com.sxt.sys.mapper;

import java.util.List;

import com.sxt.sys.domain.LogInfo;
import com.sxt.sys.vo.LogInfoVo;

public interface LogInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LogInfo record);

    int insertSelective(LogInfo record);

    LogInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LogInfo record);

    int updateByPrimaryKey(LogInfo record);
    
    //查询所有日志包括模糊查询
    List<LogInfo> queryAllLogInfo(LogInfoVo logInfoVo);
}