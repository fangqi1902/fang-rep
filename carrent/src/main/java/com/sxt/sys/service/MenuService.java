package com.sxt.sys.service;

import java.util.List;

import com.sxt.sys.domain.Menu;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.MenuVo;


public interface MenuService {
	//查询所有菜单
	public List<Menu> queryAllMenus(MenuVo menuVo);
	
	//根据用户id查询用户能使用的菜单
	public List<Menu> queryAllMenusByUid(Integer uid);
	
	//分页查询所有菜单
	public DataGridView queryAllMenusPage(MenuVo menuVo);

	
	//添加
	public int addMenu(MenuVo menuVo);
	
	//修改
	public int updateMenu(MenuVo menuVo);
	
	//删除
	public int deleteMenu(MenuVo menuVo);
	
	
	//根据角色id查询菜单
    List<Menu> queryMenusByRoleId(Integer roleid);
	
}
