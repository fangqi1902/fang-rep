package com.sxt.sys.vo;

import com.sxt.sys.domain.User;

public class UserVo extends User{
		//接收界面选中得节点数组
		private Integer ids[];
	
		// 分页参数
		private Integer page;
		private Integer rows;

		
		public Integer[] getIds() {
			return ids;
		}

		public void setIds(Integer[] ids) {
			this.ids = ids;
		}

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
