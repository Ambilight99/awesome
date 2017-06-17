package com.awesome.web.domain.system;

import java.io.Serializable;

public class SysUserModel implements Serializable {

    private Long userId;

    private Long modelId;

    public SysUserModel(){

    }

    public SysUserModel(Long userId, Long modelId) {
        this.userId = userId;
        this.modelId = modelId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }
}