package com.sxt.sys.service;


import java.util.List;

import com.sxt.sys.domain.News;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.NewsVo;

public interface NewsService {
	//全查询
	DataGridView queryAllNews(NewsVo newsVo);

	int deleteNews(NewsVo newsVo);

	int addNews(NewsVo newsVo);
	
	//单查询
	News queryNewsById(Integer id);
	
	int updateNews(NewsVo newsVo);
	
	List<News> queryAllNews();
	
}
