package com.sxt.sys.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.sxt.sys.domain.LogInfo;

public class LogInfoVo extends LogInfo{
	//分页参数
	private Integer page;
	private Integer rows;
	
	
	//模糊查询某段时间的记录
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startDate;//开始时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endDate;//结束时间
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	

}
