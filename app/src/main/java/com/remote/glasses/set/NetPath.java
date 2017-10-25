package com.remote.glasses.set;

/**
 * Created by admin on 2016/7/6.
 */
public class NetPath {
    //验光 123.57.149.121
//    private static final String PATH = "http://www.yijava.com:8080/xinfu/web/";
    private static final String PATH = "http://buy1001.cn/xinfu/web/";
    //private static final String PATH = "http://192.168.0.42/xinfu/web/";
    /**
     * 用户登陆接口
     *  请求 authstr
     *  设备 id deviceid
     *  username=??&passwd=?? 对 称 加密之后为 authstr
     */
    public static String LOGIN = PATH+"app/api/applogin.html";

    /**
     * 查询用户接口
     * phone token 必传
     */
    public static String QUERY_USER = PATH+"app/api/cust/query.html";

    /**
     * 提交订单接口
     */
    public static String SUBMIT_ORDER = PATH+"app/api/order/pushv2.html";

    /**
     *  查询历史订单接口
     */
    public static String QUERY_ORDER = PATH+"app/api/order/query.html";

    /**
     * 增加临时保存的验光记录
     */
    public static String ADD_YANGUANG_HISTORY = PATH+"app/api/light_history/push.html";

    /**
     * 查询已有的验光记录
     */
    public static String QUERY_YANGUANG_HISTORY = PATH+"app/api/light_history /query.html";

    /**
     * 更新接口
     */
    public static String UPDATE_APP = PATH+"app/api/apk/query.html";
    /**
     * 更新接口
     */
    public static String GET_APP_LOCtiON = PATH+"app/api/updatepos.html";
    /**
     * 查询VTO信息
     */
    public static String GET_APP_VTO = PATH+"app/api/product/get.html";
    /**
     * 查询VTO发货
     */
    public static String GET_APP_VTO_STATE = PATH+"app/api/order/queryprocess.html";
}

