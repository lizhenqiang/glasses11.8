package com.remote.glasses.bean;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.Default;
import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by admin on 2016/7/8.
 */
public class UserInfo {

    /**
     * birthday : 1562515200000
     * sex :
     * phone : 13800138000
     * status : 206
     * idcard : 231111111111111111
     * city : 县
     * Job : 棋圣sddd
     * medicareId : 4888888888888888
     * county : 密云县
     * name : 江流儿
     * Address :
     * province : 北京市
     * qq : 1315433910
     * custRemark :
     */

//    // 指定自增，每个对象需要有一个主键
//    @PrimaryKey(AssignType.AUTO_INCREMENT)
//    @Column("_id")
//    private int id;

    // 默认为true，指定列名
//    @Default("true")

    private long birthday;

    private String sex;

    // 指定自增，每个对象需要有一个主键
    @PrimaryKey(AssignType.BY_MYSELF)
    // 非空字段
    @NotNull
    private String phone;
    private String isShopaddress;
    private String status;
    private String idcard;
    private String city;
    private String Job;
    private String medicareId;
    private String county;
    private String name;
    private String Address;
    private String province;
    private String qq;
    private String custRemark;

    public String getIsShopaddress() {
        return isShopaddress;
    }

    public void setIsShopaddress(String isShopaddress) {
        this.isShopaddress = isShopaddress;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getJob() {
        return Job;
    }

    public void setJob(String Job) {
        this.Job = Job;
    }

    public String getMedicareId() {
        return medicareId;
    }

    public void setMedicareId(String medicareId) {
        this.medicareId = medicareId;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getCustRemark() {
        return custRemark;
    }

    public void setCustRemark(String custRemark) {
        this.custRemark = custRemark;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "_id="+
                ", birthday=" + birthday +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", status='" + status + '\'' +
                ", idcard='" + idcard + '\'' +
                ", city='" + city + '\'' +
                ", Job='" + Job + '\'' +
                ", medicareId='" + medicareId + '\'' +
                ", county='" + county + '\'' +
                ", name='" + name + '\'' +
                ", Address='" + Address + '\'' +
                ", province='" + province + '\'' +
                ", qq='" + qq + '\'' +
                ", custRemark='" + custRemark + '\'' +
                '}';
    }
}
