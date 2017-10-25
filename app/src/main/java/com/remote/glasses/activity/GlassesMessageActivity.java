package com.remote.glasses.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.remote.glasses.R;
import com.remote.glasses.base.MyApplication;
import com.remote.glasses.bean.GlassesBean;
import com.remote.glasses.utils.ListUtils;
import com.remote.glasses.utils.PreferencesUtils;
import com.zbar.lib.CaptureActivity;

import org.json.JSONObject;

import java.net.URLDecoder;
import java.util.ArrayList;

public class GlassesMessageActivity extends Activity implements View.OnClickListener  {

    private Button scan, rescan;
    private ScrollView glassInfo;
    private TextView brand, category, model, color, goodPackage, amount, fee, ratefee,back;
    private final static int SCAN = 1000;
    private LiteOrm liteOrm;
    private GlassesBean mGlassesBean;
    private ArrayList<GlassesBean> mGlassesBeans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frag_glasses);
        MyApplication myApplication = (MyApplication) this.getApplication();
        liteOrm = myApplication.liteOrm;
        glassInfo = (ScrollView) findViewById(R.id.frag_glasses_sv_info);
        brand = (TextView) findViewById(R.id.frag_glasses_brand);
        category = (TextView) findViewById(R.id.frag_glasses_category);
        model = (TextView) findViewById(R.id.frag_glasses_model);
        color = (TextView) findViewById(R.id.frag_glasses_color);
        goodPackage = (TextView)findViewById(R.id.frag_glasses_package);
        amount = (TextView)findViewById(R.id.frag_glasses_amount);
        fee = (TextView)findViewById(R.id.frag_glasses_fee);
        ratefee = (TextView)findViewById(R.id.frag_glasses_ratefee);
        back = (TextView)findViewById(R.id.back);
        scan = (Button)findViewById(R.id.frag_glasses_scan);
        scan.setOnClickListener(this);

        rescan = (Button)findViewById(R.id.frag_glasses_rescan);
        rescan.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();
                break;
            default:
                Intent intent = new Intent(this, CaptureActivity.class);
                startActivityForResult(intent, SCAN);
                break;
        }

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
            }

            brand.setText(jsonObject.optString("goodBrand"));
            category.setText(jsonObject.optString("goodCategory"));
            model.setText(jsonObject.optString("goodModel"));
            color.setText(jsonObject.optString("goodColor"));
            goodPackage.setText(jsonObject.optString("goodPackage"));
            amount.setText(jsonObject.optInt("amount", 1) + "");
            fee.setText(jsonObject.optString("fee") + "×" + jsonObject.optInt("amount", 1));
            ratefee.setText(jsonObject.optString("rateFee") + "×" + jsonObject.optInt("amount", 1));

            mGlassesBean = new GlassesBean();
            mGlassesBean.setPhone(PreferencesUtils.getString(this, PreferencesUtils.key_current_user_phone));
            mGlassesBean.setBrand(jsonObject.optString("goodBrand"));
            mGlassesBean.setCategory(jsonObject.optString("goodCategory"));
            mGlassesBean.setModel(jsonObject.optString("goodModel"));
            mGlassesBean.setColor(jsonObject.optString("goodColor"));
            mGlassesBean.setGoodPackage(jsonObject.optString("goodPackage"));
            mGlassesBean.setAmount(jsonObject.optInt("amount"));
            mGlassesBean.setFee(jsonObject.optString("fee"));
            mGlassesBean.setRatefee(jsonObject.optString("rateFee"));
            mGlassesBean.setUserId(jsonObject.optString("userId"));
            liteOrm.save(mGlassesBean);

        }
    }

//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        if (hidden) {
//
//        } else {
//
//            queryLocalDate(PreferencesUtils.getString(this, PreferencesUtils.key_current_user_phone));
//        }
//    }

    private void queryLocalDate(String phone) {
        mGlassesBeans = liteOrm.query(new QueryBuilder<GlassesBean>(GlassesBean.class)
                .whereEquals("phone", phone));
        if (ListUtils.isEmpty(mGlassesBeans)) {
            glassInfo.setVisibility(View.GONE);
            scan.setVisibility(View.VISIBLE);

        }
    }
}
