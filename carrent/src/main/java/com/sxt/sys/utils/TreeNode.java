package com.sxt.sys.utils;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.ls.LSInput;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TreeNode {
	private Integer id;
	@JsonProperty("pId")// 更改Jackson生成json字符串时的key的名字
	private Integer pid;
	private String name;
	private Boolean isParent;
	private Boolean open;
	private String href;
	private String icon;
	private String tabIcon;

	//easyui-Tree需要的属性
	
	private String text;
	private List<TreeNode> children=new ArrayList<>();
	
	
	//zTree的复选树的选中属性
	private Boolean checked;
	
	public TreeNode(Integer id, Integer pid, String name, Boolean isParent,
			Boolean open, String icon, Boolean checked) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.isParent = isParent;
		this.open = open;
		this.icon = icon;
		this.checked = checked;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public TreeNode(Integer id, Integer pid, String text) {
		super();
		this.id = id;
		this.pid = pid;
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> childern) {
		this.children = childern;
	}

	//给index.jsp左边的导航树使用
	public TreeNode(Integer id, Integer pid, String name, Boolean isParent,
			Boolean open, String href, String icon, String tabIcon) {
		super();
		this.id = id;
		this.pid = pid;
		this.name = name;
		this.isParent = isParent;
		this.open = open;
		this.href = href;
		this.icon = icon;
		this.tabIcon = tabIcon;
	}

	

	public TreeNode(Integer id, Integer pid, Boolean isParent, String text) {
		super();
		this.id = id;
		this.pid = pid;
		this.isParent = isParent;
		this.text = text;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getTabIcon() {
		return tabIcon;
	}

	public void setTabIcon(String tabIcon) {
		this.tabIcon = tabIcon;
	}
	
	

}
