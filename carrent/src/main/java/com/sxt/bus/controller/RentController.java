package com.sxt.bus.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sxt.bus.constast.BUS_Constast;
import com.sxt.bus.domain.Customer;
import com.sxt.bus.mapper.CarMapper;
import com.sxt.bus.mapper.CustomerMapper;
import com.sxt.bus.service.CarService;
import com.sxt.bus.service.CustomerService;
import com.sxt.bus.service.RentService;
import com.sxt.bus.utils.CustomerExportExcelUtils;
import com.sxt.bus.utils.RandomUtils;
import com.sxt.bus.vo.CarVo;
import com.sxt.bus.vo.CustomerVo;
import com.sxt.bus.vo.RentVo;
import com.sxt.sys.constast.SYS_Constast;
import com.sxt.sys.domain.User;
import com.sxt.sys.utils.DataGridView;

@Controller
@RequestMapping("rent")
public class RentController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private RentService rentService;
	
	/*************汽车出租开始****************/
	
	
	//跳转到出租界面
	@RequestMapping("toRentCarManager")
	public  String toRentCarManager(){
		return "business/rentCarManager";
	}


	
	
	//判断客户是否存在 返回 Boolean值
	@RequestMapping("checkCustomerIdentity")
	@ResponseBody
	public Boolean checkCustomerIdentity(String identity){
		Customer customer = customerService.queryById(identity);
		if(null!=customer){
			return true;
		}else{
			return false;
		}
	}
	//初始化出租表单数据
	@RequestMapping("initRentFormData")
	@ResponseBody
	public RentVo initRentFormData(RentVo rentVo,HttpSession session){
		User user = (User) session.getAttribute("user");
		rentVo.setRentid(RandomUtils.createRandomStrUseTime(BUS_Constast.RENT_PREFIX));
		rentVo.setOpername(user.getRealname());
		rentVo.setRentflag(SYS_Constast.TYPE_PUBLIC_ZERO);
		return rentVo;
	}
	
	//保存出租单信息
	@RequestMapping("addRent")
	@ResponseBody
	public Map<String , Object> addRent(RentVo rentVo){
		Map<String, Object> map = new HashMap<>();
		try {
			int i = rentService.addRent(rentVo);
			if(i>0){
				map.put("msg","保存成功");
			}else {
				map.put("msg","保存失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg","保存失败");
		}
		return map;
	}
	/*************汽车出租结束****************/
	
	
	/*************出租管理开始****************/
	//跳转到出租单界面
	@RequestMapping("toRentManager")
	public  String toRentManager(){
		return "business/rentManager";
	}
	
	//获取所有出租单
	@RequestMapping("queryAllRent")
	@ResponseBody
	public DataGridView queryAllrent(RentVo rentVo){
		return rentService.queryAllRents(rentVo);
	}
	
	//修改出租单
	@RequestMapping("updateRent")
	@ResponseBody
	public Map<String , Object> updateRent(RentVo rentVo){
		Map<String, Object> map = new HashMap<>();
		try {
			int i = rentService.updateRent(rentVo);
			if(i>0){
				map.put("msg","修改成功");
			}else {
				map.put("msg","修改失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg","修改失败");
		}
		return map;
	}
	//删除出租单
	@RequestMapping("deleteRent")
	@ResponseBody
	public Map<String , Object> deleteRent(RentVo rentVo){
		Map<String, Object> map = new HashMap<>();
		try {
			int i = rentService.deleteRent(rentVo.getRentid());
			if(i>0){
				map.put("msg","删除成功");
			}else {
				map.put("msg","删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg","保存失败");
		}
		return map;
	}
	
	//导出出租单
	@RequestMapping("exportRent")
	public void exportRent(RentVo rentVo,HttpServletResponse response){
		rentService.exportRent(rentVo, response);
	}
	
	
	
	
	
	/*************出租单管理结束****************/
	
	
}
