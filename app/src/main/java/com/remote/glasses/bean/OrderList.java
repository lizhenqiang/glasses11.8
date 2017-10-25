package com.remote.glasses.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2016/7/11.
 */
public class OrderList implements Serializable{


    /**
     * pagesize : 20
     * status : 206
     * cpage : 1
     * lists : [{"id":"ae41c88e2a304e54b6a0d014874ad7a2","isNewRecord":false,"createDate":"2016-08-01 13:47:02","updateDate":"2016-08-01 13:47:02","orderid":"1470030422","custName":"Fvg","idCard":"","sex":"","phone":"18354555555","job":"","province":"安徽省","city":"安庆市","county":"枞阳县","address":"双桥","bithday":"2016-08-01 00:00:00","qq":"","medicareId":"","custRemark":"","rightS":-0.25,"leftS":-1,"rightC":-3,"leftC":-1,"rightA":130,"leftA":143,"rightP":88,"leftP":55,"rightB":"上","leftB":"右","rightAdd":50,"leftAdd":50,"rightPd":20,"leftPd":20,"rightEye":0.1,"leftEye":0.1,"rightOld":0.1,"leftOld":0.1,"rightFix":0.1,"leftFix":0.1,"crightS":0,"cleftS":0,"crightC":0,"cleftC":0,"crightA":0,"cleftA":0,"crightP":0,"cleftP":0,"crightB":"","cleftB":"","crightAdd":0,"cleftAdd":0,"crightPd":0,"cleftPd":0,"crightEye":0,"cleftEye":0,"crightOld":0,"cleftOld":0,"crightFix":0,"cleftFix":0,"useType":"1","glassType":"2","optometryFee":105855,"goodBrand":"镜宴","goodCategory":"光学","goodModel":"CVAS53O216BR-LCPBECE","goodColor":"棕色","goodPackage":"镜框 1.60双抗防护片","goodNum":1,"fee":498,"ratesFee":398,"goodRemark":"","goodUserId":"42","shopid":"5ba167ed68fc4bfd817d003b63a8b03c","otherFee":0,"totalFee":398,"payType":"3","payFlag":"2","office":{"id":"5ba167ed68fc4bfd817d003b63a8b03c","isNewRecord":false,"name":"一心堂双桥店","sort":30,"type":"2","address":"双桥","dispensingCode":"1005","phone":"","parentId":"0"},"isSend":2}]
     */

    private int pagesize;
    private String status;
    private int cpage;
    /**
     * id : ae41c88e2a304e54b6a0d014874ad7a2
     * isNewRecord : false
     * createDate : 2016-08-01 13:47:02
     * updateDate : 2016-08-01 13:47:02
     * orderid : 1470030422
     * custName : Fvg
     * idCard :
     * sex :
     * phone : 18354555555
     * job :
     * province : 安徽省
     * city : 安庆市
     * county : 枞阳县
     * address : 双桥
     * bithday : 2016-08-01 00:00:00
     * qq :
     * medicareId :
     * custRemark :
     * rightS : -0.25
     * leftS : -1
     * rightC : -3
     * leftC : -1
     * rightA : 130
     * leftA : 143
     * rightP : 88
     * leftP : 55
     * rightB : 上
     * leftB : 右
     * rightAdd : 50
     * leftAdd : 50
     * rightPd : 20
     * leftPd : 20
     * rightEye : 0.1
     * leftEye : 0.1
     * rightOld : 0.1
     * leftOld : 0.1
     * rightFix : 0.1
     * leftFix : 0.1
     * crightS : 0
     * cleftS : 0
     * crightC : 0
     * cleftC : 0
     * crightA : 0
     * cleftA : 0
     * crightP : 0
     * cleftP : 0
     * crightB :
     * cleftB :
     * crightAdd : 0
     * cleftAdd : 0
     * crightPd : 0
     * cleftPd : 0
     * crightEye : 0
     * cleftEye : 0
     * crightOld : 0
     * cleftOld : 0
     * crightFix : 0
     * cleftFix : 0
     * useType : 1
     * glassType : 2
     * optometryFee : 105855
     * goodBrand : 镜宴
     * goodCategory : 光学
     * goodModel : CVAS53O216BR-LCPBECE
     * goodColor : 棕色
     * goodPackage : 镜框 1.60双抗防护片
     * goodNum : 1
     * fee : 498
     * ratesFee : 398
     * goodRemark :
     * goodUserId : 42
     * shopid : 5ba167ed68fc4bfd817d003b63a8b03c
     * otherFee : 0
     * totalFee : 398
     * payType : 3
     * payFlag : 2
     * office : {"id":"5ba167ed68fc4bfd817d003b63a8b03c","isNewRecord":false,"name":"一心堂双桥店","sort":30,"type":"2","address":"双桥","dispensingCode":"1005","phone":"","parentId":"0"}
     * isSend : 2
     */

    private List<ListsBean> lists;

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCpage() {
        return cpage;
    }

    public void setCpage(int cpage) {
        this.cpage = cpage;
    }

    public List<ListsBean> getLists() {
        return lists;
    }

    public void setLists(List<ListsBean> lists) {
        this.lists = lists;
    }

    public static class ListsBean implements Serializable{
        private String id;
        private boolean isNewRecord;
        private String createDate;
        private String updateDate;
        private String orderid;
        private String custName;
        private String idCard;
        private String sex;
        private String phone;
        private String job;
        private String province;
        private String city;
        private String county;
        private String address;
        private String bithday;
        private String qq;
        private String medicareId;
        private String custRemark;
        private String rightS;
        private String leftS;
        private String rightC;
        private String leftC;
        private String rightA;
        private String leftA;
        private String rightP;
        private String leftP;
        private String rightB;
        private String leftB;
        private String rightAdd;
        private String leftAdd;
        private String rightPd;
        private String leftPd;
        private String rightEye;
        private String leftEye;
        private String rightOld;
        private String leftOld;
        private String rightFix;
        private String leftFix;
        private String crightS;
        private String cleftS;
        private String crightC;
        private String cleftC;
        private String crightA;
        private String cleftA;
        private String crightP;
        private String cleftP;
        private String crightB;
        private String cleftB;
        private String crightAdd;
        private String cleftAdd;
        private String crightPd;
        private String cleftPd;
        private String crightEye;
        private String cleftEye;
        private String crightOld;
        private String cleftOld;
        private String crightFix;
        private String cleftFix;
        private String useType;
        private String glassType;
        private String goodBrand;
        private String goodCategory;
        private String goodModel;
        private String goodColor;
        private String goodPackage;
        private int goodNum;
        private double fee;
        private double ratesFee;
        private String goodRemark;
        private String goodUserId;
        private String shopid;
        private double otherFee;
        private double totalFee;
        private String payType;
        private String payFlag;
        private double optometryFee;
        private String orderType;

        public void setNewRecord(boolean newRecord) {
            isNewRecord = newRecord;
        }

        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }

        /**
         * id : 5ba167ed68fc4bfd817d003b63a8b03c
         * isNewRecord : false
         * name : 一心堂双桥店
         * sort : 30
         * type : 2
         * address : 双桥
         * dispensingCode : 1005
         * phone :
         * parentId : 0
         */

        private OfficeBean office;
        private int isSend;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isIsNewRecord() {
            return isNewRecord;
        }

        public void setIsNewRecord(boolean isNewRecord) {
            this.isNewRecord = isNewRecord;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getCustName() {
            return custName;
        }

        public void setCustName(String custName) {
            this.custName = custName;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
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

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCounty() {
            return county;
        }

        public void setCounty(String county) {
            this.county = county;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getBithday() {
            return bithday;
        }

        public void setBithday(String bithday) {
            this.bithday = bithday;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getMedicareId() {
            return medicareId;
        }

        public void setMedicareId(String medicareId) {
            this.medicareId = medicareId;
        }

        public String getCustRemark() {
            return custRemark;
        }

        public void setCustRemark(String custRemark) {
            this.custRemark = custRemark;
        }

        public String getRightS() {
            return rightS;
        }

        public void setRightS(String rightS) {
            this.rightS = rightS;
        }

        public String getLeftS() {
            return leftS;
        }

        public void setLeftS(String leftS) {
            this.leftS = leftS;
        }

        public String getRightC() {
            return rightC;
        }

        public void setRightC(String rightC) {
            this.rightC = rightC;
        }

        public String getLeftC() {
            return leftC;
        }

        public void setLeftC(String leftC) {
            this.leftC = leftC;
        }

        public String getRightA() {
            return rightA;
        }

        public void setRightA(String rightA) {
            this.rightA = rightA;
        }

        public String getLeftA() {
            return leftA;
        }

        public void setLeftA(String leftA) {
            this.leftA = leftA;
        }

        public String getRightP() {
            return rightP;
        }

        public void setRightP(String rightP) {
            this.rightP = rightP;
        }

        public String getLeftP() {
            return leftP;
        }

        public void setLeftP(String leftP) {
            this.leftP = leftP;
        }

        public String getRightB() {
            return rightB;
        }

        public void setRightB(String rightB) {
            this.rightB = rightB;
        }

        public String getLeftB() {
            return leftB;
        }

        public void setLeftB(String leftB) {
            this.leftB = leftB;
        }

        public String getRightAdd() {
            return rightAdd;
        }

        public void setRightAdd(String rightAdd) {
            this.rightAdd = rightAdd;
        }

        public String getLeftAdd() {
            return leftAdd;
        }

        public void setLeftAdd(String leftAdd) {
            this.leftAdd = leftAdd;
        }

        public String getRightPd() {
            return rightPd;
        }

        public void setRightPd(String rightPd) {
            this.rightPd = rightPd;
        }

        public String getLeftPd() {
            return leftPd;
        }

        public void setLeftPd(String leftPd) {
            this.leftPd = leftPd;
        }

        public String getRightEye() {
            return rightEye;
        }

        public void setRightEye(String rightEye) {
            this.rightEye = rightEye;
        }

        public String getLeftEye() {
            return leftEye;
        }

        public void setLeftEye(String leftEye) {
            this.leftEye = leftEye;
        }

        public String getRightOld() {
            return rightOld;
        }

        public void setRightOld(String rightOld) {
            this.rightOld = rightOld;
        }

        public String getLeftOld() {
            return leftOld;
        }

        public void setLeftOld(String leftOld) {
            this.leftOld = leftOld;
        }

        public String getRightFix() {
            return rightFix;
        }

        public void setRightFix(String rightFix) {
            this.rightFix = rightFix;
        }

        public String getLeftFix() {
            return leftFix;
        }

        public void setLeftFix(String leftFix) {
            this.leftFix = leftFix;
        }

        public String getCrightS() {
            return crightS;
        }

        public void setCrightS(String crightS) {
            this.crightS = crightS;
        }

        public String getCleftS() {
            return cleftS;
        }

        public void setCleftS(String cleftS) {
            this.cleftS = cleftS;
        }

        public String getCrightC() {
            return crightC;
        }

        public void setCrightC(String crightC) {
            this.crightC = crightC;
        }

        public String getCleftC() {
            return cleftC;
        }

        public void setCleftC(String cleftC) {
            this.cleftC = cleftC;
        }

        public String getCrightA() {
            return crightA;
        }

        public void setCrightA(String crightA) {
            this.crightA = crightA;
        }

        public String getCleftA() {
            return cleftA;
        }

        public void setCleftA(String cleftA) {
            this.cleftA = cleftA;
        }

        public String getCrightP() {
            return crightP;
        }

        public void setCrightP(String crightP) {
            this.crightP = crightP;
        }

        public String getCleftP() {
            return cleftP;
        }

        public void setCleftP(String cleftP) {
            this.cleftP = cleftP;
        }

        public String getCrightB() {
            return crightB;
        }

        public void setCrightB(String crightB) {
            this.crightB = crightB;
        }

        public String getCleftB() {
            return cleftB;
        }

        public void setCleftB(String cleftB) {
            this.cleftB = cleftB;
        }

        public String getCrightAdd() {
            return crightAdd;
        }

        public void setCrightAdd(String crightAdd) {
            this.crightAdd = crightAdd;
        }

        public String getCleftAdd() {
            return cleftAdd;
        }

        public void setCleftAdd(String cleftAdd) {
            this.cleftAdd = cleftAdd;
        }

        public String getCrightPd() {
            return crightPd;
        }

        public void setCrightPd(String crightPd) {
            this.crightPd = crightPd;
        }

        public String getCleftPd() {
            return cleftPd;
        }

        public void setCleftPd(String cleftPd) {
            this.cleftPd = cleftPd;
        }

        public String getCrightEye() {
            return crightEye;
        }

        public void setCrightEye(String crightEye) {
            this.crightEye = crightEye;
        }

        public String getCleftEye() {
            return cleftEye;
        }

        public void setCleftEye(String cleftEye) {
            this.cleftEye = cleftEye;
        }

        public String getCrightOld() {
            return crightOld;
        }

        public void setCrightOld(String crightOld) {
            this.crightOld = crightOld;
        }

        public String getCleftOld() {
            return cleftOld;
        }

        public void setCleftOld(String cleftOld) {
            this.cleftOld = cleftOld;
        }

        public String getCrightFix() {
            return crightFix;
        }

        public void setCrightFix(String crightFix) {
            this.crightFix = crightFix;
        }

        public String getCleftFix() {
            return cleftFix;
        }

        public void setCleftFix(String cleftFix) {
            this.cleftFix = cleftFix;
        }

        public String getUseType() {
            return useType;
        }

        public void setUseType(String useType) {
            this.useType = useType;
        }

        public String getGlassType() {
            return glassType;
        }

        public void setGlassType(String glassType) {
            this.glassType = glassType;
        }

        public double getOptometryFee() {
            return optometryFee;
        }

        public void setOptometryFee(double optometryFee) {
            this.optometryFee = optometryFee;
        }

        public String getGoodBrand() {
            return goodBrand;
        }

        public void setGoodBrand(String goodBrand) {
            this.goodBrand = goodBrand;
        }

        public String getGoodCategory() {
            return goodCategory;
        }

        public void setGoodCategory(String goodCategory) {
            this.goodCategory = goodCategory;
        }

        public String getGoodModel() {
            return goodModel;
        }

        public void setGoodModel(String goodModel) {
            this.goodModel = goodModel;
        }

        public String getGoodColor() {
            return goodColor;
        }

        public void setGoodColor(String goodColor) {
            this.goodColor = goodColor;
        }

        public String getGoodPackage() {
            return goodPackage;
        }

        public void setGoodPackage(String goodPackage) {
            this.goodPackage = goodPackage;
        }

        public int getGoodNum() {
            return goodNum;
        }

        public void setGoodNum(int goodNum) {
            this.goodNum = goodNum;
        }

        public double getFee() {
            return fee;
        }

        public void setFee(int fee) {
            this.fee = fee;
        }

        public double getRatesFee() {
            return ratesFee;
        }

        public void setRatesFee(int ratesFee) {
            this.ratesFee = ratesFee;
        }

        public String getGoodRemark() {
            return goodRemark;
        }

        public void setGoodRemark(String goodRemark) {
            this.goodRemark = goodRemark;
        }

        public String getGoodUserId() {
            return goodUserId;
        }

        public void setGoodUserId(String goodUserId) {
            this.goodUserId = goodUserId;
        }

        public String getShopid() {
            return shopid;
        }

        public void setShopid(String shopid) {
            this.shopid = shopid;
        }

        public double getOtherFee() {
            return otherFee;
        }

        public void setOtherFee(int otherFee) {
            this.otherFee = otherFee;
        }

        public double getTotalFee() {
            return totalFee;
        }

        public void setTotalFee(int totalFee) {
            this.totalFee = totalFee;
        }

        public String getPayType() {
            return payType;
        }

        public void setPayType(String payType) {
            this.payType = payType;
        }

        public String getPayFlag() {
            return payFlag;
        }

        public void setPayFlag(String payFlag) {
            this.payFlag = payFlag;
        }

        public OfficeBean getOffice() {
            return office;
        }

        public void setOffice(OfficeBean office) {
            this.office = office;
        }

        public int getIsSend() {
            return isSend;
        }

        public void setIsSend(int isSend) {
            this.isSend = isSend;
        }

        public static class OfficeBean implements Serializable{
            private String id;
            private boolean isNewRecord;
            private String name;
            private int sort;
            private String type;
            private String address;
            private String dispensingCode;
            private String phone;
            private String parentId;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public boolean isIsNewRecord() {
                return isNewRecord;
            }

            public void setIsNewRecord(boolean isNewRecord) {
                this.isNewRecord = isNewRecord;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getDispensingCode() {
                return dispensingCode;
            }

            public void setDispensingCode(String dispensingCode) {
                this.dispensingCode = dispensingCode;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getParentId() {
                return parentId;
            }

            public void setParentId(String parentId) {
                this.parentId = parentId;
            }
        }
    }
}
