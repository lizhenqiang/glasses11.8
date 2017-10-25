package com.remote.glasses.base;

import android.support.v4.app.Fragment;

import com.remote.glasses.utils.LoginUtils;
import com.remote.glasses.utils.PreferencesUtils;

/**
 * Created by admin on 2016/7/9.
 */
public class BaseFragment extends Fragment{



    public void setNetGoCallBackListener(LoginUtils.NetGoCallBackListener netGoCallBackListener){
        LoginUtils.netGoCallBackListener = netGoCallBackListener;
        if(LoginUtils.isLogin(getActivity())){
            LoginUtils.netGoCallBackListener.netOnClick();
        }else {
            LoginUtils.login(getActivity(), PreferencesUtils.getString(getActivity(), PreferencesUtils.key_username),PreferencesUtils.getString(getActivity(),PreferencesUtils.key_passwd));
        }

    }

}
