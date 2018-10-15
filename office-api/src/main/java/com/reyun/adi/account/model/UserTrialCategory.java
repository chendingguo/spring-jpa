package com.reyun.adi.account.model;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="user_trial_category")
public class UserTrialCategory {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "user_id")
    private Long userId;

    private Integer typeId;

    @Column(name = "cat_id")
    private Long catId;

    private Boolean status;

    @Column(name = "created_time")
    private Date createdTime;

    @Column(name = "modify_time")
    private Date modifyTime;

    private Integer zoneId;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getZoneId() {
        return zoneId;
    }

    public void setZoneId(Integer zoneId) {
        this.zoneId = zoneId;
    }

}