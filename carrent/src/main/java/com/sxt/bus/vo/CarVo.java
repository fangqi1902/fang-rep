package com.sxt.bus.vo;

import com.sxt.bus.domain.Car;

public class CarVo extends Car {
	// 分页参数
	private Integer page;// 当前页
	private Integer rows;// 每页显示几条
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	
	
}
