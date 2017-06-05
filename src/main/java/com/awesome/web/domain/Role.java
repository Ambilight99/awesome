package com.awesome.web.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author adam
 * @ClassName Role
 * @Description 角色
 * @create 2017/3/8 14:31
 */
public class Role implements Serializable{
    private Integer id;

    private String name;

    private Set<User> users = new HashSet<>();

    private Set<Resource> resources = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Set<User> getUsers() {
        return users;
}

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Resource> getResources() {
        return resources;
    }

    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }
}
