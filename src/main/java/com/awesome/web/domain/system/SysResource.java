package com.awesome.web.domain.system;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author adam
 * @ClassName SysResource
 * @Description 资源
 * @create 2017/3/8 14:31
 */
public class SysResource extends BaseBean{
    private Long id;
    private String name;
    private String url;
    /** 所属模块 的id */
    private Long model;
    /** 资源类型 url 或者menu */
    private String type;
    /** 资源状态 1 有效， 0  无效 */
    private int status;
    private Set<SysRole> roles = new HashSet<>();

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
        this.name = name == null ? null : name.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Long getModel() {
        return model;
    }

    public void setModel(Long model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Set<SysRole> getRoles() {
        return roles;
}

    public void setRoles(Set<SysRole> roles) {
        this.roles = roles;
    }
}
