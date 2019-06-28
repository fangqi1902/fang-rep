package com.sxt.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.sys.domain.News;
import com.sxt.sys.mapper.NewsMapper;
import com.sxt.sys.service.NewsService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.NewsVo;

@Service
public class NewsServiceImpl implements NewsService{

	@Autowired
	private NewsMapper newsMapper;
	
	@Override
	public DataGridView queryAllNews(NewsVo newsVo) {
		Page<Object> page = PageHelper.startPage(newsVo.getPage(), newsVo.getRows());
		List<News> list = newsMapper.queryAllNews(newsVo);
		return new DataGridView(page.getTotal(), list);
	}

	@Override
	public int deleteNews(NewsVo newsVo) {
		return newsMapper.deleteByPrimaryKey(newsVo.getId());
	}

	@Override
	public int addNews(NewsVo newsVo) {
		return newsMapper.insert(newsVo);
	}

	@Override
	public News queryNewsById(Integer id) {
		return newsMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateNews(NewsVo newsVo) {
		return newsMapper.updateByPrimaryKey(newsVo);
	}

	@Override
	public List<News> queryAllNews() {
		NewsVo newsVo=new NewsVo();
		return newsMapper.queryAllNews(newsVo);
	}

}
