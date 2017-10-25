package com.remote.glasses.set;

/**
 * Created by admin on 2016/7/6.
 */
public class NetPath1 {
    //验光 123.57.149.121
    private static final String PATH = "http://buy1001.cn/";

    /**
     * 用户登陆接口
     *  请求 authstr
     *  设备 id deviceid
     *  username=??&passwd=?? 对 称 加密之后为 authstr
     */
    public static String LOGIN = PATH+"xinfu/web/app/api/applogin.html";

    /**
     * 查询用户接口
     * phone token 必传
     */
    public static String QUERY_USER = PATH+"xinfu/web/app/api/cust/query.html";

    /**
     * 提交订单接口
     */
    public static String SUBMIT_ORDER = PATH+"xinfu/web/app/api/order/pushv2.html";

    /**
     *  查询历史订单接口
     */
    public static String QUERY_ORDER = PATH+"xinfu/web/app/api/order/query.html";

    /**
     * 增加临时保存的验光记录
     */
    public static String ADD_YANGUANG_HISTORY = PATH+"xinfu/web/app/api/light_history/push.html";

    /**
     * 查询已有的验光记录
     */
    public static String QUERY_YANGUANG_HISTORY = PATH+"xinfu/web/app/api/light_history /query.html";

    /**
     * 更新接口
     */
    public static String UPDATE_APP = PATH+"xinfu/web/app/api/apk/query.html";
    /**
     * 更新接口
     */
    public static String GET_APP_LOCtiON = PATH+"xinfu/web/app/api/updatepos.html";
}
