package com.sxt.sys.utils;

import java.util.List;

public class DataGridView {
	
	//easyui表格需要的json对象
	private Long total;
	private List<?> rows;
	
	
	public DataGridView(Long total, List<?> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
	public DataGridView() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
	

}
