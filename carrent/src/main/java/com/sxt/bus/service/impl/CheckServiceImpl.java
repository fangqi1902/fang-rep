package com.sxt.bus.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.bus.domain.Car;
import com.sxt.bus.domain.Check;
import com.sxt.bus.domain.Customer;
import com.sxt.bus.domain.Rent;
import com.sxt.bus.mapper.CarMapper;
import com.sxt.bus.mapper.CheckMapper;
import com.sxt.bus.mapper.CustomerMapper;
import com.sxt.bus.mapper.RentMapper;
import com.sxt.bus.service.CheckService;
import com.sxt.bus.vo.CheckVo;
import com.sxt.sys.constast.SYS_Constast;
import com.sxt.sys.utils.DataGridView;

@Service
public class CheckServiceImpl implements CheckService{

	@Autowired
	private RentMapper rentMapper;

	@Autowired
	private CarMapper carMapper;
	
	@Autowired
	private  CustomerMapper customerMapper; 
	
	@Autowired
	private CheckMapper  checkMapper;
	
	
	//初始化汽车入库页面数据 出租单数据   汽车信息 客户信息
	@Override
	public Map<String, Object> initData(CheckVo checkVo) {
		//出租单数据
		Rent rent = rentMapper.selectByPrimaryKey(checkVo.getRentid());
		//客户数据
		Customer customer = customerMapper.selectByPrimaryKey(rent.getIdentity());
		//汽车信息
		Car car = carMapper.selectByPrimaryKey(rent.getCarnumber());
		//添加到集合
		HashMap<String, Object> map= new HashMap<>();
		//添加到集合
		map.put("rent", rent);
		map.put("customer", customer);
		map.put("car", car);
		return map;
	}


	@Override
	public int addCheck(CheckVo checkVo) {
		//保存入库信息
		int i = checkMapper.insert(checkVo);
		//修改出租单状态
		Rent rent = rentMapper.selectByPrimaryKey(checkVo.getRentid());
		//设置出租单为已归还状态
		rent.setRentflag(SYS_Constast.TYPE_PUBLIC_ONE);
		rentMapper.updateByPrimaryKeySelective(rent);
		//修改车辆状态
		//1.查到入库车辆  根据出租单的信息获取到车辆的车牌号 
		Car car = carMapper.selectByPrimaryKey(rent.getCarnumber());
		//2.设置车辆为为出租状态
		car.setIsrenting(SYS_Constast.TYPE_PUBLIC_ZERO);
		carMapper.updateByPrimaryKeySelective(car);
		return i;
	}


	@Override
	public DataGridView queryAllCheck(CheckVo checkVo) {
		Page<Object> page = PageHelper.startPage(checkVo.getPage(), checkVo.getRows());
		List<Check> list = checkMapper.queryAllCheck(checkVo);
		return new DataGridView(page.getTotal(), list);
	}


	@Override
	public int updateCheck(CheckVo checkVo) {
		return checkMapper.updateByPrimaryKey(checkVo);
	}

}
