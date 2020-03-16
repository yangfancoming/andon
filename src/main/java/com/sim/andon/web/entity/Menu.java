package com.sim.andon.web.entity;

import com.sim.andon.web.core.jdbc.annotation.Key;
import com.sim.andon.web.core.jdbc.annotation.Table;

import java.util.List;

@Table(name = "t_menu")
public class Menu implements Comparable<Menu>{
    // 菜单id
    @Key
    private int id;
    // 菜单名称
    private String name;
    // 父菜单id
    private String parentId;
    // 菜单url
    private String href;
    // 菜单图标
    private String icon;
    // 菜单顺序
    private int menuOrder;
    private String selectId;
    // 子菜单
    private List<Menu> childMenus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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


    public String getSelectId() {
        return selectId;
    }

    public void setSelectId(String selectId) {
        this.selectId = selectId;
    }

    public List<Menu> getChildMenus() {
        return childMenus;
    }

    public void setChildMenus(List<Menu> childMenus) {
        this.childMenus = childMenus;
    }

    @Override
    public int compareTo(Menu o) {
        int i = this.menuOrder - o.getMenuOrder();//先按照年龄排序

       return i;
    }

	public int getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(int menuOrder) {
		this.menuOrder = menuOrder;
	}
}
