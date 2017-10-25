package com.remote.glasses.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.request.PostRequest;
import com.remote.glasses.R;
import com.remote.glasses.activity.FaPiaoActivity;
import com.remote.glasses.base.BaseFragment;
import com.remote.glasses.base.MyApplication;
import com.remote.glasses.bean.GlassesBean;
import com.remote.glasses.bean.OptometryBean;
import com.remote.glasses.bean.UserInfo;
import com.remote.glasses.set.NetPath;
import com.remote.glasses.utils.CodeUtil;
import com.remote.glasses.utils.L;
import com.remote.glasses.utils.ListUtils;
import com.remote.glasses.utils.LoginUtils;
import com.remote.glasses.utils.PreferencesUtils;
import com.remote.glasses.utils.StringUtils;
import com.remote.glasses.utils.TimeUtils;
import com.remote.glasses.utils.ToolsCallback;
import com.seller.cash.activity.YDPayActivity;
import com.seller.cash.util.MD5;
import com.seller.cash.util.SerializableMap;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import okhttp3.Request;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Feics on 2016/7/8.
 */
public class OrderSubmitFragment extends BaseFragment implements View.OnClickListener {
    private LiteOrm liteOrm;
    private String nothingShow = "";
    public static final int REQUEST_CODE = 111;
    private TextView mUame,phone, mUsex, mUdate, mUcard, mUprofession, mUaddress, mUQQ, mUyibaoard, mUremark, mUprovince;
    private TextView mYouyanqiujing, mYouyanzhujing, mYouyanzhouwei, mYouguang, mYouyantongju, mZuoyanqiujing, mZuoyanzhujing, mZuoyanzhouwei, mZuoyanxiajiaguang, mZuoyantongju, mYanguangfei;
    private TextView mBrand, mCategory, mModel, mColor, mPackage, mAmount, mFee, mRateFee;
    private TextView mSumprice,xuanzefapiao;
    //    private TextView mPaytype;
    private RadioGroup mRgPay;
    private RadioButton mReadyPay;
    private RadioButton mNoPay;
    private RadioGroup mRgYanGuang;
    private RadioButton mNoYanGuang;
    private RadioButton mAnGuang;
    private LinearLayout mBottom;
    private LinearLayout mPeiJingBj;
    private LinearLayout mYangGuangBj;
    private Button mSubmit, save;
    private LinearLayout linearLayout1;
    private int payFlag = 1;
    private String mPhone;
    private List<GlassesBean> mGlassesBeansList;
    private List<OptometryBean> mOptometryBeanList;
    private List<UserInfo> mUserinfoList;
    private UserInfo userInfo;
    private OptometryBean optometryBean;
    private GlassesBean glassesBean;

    private List<TextView> tvList1 = new ArrayList<>();
    private List<TextView> tvList2 = new ArrayList<>();
    private List<TextView> tvList3 = new ArrayList<>();

    private RadioButton rbAli, rbXianxia, rbYinlian, rbWeixin,rbXingfu;
    private View view;
    private String payType = "4";
    private String type ,vto_frame_id;
    //生产环境
//    private String key = "SclDG2RezVlbG0Lp4uyow7erM7Tz7sgPMbPoPvzmfgh5b";
    //Dev
    private String key = "SclDG2RezVlbG0Lp4uyow7erM7Tz7sgPMbPoPvzmfgh5b";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication myApplication = (MyApplication) getActivity().getApplication();
        liteOrm = myApplication.liteOrm;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.submit_order, container, false);
        linearLayout1 = (LinearLayout)view.findViewById(R.id.line1);
/**
 * 客户信息
 */
        mUame = (TextView) view.findViewById(R.id.order_deatil_uname);
        mUsex = (TextView) view.findViewById(R.id.order_deatil_usex);
        mUdate = (TextView) view.findViewById(R.id.order_deatil_udate);
        mUcard = (TextView) view.findViewById(R.id.order_deatil_ucard);
        mUprofession = (TextView) view.findViewById(R.id.order_deatil_uprofession);
        mUaddress = (TextView) view.findViewById(R.id.order_deatil_uaddress);
        mUQQ = (TextView) view.findViewById(R.id.order_deatil_uQQ);
        mUyibaoard = (TextView) view.findViewById(R.id.order_deatil_uyibao_card);
        mUremark = (TextView) view.findViewById(R.id.order_deatil_ubeizhu);
        mUprovince = (TextView) view.findViewById(R.id.order_deatil_provice);
        phone = (TextView) view.findViewById(R.id.order_deatil_uphone);
        xuanzefapiao = (TextView)view.findViewById(R.id.xuanzefapiao);
        /**
         * 验光数据
         */

        mYouyanqiujing = (TextView) view.findViewById(R.id.order_deatil_yyouyanqiujing);
        mYouyanzhujing = (TextView) view.findViewById(R.id.order_deatil_yyouyanzhujing);
        mYouyanzhouwei = (TextView) view.findViewById(R.id.order_deatil_yyouyanzhouwei);
        mYouguang = (TextView) view.findViewById(R.id.order_deatil_yyouguang);
        mYouyantongju = (TextView) view.findViewById(R.id.order_deatil_yyouyantongju);

        mZuoyanqiujing = (TextView) view.findViewById(R.id.order_deatil_yzuoyanqiujing);
        mZuoyanzhujing = (TextView) view.findViewById(R.id.order_deatil_yzuoyanzhujing);
        mZuoyanzhouwei = (TextView) view.findViewById(R.id.order_deatil_yzuoyanzhouwei);
        mZuoyanxiajiaguang = (TextView) view.findViewById(R.id.order_deatil_yzuoyanxiajiaguang);
        mZuoyantongju = (TextView) view.findViewById(R.id.order_deatil_yzuoyantongju);

        mYanguangfei = (TextView) view.findViewById(R.id.order_deatil_yyanguangfei);

        /**
         * 配镜数据
         */
        mBrand = (TextView) view.findViewById(R.id.order_deatil_ypinpai);
        mCategory = (TextView) view.findViewById(R.id.order_deatil_ytype);
        mModel = (TextView) view.findViewById(R.id.order_deatil_yxinghao);
        mColor = (TextView) view.findViewById(R.id.order_deatil_ycolor);
        mPackage = (TextView) view.findViewById(R.id.order_deatil_ytaocan);
        mAmount = (TextView) view.findViewById(R.id.order_deatil_ynumber);
        mFee = (TextView) view.findViewById(R.id.order_deatil_yprice);
        mRateFee = (TextView) view.findViewById(R.id.order_deatil_yyouiprice);
        mSumprice = (TextView) view.findViewById(R.id.order_deatil_ysumprice);

//        mPaytype = (TextView) view.findViewById(R.id.order_deatil_ypaytype);

        /**
         * 布局及RaidoButton
         */
        mRgPay = (RadioGroup) view.findViewById(R.id.order_deatil_rg_pay);
        mReadyPay = (RadioButton) view.findViewById(R.id.order_deatil_pay);
        mReadyPay.setOnClickListener(this);
        mNoPay = (RadioButton) view.findViewById(R.id.order_deatil_nopay);
        mNoPay.setOnClickListener(this);

        mRgYanGuang = (RadioGroup) view.findViewById(R.id.order_deatil_rg_yanguang);
        mNoYanGuang = (RadioButton) view.findViewById(R.id.order_deatil_no_yanguang);
        mAnGuang = (RadioButton) view.findViewById(R.id.order_deatil_yanguang);

        mBottom = (LinearLayout) view.findViewById(R.id.order_deatil_bottombj);
        mPeiJingBj = (LinearLayout) view.findViewById(R.id.order_deatil_peijing_bj);
        mYangGuangBj = (LinearLayout) view.findViewById(R.id.order_deatil_yanguang_bj);

        rbAli = (RadioButton) view.findViewById(R.id.order_deatil_Payali);
        rbWeixin = (RadioButton) view.findViewById(R.id.order_deatil_Payweixin);
        rbXianxia = (RadioButton) view.findViewById(R.id.order_deatil_Payxianxia);
        rbYinlian = (RadioButton) view.findViewById(R.id.order_deatil_PayUnion);
        rbXingfu = (RadioButton) view.findViewById(R.id.order_deatil_Payxingfuka);
        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FaPiaoActivity.class);
                startActivityForResult(intent,100);
            }
        });
        rbAli.setOnClickListener(this);
        rbWeixin.setOnClickListener(this);
        rbXianxia.setOnClickListener(this);
        rbYinlian.setOnClickListener(this);
        rbXingfu.setOnClickListener(this);

        mSubmit = (Button) view.findViewById(R.id.order_deatil_submit);
        save = (Button) view.findViewById(R.id.order_deatil_save);
//        mSubmit.setBackgroundResource(R.color.grayDark);
//        mSubmit.setClickable(false);
        save.setOnClickListener(new NoDoubleClickListener() {
            @Override
            protected void onNoDoubleClick(View v) {
                OrderOperate(1);
            }
        });
        mSubmit.setOnClickListener(new NoDoubleClickListener() {

            @Override
            protected void onNoDoubleClick(View v) {

                OrderOperate(2);
            }
        });
        mRgPay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.order_deatil_pay:
                        if (payType.equals("3")){
                            payFlag = 2;
                            save.setClickable(false);
                            save.setBackgroundResource(R.color.grayDark);
                            mSubmit.setClickable(true);
                            mSubmit.setBackgroundResource(R.drawable.order_btn_press);
                        }
                        break;
                    case R.id.order_deatil_nopay:
                        if (payType.equals("3")) {
                            payFlag = 1;
                            mSubmit.setClickable(false);
                            mSubmit.setBackgroundResource(R.color.grayDark);
                            save.setClickable(true);
                            save.setBackgroundResource(R.drawable.order_btn_press);
                        }
                        break;
                }
            }
        });

        tvList1.add(mUame);
        tvList1.add(mUsex);
        tvList1.add(mUdate);
        tvList1.add(mUcard);
        tvList1.add(mUprofession);
        tvList1.add(mUaddress);
        tvList1.add(mUQQ);
        tvList1.add(mUyibaoard);
        tvList1.add(mUremark);
        tvList1.add(mUprovince);

        tvList2.add(mYouyanqiujing);
        tvList2.add(mYouyanzhujing);
        tvList2.add(mYouyanzhouwei);
        tvList2.add(mYouguang);
        tvList2.add(mYouyantongju);
        tvList2.add(mZuoyanqiujing);
        tvList2.add(mZuoyanzhujing);
        tvList2.add(mZuoyanzhouwei);
        tvList2.add(mZuoyanxiajiaguang);
        tvList2.add(mZuoyantongju);
        tvList2.add(mYanguangfei);

        tvList3.add(mBrand);
        tvList3.add(mCategory);
        tvList3.add(mModel);
        tvList3.add(mColor);
        tvList3.add(mPackage);
        tvList3.add(mAmount);
        tvList3.add(mFee);
        tvList3.add(mRateFee);
        tvList3.add(mSumprice);
//        tvList3.add(mPaytype);
        mPhone = PreferencesUtils.getString(getActivity(), PreferencesUtils.key_current_user_phone);
        queryLocalDate(mPhone);
        return view;
    }

    private void initDateView(int id) {
        switch (id) {
            case 1:
                userInfo = mUserinfoList.get(0);
                mUame.setText(StringUtils.isEmpty(userInfo.getName()) ? nothingShow : userInfo.getName());
                mUsex.setText("1".equals(userInfo.getSex()) ? "男" : "2".equals(userInfo.getSex()) ? "女" : "保密");
                mUdate.setText(userInfo.getBirthday() <= 0 ? nothingShow : TimeUtils.getTime(userInfo.getBirthday(), TimeUtils.DATE_FORMAT_DATE));
                mUcard.setText(StringUtils.isEmpty(userInfo.getIdcard()) ? nothingShow : userInfo.getIdcard());
                phone.setText(userInfo.getPhone() + "");
                mUprofession.setText(StringUtils.isEmpty(userInfo.getJob()) ? nothingShow : userInfo.getJob());
                mUaddress.setText(StringUtils.isEmpty(userInfo.getAddress()) ? nothingShow : userInfo.getAddress());
                mUyibaoard.setText(StringUtils.isEmpty(userInfo.getMedicareId()) ? nothingShow : userInfo.getMedicareId());
                mUQQ.setText(StringUtils.isEmpty(userInfo.getQq()) ? nothingShow : userInfo.getQq());
                mUremark.setText(StringUtils.isEmpty(userInfo.getCustRemark()) ? nothingShow : userInfo.getCustRemark());
                mUprovince.setText(StringUtils.isEmpty(userInfo.getProvince()) ? nothingShow : userInfo.getProvince() + "-" + userInfo.getCity() + "-" + userInfo.getCounty());

                break;
            case 2:
                if (!OptometryFragment.isYangguang) {
                    mYangGuangBj.setVisibility(View.GONE);
                    mNoYanGuang.setChecked(true);
                    mAnGuang.setChecked(false);
                } else {
                    mNoYanGuang.setChecked(false);
                    mAnGuang.setChecked(true);
                    mYangGuangBj.setVisibility(View.VISIBLE);

                    optometryBean = mOptometryBeanList.get(0);


                    DecimalFormat    df   = new DecimalFormat("######0.00");
                    mYanguangfei.setText("￥"+df.format(Double.valueOf(optometryBean.getOptometryFee().equals("")?"0":optometryBean.getOptometryFee())));
                    /**
                     * 修改后添加的数据初始化
                     */

                    initYanguangDate(optometryBean);
                }
                break;
            case 3:
                glassesBean = mGlassesBeansList.get(0);
                mBrand.setText(StringUtils.isEmpty(glassesBean.getBrand()) ? nothingShow : glassesBean.getBrand());
                mCategory.setText(StringUtils.isEmpty(glassesBean.getCategory()) ? nothingShow : glassesBean.getCategory());
                mModel.setText(StringUtils.isEmpty(glassesBean.getModel()) ? nothingShow : glassesBean.getModel());
                mColor.setText(StringUtils.isEmpty(glassesBean.getColor()) ? nothingShow : glassesBean.getColor());
                mPackage.setText(StringUtils.isEmpty(glassesBean.getGoodPackage()) ? nothingShow : glassesBean.getGoodPackage());
                mAmount.setText(StringUtils.isEmpty(glassesBean.getAmount() + "") ? "1" : glassesBean.getAmount() + "");
                mFee.setText(StringUtils.isEmpty(glassesBean.getFee()) ? nothingShow : "￥"+glassesBean.getFee() + "×" + glassesBean.getAmount());
                mRateFee.setText(StringUtils.isEmpty(glassesBean.getRatefee()) ? nothingShow : "￥"+glassesBean.getRatefee() + "×" + glassesBean.getAmount());
                break;
        }
        mSumprice.setText((getTextValue(mRateFee) * getTextValue(mAmount) +getTextValue(mYanguangfei))+"");

    }

    private void initYanguangDate(OptometryBean optometryBean){
//        View view = getView();
        TextView orderdeatiremaker = (TextView) view.findViewById(R.id.order_deati_remaker);
        orderdeatiremaker.setText(optometryBean.getBeizhu()==null?nothingShow:optometryBean.getBeizhu());

        CheckBox ygcbchufang = (CheckBox) view.findViewById(R.id.yg_cb_chufang);//处方
        CheckBox ygcblaifang = (CheckBox) view.findViewById(R.id.yg_cb_laifang);//来方
        CheckBox ygcbyuanjing = (CheckBox) view.findViewById(R.id.yg_cb_yuanjing);//原镜
        if ("1".equals(optometryBean.getType())){
            ygcbchufang.setChecked(false);
            ygcblaifang.setChecked(false);
            ygcbyuanjing.setChecked(true);
        }else if ("2".equals(optometryBean.getType())){
            ygcbchufang.setChecked(false);
            ygcblaifang.setChecked(true);
            ygcbyuanjing.setChecked(false);
        }else if ("3".equals(optometryBean.getType())){
            ygcbchufang.setChecked(true);
            ygcblaifang.setChecked(false);
            ygcbyuanjing.setChecked(false);
        }else {
            ygcbchufang.setChecked(false);
            ygcblaifang.setChecked(false);
            ygcbyuanjing.setChecked(false);
        }

        TextView orderdeatilleftjiaozhengshili = (TextView) view.findViewById(R.id.order_deatil_left_jiaozhengshili);//矫正视力
        TextView orderdeatilleftjiujingshili = (TextView) view.findViewById(R.id.order_deatil_left_jiujingshili);//旧镜视力
        TextView orderdeatilleftluoyanshili = (TextView) view.findViewById(R.id.order_deatil_left_luoyanshili);//裸眼视力
        TextView orderdeatilleftb = (TextView) view.findViewById(R.id.order_deatil_left_b);//基底
        TextView orderdeatilleftp = (TextView) view.findViewById(R.id.order_deatil_left_p);//棱镜

        TextView orderdeatilrightjiaozhengshili = (TextView) view.findViewById(R.id.order_deatil_right_jiaozhengshili);//矫正视力
        TextView orderdeatilrightjiujingshili = (TextView) view.findViewById(R.id.order_deatil_right_jiujingshili);//旧镜视力
        TextView orderdeatilrightluoyanshili = (TextView) view.findViewById(R.id.order_deatil_right_luoyanshili);//裸眼视力
        TextView orderdeatilrightb = (TextView) view.findViewById(R.id.order_deatil_right_b);//基底
        TextView orderdeatilrightp = (TextView) view.findViewById(R.id.order_deatil_right_p);//棱镜


        RadioButton rbjin = (RadioButton) view.findViewById(R.id.rb_jin);//近
        RadioButton rbyuan = (RadioButton) view.findViewById(R.id.rb_yuan);//远

        if ("1".equals(optometryBean.getTypeDistance())){
            rbjin.setChecked(false);
            rbyuan.setChecked(true);
            orderdeatilleftjiaozhengshili.setText(optometryBean.getLeftZ());
            orderdeatilrightjiaozhengshili.setText(optometryBean.getRightZ());
            orderdeatilleftjiujingshili.setText(optometryBean.getLeftJ());
            orderdeatilrightjiujingshili.setText(optometryBean.getRightJ());
            orderdeatilleftluoyanshili.setText(optometryBean.getLeftL());
            orderdeatilrightluoyanshili.setText(optometryBean.getRightL());
            orderdeatilleftb.setText(optometryBean.getLeftB());
            orderdeatilrightb.setText(optometryBean.getRightB());
            orderdeatilleftp.setText(optometryBean.getLeftP());
            orderdeatilrightp.setText(optometryBean.getRightP());
            mYouyanqiujing.setText(optometryBean.getRightS());
            mYouyanzhujing.setText(optometryBean.getRightC());
            mYouyanzhouwei.setText(optometryBean.getRightA());
            mYouguang.setText(optometryBean.getRightAdd());
            mYouyantongju.setText(optometryBean.getRightPd());
            mZuoyanqiujing.setText(optometryBean.getLeftS());
            mZuoyanzhujing.setText(optometryBean.getLeftC());
            mZuoyanzhouwei.setText(optometryBean.getLeftA());
            mZuoyanxiajiaguang.setText(optometryBean.getLeftAdd());
            mZuoyantongju.setText(optometryBean.getLeftPd());


        }else if ("2".equals(optometryBean.getTypeDistance())){
            rbjin.setChecked(true);
            rbyuan.setChecked(false);
            orderdeatilleftjiaozhengshili.setText(optometryBean.getJleftZ());
            orderdeatilrightjiaozhengshili.setText(optometryBean.getJrightZ());
            orderdeatilleftjiujingshili.setText(optometryBean.getJleftJ());
            orderdeatilrightjiujingshili.setText(optometryBean.getJrightJ());
            orderdeatilleftluoyanshili.setText(optometryBean.getJleftL());
            orderdeatilrightluoyanshili.setText(optometryBean.getJrightL());
            orderdeatilleftb.setText(optometryBean.getJleftB());
            orderdeatilrightb.setText(optometryBean.getJrightB());
            orderdeatilleftp.setText(optometryBean.getJleftP());
            orderdeatilrightp.setText(optometryBean.getJrightP());
            mYouyanqiujing.setText(optometryBean.getJrightS());
            mYouyanzhujing.setText(optometryBean.getJrightC());
            mYouyanzhouwei.setText(optometryBean.getJrightA());
            mYouguang.setText(optometryBean.getJrightAdd());
            mYouyantongju.setText(optometryBean.getJrightPd());
            mZuoyanqiujing.setText(optometryBean.getJleftS());
            mZuoyanzhujing.setText(optometryBean.getJleftC());
            mZuoyanzhouwei.setText(optometryBean.getJleftA());
            mZuoyanxiajiaguang.setText(optometryBean.getJleftAdd());
            mZuoyantongju.setText(optometryBean.getJleftPd());
        }


        tvList2.add(orderdeatilleftjiaozhengshili);
        tvList2.add(orderdeatilrightjiaozhengshili);
        tvList2.add(orderdeatilleftjiujingshili);
        tvList2.add(orderdeatilrightjiujingshili);
        tvList2.add(orderdeatilleftluoyanshili);
        tvList2.add(orderdeatilrightluoyanshili);
        tvList2.add(orderdeatilleftb);
        tvList2.add(orderdeatilrightb);
        tvList2.add(orderdeatilleftp);
        tvList2.add(orderdeatilrightp);
        tvList2.add(orderdeatiremaker);
    }

    private Double getTextValue(TextView textView) {
        String s = textView.getText().toString().trim().replace("￥","");
        return Double.valueOf(StringUtils.isEmpty(s.split("×")[0]) ? "0.00" : s.split("×")[0]);
    }


    private void queryLocalDate(String phone) {
        mUserinfoList = liteOrm.query(new QueryBuilder<UserInfo>(UserInfo.class)
                .whereEquals("phone", phone));
        mGlassesBeansList = liteOrm.query(new QueryBuilder<GlassesBean>(GlassesBean.class)
                .whereEquals("phone", phone));
        mOptometryBeanList = liteOrm.query(new QueryBuilder<OptometryBean>(OptometryBean.class)
                .whereEquals("phone", phone));
        if (!ListUtils.isEmpty(mUserinfoList)) {
            if (mUserinfoList.size() > 0) {
                initDateView(1);
            }
        } else {
            clearAllEdit(tvList1);
        }
        if (!ListUtils.isEmpty(mOptometryBeanList)) {
            if (mOptometryBeanList.size() > 0) {
                initDateView(2);
            }
        } else {
            clearAllEdit(tvList2);
            mYangGuangBj.setVisibility(View.GONE);

        }
        if (!ListUtils.isEmpty(mGlassesBeansList)) {
            if (mGlassesBeansList.size() > 0) {
                initDateView(3);
            }
        } else {
            clearAllEdit(tvList3);
        }


    }

    /**
     * 清空编辑
     */
    private void clearAllEdit(List<TextView> tvList) {
        for (TextView textView : tvList) {
            textView.setText("");
        }
    }

    private void OrderOperate(int type) {
        if (!submitConfirm(mUame, PreferencesUtils.getString(getActivity(), PreferencesUtils.key_current_user_phone))) {
            return;
        }

        if (OptometryFragment.isYangguang) {
            if (ListUtils.isEmpty(mOptometryBeanList)) {
                Toast.makeText(getActivity(), "请填写验光信息!", Toast.LENGTH_SHORT).show();
                return;
            } else {
                if ("1".equals(mOptometryBeanList.get(0).getTypeDistance())) {
                    if (StringUtils.isEmpty(mOptometryBeanList.get(0).getRightPd()) | StringUtils.isEmpty(mOptometryBeanList.get(0).getLeftPd()) | StringUtils.isEmpty(mOptometryBeanList.get(0).getRightL()) | StringUtils.isEmpty(mOptometryBeanList.get(0).getLeftL()) | StringUtils.isEmpty(mOptometryBeanList.get(0).getRightZ()) |
                            StringUtils.isEmpty(mOptometryBeanList.get(0).getLeftZ()) | StringUtils.isEmpty(mOptometryBeanList.get(0).getType()) | StringUtils.isEmpty(mOptometryBeanList.get(0).getTypeDistance())
                            ) {
                        Toast.makeText(getActivity(), "请完善验光信息!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                } else if ("2".equals(mOptometryBeanList.get(0).getTypeDistance())) {
                    if (StringUtils.isEmpty(mOptometryBeanList.get(0).getJrightPd()) | StringUtils.isEmpty(mOptometryBeanList.get(0).getJleftPd()) | StringUtils.isEmpty(mOptometryBeanList.get(0).getJrightL()) | StringUtils.isEmpty(mOptometryBeanList.get(0).getJleftL()) | StringUtils.isEmpty(mOptometryBeanList.get(0).getJrightZ()) |
                            StringUtils.isEmpty(mOptometryBeanList.get(0).getJleftZ()) | StringUtils.isEmpty(mOptometryBeanList.get(0).getType()) | StringUtils.isEmpty(mOptometryBeanList.get(0).getTypeDistance())
                            ) {
                        Toast.makeText(getActivity(), "请完善验光信息!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
//                else if ("3".equals(mOptometryBeanList.get(0).getTypeDistance())) {
//                    if (StringUtils.isEmpty(mOptometryBeanList.get(0).getRightPd()) | StringUtils.isEmpty(mOptometryBeanList.get(0).getLeftPd()) | StringUtils.isEmpty(mOptometryBeanList.get(0).getRightL()) | StringUtils.isEmpty(mOptometryBeanList.get(0).getLeftL()) | StringUtils.isEmpty(mOptometryBeanList.get(0).getRightZ()) |
//                            StringUtils.isEmpty(mOptometryBeanList.get(0).getLeftZ()) | StringUtils.isEmpty(mOptometryBeanList.get(0).getType()) | StringUtils.isEmpty(mOptometryBeanList.get(0).getTypeDistance())
//                            ) {
//                        Toast.makeText(getActivity(), "请完善验光信息!", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                    if (StringUtils.isEmpty(mOptometryBeanList.get(0).getJrightPd()) | StringUtils.isEmpty(mOptometryBeanList.get(0).getJleftPd()) | StringUtils.isEmpty(mOptometryBeanList.get(0).getJrightL()) | StringUtils.isEmpty(mOptometryBeanList.get(0).getJleftL()) | StringUtils.isEmpty(mOptometryBeanList.get(0).getJrightZ()) |
//                            StringUtils.isEmpty(mOptometryBeanList.get(0).getJleftZ()) | StringUtils.isEmpty(mOptometryBeanList.get(0).getType()) | StringUtils.isEmpty(mOptometryBeanList.get(0).getTypeDistance())
//                            ) {
//                        Toast.makeText(getActivity(), "请完善验光信息!", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                }
            }

            if ("1".equals(mOptometryBeanList.get(0).getTypeDistance()) || "3".equals(mOptometryBeanList.get(0).getTypeDistance())) {
                if (!StringUtils.isEmpty(mOptometryBeanList.get(0).getRightC())) {
                    if (StringUtils.isEmpty(mOptometryBeanList.get(0).getRightA())) {
                        Toast.makeText(getActivity(), "光轴参数未填写！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                if (!StringUtils.isEmpty(mOptometryBeanList.get(0).getLeftC())) {
                    if (StringUtils.isEmpty(mOptometryBeanList.get(0).getLeftA())) {
                        Toast.makeText(getActivity(), "光轴参数未填写！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
            if ("2".equals(mOptometryBeanList.get(0).getTypeDistance()) || "3".equals(mOptometryBeanList.get(0).getTypeDistance())) {
                if (!StringUtils.isEmpty(mOptometryBeanList.get(0).getJrightC())) {
                    if (StringUtils.isEmpty(mOptometryBeanList.get(0).getJrightA())) {
                        Toast.makeText(getActivity(), "光轴参数未填写！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                if (!StringUtils.isEmpty(mOptometryBeanList.get(0).getJleftC())) {
                    if (StringUtils.isEmpty(mOptometryBeanList.get(0).getJleftA())) {
                        Toast.makeText(getActivity(), "光轴参数未填写！", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
            }
        }



        if (ListUtils.isEmpty(mGlassesBeansList))

        {
            Toast.makeText(getActivity(), "没有商品被选中!", Toast.LENGTH_SHORT).show();
            return;
        } else

        {

            if (StringUtils.isEmpty(mGlassesBeansList.get(0).getUserId())&&glassesBean.getType().equals("0")) {
                Toast.makeText(getActivity(), "商品信息错误!", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        switch (type)

        {
            case 1:
                Toast.makeText(getActivity(), "订单保存成功!", Toast.LENGTH_SHORT).show();
                getActivity().finish();
                break;
            case 2:

                setNetGoCallBackListener(new LoginUtils.NetGoCallBackListener() {
                    @Override
                    public void netOnClick() {
                        String token = PreferencesUtils.getString(getActivity(), PreferencesUtils.key_token);
                        String shopid = PreferencesUtils.getString(getActivity(), PreferencesUtils.key_id);
                       Log.i("info",token);
                        PostRequest postRequest = OkHttpUtils.post(NetPath.SUBMIT_ORDER)

                                .params("token", PreferencesUtils.getString(getActivity(), PreferencesUtils.key_token))
                                .params("shopid", PreferencesUtils.getString(getActivity(), PreferencesUtils.key_id))
                                .params("authstr", getAuthstr())
                                .params("idCard", mUcard.getText().toString())
                                .params("sex", userInfo.getSex())
                                .params("isShopaddress",userInfo.getIsShopaddress())
                                .params("job", mUprofession.getText().toString())
                                .params("birthday", mUdate.getText().toString())
                                .params("qq", mUQQ.getText().toString())
                                .params("medicareId", mUyibaoard.getText().toString())
                                .params("custRemark", mUremark.getText().toString())
                                .params("goodBrand", mBrand.getText().toString())
                                .params("goodCategory", mCategory.getText().toString())
                                .params("goodColor", mColor.getText().toString())
                                .params("goodPackage", mPackage.getText().toString())
                                .params("goodNum", mAmount.getText().toString())
                                .params("address", mUaddress.getText().toString())
                                .params("yd_pay", "true")
                                .params("province", StringUtils.isEmpty(userInfo.getProvince()) ? nothingShow : mUprovince.getText().toString().trim().split("-")[0])
                                .params("city", StringUtils.isEmpty(userInfo.getProvince()) ? nothingShow : mUprovince.getText().toString().trim().split("-")[1])
                                .params("county", StringUtils.isEmpty(userInfo.getProvince()) ? nothingShow : mUprovince.getText().toString().trim().split("-")[2])
                                .params("vto_arlensid",glassesBean.getArlensid());
                        if (!ListUtils.isEmpty(mOptometryBeanList) && OptometryFragment.isYangguang) {
                            if (mOptometryBeanList.size() > 0) {
                                postRequest.params("rightS", mYouyanqiujing.getText().toString())
                                        .params("leftS", mZuoyanqiujing.getText().toString())
                                        .params("rightC", mYouyanzhujing.getText().toString())
                                        .params("leftC", mZuoyanzhujing.getText().toString())
                                        .params("rightA", mYouyanzhouwei.getText().toString())
                                        .params("leftA", mZuoyanzhouwei.getText().toString())
                                        .params("rightAdd", mYouguang.getText().toString())
                                        .params("leftAdd", mZuoyanxiajiaguang.getText().toString())
                                        .params("leftPd", mZuoyantongju.getText().toString())
                                        .params("rightPd", mYouyantongju.getText().toString())
                                        .params("rightP", optometryBean.getRightP())
                                        .params("leftP", optometryBean.getLeftP())
                                        .params("rightB", optometryBean.getRightB())
                                        .params("leftB", optometryBean.getLeftB())
                                        .params("rightEye", optometryBean.getRightL())
                                        .params("leftEye", optometryBean.getLeftL())
                                        .params("rightOld", optometryBean.getRightJ())
                                        .params("leftOld", optometryBean.getLeftJ())
                                        .params("rightFix", optometryBean.getRightZ())
                                        .params("leftFix", optometryBean.getLeftZ())

                                        .params("crightS", optometryBean.getJrightS())
                                        .params("cleftS", optometryBean.getJleftS())
                                        .params("crightC", optometryBean.getJrightC())
                                        .params("cleftC", optometryBean.getJleftC())
                                        .params("crightA", optometryBean.getJrightA())
                                        .params("cleftA", optometryBean.getJleftA())
                                        .params("crightP", optometryBean.getJrightP())
                                        .params("cleftP", optometryBean.getJleftP())
                                        .params("crightB", optometryBean.getJrightB())
                                        .params("cleftB", optometryBean.getJleftB())
                                        .params("crightAdd", optometryBean.getJrightAdd())
                                        .params("cleftAdd", optometryBean.getJleftAdd())
                                        .params("crightPd", optometryBean.getJrightPd())
                                        .params("cleftPd", optometryBean.getJleftPd())
                                        .params("crightEye", optometryBean.getJrightL())
                                        .params("cleftEye", optometryBean.getJleftL())
                                        .params("crightOld", optometryBean.getJrightJ())
                                        .params("cleftOld", optometryBean.getJleftJ())
                                        .params("crightFix", optometryBean.getJrightZ())
                                        .params("cleftFix", optometryBean.getJleftZ())
                                        .params("crightFix", optometryBean.getJrightZ())
                                        .params("cleftFix", optometryBean.getJleftZ())
                                        .params("useType", optometryBean.getTypeDistance())
                                        .params("glassType", optometryBean.getType())
                                        .params("goodRemark", optometryBean.getBeizhu());
                            }

                        }


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
                                    if (status.equals("206")) {
//                                        if (payType.equals("4")) {
//                                            OrderWxPayBean.ResultMapBean bean = new Gson().fromJson(s, OrderWxPayBean.class).getResultMap();
//                                            Intent intent = new Intent();
//                                            intent.putExtra("fee", mSumprice.getText().toString());
//                                            intent.putExtra("ordersn", bean.getPrepay_id());
//                                            intent.putExtra("ordertype", bean.getTrade_type());
//                                            intent.putExtra("code", bean.getCode_url());
//                                            intent.setClass(getActivity(), WxPayActivity.class);
//                                            startActivity(intent);
//                                            Toast.makeText(getActivity(), "下单成功,跳转到支付页面!", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(getActivity(), YDPayActivity.class);
                                            SerializableMap map = new SerializableMap();
                                            map.setMap(setParams(jsonObject.getString("orderid")));
                                            Bundle bundle = new Bundle();
                                            bundle.putSerializable(YDPayActivity.PARAMS, map);
                                            bundle.putString(YDPayActivity.KEY, key);
//                                            bundle.putString("REQUEST_URL","https://dev.188yd.com/webapi/pay");
                                            intent.putExtras(bundle);
                                            startActivityForResult(intent, REQUEST_CODE);
//                                        } else {
//                                            Toast.makeText(getActivity(), "下单成功！", Toast.LENGTH_SHORT).show();
//                                        }
                                    } else {
                                        Toast.makeText(getActivity(), "下单失败！", Toast.LENGTH_SHORT).show();

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
                break;
        }

    }

    private String getAuthstr() {
        String s = "custName=" + mUame.getText().toString() + "&phone=" + PreferencesUtils.getString(getActivity(), PreferencesUtils.key_current_user_phone)
                + "&goodModel=" + mModel.getText().toString() + "&goodUserId=" + glassesBean.getUserId() + "&payFlag=" + payFlag + "&fee=" + getTextValue(mFee) + "&ratesFee=" + getTextValue(mRateFee) + "&otherFee=" + "0" + "&optometryFee=" + getTextValue(mYanguangfei) + "&payType=" + payType+"&order_type="+glassesBean.getType()+"&vto_frame_id="+glassesBean.getVto_frame_id();
       Log.i("info",s);
        return CodeUtil.appEncryMethod(s);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.order_deatil_Payali:
//            case R.id.order_deatil_PayUnion:
            case R.id.order_deatil_Payxianxia:
                rbXianxia.setChecked(true);
                mRgPay.setVisibility(View.VISIBLE);
                payType = "3";
                mNoPay.setChecked(true);
                mSubmit.setBackgroundResource(R.color.grayDark);
                mSubmit.setClickable(false);
                mReadyPay.setClickable(true);
                break;
            case R.id.order_deatil_Payweixin:
                rbWeixin.setChecked(true);
                payType = "1";
                payFlag=1;
                mSubmit.setClickable(true);
                save.setClickable(true);
                mSubmit.setBackgroundResource(R.drawable.order_btn_press);
                save.setBackgroundResource(R.drawable.order_btn_press);
                mNoPay.setChecked(true);
                mReadyPay.setClickable(false);
                break;
//            case R.id.order_deatil_nopay:
//                rbAli.setChecked(false);
//                rbYinlian.setChecked(false);
//                rbXianxia.setChecked(false);
//                rbWeixin.setChecked(false);
//                break;
            case R.id.order_deatil_Payxingfuka:
//                Intent intent = new Intent(getActivity(), YDPayActivity.class);
//                SerializableMap map = new SerializableMap();
//                map.setMap(setParams());
//                Bundle bundle = new Bundle();
//                bundle.putSerializable(YDPayActivity.PARAMS, map);
//                bundle.putString(YDPayActivity.KEY, key);
//                bundle.putString("REQUEST_URL","https://dev.188yd.com/webapi/pay");
//                intent.putExtras(bundle);
//                startActivityForResult(intent, REQUEST_CODE);
                break;
            default:
                break;
        }
    }


    public abstract class NoDoubleClickListener implements View.OnClickListener {

        public static final int MIN_CLICK_DELAY_TIME = 2000;
        private long lastClickTime = 0;

        @Override
        public void onClick(View v) {
            long currentTime = Calendar.getInstance().getTimeInMillis();
            if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {
                lastClickTime = currentTime;
                onNoDoubleClick(v);
            }
        }

        protected abstract void onNoDoubleClick(View v);

    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {

        } else {

            queryLocalDate(PreferencesUtils.getString(getActivity(), PreferencesUtils.key_current_user_phone));
        }
    }

    private boolean submitConfirm(TextView custName, String phone) {
        if (!TextUtils.isEmpty(custName.getText().toString().trim())) {
            if (!TextUtils.isEmpty(phone)) {
                return true;
            } else {
                Toast.makeText(getActivity(), "手机号未填写!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getActivity(), "姓名未填写!", Toast.LENGTH_SHORT).show();
        }

        return false;
    }

    /**
     * ��װ����
     *
     * amount
     * phoneNumber
     * outTradeNumber
     * callbackUrl
     * isScanPay
     * appId
     * timestamp
     * storeCode
     * operatorCode
     * sign
     *
     * @return SortedMap
     */

    private SortedMap<String, String> setParams(String oid) {
        SortedMap<String, String> params = new TreeMap<>();
//        params.put("amount","1");
        params.put("amount",(Float.valueOf(mSumprice.getText().toString().trim())*100)+"");
//        params.put("phoneNumber", phone.getText().toString().trim());
        params.put("phoneNumber", phone.getText().toString().trim());

        params.put("outTradeNumber", oid);
        params.put("callbackUrl", "http://buy1001.cn/xinfu/web/ydpay/callback.html");
//        params.put("callbackUrl", "http://www.yijava.com:8080/web/ydpay/callback.html");
        params.put("isScanPay", "");
        params.put("appId", "6478001245");
        params.put("timestamp", System.currentTimeMillis() + "");
        params.put("storeCode", "A001");
        params.put("operatorCode", "");

        StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
        MD5.buildPayParams(buf, params, false);
        String preStr = buf.toString();
        String sign = MD5.sign(preStr, "&key=" + key, "utf-8");
        params.put("sign", sign);

        return params;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == REQUEST_CODE){
                if(data != null){
                    SortedMap<String, String> map = ((SerializableMap) data.getExtras().get("result")).getMap();
                     if (map.get("return_code").equals("SUCCESS")) {
                         Toast.makeText(getActivity(),"订单提交成功",Toast.LENGTH_LONG).show();
                            getActivity().finish();
                        }
                }
            }
        }else {
            xuanzefapiao.setText(data.getStringExtra("title"));

        }
    }


}
