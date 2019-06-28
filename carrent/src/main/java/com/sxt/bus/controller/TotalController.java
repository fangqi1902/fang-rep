package com.sxt.bus.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sxt.bus.service.CarService;
import com.sxt.bus.service.CustomerService;
import com.sxt.bus.utils.Chart;
import com.sxt.bus.utils.PieChart;


@Controller
@RequestMapping("tjfx")
public class TotalController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CarService carService;
	
	
	//跳转到客户区域统计图
	@RequestMapping("toCountCustomerArea")
	public String toCountCustomerArea(){
		return "total/countCustomerArea";
	}
	
	//查询客户区域数，返回界面
	@RequestMapping("addressColumnChart")
	@ResponseBody
	public Chart addressColumnChart(){
		Chart chart = new Chart();
		
		List<Integer> count1 =new ArrayList<>();

		List<String> address = customerService.queryAllAddress();
		//按地址查询每个地址拥有的客户数，并把它添加到集合中
		for (String a : address) {
			count1.add(customerService.queryCountByAddress(a));
		}
		Integer[] data1=new Integer[count1.size()];
		for (int i = 0; i < data1.length; i++) {
				data1[i] =count1.get(i);
		}
		String[] categories= new String[address.size()];
		for (int i = 0; i < categories.length; i++) {
			categories[i] =address.get(i);
		}
		chart.setCategories(categories);
		chart.setData1(data1);
		return chart;
	}

	//跳转到客户男女比例统计界面
	@RequestMapping("toCountSex")
	public String toCountSex(){
		return "total/sexPiechart";
	}
	
	//将男女比例数据返回到界面需要的饼图中
	@RequestMapping("sexPieChart")
	@ResponseBody
	public Chart sexPieChart(){
		return customerService.queryCountSex();
	}
	//跳转到汽车类型统计界面
	@RequestMapping("toCountCarType")
	public String toCountCarType(){
		return "total/countCarType";
	}
	@RequestMapping("countCarType")
	@ResponseBody
	public Chart countCarType(){
		return carService.queryCountType();
	}
	//跳转到汽车颜色统计界面
	@RequestMapping("toCountCarColor")
	public String toCountCarColor(){
		return "total/colorPiechart";
	}
	@RequestMapping("countCarColor")
	@ResponseBody
	public  List<PieChart> countCarColor(){
		return carService.queryCountColor();
	}
}
