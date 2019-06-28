package com.sxt.sys.mapper;

import java.util.List;

import com.sxt.sys.domain.Menu;

public interface MenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
    
    //查询所有菜单
    List<Menu> queryAllMenus(Menu record);
    
    //根据角色id查询菜单
    List<Menu> queryMenusByRoleId(Integer roleid);

    //根据用户id查询用户拥有的菜单
	List<Menu> queryAllMenusByUserId(Integer uid);
    
}