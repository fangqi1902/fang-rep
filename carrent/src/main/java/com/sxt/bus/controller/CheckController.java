package com.sxt.bus.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sxt.bus.constast.BUS_Constast;
import com.sxt.bus.domain.Rent;
import com.sxt.bus.service.CheckService;
import com.sxt.bus.service.RentService;
import com.sxt.bus.utils.RandomUtils;
import com.sxt.bus.vo.CarVo;
import com.sxt.bus.vo.CheckVo;
import com.sxt.bus.vo.RentVo;
import com.sxt.sys.domain.User;
import com.sxt.sys.utils.DataGridView;


@Controller
@RequestMapping("check")
public class CheckController {
	
	@Autowired
	private RentService rentService;

	@Autowired
	private CheckService checkService;

	/****************汽车入库********************/
	//跳转到汽车入库的界面../check/toCheckCarManager.action
	@RequestMapping("toCheckCarManager")
	public String toCheckCarManager(){
		return "business/checkCarManager";
	}
	
	@RequestMapping("checkRentByRentId")
	@ResponseBody
	public Rent checkRentByRentId(String rentid){
		Rent rent = rentService.queryByRentId(rentid);
		return rent;
	}
	
	//初始化数据
	@RequestMapping("initData")
	@ResponseBody
	public Map<String, Object> initData(CheckVo checkVo,HttpSession session){
		//获取操作人
		User  user = (User) session.getAttribute("user");
		checkVo.setOpername(user.getRealname());
		//获取检查单号
		checkVo.setCheckid(RandomUtils.createRandomStrUseTime(BUS_Constast.CHECK_PREFIX));
		//获取检查时间
		checkVo.setCheckdate(new Date());
		Map<String, Object> map = checkService.initData(checkVo);
		map.put("check",checkVo);
		return map;
	}
	
	//添加检查单信息
		@RequestMapping("addCheck")
		@ResponseBody
		public Map<String, Object> addCheck(CheckVo checkVo) {
			Map<String, Object> map = new HashMap<String, Object>();
			try {
				int i = checkService.addCheck(checkVo);
				if (i > 0) {
					map.put("msg", "添加成功");
				} else {
					map.put("msg", "添加失败");
				}

			} catch (Exception e) {
				e.printStackTrace();
				map.put("msg", "添加失败");
			}
			return map;
		}
		/****************汽车入库结束********************/
		/******************检查单管理*******************/
		//跳转到汽车入库的界面../check/toCheckCarManager.action
		@RequestMapping("toCheckManager")
		public String toCheckManager(){
			return "business/checkManager";
		}
		
		
		//获取所有出租单
		@RequestMapping("queryAllCheck")
		@ResponseBody
		public DataGridView queryAllcheck(CheckVo checkVo){
			return checkService.queryAllCheck(checkVo);
		}
		
		//修改出租单
		@RequestMapping("updateCheck")
		@ResponseBody
		public Map<String , Object> updateCheck(CheckVo checkVo){
			Map<String, Object> map = new HashMap<>();
			try {
				int i = checkService.updateCheck(checkVo);
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
		
		
		
		
		
}
