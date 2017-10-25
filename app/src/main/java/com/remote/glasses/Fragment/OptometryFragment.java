package com.remote.glasses.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.remote.glasses.R;
import com.remote.glasses.base.BaseFragment;
import com.remote.glasses.base.MyApplication;
import com.remote.glasses.bean.OptometryBean;
import com.remote.glasses.utils.AddLightUtil;
import com.remote.glasses.utils.CircleRulerUtil;
import com.remote.glasses.utils.JiDiUtil;
import com.remote.glasses.utils.L;
import com.remote.glasses.utils.ListUtils;
import com.remote.glasses.utils.NumberPickerDialog;
import com.remote.glasses.utils.NumberPickerUtil;
import com.remote.glasses.utils.PDNumUtil;
import com.remote.glasses.utils.PreferencesUtils;
import com.remote.glasses.utils.StringUtils;
import com.remote.glasses.utils.VisionNumUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Feics on 2016/7/8.
 */
public class OptometryFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private List<EditText> etList = new ArrayList<>();
    //    private EditText ygNumber;
    private RadioGroup rbBj;
    private RadioButton rbYes;
    private RadioButton rbNo;
    //    private Button ygNunberYes;
    private EditText ygYanguangfei;
    private EditText ygYouyantongju;
    private EditText ygYouyanzhujing;
    private EditText ygYouyanqiujing;
    private EditText ygYouyanxiajiaguang;
    private EditText ygYouyanlengjing;
    private EditText ygYouyanjidi;
    private EditText ygYouyanluoyan;
    private EditText ygYouyanjiujing;
    private EditText ygYouyanjiaozheng;
    private EditText ygYouyanzhouwei;
    private EditText ygZuoyanqiujing;
    private EditText ygZuoyanxiajiaguang;
    private EditText ygZuoyantongju;
    private EditText ygZuoyanzhujing;
    private EditText ygZuoyanlengjing;
    private EditText ygZuoyanjidi;
    private EditText ygZuoyanluoyan;
    private EditText ygZuoyanjiujing;
    private EditText ygZuoyanjiaozheng;
    private EditText ygZuoyanzhouwei;

    private EditText jYanguangfei;
    private EditText jYouyantongju;
    private EditText jYouyanzhujing;
    private EditText jYouyanqiujing;
    private EditText jYouyanxiajiaguang;
    private EditText jYouyanlengjing;
    private EditText jYouyanjidi;
    private EditText jYouyanluoyan;
    private EditText jYouyanjiujing;
    private EditText jYouyanjiaozheng;
    private EditText jYouyanzhouwei;
    private EditText jZuoyanqiujing;
    private EditText jZuoyanxiajiaguang;
    private EditText jZuoyantongju;
    private EditText jZuoyanzhujing;
    private EditText jZuoyanlengjing;
    private EditText jZuoyanjidi;
    private EditText jZuoyanluoyan;
    private EditText jZuoyanjiujing;
    private EditText jZuoyanjiaozheng;
    private EditText jZuoyanzhouwei;
//    private ScrollView sv;

    private EditText ygBeizhu;

//    private TextView rAdd, lAdd, jrAdd, jlAdd;

    private CheckBox cbYuan, cbJin, cbYuanjing, cbChufang, cbLaifang;
    private String typeDistance = " ";
    private String type = " ";
    private LinearLayout data;
//    private LinearLayout search;

    private LiteOrm liteOrm;
    private OptometryBean optometry;
    private List<OptometryBean> mOptometryBeanList;
    private static String phone;
    public static boolean isYangguang = false;
    public NumberPickerDialog mDialog ;
    private AddLightUtil addLight;
    private NumberPickerUtil numberPicker;
    private PDNumUtil pdNum;
    private VisionNumUtil visionNum;
    private JiDiUtil jiDi;
    private CircleRulerUtil circleRuler;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication myApplication = (MyApplication) getActivity().getApplication();
        liteOrm = myApplication.liteOrm;
        mDialog = new NumberPickerDialog(getContext());
        addLight= new AddLightUtil(getActivity(),mDialog);
        numberPicker = new NumberPickerUtil(getActivity(),mDialog);
        pdNum= new PDNumUtil(getActivity(),mDialog);
        visionNum= new VisionNumUtil(getActivity(),mDialog);
        jiDi= new JiDiUtil(getActivity(),mDialog);
        circleRuler= new CircleRulerUtil(getActivity(),mDialog);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_optometry, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();

        rbNo = (RadioButton) view.findViewById(R.id.yg_no);//不验光
        rbYes = (RadioButton) view.findViewById(R.id.yg_yes);//验光
        rbBj = (RadioGroup) view.findViewById(R.id.yg_rg);//验光RadioGroup
        rbBj.setOnCheckedChangeListener(this);

//        sv = (ScrollView) view.findViewById(R.id.yg_sv);

//        ygNumber = (EditText)view.findViewById(R.id.yg_number);//流水号
//        ygNunberYes = (Button)view.findViewById(R.id.yg_number_yes);//确认

        ygYanguangfei = (EditText) view.findViewById(R.id.yg_yanguangfei);//验光费
        etList.add(ygYanguangfei);
        ygYouyantongju = (EditText) view.findViewById(R.id.yg_youyantongju);//右眼瞳距
        etList.add(ygYouyantongju);
        ygYouyantongju.setOnClickListener(this);
        ygYouyanzhujing = (EditText) view.findViewById(R.id.yg_youyanzhujing);//右眼柱镜
        etList.add(ygYouyanzhujing);
        ygYouyanzhujing.setOnClickListener(this);
        ygYouyanqiujing = (EditText) view.findViewById(R.id.yg_youyanqiujing);//右眼球镜
        etList.add(ygYouyanqiujing);
        ygYouyanqiujing.setOnClickListener(this);
        ygYouyanxiajiaguang = (EditText) view.findViewById(R.id.yg_youyanxiajiaguang);//右眼下加光
        etList.add(ygYouyanxiajiaguang);
        ygYouyanxiajiaguang.setOnClickListener(this);
        ygYouyanzhouwei = (EditText) view.findViewById(R.id.yg_youyanzhouwei);//右眼轴位
        etList.add(ygYouyanzhouwei);
        ygYouyanzhouwei.setOnClickListener(this);
        ygYouyanlengjing = (EditText) view.findViewById(R.id.yg_youyanlengjing);//右眼棱镜
        etList.add(ygYouyanlengjing);
        ygYouyanjidi = (EditText) view.findViewById(R.id.yg_youyanjidi);//右眼基底
        etList.add(ygYouyanjidi);
        ygYouyanjidi.setOnClickListener(this);
        ygYouyanluoyan = (EditText) view.findViewById(R.id.yg_youyanluoyan);//右眼裸眼
        etList.add(ygYouyanluoyan);
        ygYouyanluoyan.setOnClickListener(this);
        ygYouyanjiujing = (EditText) view.findViewById(R.id.yg_youyanjiujing);//右眼旧镜
        etList.add(ygYouyanjiujing);
        ygYouyanjiujing.setOnClickListener(this);
        ygYouyanjiaozheng = (EditText) view.findViewById(R.id.yg_youyanjiaozheng);//右眼矫正
        etList.add(ygYouyanjiaozheng);
        ygYouyanjiaozheng.setOnClickListener(this);


        ygZuoyanqiujing = (EditText) view.findViewById(R.id.yg_zuoyanqiujing);//左眼球镜
        etList.add(ygZuoyanqiujing);
        ygZuoyanqiujing.setOnClickListener(this);
        ygZuoyanxiajiaguang = (EditText) view.findViewById(R.id.yg_zuoyanxiajiaguang);//左眼下加光
        etList.add(ygZuoyanxiajiaguang);
        ygZuoyanxiajiaguang.setOnClickListener(this);
        ygZuoyantongju = (EditText) view.findViewById(R.id.yg_zuoyantongju);//左眼瞳距
        etList.add(ygZuoyantongju);
        ygZuoyantongju.setOnClickListener(this);
        ygZuoyanzhujing = (EditText) view.findViewById(R.id.yg_zuoyanzhujing);//左眼柱镜
        etList.add(ygZuoyanzhujing);
        ygZuoyanzhujing.setOnClickListener(this);
        ygZuoyanzhouwei = (EditText) view.findViewById(R.id.yg_zuoyanzhouwei);//左眼轴位
        etList.add(ygZuoyanzhouwei);
        ygZuoyanzhouwei.setOnClickListener(this);
        ygZuoyanlengjing = (EditText) view.findViewById(R.id.yg_zuoyanlengjing);//左眼棱镜
        etList.add(ygZuoyanlengjing);
        ygZuoyanjidi = (EditText) view.findViewById(R.id.yg_zuoyanjidi);//左眼基底
        etList.add(ygZuoyanjidi);
        ygZuoyanjidi.setOnClickListener(this);
        ygZuoyanluoyan = (EditText) view.findViewById(R.id.yg_zuoyanluoyan);//左眼裸眼
        etList.add(ygZuoyanluoyan);
        ygZuoyanluoyan.setOnClickListener(this);
        ygZuoyanjiujing = (EditText) view.findViewById(R.id.yg_zuoyanjiujing);//左眼旧镜
        etList.add(ygZuoyanjiujing);
        ygZuoyanjiujing.setOnClickListener(this);
        ygZuoyanjiaozheng = (EditText) view.findViewById(R.id.yg_zuoyanjiaozheng);//左眼矫正
        etList.add(ygZuoyanjiaozheng);
        ygZuoyanjiaozheng.setOnClickListener(this);


        //近
        jYouyantongju = (EditText) view.findViewById(R.id.yg_j_youyantongju);//右眼瞳距
        etList.add(jYouyantongju);
        jYouyantongju.setOnClickListener(this);
        jYouyanzhujing = (EditText) view.findViewById(R.id.yg_j_youyanzhujing);//右眼柱镜
        etList.add(jYouyanzhujing);
        jYouyanzhujing.setOnClickListener(this);
        jYouyanqiujing = (EditText) view.findViewById(R.id.yg_j_youyanqiujing);//右眼球镜
        etList.add(jYouyanqiujing);
        jYouyanqiujing.setOnClickListener(this);
        jYouyanxiajiaguang = (EditText) view.findViewById(R.id.yg_j_youyanxiajiaguang);//右眼下加光
        etList.add(jYouyanxiajiaguang);
        jYouyanxiajiaguang.setOnClickListener(this);
        jYouyanzhouwei = (EditText) view.findViewById(R.id.yg_j_youyanguangzhou);//右眼轴位
        etList.add(jYouyanzhouwei);
        jYouyanzhouwei.setOnClickListener(this);
        jYouyanlengjing = (EditText) view.findViewById(R.id.yg_j_youyanlengjing);//右眼棱镜
        etList.add(jYouyanlengjing);
        jYouyanjidi = (EditText) view.findViewById(R.id.yg_j_youyanjidi);//右眼基底
        etList.add(jYouyanjidi);
        jYouyanjidi.setOnClickListener(this);
        jYouyanluoyan = (EditText) view.findViewById(R.id.yg_j_youyanluoyan);//右眼裸眼
        etList.add(jYouyanluoyan);
        jYouyanluoyan.setOnClickListener(this);
        jYouyanjiujing = (EditText) view.findViewById(R.id.yg_j_youyanjiujing);//右眼旧镜
        etList.add(jYouyanjiujing);
        jYouyanjiujing.setOnClickListener(this);
        jYouyanjiaozheng = (EditText) view.findViewById(R.id.yg_j_youyanjiaozheng);//右眼矫正
        etList.add(jYouyanjiaozheng);
        jYouyanjiaozheng.setOnClickListener(this);

        jZuoyanqiujing = (EditText) view.findViewById(R.id.yg_j_zuoyanqiujing);//左眼球镜
        etList.add(jZuoyanqiujing);
        jZuoyanqiujing.setOnClickListener(this);
        jZuoyanxiajiaguang = (EditText) view.findViewById(R.id.yg_j_zuoyanxiajiaguang);//左眼下加光
        etList.add(jZuoyanxiajiaguang);
        jZuoyanxiajiaguang.setOnClickListener(this);
        jZuoyantongju = (EditText) view.findViewById(R.id.yg_j_zuoyantongju);//左眼瞳距
        etList.add(jZuoyantongju);
        jZuoyantongju.setOnClickListener(this);
        jZuoyanzhujing = (EditText) view.findViewById(R.id.yg_j_zuoyanzhujing);//左眼柱镜
        etList.add(jZuoyanzhujing);
        jZuoyanzhujing.setOnClickListener(this);
        jZuoyanzhouwei = (EditText) view.findViewById(R.id.yg_j_zuoyanguangzhou);//左眼轴位
        etList.add(jZuoyanzhouwei);
        jZuoyanzhouwei.setOnClickListener(this);
        jZuoyanlengjing = (EditText) view.findViewById(R.id.yg_j_zuoyanlengjing);//左眼棱镜
        etList.add(jZuoyanlengjing);
        jZuoyanjidi = (EditText) view.findViewById(R.id.yg_j_zuoyanjidi);//左眼基底
        etList.add(jZuoyanjidi);
        jZuoyanjidi.setOnClickListener(this);
        jZuoyanluoyan = (EditText) view.findViewById(R.id.yg_j_zuoyanluoyan);//左眼裸眼
        etList.add(jZuoyanluoyan);
        jZuoyanluoyan.setOnClickListener(this);
        jZuoyanjiujing = (EditText) view.findViewById(R.id.yg_j_zuoyanjiujing);//左眼旧镜
        etList.add(jZuoyanjiujing);
        jZuoyanjiujing.setOnClickListener(this);
        jZuoyanjiaozheng = (EditText) view.findViewById(R.id.yg_j_zuoyanjiaozheng);//左眼矫正
        etList.add(jZuoyanjiaozheng);
        jZuoyanjiaozheng.setOnClickListener(this);

        cbYuan = (CheckBox) view.findViewById(R.id.yg_cb_yuan);
        cbYuan.setOnClickListener(this);
        cbJin = (CheckBox) view.findViewById(R.id.yg_cb_jin);
        cbJin.setOnClickListener(this);
        cbYuanjing = (CheckBox) view.findViewById(R.id.yg_cb_yuanjing);
        cbYuanjing.setOnClickListener(this);
        cbLaifang = (CheckBox) view.findViewById(R.id.yg_cb_laifang);
        cbLaifang.setOnClickListener(this);
        cbChufang = (CheckBox) view.findViewById(R.id.yg_cb_chufang);
        cbChufang.setOnClickListener(this);

        ygBeizhu = (EditText) view.findViewById(R.id.yg_ed_beizhu);
        etList.add(ygBeizhu);
        data = (LinearLayout) view.findViewById(R.id.frag_optometry_data);

//        rAdd = (TextView) view.findViewById(R.id.sign_youyanxiajiaguang);
//        lAdd = (TextView) view.findViewById(R.id.sign_zuoyanxiajiaguang);
//        jrAdd = (TextView) view.findViewById(R.id.sign_j_youyanxiajiaguang);
//        jlAdd = (TextView) view.findViewById(R.id.sign_j_zuoyanxiajiaguang);
//
//        hiddenOrShow(ygYouyanxiajiaguang, rAdd);
//        hiddenOrShow(ygZuoyanxiajiaguang, lAdd);
//        hiddenOrShow(jYouyanxiajiaguang, jrAdd);
//        hiddenOrShow(jZuoyanxiajiaguang, jlAdd);

        associate(ygYouyanzhujing, ygYouyanzhouwei, "1");
        associate(ygZuoyanzhujing, ygZuoyanzhouwei, "1");
        associate(jYouyanzhujing, jYouyanzhouwei, "2");
        associate(jZuoyanzhujing, jZuoyanzhouwei, "2");

//        search = (LinearLayout) view.findViewById(R.id.frag_optometry_search);

        isYangguang = true;
        phone = PreferencesUtils.getString(getActivity(), PreferencesUtils.key_current_user_phone);

        queryLocalDate(phone);

    }

//    private void hiddenOrShow(final EditText editText, final TextView textView) {
//        editText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if (!StringUtils.isEmpty(editText.getText().toString())) {
//                    textView.setVisibility(View.VISIBLE);
//                } else {
//                    textView.setVisibility(View.GONE);
//
//                }
//
//
//            }
//        });
//        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (!hasFocus) {
//                    if (!StringUtils.isEmpty(editText.getText().toString())) {
//                        if (Double.valueOf(editText.getText().toString()) < 50.0) {
//                            ToastUtil.show(getActivity(), "请输入50~600之间的数字");
//                            editText.setText("");
//                        }
//                    }
//                }
//            }
//        });
//        InputFilter[] filters = {new EditInputFilter(getActivity())};
//        editText.setFilters(filters);
//    }

    private void associate(final EditText editText1, final EditText editText2, final String i) {
        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (typeDistance.equals(i) ) {
                    if (!StringUtils.isEmpty(editText1.getText().toString())) {
                        editText2.setHint("(必填)");
                    } else {
                        editText2.setHint(" ");

                    }
                }
            }
        });
    }

//    /**
//     * 是否可编辑 true 可编辑  / false 不可编辑
//     *
//     * @param flag
//     */
//    private void setEditEnable(boolean flag) {
//        for (EditText editText : etList) {
//            if (flag) {
//                editText.setFocusableInTouchMode(true);
//                editText.setFocusable(true);
//                editText.requestFocus();
//            } else {
//                editText.setFocusable(false);
//                editText.setFocusableInTouchMode(false);
//            }
//        }
//
//        sv.smoothScrollTo(0, 0);
//
//
//    }

    /**
     * 清空编辑
     */
    private void clearAllEdit() {
        for (EditText editText : etList) {
            editText.setText("");
        }
        cbYuan.setChecked(true);
        cbJin.setChecked(false);
        cbChufang.setChecked(false);
        cbYuanjing.setChecked(false);
        cbLaifang.setChecked(false);
        ygYouyanjidi.setBackgroundResource(0);
        ygZuoyanjidi.setBackgroundResource(0);
        jYouyanjidi.setBackgroundResource(0);
        jZuoyanjidi.setBackgroundResource(0);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.yg_no:
                data.setVisibility(View.GONE);
//                search.setVisibility(View.GONE);
                isYangguang = false;
                break;
            case R.id.yg_yes:
//                search.setVisibility(View.VISIBLE);
                data.setVisibility(View.VISIBLE);
                isYangguang = true;
                break;
        }
    }


    /**
     * 保存数据
     */
    private void onSave() {
        if (isYangguang) {
            optometry = new OptometryBean();
            optometry.setRightS(ygYouyanqiujing.getText().toString().trim());
            optometry.setLeftS(ygZuoyanqiujing.getText().toString().trim());
            optometry.setRightA(ygYouyanzhouwei.getText().toString().trim());
            optometry.setLeftA(ygZuoyanzhouwei.getText().toString().trim());
            optometry.setRightC(ygYouyanzhujing.getText().toString().trim());
            optometry.setLeftC(ygZuoyanzhujing.getText().toString().trim());
            optometry.setRightAdd(ygYouyanxiajiaguang.getText().toString().trim());
            optometry.setLeftAdd(ygZuoyanxiajiaguang.getText().toString().trim());
            optometry.setRightPd(ygYouyantongju.getText().toString().trim());
            optometry.setLeftPd(ygZuoyantongju.getText().toString().trim());
            optometry.setRightP(ygYouyanlengjing.getText().toString().trim());
            optometry.setLeftP(ygZuoyanlengjing.getText().toString().trim());
            optometry.setRightB(ygYouyanjidi.getText().toString().trim());
            optometry.setLeftB(ygZuoyanjidi.getText().toString().trim());
            optometry.setRightL(ygYouyanluoyan.getText().toString().trim());
            optometry.setLeftL(ygZuoyanluoyan.getText().toString().trim());
            optometry.setRightJ(ygYouyanjiujing.getText().toString().trim());
            optometry.setLeftJ(ygZuoyanjiujing.getText().toString().trim());
            optometry.setRightZ(ygYouyanjiaozheng.getText().toString().trim());
            optometry.setLeftZ(ygZuoyanjiaozheng.getText().toString().trim());

            optometry.setJrightS(jYouyanqiujing.getText().toString().trim());
            optometry.setJleftS(jZuoyanqiujing.getText().toString().trim());
            optometry.setJrightA(jYouyanzhouwei.getText().toString().trim());
            optometry.setJleftA(jZuoyanzhouwei.getText().toString().trim());
            optometry.setJrightC(jYouyanzhujing.getText().toString().trim());
            optometry.setJleftC(jZuoyanzhujing.getText().toString().trim());
            optometry.setJrightAdd(jYouyanxiajiaguang.getText().toString().trim());
            optometry.setJleftAdd(jZuoyanxiajiaguang.getText().toString().trim());
            optometry.setJrightPd(jYouyantongju.getText().toString().trim());
            optometry.setJleftPd(jZuoyantongju.getText().toString().trim());
            optometry.setJrightP(jYouyanlengjing.getText().toString().trim());
            optometry.setJleftP(jZuoyanlengjing.getText().toString().trim());
            optometry.setJrightB(jYouyanjidi.getText().toString().trim());
            optometry.setJleftB(jZuoyanjidi.getText().toString().trim());
            optometry.setJrightL(jYouyanluoyan.getText().toString().trim());
            optometry.setJleftL(jZuoyanluoyan.getText().toString().trim());
            optometry.setJrightJ(jYouyanjiujing.getText().toString().trim());
            optometry.setJleftJ(jZuoyanjiujing.getText().toString().trim());
            optometry.setJrightZ(jYouyanjiaozheng.getText().toString().trim());
            optometry.setJleftZ(jZuoyanjiaozheng.getText().toString().trim());

            if (cbYuan.isChecked()&&!cbJin.isChecked()){
                optometry.setTypeDistance("1");
            }else if (cbJin.isChecked()&&!cbYuan.isChecked()){
                optometry.setTypeDistance("2");
            }
//            else if (cbYuan.isChecked()&&cbJin.isChecked()){
//                optometry.setTypeDistance("3");
//            }
            if (cbYuanjing.isChecked()&&!cbChufang.isChecked()&&!cbLaifang.isChecked()){
                optometry.setType("1");
            }else if (!cbYuanjing.isChecked()&&!cbChufang.isChecked()&&cbLaifang.isChecked()){
                optometry.setType("2");
            }else if (!cbYuanjing.isChecked()&&cbChufang.isChecked()&&!cbLaifang.isChecked()){
                optometry.setType("3");
            }
            optometry.setBeizhu(ygBeizhu.getText().toString().trim());
            optometry.setOptometryFee(ygYanguangfei.getText().toString().trim());
            optometry.setPhone(PreferencesUtils.getString(getActivity(), PreferencesUtils.key_current_user_phone));

            liteOrm.save(optometry);
        }
    }

    @Override
    public void onPause() {
        L.e(phone + "onpause");
        onSave();
        super.onPause();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            onSave();
            L.e(phone + "hide");

        } else {

            queryLocalDate(PreferencesUtils.getString(getActivity(), PreferencesUtils.key_current_user_phone));
            L.e(phone + "show");
        }
    }

    private void queryLocalDate(String phone) {
        mOptometryBeanList = liteOrm.query(new QueryBuilder<OptometryBean>(OptometryBean.class)
                .whereEquals("phone", phone));
        if (!ListUtils.isEmpty(mOptometryBeanList)) {
            if (mOptometryBeanList.size() > 0) {
                initDateView(mOptometryBeanList.get(0));
            }
        } else {
            clearAllEdit();
        }
    }

    private void initDateView(OptometryBean optometry) {
        ygYouyanqiujing.setText(optometry.getRightS());
        ygZuoyanqiujing.setText(optometry.getLeftS());
        ygYouyanzhouwei.setText(optometry.getRightA());
        ygZuoyanzhouwei.setText(optometry.getLeftA());
        ygYouyanzhujing.setText(optometry.getRightC());
        ygZuoyanzhujing.setText(optometry.getLeftC());
        ygYouyanxiajiaguang.setText(optometry.getRightAdd());
        ygZuoyanxiajiaguang.setText(optometry.getLeftAdd());
        ygYouyantongju.setText(optometry.getRightPd());
        ygZuoyantongju.setText(optometry.getLeftPd());
        ygYouyanlengjing.setText(optometry.getRightP());
        ygZuoyanlengjing.setText(optometry.getLeftP());
        ygYouyanjidi.setText(optometry.getRightB());
        ygZuoyanjidi.setText(optometry.getLeftB());
        ygYouyanluoyan.setText(optometry.getRightL());
        ygZuoyanluoyan.setText(optometry.getLeftL());
        ygYouyanjiaozheng.setText(optometry.getRightZ());
        ygZuoyanjiaozheng.setText(optometry.getLeftZ());
        ygYouyanjiujing.setText(optometry.getRightJ());
        ygZuoyanjiujing.setText(optometry.getLeftJ());

        jYouyanqiujing.setText(optometry.getJrightS());
        jZuoyanqiujing.setText(optometry.getJleftS());
        jYouyanzhouwei.setText(optometry.getJrightA());
        jZuoyanzhouwei.setText(optometry.getJleftA());
        jYouyanzhujing.setText(optometry.getJrightC());
        jZuoyanzhujing.setText(optometry.getJleftC());
        jYouyanxiajiaguang.setText(optometry.getJrightAdd());
        jZuoyanxiajiaguang.setText(optometry.getJleftAdd());
        jYouyantongju.setText(optometry.getJrightPd());
        jZuoyantongju.setText(optometry.getJleftPd());
        jYouyanlengjing.setText(optometry.getJrightP());
        jZuoyanlengjing.setText(optometry.getJleftP());
        jYouyanjidi.setText(optometry.getJrightB());
        jZuoyanjidi.setText(optometry.getJleftB());
        jYouyanluoyan.setText(optometry.getJrightL());
        jZuoyanluoyan.setText(optometry.getJleftL());
        jYouyanjiaozheng.setText(optometry.getJrightZ());
        jZuoyanjiaozheng.setText(optometry.getJleftZ());
        jYouyanjiujing.setText(optometry.getJrightJ());
        jZuoyanjiujing.setText(optometry.getJleftJ());
        if(!StringUtils.isEmpty(optometry.getTypeDistance())) {
            if ("1".equals(optometry.getTypeDistance())) {
                cbYuan.setChecked(true);
                cbJin.setChecked(false);
                cbYuanisChecked();
                cbJinisNotChecked();
            } else if ("2".equals(optometry.getTypeDistance())) {
                cbJin.setChecked(true);
                cbYuan.setChecked(false);
                cbJinisChecked();
                cbYuanisNotChecked();
            }
//            else if ("3".equals(optometry.getTypeDistance())) {
//                cbJin.setChecked(true);
//                cbYuan.setChecked(true);
//                cbJinisChecked();
//                cbYuanisChecked();
//            }
        }
        if(!StringUtils.isEmpty(optometry.getType())) {

            switch (optometry.getType()) {
                case "1":
                    cbYuanjing.setChecked(true);
                    cbLaifang.setChecked(false);
                    cbChufang.setChecked(false);
                    break;
                case "2":
                    cbLaifang.setChecked(true);
                    cbYuanjing.setChecked(false);
                    cbChufang.setChecked(false);
                    break;
                case "3":
                    cbChufang.setChecked(true);
                    cbLaifang.setChecked(false);
                    cbYuanjing.setChecked(false);
                    break;
            }
        }else {
            cbYuanjing.setChecked(false);
            cbLaifang.setChecked(false);
            cbChufang.setChecked(false);

        }
        ygBeizhu.setText(optometry.getBeizhu());
        ygYanguangfei.setText(optometry.getOptometryFee());
        if(!StringUtils.isEmpty(optometry.getRightB())) {
            jidiSetbg(ygYouyanjidi, optometry.getRightB());
        }
        if(!StringUtils.isEmpty(optometry.getLeftB())) {
            jidiSetbg(ygZuoyanjidi, optometry.getLeftB());
        }
        if(!StringUtils.isEmpty(optometry.getJrightB())) {
            jidiSetbg(jYouyanjidi, optometry.getJrightB());
        }
        if(!StringUtils.isEmpty(optometry.getJleftB())) {
            jidiSetbg(jZuoyanjidi, optometry.getJleftB());
        }

    }

    private void jidiSetbg(EditText editText, String s) {
        switch (s) {
            case "上":
                editText.setBackgroundResource(R.drawable.top_bg);
                break;
            case "下":
                editText.setBackgroundResource(R.drawable.bottom_bg);
                break;
            case "左":
                editText.setBackgroundResource(R.drawable.left_bg);
                break;
            case "右":
                editText.setBackgroundResource(R.drawable.right_bg);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.yg_cb_yuan:
//                if (cbYuan.isChecked()) {
//                    cbYuanisChecked();
//                    if (cbJin.isChecked()) {
//                        cbJin.setChecked(false);
//                    } else {
//                        typeDistance = "1";
//                    }
//                } else {
//                    if (cbJin.isChecked()) {
//                        cbYuanisNotChecked();
//                        typeDistance = "2";
//                    } else {
//                        cbYuan.setChecked(true);
//                    }
//                }
                if (cbYuan.isChecked()) {
                    cbYuanisChecked();
                    cbJinisNotChecked();
                    cbJin.setChecked(false);
                    typeDistance = "1";
                } else {
                        cbYuan.setChecked(true);
                }
                break;
            case R.id.yg_cb_jin:
//                if (cbJin.isChecked()) {
//                    if (cbYuan.isChecked()) {
//                        cbJinisChecked();
//                        typeDistance = "3";
//                    } else {
//                        cbJinisChecked();
//                        typeDistance = "2";
//                    }
//                } else {
//                    if (cbYuan.isChecked()) {
//                        cbJinisNotChecked();
//                        typeDistance = "1";
//                    } else {
//                        cbJin.setChecked(true);
//                    }
//                }
                if (cbJin.isChecked()) {
                    cbJinisChecked();
                    cbYuanisNotChecked();
                    cbYuan.setChecked(false);
                    typeDistance = "2";
                } else {
                    cbJin.setChecked(true);
                }
                break;
            case R.id.yg_cb_chufang:
                cbYuanjing.setChecked(false);
                cbLaifang.setChecked(false);
                cbChufang.setChecked(true);
                type = "3";
                break;
            case R.id.yg_cb_laifang:
                cbYuanjing.setChecked(false);
                cbLaifang.setChecked(true);
                cbChufang.setChecked(false);
                type = "2";
                break;
            case R.id.yg_cb_yuanjing:
                cbYuanjing.setChecked(true);
                cbLaifang.setChecked(false);
                cbChufang.setChecked(false);
                type = "1";
                break;
            case R.id.yg_youyanqiujing:
            case R.id.yg_youyanzhujing:
            case R.id.yg_zuoyanqiujing:
            case R.id.yg_zuoyanzhujing:
            case R.id.yg_j_youyanzhujing:
            case R.id.yg_j_zuoyanzhujing:
            case R.id.yg_j_youyanqiujing:
            case R.id.yg_j_zuoyanqiujing:
                numberPicker.numberPicKDialog((EditText) v);
                break;
            case R.id.yg_youyanzhouwei:
            case R.id.yg_zuoyanzhouwei:
            case R.id.yg_j_youyanguangzhou:
            case R.id.yg_j_zuoyanguangzhou:
                circleRuler.cycleRulerDialog((EditText) v);
                break;

            case R.id.yg_youyanjidi:  //右眼基底
            case R.id.yg_zuoyanjidi:  //左眼基底
            case R.id.yg_j_youyanjidi:  //近-右眼基底
            case R.id.yg_j_zuoyanjidi:  //近-左眼基底
                jiDi.diJiDialog((EditText) v);
                break;
            case R.id.yg_youyanluoyan:
            case R.id.yg_zuoyanluoyan:
            case R.id.yg_youyanjiujing:
            case R.id.yg_zuoyanjiujing:
            case R.id.yg_youyanjiaozheng:
            case R.id.yg_zuoyanjiaozheng:
            case R.id.yg_j_youyanluoyan:
            case R.id.yg_j_zuoyanluoyan:
            case R.id.yg_j_youyanjiujing:
            case R.id.yg_j_zuoyanjiujing:
            case R.id.yg_j_youyanjiaozheng:
            case R.id.yg_j_zuoyanjiaozheng:
                visionNum.visionNumDialog((EditText) v);
                break;
            case R.id.yg_youyantongju:
            case R.id.yg_zuoyantongju:
            case R.id.yg_j_youyantongju:
            case R.id.yg_j_zuoyantongju:
                pdNum.showDialog((EditText) v);
                break;
            case R.id.yg_youyanxiajiaguang:
            case R.id.yg_zuoyanxiajiaguang:
            case R.id.yg_j_youyanxiajiaguang:
            case R.id.yg_j_zuoyanxiajiaguang:
                addLight.showDialog((EditText) v);
                break;

        }
    }

    private void cbYuanisNotChecked() {
        ygYouyantongju.setHint("");
        ygZuoyantongju.setHint("");
        ygYouyanluoyan.setHint("");
        ygZuoyanluoyan.setHint("");
        ygYouyanjiaozheng.setHint("");
        ygZuoyanjiaozheng.setHint("");
        ygYouyanzhouwei.setHint("");
        ygZuoyanzhouwei.setHint("");
    }

    private void cbJinisChecked() {
        jYouyantongju.setHint("(必填)");
        jZuoyantongju.setHint("(必填)");
        jYouyanluoyan.setHint("(必填)");
        jZuoyanluoyan.setHint("(必填)");
        jYouyanjiaozheng.setHint("(必填)");
        jZuoyanjiaozheng.setHint("(必填)");
        if (!StringUtils.isEmpty(jYouyanzhujing.getText().toString())) {
            jYouyanzhouwei.setHint("必填");
        }
        if (!StringUtils.isEmpty(jZuoyanzhujing.getText().toString())) {
            jZuoyanzhouwei.setHint("必填");
        }
    }

    private void cbJinisNotChecked() {
        jYouyantongju.setHint("");
        jZuoyantongju.setHint("");
        jYouyanluoyan.setHint("");
        jZuoyanluoyan.setHint("");
        jYouyanjiaozheng.setHint("");
        jZuoyanjiaozheng.setHint("");
        jZuoyanzhouwei.setHint("");
        jYouyanzhouwei.setHint("");
    }


    private void cbYuanisChecked() {
        ygYouyantongju.setHint("(必填)");
        ygZuoyantongju.setHint("(必填)");
        ygYouyanluoyan.setHint("(必填)");
        ygZuoyanluoyan.setHint("(必填)");
        ygYouyanjiaozheng.setHint("(必填)");
        ygZuoyanjiaozheng.setHint("(必填)");
        if (!StringUtils.isEmpty(ygYouyanzhujing.getText().toString())) {
            ygYouyanzhouwei.setHint("必填");
        }
        if (!StringUtils.isEmpty(ygZuoyanzhujing.getText().toString())) {
            ygZuoyanzhouwei.setHint("必填");
        }
    }

}
