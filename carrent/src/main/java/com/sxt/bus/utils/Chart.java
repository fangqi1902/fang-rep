package com.sxt.bus.utils;
/**
 * 柱状图的数据模型,封装柱状图需要的动态数据
 * @author lujun
 *
 */
public class Chart {
    private    String[]  categories;  //X轴的分类名称
    private Integer[]  data1;//数据列1的数据
	public String[] getCategories() {
		return categories;
	}
	public void setCategories(String[] categories) {
		this.categories = categories;
	}
	public Integer[] getData1() {
		return data1;
	}
	public void setData1(Integer[] data1) {
		this.data1 = data1;
	}
	public Chart(String[] categories, Integer[] data1) {
		super();
		this.categories = categories;
		this.data1 = data1;
	}
	public Chart() {
		super();
		// TODO Auto-generated constructor stub
	}  
}
