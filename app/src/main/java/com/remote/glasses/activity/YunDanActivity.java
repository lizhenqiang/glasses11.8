package com.remote.glasses.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.AbsCallback;
import com.lzy.okhttputils.request.BaseRequest;
import com.lzy.okhttputils.request.GetRequest;
import com.remote.glasses.R;
import com.remote.glasses.base.BaseActivity;
import com.remote.glasses.set.NetPath;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public class YunDanActivity extends BaseActivity {


    private ListView listView;
    ArrayList<String> mylist = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    private ImageView back;
    private TextView title;
    @Override
    protected void initCreate(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
    setContentView(R.layout.activity_yun_dan);
        back = (ImageView)findViewById(R.id.back);
        title = (TextView)findViewById(R.id.titles);
        title.setText("订单查询");
        listView = (ListView)findViewById(R.id.listView);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        GetRequest request = OkHttpUtils.get(NetPath.GET_APP_VTO_STATE)//
                .tag(this)//:version(当前版本号) token 两者均为必填
//                        .params("version", ActivityUtil.getPhoneInfo(MainActivity.this, 4));
                .params("orderid", getIntent().getStringExtra("id"));
        request.execute(new AbsCallback<Object>() {

            @Override
            public void onBefore(BaseRequest request) {
                super.onBefore(request);
            }

            @Override
            public void onAfter(boolean isFromCache, Object o, Call call, Response response, Exception e) {
                super.onAfter(isFromCache, o, call, response, e);
            }

            @Override
            public Object parseNetworkResponse(Response response) {
                return null;
            }

            @Override
            public void onResponse(boolean isFromCache, Object o, Request request, Response response) {
                String s = null;
                try {
                    s = response.body().string();
                    JSONObject jsonObject = new JSONObject(s);
                    if(jsonObject.getString("status").equals("1")){
                        JSONArray jsonArray1 = jsonObject.getJSONArray("data");
                        for(int i = 0;i<jsonArray1.length();i++){
                            JSONObject jsonObject2 = jsonArray1.getJSONObject(i);
                            if(jsonObject2.has("createtime")&&jsonObject2.has("code")){
                                StringBuffer stringBuffer = new StringBuffer();
                                stringBuffer.append(jsonObject2.getString("createtime"))
                                        .append("  ")
                                        .append(jsonObject2.getString("code")+"  ");
                                if(jsonObject2.has("expresscom")){
                                    stringBuffer.append(jsonObject2.getString("expresscom")+"  ");
                                }
                                if(jsonObject2.has("expressno")){
                                    stringBuffer.append(jsonObject2.getString("expressno")+"  ");
                                }
                                mylist.add(stringBuffer.toString());
                            }
                            adapter = new ArrayAdapter<String>(YunDanActivity.this, R.layout.list_tv,mylist);
                            listView.setAdapter(adapter);
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
