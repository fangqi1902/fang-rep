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
import com.sxt.sys.service.RoleService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.utils.TreeNode;
import com.sxt.sys.vo.MenuVo;
import com.sxt.sys.vo.RoleVo;

@Controller
@RequestMapping("role")
public class RoleController {
	@Autowired
	private RoleService roleService;

	@Autowired
	private MenuService menuService;

	// 跳转到角色列表界面
	@RequestMapping("toRoleManager")
	public String toRoleMannger() {
		return "system/roleManager";
	}

	// 查询所有角色，并以json格式返回到列表界面
	@RequestMapping("queryAllRole")
	@ResponseBody
	public DataGridView queryAllRole(RoleVo roleVo) {
		return roleService.queryAllRoles(roleVo);
	}

	// 删除角色
	@RequestMapping("deleteRole")
	@ResponseBody
	public Map<String, Object> deleteRole(RoleVo roleVo) {
		Map<String, Object> map = new HashMap<>();
		try {
			int i = roleService.deleteRole(roleVo.getRoleid());
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

	// 添加
	@RequestMapping("addRole")
	@ResponseBody
	public Map<String, Object> addRole(RoleVo roleVo) {
		Map<String, Object> map = new HashMap<>();
		try {
			int i = roleService.addRole(roleVo);
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

	// 修改
	@RequestMapping("updateRole")
	@ResponseBody
	public Map<String, Object> updateRole(RoleVo roleVo) {
		Map<String, Object> map = new HashMap<>();
		try {
			int i = roleService.updateRole(roleVo);
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

	// 加载分配菜单所需要你的菜单 并选中之前有的菜单
	// initRoleMenuTree

	@RequestMapping("initRoleMenuTree")
	@ResponseBody
	public List<TreeNode> initRoleMenuTree(RoleVo roleVo) {
		List<TreeNode> treeNodes = new ArrayList<>();
		// 1.查询角色之前拥有的菜单
		List<Menu> menus = menuService.queryMenusByRoleId(roleVo.getRoleid());
		// 2.查询所有可用菜单
		MenuVo menuVo = new MenuVo();
		menuVo.setAvailable(SYS_Constast.TYPE_AVAILABLE_YES);
		List<Menu> allMenus = menuService.queryAllMenus(menuVo);
		for (Menu menu : allMenus) {
			Boolean checked = false;// 默认都没选中
			for (Menu m : menus) {
				if (menu.getId() == m.getId()) {
					checked = true;
					break;
				}
			}
			Boolean open = menu.getOpen() == SYS_Constast.TYPE_PUBLIC_ONE ? true
					: false;
			Boolean isParent = menu.getParent() == SYS_Constast.TYPE_PUBLIC_ONE ? true
					: false;
			treeNodes.add(new TreeNode(menu.getId(), menu.getPid(), menu
					.getName(), isParent, open, menu.getIcon().substring(1, menu.getIcon().length()), checked));
		}
		return treeNodes;
	}
	//添加保存角色所拥有的菜单
	@RequestMapping("saveRoleMenus")
	@ResponseBody
	public Map<String, Object> saveRoleMenus(RoleVo roleVo) {
		Map<String, Object> map = new HashMap<>();
		try {
			int i = roleService.saveRoleMenus(roleVo);
			if (i > 0) {
				map.put("msg", "分配成功");
			} else {
				map.put("msg", "分配失败");
			}

		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "分配失败");
		}
		return map;
	}
	

}
