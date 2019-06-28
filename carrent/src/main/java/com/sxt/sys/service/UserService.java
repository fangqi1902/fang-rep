package com.sxt.sys.service;

import com.sxt.sys.domain.User;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.UserVo;
import com.sxt.sys.vo.UserVo;

public interface UserService {

	// 用户登陆
	User login(UserVo userVo);

	int updateUserPwd(UserVo userVo);

	// 分页查询所有用户
	public DataGridView queryAllUser(UserVo userVo);

	// 添加
	public int addUser(UserVo userVo);

	// 修改
	public int updateUser(UserVo userVo);

	// 删除
	public int deleteUser(UserVo userVo);

	//重置密码
	int resetUserPwd(UserVo userVo);
	
	
	//保存分配的角色
	int saveUserRole(UserVo userVo);

}
