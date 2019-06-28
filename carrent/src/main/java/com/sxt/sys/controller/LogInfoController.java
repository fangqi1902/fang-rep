package com.sxt.sys.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sxt.sys.service.LogInfoService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.LogInfoVo;


@Controller
@RequestMapping("logInfo")
public class LogInfoController {
	
	@Autowired
	private LogInfoService  logInfoService;
	
	//跳转到logInfoManager.jsp界面
	@RequestMapping("toLogInfoManager")
	public String toLogInfoManager(){
		
		return "system/logInfoManager";
	}
	
	//查询所有的日志信息 以json格式返回
	@RequestMapping("queryAllLogInfo")
	@ResponseBody
	public DataGridView queryAllLogInfo(LogInfoVo logInfoVo){
		return logInfoService.queryAllLogInfo(logInfoVo);
	}
	//删除日志信息
	@RequestMapping("deleteLogInfo")
	@ResponseBody
	public Map<String,Object> deleteLogInfo(LogInfoVo logInfoVo){
		Map<String,Object> map = new HashMap<>();
		int i = logInfoService.deleteLogInfo(logInfoVo);
			if(i>0){
				map.put("msg", "删除成功");
			}else {
				map.put("msg", "删除失败");
			}
		return map;
	}
}
