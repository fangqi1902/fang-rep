package com.sxt.bus.service;

import java.util.Map;

import com.sxt.bus.domain.Check;
import com.sxt.bus.vo.CheckVo;
import com.sxt.sys.utils.DataGridView;

public interface CheckService {
	//初始化数据
	Map<String, Object> initData(CheckVo checkVo);

	int addCheck(CheckVo checkVo);
	
	//查询所有检查单
	DataGridView queryAllCheck(CheckVo checkVo);

	int updateCheck(CheckVo checkVo);
}
