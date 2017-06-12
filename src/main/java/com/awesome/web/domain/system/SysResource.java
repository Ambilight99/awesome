package com.awesome.web.domain.system;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.Set;

/**
 * @author adam
 * @ClassName SysResource
 * @Description 资源
 * @create 2017/3/8 14:31
 */
public class SysResource extends BaseBean{
    public final static String URL = "url";
    public final static String MENU = "menu";

    private Long id;
    private String name;
    private String url;
    /** 所属模块 的id */
    private Long model;
    /** 资源类型 url 或者menu */
    private String type;
    /** 资源状态 1 有效， 0  无效 */
    private Integer status;
    /** 所属模块 的名称 */
    private String modelName;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Set<SysRole> getRoles() {
        return roles;
}

    public void setRoles(Set<SysRole> roles) {
        this.roles = roles;
    }
}
