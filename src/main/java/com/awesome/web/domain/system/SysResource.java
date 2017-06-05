package com.awesome.web.domain.system;

import com.awesome.web.domain.Role;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author adam
 * @ClassName SysResource
 * @Description 资源
 * @create 2017/3/8 14:31
 */
public class SysResource implements Serializable {
    private Long id;

    private String name;

    private String url;

    private Set<Role> roles = new HashSet<>();

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

    public Set<Role> getRoles() {
        return roles;
}

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
