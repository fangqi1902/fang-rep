package com.sxt.sys.mapper;

import java.util.List;

import com.sxt.sys.domain.Role;
import com.sxt.sys.vo.RoleVo;

public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
    
    //查询所有
    List<Role> queryAllRole(Role role);

    //保存角色分配的关系
    int saveRoleMenus(Integer roleId,Integer mid);
    //根据角色的id删除原来拥有的菜单 防止重复添加相同的菜单
	void deleteRoleMenus(Integer roleid);
	   //根据用户id获取用户拥有的角色
    List<Role> queryRolesByUserId(Integer userid);
}