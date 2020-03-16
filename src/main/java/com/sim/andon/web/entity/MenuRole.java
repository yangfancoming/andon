package com.sim.andon.web.entity;

import com.sim.andon.web.core.jdbc.annotation.Table;

@Table(name = "t_menu_role")
public class MenuRole {
    private String roleId;
    // 菜单名称
    private String menuId;

    private String workshopId;
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getWorkshopId() {
        return workshopId;
    }

    public void setWorkshopId(String workshopId) {
        this.workshopId = workshopId;
    }
}
