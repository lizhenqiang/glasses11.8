package com.remote.glasses.bean;

import com.litesuits.orm.db.annotation.NotNull;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by Feics on 2016/7/11.
 */
public class OptometryBean {
    // 每个对象需要有一个主键
    @PrimaryKey(AssignType.BY_MYSELF)
    // 非空字段
    @NotNull
    private String phone;

    private String rightS;
    private String leftS;
    private String rightC;
    private String leftC;
    private String rightA;
    private String leftA;
    private String rightAdd;
    private String leftAdd;
    private String leftPd;
    private String rightPd;
    private String rightP;
    private String leftP;
    private String rightB;
    private String leftB;
    private String rightL;
    private String leftL;
    private String rightJ;
    private String leftJ;
    private String rightZ;
    private String leftZ;

    private String JrightS;
    private String JleftS;
    private String JrightC;
    private String JleftC;
    private String JrightA;
    private String JleftA;
    private String JrightAdd;
    private String JleftAdd;
    private String JleftPd;
    private String JrightPd;
    private String JrightP;
    private String JleftP;
    private String JrightB;
    private String JleftB;
    private String JrightL;
    private String JleftL;
    private String JrightJ;
    private String JleftJ;
    private String JrightZ;
    private String JleftZ;

    private String typeDistance;
    private String type;
    private String beizhu;


    private String optometryFee;
//    private String correctFee;

//    public String getCorrectFee() {
//        return correctFee;
//    }
//
//    public void setCorrectFee(String correctFee) {
//        this.correctFee = correctFee;
//    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu;
    }

    public String getJleftA() {
        return JleftA;
    }

    public void setJleftA(String jleftA) {
        JleftA = jleftA;
    }

    public String getJleftAdd() {
        return JleftAdd;
    }

    public void setJleftAdd(String jleftAdd) {
        JleftAdd = jleftAdd;
    }

    public String getJleftB() {
        return JleftB;
    }

    public void setJleftB(String jleftB) {
        JleftB = jleftB;
    }

    public String getJleftC() {
        return JleftC;
    }

    public void setJleftC(String jleftC) {
        JleftC = jleftC;
    }

    public String getJleftJ() {
        return JleftJ;
    }

    public void setJleftJ(String jleftJ) {
        JleftJ = jleftJ;
    }

    public String getJleftL() {
        return JleftL;
    }

    public void setJleftL(String jleftL) {
        JleftL = jleftL;
    }

    public String getJleftP() {
        return JleftP;
    }

    public void setJleftP(String jleftP) {
        JleftP = jleftP;
    }

    public String getJleftPd() {
        return JleftPd;
    }

    public void setJleftPd(String jleftPd) {
        JleftPd = jleftPd;
    }

    public String getJleftS() {
        return JleftS;
    }

    public void setJleftS(String jleftS) {
        JleftS = jleftS;
    }

    public String getJleftZ() {
        return JleftZ;
    }

    public void setJleftZ(String jleftZ) {
        JleftZ = jleftZ;
    }

    public String getJrightA() {
        return JrightA;
    }

    public void setJrightA(String jrightA) {
        JrightA = jrightA;
    }

    public String getJrightAdd() {
        return JrightAdd;
    }

    public void setJrightAdd(String jrightAdd) {
        JrightAdd = jrightAdd;
    }

    public String getJrightB() {
        return JrightB;
    }

    public void setJrightB(String jrightB) {
        JrightB = jrightB;
    }

    public String getJrightC() {
        return JrightC;
    }

    public void setJrightC(String jrightC) {
        JrightC = jrightC;
    }

    public String getJrightJ() {
        return JrightJ;
    }

    public void setJrightJ(String jrightJ) {
        JrightJ = jrightJ;
    }

    public String getJrightL() {
        return JrightL;
    }

    public void setJrightL(String jrightL) {
        JrightL = jrightL;
    }

    public String getJrightP() {
        return JrightP;
    }

    public void setJrightP(String jrightP) {
        JrightP = jrightP;
    }

    public String getJrightPd() {
        return JrightPd;
    }

    public void setJrightPd(String jrightPd) {
        JrightPd = jrightPd;
    }

    public String getJrightS() {
        return JrightS;
    }

    public void setJrightS(String jrightS) {
        JrightS = jrightS;
    }

    public String getJrightZ() {
        return JrightZ;
    }

    public void setJrightZ(String jrightZ) {
        JrightZ = jrightZ;
    }

    public String getLeftB() {
        return leftB;
    }

    public void setLeftB(String leftB) {
        this.leftB = leftB;
    }

    public String getLeftJ() {
        return leftJ;
    }

    public void setLeftJ(String leftJ) {
        this.leftJ = leftJ;
    }

    public String getLeftL() {
        return leftL;
    }

    public void setLeftL(String leftL) {
        this.leftL = leftL;
    }

    public String getLeftP() {
        return leftP;
    }

    public void setLeftP(String leftP) {
        this.leftP = leftP;
    }

    public String getLeftZ() {
        return leftZ;
    }

    public void setLeftZ(String leftZ) {
        this.leftZ = leftZ;
    }

    public String getRightB() {
        return rightB;
    }

    public void setRightB(String rightB) {
        this.rightB = rightB;
    }

    public String getRightJ() {
        return rightJ;
    }

    public void setRightJ(String rightJ) {
        this.rightJ = rightJ;
    }

    public String getRightL() {
        return rightL;
    }

    public void setRightL(String rightL) {
        this.rightL = rightL;
    }

    public String getRightP() {
        return rightP;
    }

    public void setRightP(String rightP) {
        this.rightP = rightP;
    }

    public String getRightZ() {
        return rightZ;
    }

    public void setRightZ(String rightZ) {
        this.rightZ = rightZ;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeDistance() {
        return typeDistance;
    }

    public void setTypeDistance(String typeDistance) {
        this.typeDistance = typeDistance;
    }

    public String getLeftA() {
        return leftA;
    }

    public void setLeftA(String leftA) {
        this.leftA = leftA;
    }

    public String getLeftAdd() {
        return leftAdd;
    }

    public void setLeftAdd(String leftAdd) {
        this.leftAdd = leftAdd;
    }

    public String getLeftC() {
        return leftC;
    }

    public void setLeftC(String leftC) {
        this.leftC = leftC;
    }

    public String getLeftPd() {
        return leftPd;
    }

    public void setLeftPd(String leftPd) {
        this.leftPd = leftPd;
    }

    public String getLeftS() {
        return leftS;
    }

    public void setLeftS(String leftS) {
        this.leftS = leftS;
    }

    public String getOptometryFee() {
        return optometryFee;
    }

    public void setOptometryFee(String optometryFee) {
        this.optometryFee = optometryFee;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRightA() {
        return rightA;
    }

    public void setRightA(String rightA) {
        this.rightA = rightA;
    }

    public String getRightAdd() {
        return rightAdd;
    }

    public void setRightAdd(String rightAdd) {
        this.rightAdd = rightAdd;
    }

    public String getRightC() {
        return rightC;
    }

    public void setRightC(String rightC) {
        this.rightC = rightC;
    }

    public String getRightPd() {
        return rightPd;
    }

    public void setRightPd(String rightPd) {
        this.rightPd = rightPd;
    }

    public String getRightS() {
        return rightS;
    }

    public void setRightS(String rightS) {
        this.rightS = rightS;
    }
}
