package com.sxt.bus.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;




import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sxt.bus.domain.Customer;
import com.sxt.bus.service.CustomerService;
import com.sxt.bus.utils.CustomerExportExcelUtils;
import com.sxt.bus.vo.CustomerVo;
import com.sxt.sys.utils.DataGridView;

@Controller
@RequestMapping("customer")
public class CustomerController {
	@Autowired
	private CustomerService customerService;
	
	//跳转到客户列表界面
	@RequestMapping("toCustomerManager")
	public String toCustomerMannger(){
		return "business/customerManager";
	}
	
	//查询所有客户，并以json格式返回到列表界面
	@RequestMapping("queryAllCustomer")
	@ResponseBody
	public DataGridView queryAllCustomer(CustomerVo customerVo){
		return customerService.queryAllCustomers(customerVo);
	}
	
	
	
	@RequestMapping("deleteCustomer")
	@ResponseBody
	public Map<String, Object> deleteCustomer(CustomerVo customerVo){
		Map<String, Object> map = new HashMap<>();
		try {
			int i = customerService.deleteCustomer(customerVo.getIdentity());
			if(i>0){
				map.put("msg","删除成功");
			}else{
				map.put("msg","删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg","删除失败");
		}
		return map;
	}
	
	
	//添加
	@RequestMapping("addCustomer")
	@ResponseBody
	public Map<String, Object> addCustomer(CustomerVo customerVo){
		Map<String, Object> map = new HashMap<>();
		try {
			int i = customerService.addCustomer(customerVo);
			if(i>0){
				map.put("msg","添加成功");
			}else{
				map.put("msg","添加失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg","添加失败");
		}
		return map;
	}
	
	
	//修改
	@RequestMapping("updateCustomer")
	@ResponseBody
	public Map<String, Object> updateCustomer(CustomerVo customerVo){
		Map<String, Object> map = new HashMap<>();
		try {
			int i = customerService.updateCustomer(customerVo);
			if(i>0){
				map.put("msg","修改成功");
			}else{
				map.put("msg","修改失败");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg","修改失败");
		}
		return map;
	}
	
	//导出客户数据
	@RequestMapping("exportCustomer")
	public void exportCustomer(CustomerVo customerVo,HttpServletResponse response){
		String fileName="客户列表.xls";
		List<Customer> cus = customerService.queryAllCustomersForExport(customerVo);
		CustomerExportExcelUtils.exportCustomerEexcel(cus, fileName, response);
	}
	
}
