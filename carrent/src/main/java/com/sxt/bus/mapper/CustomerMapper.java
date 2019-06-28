package com.sxt.bus.mapper;

import java.util.List;

import com.sxt.bus.domain.Customer;
import com.sxt.bus.vo.CustomerVo;

public interface CustomerMapper {
    int deleteByPrimaryKey(String identity);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(String identity);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);
    
    //查询所有客户包括模糊查询
    List<Customer> queryAllCustomer(Customer customer);
    
    //查询所有的地址 返回list集合
    
    List<String> queryAllAddress();
    
    //返回每个地址拥有的客户数
    Integer queryCountByAddress(String address);
    
    //返回每个性别的客户数
    Integer queryCountSex(Integer sex);
    
    
    
    
    
    
    
}