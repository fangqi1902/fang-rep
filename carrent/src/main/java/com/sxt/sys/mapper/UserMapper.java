package com.sxt.sys.mapper;

import java.util.List;

import com.sxt.sys.domain.Role;
import com.sxt.sys.domain.User;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    //用户登录
    User login(User user);
    
    //查询所有用户
    List<User> queryAllUser(User user);
    
    //删除原来的角色
  	int deleteUserRoleByUserId(Integer userid);
  	
  	//保存分配的角色
  	int saveUserRole(Integer userid,Integer rid);
 
}