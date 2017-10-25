package com.remote.glasses.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.remote.glasses.R;
import com.remote.glasses.base.BaseActivity;
import com.remote.glasses.utils.QRCodeUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WxPayActivity extends BaseActivity {
    private Button close;
    private TextView orderSn;
    private TextView orderType;
    private TextView fee;
    private ImageView paycode;
    private String filePath;
    private Bitmap bitmap;


    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_wx_pay);

    }
    @Override
    public void initView() {
        close = (Button) findViewById(R.id.wxpay_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        orderSn = (TextView) findViewById(R.id.orderSn);
        orderType = (TextView) findViewById(R.id.orderType);
        fee = (TextView) findViewById(R.id.fee);
        paycode = (ImageView) findViewById(R.id.paycode);

        setData();
    }

    private void setData() {
        Intent intent = getIntent();
        fee.setText(intent.getStringExtra("fee"));
        orderSn.setText(intent.getStringExtra("ordersn"));
        orderType.setText(intent.getStringExtra("ordertype"));

        filePath = "/data/data/" + getPackageName() + "/cache/"+"pay.jpg";
        if (QRCodeUtil.createQRImage(intent.getStringExtra("code"),300,300,null,filePath)){
            bitmap = getLoacalBitmap(filePath);
            paycode.setImageBitmap(bitmap);
        }

    }
    /**
     * 加载本地图片
     * @param url
     * @return
     */
    public  static Bitmap getLoacalBitmap(String url) {
        FileInputStream fis=null;
        try {
            fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }finally {
            if (fis!=null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(bitmap!=null&&!bitmap.isRecycled() ){
            bitmap.recycle() ;  //回收图片所占的内存
            System.gc();//提醒系统及时回收
        }
    }
}
