package com.awesome.web.domain.system;

import com.awesome.web.domain.Resource;
import com.awesome.web.domain.User;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author adam
 * @ClassName SysRole
 * @Description 系统角色
 * @create 2017/6/3 14:00
 */
public class SysRole implements Serializable{
    private Long id;
    private String name;

    private Set<User> users = new HashSet<>();

    private Set<Resource> resources = new HashSet<>();


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
