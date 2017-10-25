package com.remote.glasses.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.remote.glasses.R;
import com.remote.glasses.base.BaseActivity;
import com.remote.glasses.utils.ActivityUtil;
import com.remote.glasses.utils.LoginUtils;
import com.remote.glasses.utils.PreferencesUtils;

public class SetingActivity extends BaseActivity implements View.OnClickListener {

    private TextView save;
    private TextView nosave;
    private Drawable drawable;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_seting);
    }

    @Override
    public void initView() {

        drawable = getResources().getDrawable(R.drawable.icon_select);
        /// 这一步必须要做,否则不会显示.
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());

        findViewById(R.id.set_shut_down).setOnClickListener(this);
        save = (TextView) findViewById(R.id.set_save);
        save.setOnClickListener(this);
        nosave = (TextView) findViewById(R.id.set_nosave);
        nosave.setOnClickListener(this);
        findViewById(R.id.set_shut_down).setOnClickListener(this);
        findViewById(R.id.set_back_login).setOnClickListener(this);

        //设置版本号
        try {
            ((TextView)findViewById(R.id.set_version_msg)).setText(ActivityUtil.getPhoneInfo(this,4));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        //是否保存
        if(PreferencesUtils.getBoolean(SetingActivity.this, PreferencesUtils.key_is_save)){
            nosave.setCompoundDrawables(null, null, drawable, null);
            save.setCompoundDrawables(null, null, null, null);
        }else {
            save.setCompoundDrawables(null, null, drawable, null);
            nosave.setCompoundDrawables(null, null, null, null);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.set_shut_down:
                finish();
                break;
            case R.id.set_nosave:
                nosave.setCompoundDrawables(null, null, drawable, null);
                save.setCompoundDrawables(null, null, null, null);
                PreferencesUtils.putBoolean(SetingActivity.this, PreferencesUtils.key_is_save, true);
                break;
            case R.id.set_save:
                save.setCompoundDrawables(null, null, drawable, null);
                nosave.setCompoundDrawables(null, null, null, null);
                PreferencesUtils.putBoolean(SetingActivity.this, PreferencesUtils.key_is_save, false);
                break;
            case R.id.set_back_login:
                LoginUtils.backLogin(SetingActivity.this);
                startActivity(new Intent(SetingActivity.this, LoginActiviy.class));
                finish();
                break;
            default:
                break;
        }
    }
}
