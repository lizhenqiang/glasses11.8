package com.remote.glasses.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import com.google.gson.Gson;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.request.PostRequest;
import com.remote.glasses.R;
import com.remote.glasses.base.BaseFragment;
import com.remote.glasses.base.MyApplication;
import com.remote.glasses.bean.UserInfo;
import com.remote.glasses.set.NetPath;
import com.remote.glasses.utils.ActivityUtil;
import com.remote.glasses.utils.ArrayUtils;
import com.remote.glasses.utils.DateTimePickDialogUtil;
import com.remote.glasses.utils.L;
import com.remote.glasses.utils.ListUtils;
import com.remote.glasses.utils.LoginUtils;
import com.remote.glasses.utils.PreferencesUtils;
import com.remote.glasses.utils.StringUtils;
import com.remote.glasses.utils.TimeUtils;
import com.remote.glasses.utils.ToastUtil;
import com.remote.glasses.utils.ToolsCallback;
import com.remote.glasses.utils.citySelect.MainActivity;
import com.zhy.android.percent.support.PercentLinearLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Feics on 2016/7/7.
 */
public class UserInfoFragment extends BaseFragment implements View.OnClickListener {

    private ArrayList<EditText> etList = new ArrayList<>();
    private EditText etPhone;
    private EditText address;
    private EditText name;
    private EditText card;
    private EditText yibao_card;
    private EditText date;
    private EditText remark;
    private EditText mobile;
    private EditText qq;
    private EditText prefession;
    private RadioButton rbNan;
    private RadioButton rbNv;
    private RadioButton sexSecret;
    private RadioGroup sexRg;

    private UserInfo userInfoNet;
    private LiteOrm liteOrm;

    private String nothingShow = "";
    private LinearLayout msgMain;
    private EditText city;
    private ScrollView sv;
    private String sexState = "";

    /**
     * 用户地址保存
     */
    private String addressTemp = "2";

    /**
     * 是否选择门店地址
     * 值为 1 ：该订单向镜宴发送店铺地址
     值为 2 ：该订单向镜宴发送用户(买家)地址
     */
    private String isShopaddress = "2";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_userinfo, container, false);

        return view;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MyApplication myApplication = (MyApplication) getActivity().getApplication();
        liteOrm = myApplication.liteOrm;

        final View view = getView();

        msgMain = (LinearLayout) view.findViewById(R.id.userinfo_msg_main);

        sv = (ScrollView) view.findViewById(R.id.userinfo_sv);

        etPhone = (EditText) view.findViewById(R.id.userinfo_phone_et);
        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (ActivityUtil.isMobileNO(etPhone.getText().toString().trim())) {
                    onSave();
                    confirmUser(etPhone.getText().toString().trim());
                    getActivity().findViewById(R.id.create_order_Glasses).setEnabled(true);
                    getActivity().findViewById(R.id.create_order_Optometry).setEnabled(true);
                    getActivity().findViewById(R.id.create_order_submit).setEnabled(true);
                    msgMain.setVisibility(View.VISIBLE);
                } else {
                    msgMain.setVisibility(View.INVISIBLE);
                }
            }
        });
        Button confirm = (Button) view.findViewById(R.id.userinfo_phone_confirm);//确认搜索
        confirm.setOnClickListener(this);

        address = (EditText) view.findViewById(R.id.userinfo_address);
        name = (EditText) view.findViewById(R.id.userinfo_name);
        card = (EditText) view.findViewById(R.id.userinfo_card);
        yibao_card = (EditText) view.findViewById(R.id.userinfo_yibao_card);
        date = (EditText) view.findViewById(R.id.userinfo_date);
        date.setOnClickListener(this);
        remark = (EditText) view.findViewById(R.id.userinfo_remark);
        mobile = (EditText) view.findViewById(R.id.userinfo_mobile);
        qq = (EditText) view.findViewById(R.id.userinfo_qq);
        prefession = (EditText) view.findViewById(R.id.userinfo_prefession);

        city = (EditText) view.findViewById(R.id.userinfo_city);
        city.setOnClickListener(this);

        rbNan = (RadioButton) view.findViewById(R.id.userinfo_sex_nan);
        rbNv = (RadioButton) view.findViewById(R.id.userinfo_sex_nv);
        sexSecret = (RadioButton) view.findViewById(R.id.userinfo_sex_secret);
        sexRg = (RadioGroup) view.findViewById(R.id.userinfo_sex_rg);
        sexSecret.setChecked(true);
        sexRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.userinfo_sex_nan:
                        sexState = "1";
                        break;
                    case R.id.userinfo_sex_nv:
                        sexState = "2";
                        break;
                    case R.id.userinfo_sex_secret:
                        sexState = "";
                        break;
                }
            }
        });

        etList.add(address);
        etList.add(name);
        etList.add(card);
        etList.add(yibao_card);
        etList.add(date);
        etList.add(mobile);
        etList.add(remark);
        etList.add(qq);
        etList.add(prefession);
        etList.add(city);


        /**
         * 门店地址|用户地址
         */

        RadioGroup addressRg = (RadioGroup) view.findViewById(R.id.userinfo_address_rg);
        RadioButton user = (RadioButton) view.findViewById(R.id.userinfo_address_user);
        RadioButton mendian = (RadioButton) view.findViewById(R.id.userinfo_address_mendian);

        addressRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.userinfo_address_mendian:
                        isShopaddress = "2";
                        view.findViewById(R.id.userinfo_country_bj).setVisibility(View.GONE);
                        addressTemp = address.getText().toString();
                        address.setText(PreferencesUtils.getString(getActivity(),PreferencesUtils.key_address,""));
                        break;
                    case R.id.userinfo_address_user:
                        city.setFocusable(false);
                        city.setFocusableInTouchMode(false);
                        view.findViewById(R.id.userinfo_country_bj).setVisibility(View.VISIBLE);
                        address.setText(addressTemp);
                        isShopaddress = "1";
                        break;
                }
            }
        });

    }

    private void queryLocalDate(String phone){
        List<UserInfo> userinfo = liteOrm.query(new QueryBuilder<UserInfo>(UserInfo.class)
                .whereEquals("phone", phone));
        if(!ListUtils.isEmpty(userinfo)){
            if(userinfo.size()>0){
                initDateView(userinfo.get(0));
//                L.e("jishuaiepng" + userinfo);
//                ToastUtil.showDebug(getActivity(), "lite_orm_1");
            }else {
//                ToastUtil.showDebug(getActivity(), "lite_orm_00");
            }
        }else {
            address.setText(PreferencesUtils.getString(getActivity(),PreferencesUtils.key_address,""));
            ToastUtil.show(getActivity(), " 您是新用户，请先填写信息！");
            rbNan.setChecked(false);
            rbNv.setChecked(false);
            sexSecret.setChecked(true);


        }
    }


    /**
     * 是否可编辑 true 可编辑  / false 不可编辑
     * @param flag
     */
    private void setEditEnable(boolean flag){
        for (EditText editText : etList){
            if(flag){
                editText.setFocusableInTouchMode(true);
                editText.setFocusable(true);
                editText.requestFocus();
                rbNan.setEnabled(true);
                rbNv.setEnabled(true);
                sexSecret.setEnabled(true);

            }else {
                editText.setFocusable(false);
                editText.setFocusableInTouchMode(false);
                rbNan.setEnabled(false);
                rbNv.setEnabled(false);
                sexSecret.setEnabled(false);
            }
        }
        mobile.setFocusable(false);
        mobile.setFocusableInTouchMode(false);

        date.setFocusable(false);
        date.setFocusableInTouchMode(false);

        city.setFocusable(false);
        city.setFocusableInTouchMode(false);

        sv.smoothScrollTo(0, 0);

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(hidden){
            onSave();
        }
    }


    /**
     * 清空编辑
     */
    private void clearAllEdit(){
        for (EditText editText : etList){
            editText.setText("");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.userinfo_phone_confirm://确认
                if(ActivityUtil.isMobileNO(etPhone.getText().toString().trim())){
                    confirmUser(etPhone.getText().toString().trim());
                    msgMain.setVisibility(View.VISIBLE);
                } else {
                    msgMain.setVisibility(View.INVISIBLE);
                    ToastUtil.show(getActivity(), "请检查手机号格式");
                }
                break;
            case R.id.userinfo_date://出生日期

                if(userInfoNet==null){
                    String b = date.getText().toString().trim();
                    DateTimePickDialogUtil picker = new DateTimePickDialogUtil(getActivity(),b.equals("")
                            ? TimeUtils.getTime(System.currentTimeMillis(),TimeUtils.DATE_FORMAT_DATE)+"":b);
                    picker.dateTimePicKDialog(date,0);
                }else {
                    break;
                }
                break;
            case R.id.userinfo_city://城市选择

                if(userInfoNet==null){
                    startActivityForResult(new Intent(getActivity(), MainActivity.class),20);
                }else {
                    break;
                }

                break;
            default:
                break;
        }
    }

    private void confirmUser(final String phone){
        setNetGoCallBackListener(new LoginUtils.NetGoCallBackListener() {
            @Override
            public void netOnClick() {
                PostRequest postRequest = OkHttpUtils.post(NetPath.QUERY_USER)
                        .params("phone", phone)
                        .params("token", PreferencesUtils.getString(getActivity(),PreferencesUtils.key_token));
                postRequest.execute(new ToolsCallback<Object>(getActivity()) {
                    @Override
                    public Object parseNetworkResponse(Response response) {
                        return null;
                    }

                    @Override
                    public void onResponse(boolean isFromCache, Object o, Request request, @Nullable Response response) {
                        try {
                            String s = response.body().string();
                            L.e(s);
                            JSONObject jsonObject = new JSONObject(s);
                            String status = jsonObject.optString("status");
                            if(status.equals("206")){
                                setEditEnable(false);
                                Gson gson = new Gson();
                                UserInfo userInfo = gson.fromJson(s, UserInfo.class);
                                userInfoNet = userInfo;
                                initDateView(userInfo);
                            }else {
                                userInfoNet = null;
                                clearAllEdit();
                                setEditEnable(true);
                                queryLocalDate(phone.trim());
                                mobile.setText(phone.trim());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }



    private void initDateView(UserInfo userInfo) {
        L.e("jishuaipeng" + userInfo.toString());
//        etPhone.setText(StringUtils.isEmpty(userInfo.getPhone()) ? nothingShow : userInfo.getPhone());
        address.setText(StringUtils.isEmpty(userInfo.getAddress()) ?
                PreferencesUtils.getString(getActivity(),PreferencesUtils.key_address) : userInfo.getAddress());
        mobile.setText(StringUtils.isEmpty(userInfo.getPhone()) ? nothingShow : userInfo.getPhone());
        date.setText(userInfo.getBirthday() <= 0 ? nothingShow : TimeUtils.getTime(userInfo.getBirthday(), TimeUtils.DATE_FORMAT_DATE));
        qq.setText(StringUtils.isEmpty(userInfo.getQq()) ? nothingShow : userInfo.getQq());
        yibao_card.setText(StringUtils.isEmpty(userInfo.getMedicareId()) ? nothingShow : userInfo.getMedicareId());
        prefession.setText(StringUtils.isEmpty(userInfo.getJob()) ? nothingShow : userInfo.getJob());
        card.setText(StringUtils.isEmpty(userInfo.getIdcard()) ? nothingShow : userInfo.getIdcard());
        remark.setText(StringUtils.isEmpty(userInfo.getCustRemark()) ? nothingShow : userInfo.getCustRemark());
        name.setText(StringUtils.isEmpty(userInfo.getName()) ? nothingShow : userInfo.getName());
        if (StringUtils.isEmpty(userInfo.getProvince()) || StringUtils.isEmpty(userInfo.getCity()) || StringUtils.isEmpty(userInfo.getCounty())){
            city.setText("");
        }else{
            city.setText(userInfo.getProvince() + "-" + userInfo.getCity() + "-" + userInfo.getCounty());
        }

        if(StringUtils.isEmpty(userInfo.getSex())){
            rbNan.setChecked(false);
            rbNv.setChecked(false);
            sexSecret.setChecked(true);
        }else{
            if(userInfo.getSex().equals("1")){
                rbNan.setChecked(true);
                rbNv.setChecked(false);
                sexSecret.setChecked(false);
            }else {
                rbNv.setChecked(true);
                rbNan.setChecked(false);
                sexSecret.setChecked(false);
            }
        }
    }

    /**
     * 保存数据
     */
    private void onSave(){
        if(userInfoNet!=null){
            PreferencesUtils.putString(getActivity(), PreferencesUtils.key_current_user_phone, userInfoNet.getPhone().trim());
            liteOrm.save(userInfoNet);
        }else {
            if (ActivityUtil.isMobileNO(mobile.getText().toString().trim())) {
                UserInfo userInfoEdit = new UserInfo();
                setUserInfo(userInfoEdit);
            }

        }
    }

    @Override
    public void onPause() {
        onSave();
        super.onPause();
    }

//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        onSave();
//        super.onSaveInstanceState(outState);
//    }

    private void setUserInfo(UserInfo userInfo){
        userInfo.setAddress(address.getText().toString().trim());
        userInfo.setPhone(mobile.getText().toString().trim());
        userInfo.setMedicareId(yibao_card.getText().toString().trim());
        userInfo.setCustRemark(remark.getText().toString().trim());
        if(date.getText().toString().trim().equals("")){
            userInfo.setBirthday(0L);
        }else {
            userInfo.setBirthday(TimeUtils.getLongTime(date.getText().toString().trim(), TimeUtils.DATE_FORMAT_DATE));
        }
        userInfo.setBirthday(TimeUtils.getLongTime(date.getText().toString().trim(), TimeUtils.DATE_FORMAT_DATE));
        userInfo.setJob(prefession.getText().toString().trim());
        userInfo.setQq(qq.getText().toString().trim());
        userInfo.setIdcard(card.getText().toString().trim());
        userInfo.setName(name.getText().toString().trim());
        userInfo.setIsShopaddress(isShopaddress);
        userInfo.setSex(sexState);

        String citys[] = city.getText().toString().trim().split("-");

        if(!ArrayUtils.isEmpty(citys)){
            if(citys.length==3){
                userInfo.setProvince(citys[0]);//省
                userInfo.setCity(citys[1]);//市
                userInfo.setCounty(citys[2]);//县
            }
        }
        PreferencesUtils.putString(getActivity(), PreferencesUtils.key_current_user_phone, userInfo.getPhone().trim());
        liteOrm.save(userInfo);
        ToastUtil.showDebug(getActivity(), userInfo.toString()+"__");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==20&&resultCode==100&&data!=null){
            city.setText(data.getStringExtra("city"));
        }
    }
}
