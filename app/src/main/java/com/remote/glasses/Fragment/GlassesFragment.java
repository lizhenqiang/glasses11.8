package com.remote.glasses.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.remote.glasses.R;
import com.remote.glasses.base.BaseFragment;
import com.remote.glasses.base.MyApplication;
import com.remote.glasses.bean.GlassesBean;
import com.remote.glasses.utils.ListUtils;
import com.remote.glasses.utils.PreferencesUtils;
import com.zbar.lib.CaptureActivity;
import com.zhy.android.percent.support.PercentLinearLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLDecoder;
import java.util.ArrayList;

/**
 * Created by Feics on 2016/7/8.
 */
public class GlassesFragment extends BaseFragment implements View.OnClickListener {
    private Button scan, rescan,rescan2;
    private ScrollView glassInfo,goodsInfo;
    private ImageView goodsPhoto;
    private TextView brand, category, model, color, goodPackage, amount, fee, ratefee,name,price,vipPrice,barcode,skuNumber,description;
    private final static int SCAN = 1000;
    private LiteOrm liteOrm;
    private GlassesBean mGlassesBean;
    private ArrayList<GlassesBean> mGlassesBeans;
    private boolean flag = false;
    private RadioGroup rdg;
    private String arlensid = "657";
    private PercentLinearLayout percentLinearLayout;
    private View viewLines;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication myApplication = (MyApplication) getActivity().getApplication();
        liteOrm = myApplication.liteOrm;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_glasses, container, false);
        glassInfo = (ScrollView) view.findViewById(R.id.frag_glasses_sv_info);
        brand = (TextView) view.findViewById(R.id.frag_glasses_brand);
        category = (TextView) view.findViewById(R.id.frag_glasses_category);
        model = (TextView) view.findViewById(R.id.frag_glasses_model);
        color = (TextView) view.findViewById(R.id.frag_glasses_color);
        goodPackage = (TextView) view.findViewById(R.id.frag_glasses_package);
        amount = (TextView) view.findViewById(R.id.frag_glasses_amount);
        fee = (TextView) view.findViewById(R.id.frag_glasses_fee);
        ratefee = (TextView) view.findViewById(R.id.frag_glasses_ratefee);
        rdg = (RadioGroup)view.findViewById(R.id.rdg);
        scan = (Button) view.findViewById(R.id.frag_glasses_scan);
        percentLinearLayout = (PercentLinearLayout)view.findViewById(R.id.c_choice);
        viewLines = (View)view.findViewById(R.id.c_lines);

        //goods对应布局
        goodsInfo = (ScrollView) view.findViewById(R.id.frag_goods_sv_info);
        name = (TextView) view.findViewById(R.id.frag_goods_name);
        price = (TextView) view.findViewById(R.id.frag_goods_price);
        vipPrice = (TextView) view.findViewById(R.id.frag_goods_vipPrice);
        skuNumber = (TextView) view.findViewById(R.id.frag_goods_skuNumber);
        barcode = (TextView) view.findViewById(R.id.frag_goods_barcode);
        description = (TextView) view.findViewById(R.id.frag_goods_description);
        goodsPhoto = (ImageView) view.findViewById(R.id.frag_goods_photo);

        scan.setOnClickListener(this);


        rescan = (Button) view.findViewById(R.id.frag_glasses_rescan);
        rescan2 = (Button) view.findViewById(R.id.frag_goods_rescan2);
        rdg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.check1:
                        arlensid = "657";
                        break;
                    case R.id.check2:
                        arlensid = "658";
                        break;
                    case R.id.check3:
                        arlensid = "659";
                        break;
                    case R.id.check4:
                        arlensid = "660";
                        break;
                    case R.id.check5:
                        arlensid = "661";
                        break;
                }
                mGlassesBean.setArlensid(arlensid);
                liteOrm.save(mGlassesBean);
            }
        });
        rescan.setOnClickListener(this);
        rescan2.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), CaptureActivity.class);
        startActivityForResult(intent, SCAN);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && requestCode == SCAN && data != null) {

            scan.setVisibility(View.GONE);

            glassInfo.setVisibility(View.VISIBLE);

            String info = data.getStringExtra("glassesInfo");
            JSONObject jsonObject = null;

            try {
            jsonObject = new JSONObject(URLDecoder.decode(info, "UTF-8"));
            } catch (Exception e) {
                e.printStackTrace();
                flag = true;

            }
            if(data.getStringExtra("type").equals("1")){
                JSONObject jsonObject1 = null;
                try {
                    jsonObject1 = jsonObject.getJSONObject("data");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                mGlassesBean = new GlassesBean();
                mGlassesBean.setPhone(PreferencesUtils.getString(getActivity(), PreferencesUtils.key_current_user_phone));
                mGlassesBean.setBrand(jsonObject1.optString("brandName"));
                mGlassesBean.setCategory(jsonObject1.optString("isSuglasses").equals("0")?"":"太阳镜");
                mGlassesBean.setModel("");
                mGlassesBean.setColor(jsonObject1.optString("frameColor"));
                mGlassesBean.setGoodPackage("");
                mGlassesBean.setAmount(1);
                mGlassesBean.setFee(jsonObject1.optString("price"));
                mGlassesBean.setRatefee(jsonObject1.optString("price"));
                mGlassesBean.setType("2");
                mGlassesBean.setVto_frame_id(data.getStringExtra("framId"));
                mGlassesBean.setUserId("");
                mGlassesBean.setArlensid(arlensid);
                percentLinearLayout.setVisibility(View.VISIBLE);
                viewLines.setVisibility(View.VISIBLE);
            }else {
                mGlassesBean = new GlassesBean();
                mGlassesBean.setPhone(PreferencesUtils.getString(getActivity(), PreferencesUtils.key_current_user_phone));
                mGlassesBean.setBrand(jsonObject.optString("goodBrand"));
                mGlassesBean.setCategory(jsonObject.optString("goodCategory"));
                mGlassesBean.setModel(jsonObject.optString("goodModel"));
                mGlassesBean.setColor(jsonObject.optString("goodColor"));
                mGlassesBean.setGoodPackage(jsonObject.optString("goodPackage"));
                mGlassesBean.setAmount(jsonObject.optInt("amount"));
                mGlassesBean.setFee(jsonObject.optString("fee"));
                mGlassesBean.setRatefee(jsonObject.optString("rateFee"));
                mGlassesBean.setUserId(jsonObject.optString("userId"));
                mGlassesBean.setType("1");
                mGlassesBean.setVto_frame_id("");
                mGlassesBean.setArlensid(arlensid);
                percentLinearLayout.setVisibility(View.GONE);
                viewLines.setVisibility(View.GONE);
            }
            brand.setText(mGlassesBean.getBrand());
            category.setText(mGlassesBean.getCategory());
            model.setText(mGlassesBean.getModel());
            color.setText(mGlassesBean.getColor());
            goodPackage.setText(mGlassesBean.getGoodPackage());
            amount.setText(mGlassesBean.getAmount() + "");
            fee.setText(mGlassesBean.getFee()+ "×" + jsonObject.optInt("amount", 1));
            ratefee.setText(mGlassesBean.getRatefee() + "×" + jsonObject.optInt("amount", 1));
            liteOrm.save(mGlassesBean);

        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {

        } else {

            queryLocalDate(PreferencesUtils.getString(getActivity(), PreferencesUtils.key_current_user_phone));
        }
    }

    private void queryLocalDate(String phone) {
        mGlassesBeans = liteOrm.query(new QueryBuilder<GlassesBean>(GlassesBean.class)
                .whereEquals("phone", phone));
        if (ListUtils.isEmpty(mGlassesBeans)) {
            glassInfo.setVisibility(View.GONE);
            scan.setVisibility(View.VISIBLE);

        }
    }

}
