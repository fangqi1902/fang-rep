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
import com.sxt.sys.domain.Role;
import com.sxt.sys.domain.User;
import com.sxt.sys.mapper.UserMapper;
import com.sxt.sys.service.RoleService;
import com.sxt.sys.service.UserService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.utils.TreeNode;
import com.sxt.sys.vo.LogInfoVo;
import com.sxt.sys.vo.UserVo;
import com.sxt.sys.vo.RoleVo;
import com.sxt.sys.vo.UserVo;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	// 跳转到修改密码界面

	@RequestMapping("toPwdManager")
	public String toPwdManager() {
		return "system/pwdManager";
	}

	// 跳转到userManager.jsp界面
	@RequestMapping("toUserManager")
	public String toUserManager() {
		return "system/userManager";
	}

	// 查询所有的用户信息 以json格式返回
	@RequestMapping("queryAllUser")
	@ResponseBody
	public DataGridView queryAllUser(UserVo userVo) {
		return userService.queryAllUser(userVo);
	}

	@RequestMapping("updatePwd")
	@ResponseBody
	public Map<String, Object> updatePwd(UserVo userVo) {
		Map<String, Object> map = new HashMap<>();
		try {
			int i = userService.updateUserPwd(userVo);
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

	// 添加用户
	@RequestMapping("addUser")
	@ResponseBody
	public Map<String, Object> addUser(UserVo userVo) {
		Map<String, Object> map = new HashMap<>();
		try {
			// 设置默认密码
			userVo.setPwd(SYS_Constast.USER_PWD_DEFAULT);
			// 设置用户类型
			userVo.setType(SYS_Constast.USER_TYPE_NORMAL);
			int i = userService.addUser(userVo);
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

	// 修改用户
	@RequestMapping("updateUser")
	@ResponseBody
	public Map<String, Object> updateUser(UserVo userVo) {
		Map<String, Object> map = new HashMap<>();
		try {
			int i = userService.updateUser(userVo);
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

	// 删除用户
	@RequestMapping("deleteUser")
	@ResponseBody
	public Map<String, Object> deleteUser(UserVo userVo) {
		Map<String, Object> map = new HashMap<>();
		try {
			int i = userService.deleteUser(userVo);
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

	// 重置密码
	@RequestMapping("resetUserPwd")
	@ResponseBody
	public Map<String, Object> resetUserPwd(UserVo userVo) {
		Map<String, Object> map = new HashMap<>();
		try {
			int i = userService.resetUserPwd(userVo);
			if (i > 0) {
				map.put("msg", "重置成功");
			} else {
				map.put("msg", "重置失败");
			}

		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "重置失败");
		}
		return map;
	}

	// 初始化界面所需要得角色树
	@RequestMapping("initUserRoleTree")
	@ResponseBody
	public List<TreeNode> initUserRoleTree(UserVo userVo) {
		// 根据用户id查询用户拥有得角色
		List<Role> userRoles = roleService.queryRolesByUserId(userVo
				.getUserid());
		// 查询所有角色
		List<Role> allRoles = roleService.queryAllRolesForList();
		List<TreeNode> treeNodes = new ArrayList<>();
		for (Role role : allRoles) {
			Boolean checked = false;
			for (Role role2 : userRoles) {
				// 如果有相等 说明该用户已有该角色
				if (role.getRoleid() == role2.getRoleid()) {
					checked = true;
				}
			}
			treeNodes.add(new TreeNode(role.getRoleid(), 0, role.getRolename()
					+ "[" + role.getRoledesc() + "]", false, true,
					"./resources/css/icons/FUNC20001.gif", checked));
		}
		return treeNodes;
	}
	//保存用户分配的角色
	@RequestMapping("saveUserRole")
	@ResponseBody
	public Map<String, Object> saveUserRole(UserVo userVo) {
		Map<String, Object> map = new HashMap<>();
		try {
			int i = userService.saveUserRole(userVo);
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
