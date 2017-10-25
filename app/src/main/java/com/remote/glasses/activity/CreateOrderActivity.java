package com.remote.glasses.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.remote.glasses.Fragment.GlassesChoiceFragment;
import com.remote.glasses.Fragment.GlassesFragment;
import com.remote.glasses.Fragment.OptometryFragment;
import com.remote.glasses.Fragment.OrderSubmitFragment;
import com.remote.glasses.Fragment.UserInfoFragment;
import com.remote.glasses.R;
import com.remote.glasses.base.BaseActivity;
import com.remote.glasses.utils.LoadingUtil;

import java.util.HashMap;
import java.util.Map;

public class CreateOrderActivity extends BaseActivity {
    private ImageView back;
    private RadioButton rbInfo,rbOptometry,rbGlasses,rbSubmit;
    private RadioGroup mRadioGroup;
    private FrameLayout mFrameLayout;
    private UserInfoFragment mUserInfoFragment;
    private Map<Integer, Fragment> fmap = new HashMap<Integer, Fragment>();
    private int currentRadio;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_create_order);
        fmap.clear();
        fmap.put(R.id.create_order_info, new UserInfoFragment());
        fmap.put(R.id.create_order_Optometry, new OptometryFragment());
        fmap.put(R.id.create_order_Glasses, new GlassesFragment());
        fmap.put(R.id.create_order_submit, new OrderSubmitFragment());
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.create_order_framelayout,fmap.get(R.id.create_order_info)).commit();

        }
    }

    @Override
    public void initView() {
        back = (ImageView) findViewById(R.id.create_order_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               LoadingUtil.showAlertDialog(CreateOrderActivity.this);
            }
        });

        rbInfo = (RadioButton) findViewById(R.id.create_order_info);
        rbOptometry = (RadioButton) findViewById(R.id.create_order_Optometry);
        rbGlasses = (RadioButton) findViewById(R.id.create_order_Glasses);
        rbSubmit = (RadioButton) findViewById(R.id.create_order_submit);
        mRadioGroup = (RadioGroup) findViewById(R.id.create_order_rg);
        mFrameLayout = (FrameLayout) findViewById(R.id.create_order_framelayout);
        currentRadio = R.id.create_order_info;
        rbOptometry.setEnabled(false);
        rbGlasses.setEnabled(false);
        rbSubmit.setEnabled(false);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Fragment temp = fmap.get(checkedId);
                switch (checkedId) {
                    case R.id.create_order_info:
                        switchContent(temp,checkedId);
                        break;
                    case R.id.create_order_Optometry:
                        switchContent(temp,checkedId);
                        break;
                    case R.id.create_order_Glasses:
                        switchContent(temp,checkedId);
                        break;
                    case R.id.create_order_submit:
                        switchContent(temp,checkedId);
                        break;
                }
            }
        });
    }

    /* 修改显示的内容 不会重新加载 */
    private Fragment mContent;
    public void switchContent(Fragment to, int id) {
        mContent = fmap.get(currentRadio);
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        if (to != mContent) {

            if (!to.isAdded()) { // 先判断是否被add过
                transaction.hide(mContent).add(R.id.create_order_framelayout, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(mContent).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
            currentRadio = id;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        fmap.get(R.id.create_order_Glasses).onActivityResult(requestCode, resultCode, data);
        fmap.get(R.id.create_order_info).onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            LoadingUtil.showAlertDialog(CreateOrderActivity.this);
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

}
