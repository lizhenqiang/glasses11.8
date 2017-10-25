package com.remote.glasses.bean;

import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by admin on 2016/7/8.
 */
public class GlassesBean {


//    // 指定自增，每个对象需要有一个主键
//    @PrimaryKey(AssignType.AUTO_INCREMENT)
//    @Column("_id")
//    private int id;

    // 默认为true，指定列名
//    @Default("true")

// 每个对象需要有一个主键
    @PrimaryKey(AssignType.BY_MYSELF)
    // 非空字段
    @NotNull
    private String phone;

    private String brand;
    private String category;
    private String model;
    private String goodPackage;
    private int amount;
    private String fee;
    private String ratefee;
    private String color;
    private String userId;
    private String type;
    private String vto_frame_id;
    private String arlensid;

    public String getArlensid() {
        return arlensid;
    }

    public void setArlensid(String arlensid) {
        this.arlensid = arlensid;
    }

    public String getType() {
        return type;
    }

    public String getVto_frame_id() {
        return vto_frame_id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setVto_frame_id(String vto_frame_id) {
        this.vto_frame_id = vto_frame_id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getGoodPackage() {
        return goodPackage;
    }

    public void setGoodPackage(String goodPackage) {
        this.goodPackage = goodPackage;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRatefee() {
        return ratefee;
    }

    public void setRatefee(String ratefee) {
        this.ratefee = ratefee;
    }
}
