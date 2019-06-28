package com.sxt.bus.service;

import java.util.List;

import com.sxt.bus.domain.Customer;
import com.sxt.bus.utils.Chart;
import com.sxt.bus.vo.CustomerVo;
import com.sxt.sys.utils.DataGridView;

public interface CustomerService {
	//全查询包括模糊查询
	DataGridView queryAllCustomers(CustomerVo  customerVo);
	//单查询
	Customer  queryById(String identity);
	//添加
	int  addCustomer(CustomerVo customerVo);
	//修改
	int  updateCustomer(CustomerVo customerVo);
	//删除
	int deleteCustomer(String identity);
	
	//导出客户数据
	List<Customer> queryAllCustomersForExport(CustomerVo customerVo);
	
	//查询所有的地址 返回list集合
    List<String> queryAllAddress();
    
    //返回每个地址拥有的客户数
    Integer queryCountByAddress(String address);
    
    //返回每个性别的客户数
    Chart queryCountSex();

}
