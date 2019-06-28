package com.sxt.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.sys.domain.LogInfo;
import com.sxt.sys.mapper.LogInfoMapper;
import com.sxt.sys.service.LogInfoService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.LogInfoVo;
@Service
public class LogInfoServiceImpl implements LogInfoService{
	@Autowired
	private LogInfoMapper logInfoMapper;
	
	@Override
	public DataGridView queryAllLogInfo(LogInfoVo logInfoVo) {
		Page<Object> page = PageHelper.startPage(logInfoVo.getPage(), logInfoVo.getRows());
		List<LogInfo> list = logInfoMapper.queryAllLogInfo(logInfoVo);
		return new DataGridView(page.getTotal(),list);
	}

	@Override
	public int deleteLogInfo(LogInfoVo logInfoVo) {
		int i = logInfoMapper.deleteByPrimaryKey(logInfoVo.getId());
		return i;
	}

	@Override
	public void addLogInfo(LogInfoVo logInfoVo) {
			logInfoMapper.insertSelective(logInfoVo);
	}

}
