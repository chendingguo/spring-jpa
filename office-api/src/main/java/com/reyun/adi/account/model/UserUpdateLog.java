package com.reyun.adi.account.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Entity
@Table(name="user_update_log")
public class UserUpdateLog {

    @Id
    @GeneratedValue
    private Long id;

    private String updType;

    private Integer opMan;

    private Date createTime;

    private String preContent;

    private String afterContent;

    public String getPreContent() {
        return preContent;
    }

    public void setPreContent(String preContent) {
        this.preContent = preContent == null ? null : preContent.trim();
    }

    public String getAfterContent() {
        return afterContent;
    }

    public void setAfterContent(String afterContent) {
        this.afterContent = afterContent == null ? null : afterContent.trim();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getUpdType() {
        return updType;
    }

    public void setUpdType(String updType) {
        this.updType = updType == null ? null : updType.trim();
    }

    public Integer getOpMan() {
        return opMan;
    }

    public void setOpMan(Integer opMan) {
        this.opMan = opMan;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}