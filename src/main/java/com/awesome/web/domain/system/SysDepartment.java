package com.awesome.web.domain.system;

import java.util.List;

/**
 * @author adam
 * @ClassName SysDepartment
 * @Description 系统部门Bean
 * @create 2017/6/11 11:01
 */
public class SysDepartment extends BaseBean {
    private Long id;
    private String name;
    private String description;
    private Long parent;
    /** 部门层级 */
    private Integer level;
    /** 状态 1 有效； 0 无效 */
    private Integer status;
    /** 排序 */
    private Integer order;
    /** 子节点 */
    private List<SysModel> children;
    /** ztree 中是否展开 */
    private Boolean open;

    /** 树图标 */
    private String iconSkin="department_icon";

    /*****************************************setter/getter**************************************/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public List<SysModel> getChildren() {
        return children;
    }

    public void setChildren(List<SysModel> children) {
        this.children = children;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public String getIconSkin() {
        return iconSkin;
    }

    public void setIconSkin(String iconSkin) {
        this.iconSkin = iconSkin;
    }
}
