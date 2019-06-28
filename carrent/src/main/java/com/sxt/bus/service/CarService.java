package com.sxt.bus.service;

import java.util.List;

import com.sxt.bus.domain.Car;
import com.sxt.bus.utils.Chart;
import com.sxt.bus.utils.PieChart;
import com.sxt.bus.vo.CarVo;
import com.sxt.sys.utils.DataGridView;

public interface CarService {
	//全查询包括模糊查询分页
	DataGridView queryAllCars(CarVo carVo);
	//添加
	int addCar(CarVo carVo);
	//修改
	int updateCar(CarVo carVo);
	//删除
	int deleteCar(String carnumber);
	
	Car queryCarByCarNumber(String carnumber);
	
	 //车辆类型统计
	Chart queryCountType();
    //车辆颜色统计
    List<PieChart> queryCountColor();
    
}
