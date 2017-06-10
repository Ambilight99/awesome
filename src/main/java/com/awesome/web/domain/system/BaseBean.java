package com.awesome.web.domain.system;

import java.io.Serializable;
import java.util.Date;

/**
 * @author adam
 * @ClassName BaseBean
 * @Description 基础bean
 * @create 2017/6/8 14:31
 */
public class BaseBean implements Serializable {
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 更新时间
     */
    private Date updateDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
