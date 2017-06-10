package com.awesome.web.domain.system;

import java.util.List;

import java.io.Serializable;

/**
 * @author adam
 * @ClassName SysModel
 * @Description 模块
 * @create 2017/6/8 18:28
 */
public class SysModel implements Serializable {
    private Long id;
    /** 资源名称 */
    private String name;
    /** 简称 */
    private String abbr;
    /** 描述 */
    private String description;
    /** 父节点 */
    private Long parent;
    /** 排序 */
    private Integer order;
    /** 子节点 */
    private List<SysModel> children;
    /** ztree 中是否展开 */
    private boolean open;

    /***********************************   setter/getter ****************************/
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

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
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

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }
}
