package com.yidian.ydpaydemo;

import java.util.SortedMap;
import java.util.TreeMap;

import com.google.gson.Gson;
import com.seller.cash.activity.YDPayActivity;
import com.seller.cash.util.MD5;
import com.seller.cash.util.SerializableMap;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	public static final int REQUEST_CODE = 111;
	private EditText amount;
	private EditText phoneNumber;
	private EditText outTradeNumber;
	private EditText callbackUrl;
	private EditText appId;
	private EditText isScanPay;
	private EditText storeCode;
	private EditText operatorCode;
	private EditText key;
	private TextView tvResult;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		amount = (EditText) findViewById(R.id.amount);
		phoneNumber = (EditText) findViewById(R.id.phone);
		outTradeNumber = (EditText) findViewById(R.id.orderNum);
		isScanPay = (EditText) findViewById(R.id.isScan);
		appId = (EditText) findViewById(R.id.appId);
		storeCode = (EditText) findViewById(R.id.storeCode);
		operatorCode = (EditText) findViewById(R.id.operatorCode);
		key = (EditText) findViewById(R.id.key);
		callbackUrl = (EditText) findViewById(R.id.noticeUrl);
		tvResult = (TextView)findViewById(R.id.textview);

		Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, YDPayActivity.class);

				SerializableMap map = new SerializableMap();
				map.setMap(setParams());
				Bundle bundle = new Bundle();
				bundle.putSerializable(YDPayActivity.PARAMS, map);
				bundle.putString(YDPayActivity.KEY, key.getText().toString().trim());
				
				//参数：屏幕方向  （1为横屏，2为竖屏）
//				bundle.putInt(YDPayActivity.SCREEN_ORIENTATION,1);//横屏
				
				bundle.putString("REQUEST_URL", "https://test.188yd.com/webapi/pay");
				
				intent.putExtras(bundle);
				startActivityForResult(intent, REQUEST_CODE);
			}
		});
	}

	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK){
    		if(requestCode == REQUEST_CODE){
    			if(data != null){
    				//接收调用结果
    				SortedMap<String, String> map = ((SerializableMap) data.getExtras().get("result")).getMap();
    				tvResult.setText("输出结果 ：" + map.toString());
    			}
    		}
    	}
	}

	/**
	 * 封装参数  
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
	 * goodsList
	 * sign
	 * 
	 * @return SortedMap
	 */
	
	private SortedMap<String, String> setParams() {
		SortedMap<String, String> params = new TreeMap<String, String>();
		params.put("amount", amount.getText().toString().trim());
		params.put("phoneNumber", phoneNumber.getText().toString().trim());
		params.put("outTradeNumber", outTradeNumber.getText().toString().trim());
		params.put("callbackUrl", callbackUrl.getText().toString().trim());
		params.put("isScanPay", isScanPay.getText().toString().trim());
		params.put("appId", appId.getText().toString().trim());
		params.put("timestamp", System.currentTimeMillis() + "");
		params.put("storeCode", storeCode.getText().toString().trim());
		params.put("operatorCode", operatorCode.getText().toString().trim());
		params.put("goodsList", createGoodsListString());

		StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
        MD5.buildPayParams(buf, params, false);
        String preStr = buf.toString();
        
        String sign = MD5.sign(preStr, "&key=" + key.getText().toString().trim(), "utf-8");
        
        params.put("sign", sign);
		
		return params;
	}

	private String createGoodsListString(){
		String goodsListStr = "";
		SortedMap<String, String> map = new TreeMap<String,String>();
		//key --- 商品条形码
		//value --- 商品数量
		map.put("00001111","1");
		map.put("11110000","2");
		map.put("00002222","1");
		Gson gson = new Gson();
		goodsListStr = gson.toJson(map);
		return goodsListStr;
	}

}
