package com.awesome.web.domain.system;

import java.util.HashSet;
import java.util.Set;

/**
 * @author adam
 * @ClassName SysRole
 * @Description 系统角色
 * @create 2017/6/3 14:00
 */
public class SysRole extends BaseBean{
    /** system系统内置不可删除 */
    public static final String SYSTEM = "system";
    /** custom自定义的 */
    public static final String CUSTOM = "custom";

    private Long id;
    private String name;
    /** 角色描述 */
    private String description;
    /** 角色类型 system 系统内置不可删除 或者 custom自定义的 */
    private String type;
    /** 角色状态 1 有效， 0  无效 */
    private int status;

    private Set<SysUser> users = new HashSet<>();

    private Set<SysResource> resources = new HashSet<>();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Set<SysUser> getUsers() {
        return users;
    }

    public void setUsers(Set<SysUser> users) {
        this.users = users;
    }

    public Set<SysResource> getResources() {
        return resources;
    }

    public void setResources(Set<SysResource> resources) {
        this.resources = resources;
    }
}
