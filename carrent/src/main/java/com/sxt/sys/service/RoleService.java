package com.sxt.sys.service;

import java.util.List;

import com.sxt.sys.domain.Role;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.RoleVo;

public interface RoleService {
	//全查询包括模糊查询
	DataGridView queryAllRoles(RoleVo  roleVo);
	//添加
	int  addRole(RoleVo roleVo);
	//修改
	int  updateRole(RoleVo roleVo);
	//删除
	int deleteRole(Integer roleid);
	
	//保存角色分配的菜单
	int saveRoleMenus(RoleVo roleVo);

	//根据用户id获取用户拥有的角色
	List<Role> queryRolesByUserId(Integer userid);
	
	//查询所有角色
	List<Role> queryAllRolesForList();

}
