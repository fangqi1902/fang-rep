package com.sxt.bus.service;

import javax.servlet.http.HttpServletResponse;

import com.sxt.bus.domain.Rent;
import com.sxt.bus.vo.RentVo;
import com.sxt.sys.utils.DataGridView;

public interface RentService {
	//全查询包括模糊查询分页
	DataGridView queryAllRents(RentVo rentVo);
	//添加
	
	int addRent(RentVo rentVo);
	
	//修改
	int updateRent(RentVo rentVo);
	
	//删除
	int deleteRent(String rentId);
	
	Rent queryByRentId(String rentid);
	
	//导出出租单
	void exportRent(RentVo rentVo,HttpServletResponse response);
}
