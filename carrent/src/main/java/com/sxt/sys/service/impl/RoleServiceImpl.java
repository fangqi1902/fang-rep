package com.sxt.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.sys.domain.Role;
import com.sxt.sys.mapper.RoleMapper;
import com.sxt.sys.service.RoleService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.RoleVo;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public DataGridView queryAllRoles(RoleVo roleVo) {
		Page<Object> page = PageHelper.startPage(roleVo.getPage(),
				roleVo.getRows());
		List<Role> list = roleMapper.queryAllRole(roleVo);
		return new DataGridView(page.getTotal(), list);
	}


	@Override
	public int addRole(RoleVo roleVo) {
		int i = roleMapper.insert(roleVo);
		return i;
	}

	@Override
	public int updateRole(RoleVo roleVo) {
		int i = roleMapper.updateByPrimaryKey(roleVo);
		return i;
	}

	@Override
	public int deleteRole(Integer roleid) {
		int i = roleMapper.deleteByPrimaryKey(roleid);
		return i;
	}


	@Override
	public int saveRoleMenus(RoleVo roleVo) {
		Integer roleid = roleVo.getRoleid();
		Integer[] mids = roleVo.getIds();
		//保存前先删除原先分配的菜单 防止添加已有的菜单
		roleMapper.deleteRoleMenus(roleid);
		if(null!=mids&&mids.length>0){
			for (Integer mid : mids) {
				roleMapper.saveRoleMenus(roleid, mid);
			}
		}
		return 1;
	}


	@Override
	public List<Role> queryRolesByUserId(Integer userid) {
		return roleMapper.queryRolesByUserId(userid);
	}


	@Override
	public List<Role> queryAllRolesForList() {
		return roleMapper.queryAllRole(new Role());
	}

}
