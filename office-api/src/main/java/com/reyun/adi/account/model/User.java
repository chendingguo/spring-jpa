package com.reyun.adi.account.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Date;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private Long activeTime;

    private String company;

    private Long createAccount;

    private Date createTime;

    private Boolean delFlag;

    private String email;

    private Long modifyAccount;

    private Date modifyTime;

    private String name;


    private String password;

    private String phone;

    private String qq;

    private String regIpAddr;

    private String regIpLocation;

    private Integer status;

    private String wechat;

    private Boolean whetherCompany;

    private Boolean onMedia;

    private Integer onTrial;

    private Date expriedTime;

    private Boolean closeFloat;

    private Date constractStartTime;

    private Boolean sitOn;

    private Boolean useStatus;

    private Integer sitNum;


    private String passwd;

    private Integer zoneId;

    private Date turnTime;

    @Transient
    private String statusStr;

    @Transient
    private String wheterCompanyStr;

    @Transient
    private String onTrialStr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Long activeTime) {
        this.activeTime = activeTime;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public Long getCreateAccount() {
        return createAccount;
    }

    public void setCreateAccount(Long createAccount) {
        this.createAccount = createAccount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Long getModifyAccount() {
        return modifyAccount;
    }

    public void setModifyAccount(Long modifyAccount) {
        this.modifyAccount = modifyAccount;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getRegIpAddr() {
        return regIpAddr;
    }

    public void setRegIpAddr(String regIpAddr) {
        this.regIpAddr = regIpAddr == null ? null : regIpAddr.trim();
    }

    public String getRegIpLocation() {
        return regIpLocation;
    }

    public void setRegIpLocation(String regIpLocation) {
        this.regIpLocation = regIpLocation == null ? null : regIpLocation.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat == null ? null : wechat.trim();
    }

    public Boolean getWhetherCompany() {
        return whetherCompany;
    }

    public void setWhetherCompany(Boolean whetherCompany) {
        this.whetherCompany = whetherCompany;
    }

    public Boolean getOnMedia() {
        return onMedia;
    }

    public void setOnMedia(Boolean onMedia) {
        this.onMedia = onMedia;
    }

    public Integer getOnTrial() {
        return onTrial;
    }

    public void setOnTrial(Integer onTrial) {
        this.onTrial = onTrial;
    }
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public Date getExpriedTime() {
        return expriedTime;
    }

    public void setExpriedTime(Date expriedTime) {
        this.expriedTime = expriedTime;
    }

    public Boolean getCloseFloat() {
        return closeFloat;
    }

    public void setCloseFloat(Boolean closeFloat) {
        this.closeFloat = closeFloat;
    }

    public Date getConstractStartTime() {
        return constractStartTime;
    }

    public void setConstractStartTime(Date constractStartTime) {
        this.constractStartTime = constractStartTime;
    }

    public Boolean getSitOn() {
        return sitOn;
    }

    public void setSitOn(Boolean sitOn) {
        this.sitOn = sitOn;
    }

    public Boolean getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Boolean useStatus) {
        this.useStatus = useStatus;
    }

    public Integer getSitNum() {
        return sitNum;
    }

    public void setSitNum(Integer sitNum) {
        this.sitNum = sitNum;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    public Integer getZoneId() {
        return zoneId;
    }

    public void setZoneId(Integer zoneId) {
        this.zoneId = zoneId;
    }

    public Date getTurnTime() {
        return turnTime;
    }

    public void setTurnTime(Date turnTime) {
        this.turnTime = turnTime;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getWheterCompanyStr() {
        return wheterCompanyStr;
    }

    public void setWheterCompanyStr(String wheterCompanyStr) {
        this.wheterCompanyStr = wheterCompanyStr;
    }

    public String getOnTrialStr() {
        return onTrialStr;
    }

    public void setOnTrialStr(String onTrialStr) {
        this.onTrialStr = onTrialStr;
    }
}