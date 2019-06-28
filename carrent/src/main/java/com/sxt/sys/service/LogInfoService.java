package com.sxt.sys.service;


import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.LogInfoVo;

public interface LogInfoService {
	//全查询
	DataGridView queryAllLogInfo(LogInfoVo logInfoVo);

	int deleteLogInfo(LogInfoVo logInfoVo);

	void addLogInfo(LogInfoVo logInfoVo);
}
