package com.sxt.bus.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.weaver.ArrayAnnotationValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.bus.domain.Customer;
import com.sxt.bus.mapper.CustomerMapper;
import com.sxt.bus.service.CustomerService;
import com.sxt.bus.utils.Chart;
import com.sxt.bus.vo.CustomerVo;
import com.sxt.sys.utils.DataGridView;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerMapper customerMapper;

	@Override
	public DataGridView queryAllCustomers(CustomerVo customerVo) {
		Page<Object> page = PageHelper.startPage(customerVo.getPage(),
				customerVo.getRows());
		List<Customer> list = customerMapper.queryAllCustomer(customerVo);
		return new DataGridView(page.getTotal(), list);
	}

	@Override
	public Customer queryById(String identity) {
		return customerMapper.selectByPrimaryKey(identity);
	}

	@Override
	public int addCustomer(CustomerVo customerVo) {
		int i = customerMapper.insert(customerVo);
		return i;
	}

	@Override
	public int updateCustomer(CustomerVo customerVo) {
		int i = customerMapper.updateByPrimaryKey(customerVo);
		return i;
	}

	@Override
	public int deleteCustomer(String identity) {
		int i = customerMapper.deleteByPrimaryKey(identity);
		return i;
	}

	@Override
	public List<Customer> queryAllCustomersForExport(CustomerVo customerVo) {
		return customerMapper.queryAllCustomer(customerVo);
	}

	@Override
	public List<String> queryAllAddress() {
		return customerMapper.queryAllAddress();
	}

	@Override
	public Integer queryCountByAddress(String address) {
		return customerMapper.queryCountByAddress(address);
	}

	@Override
	public Chart queryCountSex() {
		Chart chart  = new Chart();
		List<String> sex = new ArrayList<>();
		sex.add("女");
		sex.add("男");
		
		List<Integer> count1 = new ArrayList<>();
		count1.add(customerMapper.queryCountSex(0));
		count1.add(customerMapper.queryCountSex(1));
		
		Integer[] data1=new Integer[count1.size()];
		for (int i = 0; i < data1.length; i++) {
				data1[i] =count1.get(i);
		}
		String[] categories= new String[sex.size()];
		for (int i = 0; i < categories.length; i++) {
			categories[i] =sex.get(i);
		}
		chart.setCategories(categories);
		chart.setData1(data1);
		return chart;
	}

}
