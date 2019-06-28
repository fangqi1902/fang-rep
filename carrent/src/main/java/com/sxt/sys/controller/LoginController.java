package com.sxt.sys.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.sxt.sys.constast.SYS_Constast;
import com.sxt.sys.domain.Menu;
import com.sxt.sys.domain.User;
import com.sxt.sys.service.LogInfoService;
import com.sxt.sys.service.MenuService;
import com.sxt.sys.service.UserService;
import com.sxt.sys.utils.CookieUtil;
import com.sxt.sys.utils.ThreadLocalUtil;
import com.sxt.sys.utils.TreeNode;
import com.sxt.sys.vo.LogInfoVo;
import com.sxt.sys.vo.MenuVo;
import com.sxt.sys.vo.UserVo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Controller
@RequestMapping("login")
public class LoginController {
	@Autowired
	private UserService userService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private LogInfoService logInfoService;
	@Autowired
	private JedisPool pool;

	// 跳转到登陆界面
	@RequestMapping("toLogin")
	public String toLogin(HttpSession session) {
		
		return "system/login";
	}

	// 登陆
	@RequestMapping("login")
	public String login(UserVo userVo, HttpServletRequest request,HttpServletResponse response, Model model) {
		User user1 = userService.login(userVo);
		if (null != user1) {
			// 把用户对象存到session里
			//request.getSession().setAttribute("user", user);
			//HttpSession session = request.getSession();
			//将登录信息放在reids里面
			Jedis jedis =null;
			User user=null;
			String userJson=null;
			String id =CookieUtil.getCookieValue(request, "car-login-code") ;
			
			if(id==null) {
				id =UUID.randomUUID().toString();
				// 将该cookie写入
//				CookieUtil.setCookie(request, resp, "car-login-code", key);
				// 30 天即可
				CookieUtil.setCookie(request, response, "car-login-code", id, 30*24*3600);
			}
			
			try {
				//从池中借一个对象
				jedis = pool.getResource();
				//操作
				user1.setPwd("******");//简单设置密码安全
				jedis.set(id, JSON.toJSONString(user1));
				userJson = jedis.get(id);
				user=JSON.parseObject(userJson, User.class);
				//设置过期时间  用户进行登录后没有进行任何操作超过30分钟
				jedis.expire(id, 1800);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				if(null!=jedis) {
					jedis.close();
				}
			}
			// 记录登陆日志
			LogInfoVo logInfoVo = new LogInfoVo();	
			logInfoVo.setLoginname(user1.getLoginname()+"/"+user1.getRealname());
			//得到客户端地址
			logInfoVo.setLoginip(request.getRemoteAddr());
			logInfoVo.setLogintime(new Date());
			logInfoService.addLogInfo(logInfoVo);
			model.addAttribute("user", user);
			return "system/index";
		} else {
			model.addAttribute("error", "用户名或密码不正确");
			return "system/login";
		}

	}

	// 加载index.jsp左边的导航树
	@RequestMapping("loadIndexLeftTree")
	@ResponseBody// 返回json数据
	public List<TreeNode> loadIndexLeftTree(HttpSession session, MenuVo menuVo) {
		//User user = (User) session.getAttribute("user");
		//通过ThreadLocal 获取用户数据
		User user = (User) ThreadLocalUtil.getValue("user");
		List<Menu> menus = null;
		if (user.getType() == SYS_Constast.USER_TYPE_SUPER) {
			// 查询所有可用菜单
			menuVo.setAvailable(SYS_Constast.TYPE_AVAILABLE_YES);
			menus = menuService.queryAllMenus(menuVo);
		} else {
			menus = menuService.queryAllMenusByUid(user.getUserid());
		}
		// 将查询的到菜单集合添加到TreeNode类型的集合中，返回json
		List<TreeNode> treeNodes = new ArrayList<>();
		for (Menu menu : menus) {
			Boolean isParent = menu.getParent().equals( SYS_Constast.TYPE_PUBLIC_ONE) ? true: false;
			Boolean open = menu.getOpen().equals(SYS_Constast.TYPE_PUBLIC_ONE) ? true : false;
			treeNodes.add(new TreeNode(menu.getId(), menu.getPid(), menu
					.getName(), isParent, open, menu.getHref(), menu.getIcon(),
					menu.getTabicon()));
		}
		return treeNodes;
	}
	
	//退出登录
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		String id = session.getId();
		Jedis jedis =null;
		try {
			jedis=pool.getResource();
			jedis.del(id);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(null!=jedis) {
				jedis.close();
			}
		}
		return "system/login";
	}
	
}
