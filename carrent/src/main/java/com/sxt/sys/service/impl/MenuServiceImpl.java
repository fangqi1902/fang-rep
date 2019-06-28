package com.sxt.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sun.org.apache.regexp.internal.recompile;
import com.sxt.sys.domain.Menu;
import com.sxt.sys.domain.User;
import com.sxt.sys.mapper.MenuMapper;
import com.sxt.sys.service.MenuService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.MenuVo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuMapper menuMapper;

	@Override
	public List<Menu> queryAllMenus(MenuVo menuVo) {
		return menuMapper.queryAllMenus(menuVo);
	}

	@Override
	public List<Menu> queryAllMenusByUid(Integer uid) {
		System.err.println("真实方法调用");
		List<Menu> queryAllMenusByUserId = menuMapper.queryAllMenusByUserId(uid);
		System.err.println(queryAllMenusByUserId.size());
		return queryAllMenusByUserId;
	}

	@Override
	public DataGridView queryAllMenusPage(MenuVo menuVo) {
		Page<Object> page = PageHelper.startPage(menuVo.getPage(), menuVo.getRows());
		List<Menu> menus = menuMapper.queryAllMenus(menuVo);
		return new DataGridView(page.getTotal(), menus);
	}

	@Override
	public int addMenu(MenuVo menuVo) {
		return menuMapper.insert(menuVo);
	}

	@Override
	public int updateMenu(MenuVo menuVo) {
		return menuMapper.updateByPrimaryKey(menuVo);
	}

	@Override
	public int deleteMenu(MenuVo menuVo) {
		return menuMapper.deleteByPrimaryKey(menuVo.getId());
	}

	@Override
	public List<Menu> queryMenusByRoleId(Integer roleid) {
		return menuMapper.queryMenusByRoleId(roleid);
	}

}
