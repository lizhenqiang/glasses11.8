package com.remote.glasses.bean;

/**
 * Created by Feics on 2016/9/29.
 */
public class OrderWxPayBean {

    /**
     * remoteIp : 127.0.0.1
     * status : 206
     * resultMap : {"appid":"wx9f05d4b2f498916b","code_url":"weixin://wxpay/bizpayurl?pr=4ZXRx2L","mch_id":"1381558002","nonce_str":"lUKtT1qNK29Icfqh","prepay_id":"wx20160929212922bee58e8f220750689499","result_code":"SUCCESS","return_code":"SUCCESS","return_msg":"OK","sign":"A02AB8569895963BD01BD3F3486F86EE","trade_type":"NATIVE"}
     * success : true
     * order_status : true
     */

    private String remoteIp;
    private String status;
    /**
     * appid : wx9f05d4b2f498916b
     * code_url : weixin://wxpay/bizpayurl?pr=4ZXRx2L
     * mch_id : 1381558002
     * nonce_str : lUKtT1qNK29Icfqh
     * prepay_id : wx20160929212922bee58e8f220750689499
     * result_code : SUCCESS
     * return_code : SUCCESS
     * return_msg : OK
     * sign : A02AB8569895963BD01BD3F3486F86EE
     * trade_type : NATIVE
     */

    private ResultMapBean resultMap;
    private String success;
    private String order_status;

    public String getRemoteIp() {
        return remoteIp;
    }

    public void setRemoteIp(String remoteIp) {
        this.remoteIp = remoteIp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ResultMapBean getResultMap() {
        return resultMap;
    }

    public void setResultMap(ResultMapBean resultMap) {
        this.resultMap = resultMap;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public static class ResultMapBean {
        private String appid;
        private String code_url;
        private String mch_id;
        private String nonce_str;
        private String prepay_id;
        private String result_code;
        private String return_code;
        private String return_msg;
        private String sign;
        private String trade_type;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getCode_url() {
            return code_url;
        }

        public void setCode_url(String code_url) {
            this.code_url = code_url;
        }

        public String getMch_id() {
            return mch_id;
        }

        public void setMch_id(String mch_id) {
            this.mch_id = mch_id;
        }

        public String getNonce_str() {
            return nonce_str;
        }

        public void setNonce_str(String nonce_str) {
            this.nonce_str = nonce_str;
        }

        public String getPrepay_id() {
            return prepay_id;
        }

        public void setPrepay_id(String prepay_id) {
            this.prepay_id = prepay_id;
        }

        public String getResult_code() {
            return result_code;
        }

        public void setResult_code(String result_code) {
            this.result_code = result_code;
        }

        public String getReturn_code() {
            return return_code;
        }

        public void setReturn_code(String return_code) {
            this.return_code = return_code;
        }

        public String getReturn_msg() {
            return return_msg;
        }

        public void setReturn_msg(String return_msg) {
            this.return_msg = return_msg;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getTrade_type() {
            return trade_type;
        }

        public void setTrade_type(String trade_type) {
            this.trade_type = trade_type;
        }
    }
}
