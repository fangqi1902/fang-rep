package com.sxt.sys.domain;

import java.io.Serializable;

public class Menu implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer pid;//父节点

    private String name;

    private String href;

    private Integer open;//是否展开

    private Integer parent;//是否有父节点

    private String target;

    private String icon;//Ztree的图标

    private String tabicon;//tab的图标

    private Integer available;//是否可用

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
        this.name = name == null ? null : name.trim();
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href == null ? null : href.trim();
    }

    public Integer getOpen() {
        return open;
    }

    public void setOpen(Integer open) {
        this.open = open;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getTabicon() {
        return tabicon;
    }

    public void setTabicon(String tabicon) {
        this.tabicon = tabicon == null ? null : tabicon.trim();
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

	@Override
	public String toString() {
		return "Menu [id=" + id + ", pid=" + pid + ", name=" + name + ", href=" + href + ", open=" + open + ", parent="
				+ parent + ", target=" + target + ", icon=" + icon + ", tabicon=" + tabicon + ", available=" + available
				+ "]";
	}
    
    
}