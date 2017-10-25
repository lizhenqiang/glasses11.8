package com.remote.glasses.activity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.AbsCallback;
import com.lzy.okhttputils.request.PostRequest;
import com.remote.glasses.R;
import com.remote.glasses.base.BaseActivity;
import com.remote.glasses.set.NetPath;
import com.remote.glasses.utils.CodeUtil;
import com.remote.glasses.utils.L;
import com.remote.glasses.utils.LoadingUtil;
import com.remote.glasses.utils.LoginUtils;
import com.remote.glasses.utils.PreferencesUtils;
import com.remote.glasses.utils.ToastUtil;
import com.remote.glasses.utils.ToolsCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

public class LoginActiviy extends BaseActivity {
    private List<EditText> etList = new ArrayList<>();
    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login_activiy);
    }

    @Override
    public void initView() {


        final EditText uname = (EditText) findViewById(R.id.login_uname);
        final EditText pas = (EditText) findViewById(R.id.login_pas);

        etList.add(uname);
        etList.add(pas);

        if(PreferencesUtils.getString(this,PreferencesUtils.key_username,"").equals("")
                ||PreferencesUtils.getString(this,PreferencesUtils.key_passwd,"").equals("")){

        }else {
            setEditEnable(true); //关闭不可编辑
            uname.setText(PreferencesUtils.getString(this, PreferencesUtils.key_username, ""));
            pas.setText(PreferencesUtils.getString(this, PreferencesUtils.key_passwd, ""));
        }

        Button login = (Button) findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginUtils.login(LoginActiviy.this, uname.getText().toString(), pas.getText().toString());
            }
        });

    }

    /**
     * 是否可编辑 true 可编辑  / false 不可编辑
     * @param flag
     */
    private void setEditEnable(boolean flag) {
        for (EditText editText : etList) {
            if (flag) {
                editText.setFocusableInTouchMode(true);
                editText.setFocusable(true);
                editText.requestFocus();
            } else {
                editText.setFocusable(false);
                editText.setFocusableInTouchMode(false);
            }
        }
    }

}
