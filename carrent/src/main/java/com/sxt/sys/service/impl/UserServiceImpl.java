package com.sxt.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.sys.constast.SYS_Constast;
import com.sxt.sys.domain.User;
import com.sxt.sys.mapper.UserMapper;
import com.sxt.sys.service.UserService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.UserVo;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User login(UserVo userVo) {
		return userMapper.login(userVo);
	}

	@Override
	public int updateUserPwd(UserVo userVo) {
		return userMapper.updateByPrimaryKeySelective(userVo);
	}

	@Override
	public DataGridView queryAllUser(UserVo userVo) {
		Page<Object> page = PageHelper.startPage(userVo.getPage(), userVo.getRows());
		List<User> list = userMapper.queryAllUser(userVo);
		return new DataGridView(page.getTotal(), list);
	}

	@Override
	public int addUser(UserVo userVo) {
		return userMapper.insert(userVo);
	}

	@Override
	public int updateUser(UserVo userVo) {
		return userMapper.updateByPrimaryKey(userVo);
	}

	@Override
	public int deleteUser(UserVo userVo) {
		return userMapper.deleteByPrimaryKey(userVo.getUserid());
	}

	@Override
	public int resetUserPwd(UserVo userVo) {
		userVo.setPwd(SYS_Constast.USER_PWD_DEFAULT);
		return userMapper.updateByPrimaryKeySelective(userVo);
	}

	@Override
	public int saveUserRole(UserVo userVo) {
		Integer userid = userVo.getUserid();
		Integer[] rids = userVo.getIds();
		//先删除原来的角色
		userMapper.deleteUserRoleByUserId(userid);
		//重新分配角色
		if(null!=rids&&rids.length>0){
			for (Integer rid : rids) {
				userMapper.saveUserRole(userid, rid);
			}
		}
		return 1;
	}

}
