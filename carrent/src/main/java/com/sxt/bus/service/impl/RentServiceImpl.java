package com.sxt.bus.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.bus.domain.Car;
import com.sxt.bus.domain.Customer;
import com.sxt.bus.domain.Rent;
import com.sxt.bus.mapper.CarMapper;
import com.sxt.bus.mapper.RentMapper;
import com.sxt.bus.service.CarService;
import com.sxt.bus.service.CustomerService;
import com.sxt.bus.service.RentService;
import com.sxt.bus.utils.RentExportExcelUtils;
import com.sxt.bus.vo.RentVo;
import com.sxt.sys.constast.SYS_Constast;
import com.sxt.sys.utils.DataGridView;

@Service
public class RentServiceImpl implements RentService{

	@Autowired
	private RentMapper rentMapper;
	
	@Autowired
	private CarMapper carMapper;
	
	@Autowired
	private CustomerService customerService;
	@Override
	public int addRent(RentVo rentVo) {
		//保存出租单得信息
		int i = rentMapper.insert(rentVo);
		//同时修改车辆得出租状态(一个事务)
		Car car= new Car();
		car.setCarnumber(rentVo.getCarnumber());
		car.setIsrenting(SYS_Constast.TYPE_PUBLIC_ONE);
		carMapper.updateByPrimaryKeySelective(car);
		return i;
	}

	@Override
	public DataGridView queryAllRents(RentVo rentVo) {
		Page<Object> page = PageHelper.startPage(rentVo.getPage(), rentVo.getRows());
		List<Rent> list = rentMapper.queryAllRent(rentVo);
		return new DataGridView(page.getTotal(), list);
	}

	@Override
	public int updateRent(RentVo rentVo) {
		return rentMapper.updateByPrimaryKey(rentVo);
	}

	@Override
	public int deleteRent(String rentId) {
		//先更新车辆得出租状态
		//显查到该出租单
		Rent rent = rentMapper.selectByPrimaryKey(rentId);
		//通过出租单得车牌号,然后修改车辆得出租状态
		Car car = new Car();
		car.setCarnumber(rent.getCarnumber());
		car.setIsrenting(SYS_Constast.TYPE_PUBLIC_ZERO);
		carMapper.updateByPrimaryKeySelective(car);
		//删除出租单号
		int i = rentMapper.deleteByPrimaryKey(rentId);
		return i;
	}

	@Override
	public Rent queryByRentId(String rentid) {
		return rentMapper.selectByPrimaryKey(rentid);
	}

	@Override
	public void exportRent(RentVo rentVo, HttpServletResponse response) {
		Rent rent = rentMapper.selectByPrimaryKey(rentVo.getRentid());
		Customer customer = customerService.queryById(rent.getIdentity());
		String fileName=rent.getIdentity()+"客户的出租单表.xls";
		RentExportExcelUtils.exportRentEexcel(rent,customer ,fileName, response);
	}

}
