package com.remote.glasses.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.remote.glasses.utils.AppManager;
import com.remote.glasses.utils.L;
import com.remote.glasses.utils.LoginUtils;
import com.remote.glasses.utils.PreferencesUtils;

public abstract class BaseActivity extends AbsBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /**
         * 设置为横屏
         */
        if(getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        super.onCreate(savedInstanceState);
        // setContentView(android.R.layout.demo);
        initCreate(savedInstanceState);
        //将 Activity 添加到栈
        AppManager.addActivity(this);
        //初始化控件
        initView();

        L.e(this.getPackageName(),"BaseActivity________________________");
    }





    /**
     * 设置布局文件
     * @param savedInstanceState
     */
    protected abstract void initCreate(Bundle savedInstanceState);

    /**
     * 初始化控件
     */
    public abstract void initView();


    public void setNetGoCallBackListener(LoginUtils.NetGoCallBackListener netGoCallBackListener){
        LoginUtils.netGoCallBackListener = netGoCallBackListener;
        if(LoginUtils.isLogin(this)){
            LoginUtils.netGoCallBackListener.netOnClick();
        }else {
            LoginUtils.login(this, PreferencesUtils.getString(this,PreferencesUtils.key_username),PreferencesUtils.getString(this,PreferencesUtils.key_passwd));
        }

    }


}
