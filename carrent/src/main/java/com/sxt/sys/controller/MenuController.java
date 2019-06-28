package com.sxt.sys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sxt.sys.constast.SYS_Constast;
import com.sxt.sys.domain.Menu;
import com.sxt.sys.service.MenuService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.utils.TreeNode;
import com.sxt.sys.utils.TreeNodeBuilder;
import com.sxt.sys.vo.MenuVo;
import com.sxt.sys.vo.MenuVo;

@Controller
@RequestMapping("menu")
public class MenuController {

	@Autowired
	private MenuService menuService;

	// 跳转到菜单管理界面

	@RequestMapping("toMenuManager")
	public String toMenuManager() {
		return "system/menuManager";
	}

	// 加载菜单树
	// 加载左边的导航树
	@RequestMapping("loadLeftTree")
	@ResponseBody
	// 返回json数据
	public List<TreeNode> loadLeftTree(MenuVo menuVo) {
		// 查询所有的菜单
		List<Menu> menus = menuService.queryAllMenus(menuVo);
		// 将查询的到菜单集合添加到TreeNode类型的集合中，返回json
		List<TreeNode> treeNodes = new ArrayList<>();
		for (Menu menu : menus) {
			Boolean isParent = menu.getParent() == SYS_Constast.TYPE_PUBLIC_ONE ? true
					: false;
			Boolean open = menu.getOpen() == SYS_Constast.TYPE_PUBLIC_ONE ? true
					: false;
			treeNodes.add(new TreeNode(menu.getId(), menu.getPid(), menu
					.getName(), isParent, open, menu.getHref(), menu.getIcon(),
					menu.getTabicon()));
		}
		return treeNodes;
	}

	// 加载菜单列表
	@RequestMapping("queryAllMenus")
	@ResponseBody
	public DataGridView queryAllMenus(MenuVo menuVo) {
		return menuService.queryAllMenusPage(menuVo);
	}

	// 加载easyui comboTree的json数据
	@RequestMapping("loadMenuTreeForNormalJosn")
	@ResponseBody
	public List<TreeNode> loadMenuTreeForNormalJosn(MenuVo menuVo) {
		// 查询所有可用菜单
		List<Menu> menus = menuService.queryAllMenus(menuVo);
		List<TreeNode> treeNodes = new ArrayList<>();
		for (Menu menu : menus) {
			menu.setOpen(1);
			treeNodes.add(new TreeNode(menu.getId(), menu.getPid(), menu
					.getName()));
		}
		// 使用方法去把treeNodes里面的数据变成有层级关系的数据
		List<TreeNode> nodes = TreeNodeBuilder.builder(treeNodes, 0);
		return nodes;
	}

	// 添加菜单
	@RequestMapping("addMenu")
	@ResponseBody
	public Map<String, Object> addMenu(MenuVo menuVo) {
		Map<String, Object> map = new HashMap<>();
		try {
			int i = menuService.addMenu(menuVo);
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

	// 修改菜单
	@RequestMapping("updateMenu")
	@ResponseBody
	public Map<String, Object> updateMenu(MenuVo menuVo) {
		Map<String, Object> map = new HashMap<>();
		try {
			int i = menuService.updateMenu(menuVo);
			if (i > 0) {
				map.put("msg", "修改成功");
			} else {
				map.put("msg", "修改失败");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "修改失败");
		}
		return map;
	}

	// 删除菜单
	@RequestMapping("deleteMenu")
	@ResponseBody
	public Map<String, Object> deleteMenu(MenuVo menuVo) {
		Map<String, Object> map = new HashMap<>();
		try {
			int i = menuService.deleteMenu(menuVo);
			if (i > 0) {
				map.put("msg", "删除成功");
			} else {
				map.put("msg", "删除失败");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "删除失败");
		}
		return map;
	}

}
