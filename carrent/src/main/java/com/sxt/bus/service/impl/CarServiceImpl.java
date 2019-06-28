package com.sxt.bus.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.bus.domain.Car;
import com.sxt.bus.mapper.CarMapper;
import com.sxt.bus.service.CarService;
import com.sxt.bus.utils.Chart;
import com.sxt.bus.utils.PieChart;
import com.sxt.bus.vo.CarVo;
import com.sxt.sys.utils.DataGridView;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarMapper carMapper;
	
	@Override
	public DataGridView queryAllCars(CarVo carVo) {
		Page<Object> page = PageHelper.startPage(carVo.getPage(),
				carVo.getRows());
		List<Car> list = carMapper.queryAllCars(carVo);
		return new DataGridView(page.getTotal(), list);
	}

	@Override
	public int addCar(CarVo carVo) {
		int i = carMapper.insert(carVo);		
		return i;
	}

	@Override
	public int updateCar(CarVo carVo) {
		int i = carMapper.updateByPrimaryKey(carVo);
		return i;
	}

	@Override
	public int deleteCar(String carnumber) {
		int i = carMapper.deleteByPrimaryKey(carnumber);
		return i;
	}

	@Override
	public Car queryCarByCarNumber(String carnumber) {
		return carMapper.selectByPrimaryKey(carnumber);
	}

	
	
	@Override
	public Chart queryCountType() {
		Chart chart  = new Chart();
		List<String>  color=carMapper.queryType();
		List<Integer>  count1= new ArrayList<>();
		for (String s : color) {
			count1.add(carMapper.queryCountType(s));
		}
		Integer[] data1=new Integer[count1.size()];
		for (int i = 0; i < data1.length; i++) {
				data1[i] =count1.get(i);
		}
		String[] categories= new String[color.size()];
		for (int i = 0; i < categories.length; i++) {
			categories[i] =color.get(i);
		}
		chart.setCategories(categories);
		chart.setData1(data1);
		
		return chart;
	}

	@Override
	public  List<PieChart> queryCountColor() {
		List<String>  color=carMapper.queryColor();
		List<PieChart> pieList = new ArrayList<>();
		for (String s : color) {
			pieList.add(new PieChart(s, carMapper.queryCountColor(s)));
		}
		return pieList;
	}

}
