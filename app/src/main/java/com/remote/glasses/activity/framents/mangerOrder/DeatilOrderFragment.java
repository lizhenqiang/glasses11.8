package com.remote.glasses.activity.framents.mangerOrder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.AbsCallback;
import com.lzy.okhttputils.request.BaseRequest;
import com.lzy.okhttputils.request.GetRequest;
import com.lzy.okhttputils.request.PostRequest;
import com.remote.glasses.R;
import com.remote.glasses.activity.MainActivity;
import com.remote.glasses.base.BaseFragment;
import com.remote.glasses.bean.OrderList;
import com.remote.glasses.set.NetPath;
import com.remote.glasses.utils.PreferencesUtils;
import com.remote.glasses.utils.StringUtils;
import com.remote.glasses.utils.TimeUtils;
import com.remote.glasses.utils.update.UpdateManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by admin on 2016/7/8.
 */
public class DeatilOrderFragment extends BaseFragment {

    private String nothing = "";
    private TextView order_type;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.order_detail,null);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

    OrderList.ListsBean bean = (OrderList.ListsBean) getArguments().getSerializable("Detail");

        View view = getView();
        order_type = (TextView)view.findViewById(R.id.order_type);

        if(bean.getOrderType().equals("2")){
            order_type.setVisibility(View.VISIBLE);

        }else {
            order_type.setVisibility(View.GONE);
        }
        /**
         * 支付方式
         */
        RadioGroup payType = (RadioGroup) view.findViewById(R.id.order_detail_payType);
        disableRadioGroup(payType);
        payType(bean);

        /**
         * 客户信息
         */
        TextView uame = (TextView) view.findViewById(R.id.order_deatil_uname);//姓名
        uame.setText(bean.getCustName());

        TextView usex = (TextView) view.findViewById(R.id.order_deatil_usex);//性别
        if(bean.getSex()!=null){
            usex.setText("1".equals(bean.getSex())?"男":"2".equals(bean.getSex())?"女":"保密");
        }else {
            usex.setText("保密");
        }

        TextView udate = (TextView) view.findViewById(R.id.order_deatil_udate);//出生日期
        if(bean.getBithday()!=null){
            udate.setText(bean.getBithday().equals("1970-01-01 00:00:00")?nothing:
                    TimeUtils.getTime(TimeUtils.getLongTime(bean.getBithday(),
                            TimeUtils.DEFAULT_DATE_FORMAT), TimeUtils.DATE_FORMAT_DATE));
        }else {
            udate.setText("");
        }


        TextView ucard = (TextView) view.findViewById(R.id.order_deatil_ucard);//身份证号
        if(bean.getIdCard()!=null){
            ucard.setText(bean.getIdCard());
        }


        TextView uprofession = (TextView) view.findViewById(R.id.order_deatil_uprofession);//职业
        if(bean.getJob()!=null){
            uprofession.setText(bean.getJob());
        }

        TextView uaddress = (TextView) view.findViewById(R.id.order_deatil_uaddress);//联系地址
        if(bean.getAddress()!=null){
            uaddress.setText(bean.getAddress());
        }


        TextView provice = (TextView) view.findViewById(R.id.order_deatil_provice);
        provice.setText(bean.getProvince()+"-"+bean.getCity()+"-"+bean.getCounty());

        TextView uQQ = (TextView) view.findViewById(R.id.order_deatil_uQQ);//QQ/微信号
        if(bean.getQq()!=null){
            uQQ.setText(bean.getQq());
        }

        TextView phone = (TextView) view.findViewById(R.id.order_deatil_uphone);
        phone.setText(bean.getPhone());

        TextView uyibaoard = (TextView) view.findViewById(R.id.order_deatil_uyibao_card);//医保卡号
        uyibaoard.setText(nothing);

        TextView ubeizhu = (TextView) view.findViewById(R.id.order_deatil_ubeizhu);//备注
        if(bean.getCustRemark()!=null) {
            ubeizhu.setText(bean.getCustRemark());
        }
        String m = "";

        /**
         * 布局及RaidoButton
         */
        RadioGroup rgPay = (RadioGroup) view.findViewById(R.id.order_deatil_rg_pay);//已付款 未付款
        RadioButton readyPay = (RadioButton) view.findViewById(R.id.order_deatil_pay);//已付款
        RadioButton noPay = (RadioButton) view.findViewById(R.id.order_deatil_nopay);//未付款

        readyPay.setEnabled(false);
        noPay.setEnabled(false);
        if(!StringUtils.isEmpty(bean.getPayFlag())){
            if(bean.getPayFlag().equals("2")){
                readyPay.setChecked(true);
                noPay.setChecked(false);
            }else {
                noPay.setChecked(true);
                readyPay.setChecked(false);
            }
            rgPay.setVisibility(View.VISIBLE);
        }else {
            rgPay.setVisibility(View.INVISIBLE);
        }


        RadioGroup rgYanGuang = (RadioGroup) view.findViewById(R.id.order_deatil_rg_yanguang);//验光 不验光
        RadioButton noYanGuang = (RadioButton) view.findViewById(R.id.order_deatil_no_yanguang);//不验光
        RadioButton anGuang = (RadioButton) view.findViewById(R.id.order_deatil_yanguang);//验光

        LinearLayout bottom = (LinearLayout) view.findViewById(R.id.order_deatil_bottombj);//最底部布局
        LinearLayout peiJingBj = (LinearLayout) view.findViewById(R.id.order_deatil_peijing_bj);//配镜布局
        LinearLayout yangGuangBj = (LinearLayout) view.findViewById(R.id.order_deatil_yanguang_bj);//验光布局
        if((TextUtils.isEmpty(bean.getRightPd())&&TextUtils.isEmpty(bean.getRightPd()))||(Double.valueOf(bean.getRightPd())==0&&Double.valueOf(bean.getLeftPd())==0)){
            noYanGuang.setChecked(true);
            yangGuangBj.setVisibility(View.GONE);
        }else {
            anGuang.setChecked(true);
            yangGuangBj.setVisibility(View.VISIBLE);
            /**
             * 验光数据
             */
            TextView youyanqiujing = (TextView) view.findViewById(R.id.order_deatil_yyouyanqiujing);//右眼球镜
            youyanqiujing.setText(bean.getRightS()+m);

            TextView youyanzhujing = (TextView) view.findViewById(R.id.order_deatil_yyouyanzhujing);//右眼柱镜
            youyanzhujing.setText(bean.getRightC()+m);

            TextView youyanzhouwei = (TextView) view.findViewById(R.id.order_deatil_yyouyanzhouwei);//右眼轴位
            youyanzhouwei.setText(bean.getRightA()+m);

            TextView youguang = (TextView) view.findViewById(R.id.order_deatil_yyouguang);//右眼下加光
            youguang.setText(bean.getRightAdd()+m);

            TextView youyantongju = (TextView) view.findViewById(R.id.order_deatil_yyouyantongju);//右眼瞳距
            youyantongju.setText(bean.getRightPd()+"㎜");


            TextView zuoyanqiujing = (TextView) view.findViewById(R.id.order_deatil_yzuoyanqiujing);//左眼球镜
            zuoyanqiujing.setText(bean.getLeftS()+m);

            TextView zuoyanzhujing = (TextView) view.findViewById(R.id.order_deatil_yzuoyanzhujing);//左眼柱镜
            zuoyanzhujing.setText(bean.getLeftC()+m);

            TextView zuoyanzhouwei = (TextView) view.findViewById(R.id.order_deatil_yzuoyanzhouwei);//左眼轴位
            zuoyanzhouwei.setText(bean.getLeftA()+m);

            TextView zuoyanxiajiaguang = (TextView) view.findViewById(R.id.order_deatil_yzuoyanxiajiaguang);//左眼下加光
            zuoyanxiajiaguang.setText(bean.getLeftAdd()+m);

            TextView zuoyantongju = (TextView) view.findViewById(R.id.order_deatil_yzuoyantongju);//左眼瞳距
            zuoyantongju.setText(bean.getLeftPd()+"㎜");

            TextView yanguangfei = (TextView) view.findViewById(R.id.order_deatil_yyanguangfei);//验光费
            yanguangfei.setText("￥"+bean.getOptometryFee());

            TextView yinzhengfei = (TextView) view.findViewById(R.id.order_deatil_yyinzhengfei);//引正费用
            yinzhengfei.setText(Math.abs(bean.getOtherFee())+"");

        }


        /**
         * 配镜数据
         */
        TextView pinpai = (TextView) view.findViewById(R.id.order_deatil_ypinpai);//品牌
        pinpai.setText(bean.getGoodBrand());

        TextView type = (TextView) view.findViewById(R.id.order_deatil_ytype);//类型
        type.setText(bean.getGoodCategory());

        TextView xinghao = (TextView) view.findViewById(R.id.order_deatil_yxinghao);//型号
        xinghao.setText(bean.getGoodModel());

        TextView color = (TextView) view.findViewById(R.id.order_deatil_ycolor);//颜色
        color.setText(bean.getGoodColor());

        TextView taocan = (TextView) view.findViewById(R.id.order_deatil_ytaocan);//套餐
        taocan.setText(bean.getGoodPackage());

        TextView number = (TextView) view.findViewById(R.id.order_deatil_ynumber);//数量
        number.setText(bean.getGoodNum()+"");

        TextView price = (TextView) view.findViewById(R.id.order_deatil_yprice);//价格
        price.setText("￥"+bean.getFee());

        TextView youhuiprice = (TextView) view.findViewById(R.id.order_deatil_yyouiprice);//优惠价
        youhuiprice.setText("￥"+bean.getRatesFee());

        TextView sumprice = (TextView) view.findViewById(R.id.order_deatil_ysumprice);//总价格
        sumprice.setText(sumPrice(bean)+"");

//        TextView paytype = (TextView) view.findViewById(R.id.order_deatil_ypaytype);//支付方式
//        if(bean.getPayType()!=null){
//            paytype.setText(payType(bean.getPayType(),bean));
//        }else {
//            paytype.setText("");
//        }



        LinearLayout orderOperate = (LinearLayout) view.findViewById(R.id.order_detail_ll);//提交订单
        orderOperate.setVisibility(View.GONE);
        /**
         * 新增字段
         */
        initYanguangDate(bean);

    }

    private double sumPrice(OrderList.ListsBean listsBean){
        //优惠价 * 数量 +验光费+引正费
        return  listsBean.getRatesFee()*listsBean.getGoodNum()+Math.abs(listsBean.getOtherFee())+listsBean.getOptometryFee();
    }

    /**
     * 支付方式
     * @param bean
     * @return
     */
    private String payType(OrderList.ListsBean bean){
        ((RadioButton)getView().findViewById(R.id.order_deatil_Payali)).setChecked(false);
        ((RadioButton)getView().findViewById(R.id.order_deatil_Payweixin)).setChecked(false);
        ((RadioButton)getView().findViewById(R.id.order_deatil_Payxianxia)).setChecked(false);
        ((RadioButton)getView().findViewById(R.id.order_deatil_PayUnion)).setChecked(false);
        if(bean.getPayType().equals("1")){
            ((RadioButton)getView().findViewById(R.id.order_deatil_Payweixin)).setChecked(true);
            return "微信支付";
        }else if(bean.getPayType().equals("2")){
            ((RadioButton)getView().findViewById(R.id.order_deatil_Payali)).setChecked(true);
            return "支付宝支付";
        }else if(bean.getPayType().equals("3")){
            ((RadioButton)getView().findViewById(R.id.order_deatil_Payxianxia)).setChecked(true);
            return "线下支付";
        }else {
            ((RadioButton)getView().findViewById(R.id.order_deatil_PayUnion)).setChecked(true);
            return "银联支付";
        }
    }

    private void initYanguangDate(OrderList.ListsBean bean){
        View view = getView();
        TextView orderdeatiremaker = (TextView) view.findViewById(R.id.order_deati_remaker);
        orderdeatiremaker.setText(bean.getGoodRemark()==null?nothing:bean.getGoodRemark());

        CheckBox ygcbchufang = (CheckBox) view.findViewById(R.id.yg_cb_chufang);//处方
        CheckBox ygcblaifang = (CheckBox) view.findViewById(R.id.yg_cb_laifang);//来方
        CheckBox ygcbyuanjing = (CheckBox) view.findViewById(R.id.yg_cb_yuanjing);//原镜
        // 1:原镜;2:来方;3:处方
        if("1".equals(bean.getGlassType())){
            ygcbyuanjing.setChecked(true);
        }else if("2".equals(bean.getGlassType())){
            ygcblaifang.setChecked(true);
        }else if("3".equals(bean.getGlassType())){
            ygcbchufang.setChecked(true);
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

        if("1".equals(bean.getUseType())){//// 用途(1:远;2:中)
            rbyuan.setChecked(true);
            //左
            orderdeatilleftjiaozhengshili.setText(bean.getLeftFix()+"");
            orderdeatilleftjiujingshili.setText(bean.getLeftOld()+"");
            orderdeatilleftluoyanshili.setText(bean.getLeftEye()+"");
            orderdeatilleftb.setText(bean.getLeftB()+"");
            orderdeatilleftp.setText(bean.getLeftP()+"");
            //右
            orderdeatilrightjiaozhengshili.setText(bean.getRightFix()+"");
            orderdeatilrightjiujingshili.setText(bean.getRightOld()+"");
            orderdeatilrightluoyanshili.setText(bean.getRightEye()+"");
            orderdeatilrightb.setText(bean.getRightB()+"");
            orderdeatilrightp.setText(bean.getRightP()+"");
        }else {

            rbjin.setChecked(true);
            //左
            orderdeatilleftjiaozhengshili.setText(bean.getCleftFix()+"");
            orderdeatilleftjiujingshili.setText(bean.getCleftOld()+"");
            orderdeatilleftluoyanshili.setText(bean.getCleftEye()+"");
            orderdeatilleftb.setText(bean.getCleftB()+"");
            orderdeatilleftp.setText(bean.getCleftP()+"");
            //右
            orderdeatilrightjiaozhengshili.setText(bean.getCrightFix()+"");
            orderdeatilrightjiujingshili.setText(bean.getCrightOld()+"");
            orderdeatilrightluoyanshili.setText(bean.getCrightEye()+"");
            orderdeatilrightb.setText(bean.getCrightB()+"");
            orderdeatilrightp.setText(bean.getCrightP()+"");
        }

    }


    public void disableRadioGroup(RadioGroup testRadioGroup) {
        for (int i = 0; i < testRadioGroup.getChildCount(); i++) {
            testRadioGroup.getChildAt(i).setEnabled(false);
        }
    }

    public void enableRadioGroup(RadioGroup testRadioGroup) {
        for (int i = 0; i < testRadioGroup.getChildCount(); i++) {
            testRadioGroup.getChildAt(i).setEnabled(true);
        }
    }


}
