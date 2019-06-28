package com.sxt.sys.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sxt.sys.domain.User;
import com.sxt.sys.service.NewsService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.NewsVo;

@Controller
@RequestMapping("news")
public class NewsController {

	@Autowired
	private NewsService newsService;

	// 跳转到system/newsManager.jsp
	@RequestMapping("toNewsManager")
	public String toNewsManager() {
		return "system/newsManager";
	}

	// 加载所有数据
	// 查询所有的公告信息 以json格式返回
	@RequestMapping("queryAllNews")
	@ResponseBody
	public DataGridView queryAllNews(NewsVo newsVo) {
		return newsService.queryAllNews(newsVo);
	}


	// 添加
	// 查询所有的公告信息 以json格式返回
	@RequestMapping("addNews")
	@ResponseBody
	public Map<String, Object> addNews(NewsVo newsVo,HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		User user = (User) session.getAttribute("user");
		newsVo.setCreatetime(new Date());
		newsVo.setOpername(user.getRealname());
		try {
			
			int i = newsService.addNews(newsVo);
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

	// 删除
	// 查询所有的公告信息 以json格式返回
	@RequestMapping("deleteNews")
	@ResponseBody
	public Map<String, Object> deleteNews(NewsVo newsVo) {
		Map<String, Object> map = new HashMap<>();
		try {
			int i = newsService.deleteNews(newsVo);
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

	// 修改
	@RequestMapping("updateNews")
	@ResponseBody
	public Map<String, Object> updateNews(NewsVo newsVo) {
		Map<String, Object> map = new HashMap<>();
		try{
		int i = newsService.updateNews(newsVo);
		if (i > 0) {
			map.put("msg", "修改成功");
		} else {
			map.put("msg", "修改失败");
		}
		}catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "修改失败");
		}
		return map;
	}

}
