package com.remote.glasses.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.AbsCallback;
import com.lzy.okhttputils.request.PostRequest;
import com.remote.glasses.R;
import com.remote.glasses.activity.framents.mangerOrder.DeatilOrderFragment;
import com.remote.glasses.activity.framents.mangerOrder.ListOrderFragment;
import com.remote.glasses.base.BaseActivity;
import com.remote.glasses.bean.OrderList;
import com.remote.glasses.set.NetPath;
import com.remote.glasses.utils.DateTimePickDialogUtil;
import com.remote.glasses.utils.TimeUtils;
import com.remote.glasses.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Response;

public class ManagerOrderActivity extends BaseActivity{


    public Map<Integer,Fragment> fmap = new HashMap<>();
    private Button detailBack;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_manager_order);
    }

    @Override
    public void initView() {

        findViewById(R.id.manage_order_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        detailBack = (Button) findViewById(R.id.manage_detail_back);
        detailBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toList(new Object());
            }
        });

        fmap.put(0, new ListOrderFragment());
        fmap.put(1, new DeatilOrderFragment());

        getSupportFragmentManager().beginTransaction().add(R.id.manage_frame, fmap.get(0)).commit();

    }
    /**
     * 切换fragment
     */
    public void toDetatil(OrderList.ListsBean object){
        if(!fmap.get(1).isAdded()){
            detailBack.setVisibility(View.VISIBLE);
            Bundle bundle = new Bundle();
            bundle.putSerializable("Detail", object);
            fmap.get(1).setArguments(bundle);
            getSupportFragmentManager().beginTransaction().add(R.id.manage_frame, fmap.get(1)).hide(fmap.get(0)).commit();

        }
    }

    /**
     * 切换fragment
     */
    public void toList(Object object){
        detailBack.setVisibility(View.GONE);
        getSupportFragmentManager().beginTransaction().show(fmap.get(0)).remove(fmap.get(1)).commit();

    }

}