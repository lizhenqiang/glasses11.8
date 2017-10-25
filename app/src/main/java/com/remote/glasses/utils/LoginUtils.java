package com.remote.glasses.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.AbsCallback;
import com.lzy.okhttputils.request.BaseRequest;
import com.lzy.okhttputils.request.PostRequest;
import com.remote.glasses.activity.LoginActiviy;
import com.remote.glasses.set.NetPath;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by admin on 2016/7/9.
 */
public class LoginUtils {



    public static final long LOGIN_FAIED_TIME = 1000 * 60 * 60 * 10; // 10小时
//    public static final long LOGIN_FAIED_TIME = 1000 * 10; // 1分钟
    /**
     * 是否登录 toKen是否失效
     */
    public static boolean isLogin(Context context){
        long time = PreferencesUtils.getLong(context,PreferencesUtils.key_token_time,-1);
        if(time != -1){
            //登录失效
            if((System.currentTimeMillis()-time)>LOGIN_FAIED_TIME){
                return false;
            }else {
                return true;
            }
        }else { //未登录
            return false;
        }
    }

    /**
     *  退出登录
     * @param context
     * @return
     */
    public static boolean backLogin(Context context){
        return PreferencesUtils.putLong(context,PreferencesUtils.key_token_time,-1);
    }

    /**
     * 登录方法
     * @param context
     * @param uname
     * @param pas
     */
    public static void login(final Context context, final String uname, final String pas){

        try {
            L.e("token = " + CodeUtil.getAuthstr(uname, pas)+"__"+ActivityUtil.getPhoneInfo(context, 1));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        PostRequest postRequest = null;
        try {
            postRequest = OkHttpUtils.post(NetPath.LOGIN)
                    .params("authstr", CodeUtil.getAuthstr(uname, pas))
                    .params("deviceid", ActivityUtil.getPhoneInfo(context, 1));//获取设备id
          String  tag = ActivityUtil.getPhoneInfo(context, 1);
            Log.i("info",tag);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        postRequest.execute(new AbsCallback<Object>() {

            @Override
            public void onBefore(BaseRequest request) {
                super.onBefore(request);
                LoadingUtil.show(context);
            }

            @Override
            public void onAfter(boolean isFromCache, Object o, Call call, Response response, Exception e) {
                super.onAfter(isFromCache, o, call, response, e);
               LoadingUtil.dismis();
            }

            @Override
            public Object parseNetworkResponse(Response response) {
                return null;
            }

            @Override
            public void onResponse(boolean isFromCache, Object o, Request request, Response response) {

                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response.body().string());
                    ToastUtil.show(context,jsonObject.toString()+"返回的全部");
                    if(jsonObject!=null){
                        String status = jsonObject.optString("status");
                        ToastUtil.show(context,status+"------"+jsonObject.toString());

                        if(status.equals("206")||!jsonObject.optString("token").equals("")){
                            /**
                             * 用户名密码保存
                             */
                            PreferencesUtils.putString(context,PreferencesUtils.key_username,uname);
                            PreferencesUtils.putString(context,PreferencesUtils.key_passwd,pas);
                            /**
                             * 登录信息
                             */
                            PreferencesUtils.putString(context, PreferencesUtils.key_name, jsonObject.optString(PreferencesUtils.key_name));
                            PreferencesUtils.putString(context,PreferencesUtils.key_id,jsonObject.optString(PreferencesUtils.key_id));
                            PreferencesUtils.putString(context,PreferencesUtils.key_token,jsonObject.optString(PreferencesUtils.key_token));
                            PreferencesUtils.putString(context,PreferencesUtils.key_optometryCode,jsonObject.optString(PreferencesUtils.key_optometryCode));
                            PreferencesUtils.putString(context,PreferencesUtils.key_phone,jsonObject.optString(PreferencesUtils.key_phone));
                            PreferencesUtils.putString(context,PreferencesUtils.key_address,jsonObject.optString(PreferencesUtils.key_address));
                            PreferencesUtils.putString(context, PreferencesUtils.key_dispensingCode, jsonObject.optString(PreferencesUtils.key_dispensingCode));

                            /**
                             * token 保存时间
                             */
                            PreferencesUtils.putLong(context, PreferencesUtils.key_token_time, System.currentTimeMillis());

                            LoadingUtil.dismis();

                            if(netGoCallBackListener!=null){
                                netGoCallBackListener.netOnClick();
                            }

                            if(context instanceof LoginActiviy){
                                ((LoginActiviy) context).finish();
                            }

                        }else {
                            ToastUtil.show(context,"登录失败");
                        }

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return;
            }
        });
    }

    /*
    * 网络请求 除 登录 接口
   * 都需经过接口回掉方可
   */
    public static LoginUtils.NetGoCallBackListener netGoCallBackListener;

    public interface NetGoCallBackListener{
        void netOnClick();
    }

}
