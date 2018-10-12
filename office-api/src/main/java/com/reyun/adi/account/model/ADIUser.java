package com.reyun.adi.account.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class ADIUser implements Serializable{

    private Long id;
    private String email;
    private String password;
    private String name;
    private String company;
    private String phone;
    private String qq;
    private String wechat;
    private Integer status;//是否激活: true-激活
    private Long modifyAccount;
    private Long createAccount;
    private Date modifyTime = new Date();
    private Date createTime;
    private Boolean delFlag;
    private String regIpAddr;
    private String regIpLocation;
    private Long activeTime;//激活时间
    private Boolean whetherCompany;//是否企业用户
    private Boolean onMedia;//是否选择媒体
    private Boolean onTrial;//是否试用账户
    private Date expriedTime;//账户到期时间
    private Boolean closeFloat;//是否已关闭“试用”提示框
    private String packageType;
    private Long bussinessMan;
    private String bussinessManEmail;
    private String mediaList;
    private String area;
    private Date constractStartTime;//合同开始时间
    private String mediaListStr;
    private String expriedTimeStr;
    private String createTimeStr;


    private Boolean sitOn=false;//是否坐席
    private Boolean useStatus=true;//坐席启用
    private Integer sitNum;//坐席账号最大数量

    public String getCreateTimeStr() {

        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getExpriedTimeStr() {
        return expriedTimeStr;
    }

    public void setExpriedTimeStr(String expriedTimeStr) {
        this.expriedTimeStr = expriedTimeStr;
    }

    public String getMediaListStr() {
        return mediaListStr;
    }

    public void setMediaListStr(String mediaListStr) {
        this.mediaListStr = mediaListStr;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBussinessManEmail() {
        return bussinessManEmail;
    }

    public void setBussinessManEmail(String bussinessManEmail) {
        this.bussinessManEmail = bussinessManEmail;
    }

    public String getMediaList() {
        return mediaList;
    }

    public void setMediaList(String mediaList) {
        this.mediaList = mediaList;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getModifyAccount() {
        return modifyAccount;
    }

    public void setModifyAccount(Long modifyAccount) {
        this.modifyAccount = modifyAccount;
    }

    public Long getCreateAccount() {
        return createAccount;
    }

    public void setCreateAccount(Long createAccount) {
        this.createAccount = createAccount;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getRegIpAddr() {
        return regIpAddr;
    }

    public void setRegIpAddr(String regIpAddr) {
        this.regIpAddr = regIpAddr;
    }

    public String getRegIpLocation() {
        return regIpLocation;
    }

    public void setRegIpLocation(String regIpLocation) {
        this.regIpLocation = regIpLocation;
    }

    public Long getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Long activeTime) {
        this.activeTime = activeTime;
    }

    public Boolean getOnMedia() {
        return onMedia;
    }

    public void setOnMedia(Boolean onMedia) {
        this.onMedia = onMedia;
    }

    public Boolean getOnTrial() {
        return onTrial;
    }

    public void setOnTrial(Boolean onTrial) {
        this.onTrial = onTrial;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getExpriedTime() {
        return expriedTime;
    }

    public void setExpriedTime(Date expriedTime) {
        this.expriedTime = expriedTime;
    }

    public Boolean getWhetherCompany() {
        return whetherCompany;
    }

    public void setWhetherCompany(Boolean whetherCompany) {
        this.whetherCompany = whetherCompany;
    }

    public Boolean getCloseFloat() {
        return closeFloat;
    }

    public void setCloseFloat(Boolean closeFloat) {
        this.closeFloat = closeFloat;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public Long getBussinessMan() {
        return bussinessMan;
    }

    public void setBussinessMan(Long bussinessMan) {
        this.bussinessMan = bussinessMan;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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

    @Override
    public String toString() {
        return "ADIUser{" +
            "id=" + id +
            ", email='" + email + '\'' +
            ", password='" + password + '\'' +
            ", name='" + name + '\'' +
            ", company='" + company + '\'' +
            ", phone='" + phone + '\'' +
            ", qq='" + qq + '\'' +
            ", wechat='" + wechat + '\'' +
            ", status=" + status +
            ", modifyAccount=" + modifyAccount +
            ", createAccount=" + createAccount +
            ", modifyTime=" + modifyTime +
            ", createTime=" + createTime +
            ", delFlag=" + delFlag +
            ", regIpAddr='" + regIpAddr + '\'' +
            ", regIpLocation='" + regIpLocation + '\'' +
            ", activeTime=" + activeTime +
            ", whetherCompany=" + whetherCompany +
            ", onMedia=" + onMedia +
            ", onTrial=" + onTrial +
            ", expriedTime=" + expriedTime +
            ", closeFloat=" + closeFloat +
            ", packageType='" + packageType + '\'' +
            ", bussinessMan=" + bussinessMan +
            ", mediaList=" + mediaList +
            '}';
    }
}
