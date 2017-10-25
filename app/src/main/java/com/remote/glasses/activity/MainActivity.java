package com.remote.glasses.activity;

import android.app.ActivityManager;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.handmark.pulltorefresh.library.internal.Utils;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.AbsCallback;
import com.lzy.okhttputils.request.BaseRequest;
import com.lzy.okhttputils.request.PostRequest;
import com.panxw.android.imageindicator.AutoPlayManager;
import com.panxw.android.imageindicator.ImageIndicatorView;
import com.remote.glasses.R;
import com.remote.glasses.base.BaseActivity;
import com.remote.glasses.set.NetPath;
import com.remote.glasses.utils.AppManager;
import com.remote.glasses.utils.LoadingUtil;
import com.remote.glasses.utils.LoginUtils;
import com.remote.glasses.utils.PreferencesUtils;
import com.remote.glasses.utils.ToastUtil;
import com.remote.glasses.utils.update.UpdateManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    Integer[] resArray = new Integer[]{R.drawable.bannere};

    private TextView nameinfo;

    private TextView name;

    private boolean isUpdate = true;// 暂不开启 更新 false

    private long exitTime;
    private ImageIndicatorView imageIndicatorView;
    private AutoPlayManager autoBrocastManager;
    //声明AMapLocationClient类对象
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private String lat,lng;

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initView() {
        nameinfo = (TextView) findViewById(R.id.main_nameinfo);
        name = (TextView) findViewById(R.id.main_name);
        name.setOnClickListener(this);
        name.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        name.getPaint().setAntiAlias(true);//抗锯齿

        TextView create = (TextView) findViewById(R.id.main_create_from);
        create.setOnClickListener(this);
        TextView manage = (TextView) findViewById(R.id.main_manage_from);
        manage.setOnClickListener(this);
        TextView seting = (TextView) findViewById(R.id.main_setting);
        seting.setOnClickListener(this);
        TextView autoask = (TextView) findViewById(R.id.main_autoask);
        autoask.setOnClickListener(this);
        ImageView imageView = (ImageView) findViewById(R.id.img_view);
//        imageView.setImageResource(resArray[0]);
       initLocation();
        startLocation();
        //轮播
//        imageIndicatorView = (ImageIndicatorView) findViewById(R.id.indicate_view);
//        imageIndicatorView.setupLayoutByDrawable(resArray);
////        imageIndicatorView.setIndicateStyle(ImageIndicatorView.INDICATE_USERGUIDE_STYLE);
//        imageIndicatorView.show();
//
//        autoBrocastManager = new AutoPlayManager(imageIndicatorView);
//        autoBrocastManager.setBroadcastEnable(true);
//        autoBrocastManager.setBroadCastTimes(5);//loop times
//        autoBrocastManager.setBroadcastTimeIntevel(3 * 1000, 3 * 1000);//set first play time and interval
        update();
    }


    private void update() {
        if (isUpdate) {
//            try {
            PostRequest request = OkHttpUtils.post(NetPath.UPDATE_APP)//
                    .tag(getApplicationContext())//:version(当前版本号) token 两者均为必填

//                    .params("token", PreferencesUtils.getString(MainActivity.this, PreferencesUtils.key_token, ""))
//                        .params("version", ActivityUtil.getPhoneInfo(MainActivity.this, 4));
                    .params("version", getVersion());
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

                    UpdateManager updateManager = new UpdateManager(MainActivity.this);
                    try {
                        String s = response.body().string();
                        if (s != null) {
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(s);
                                String result = jsonObject.getString("result");
                                if(result.equals("success")){
                                    updateManager.checkUpdate(s);

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    } catch (IOException e) {
                    }
                }
            });
        }
    }

    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.main_name://登录
                Intent intent = new Intent(this, LoginActiviy.class);
                startActivity(intent);
                break;
            case R.id.main_create_from://创建订单
                setNetGoCallBackListener(new LoginUtils.NetGoCallBackListener() {
                    @Override
                    public void netOnClick() {
                        Intent create = new Intent(MainActivity.this, CreateOrderActivity.class);
                        startActivity(create);
                        LoginUtils.netGoCallBackListener = null;
                    }
                });

                break;
            case R.id.main_manage_from://订单管理
                setNetGoCallBackListener(new LoginUtils.NetGoCallBackListener() {
                    @Override
                    public void netOnClick() {
                        Intent manage = new Intent(MainActivity.this, ManagerOrderActivity.class);
                        startActivity(manage);
                        LoginUtils.netGoCallBackListener = null;
                    }
                });

                break;
            case R.id.main_setting://系统设置
                setNetGoCallBackListener(new LoginUtils.NetGoCallBackListener() {
                    @Override
                    public void netOnClick() {
                        Intent set = new Intent(MainActivity.this, SetingActivity.class);
                        startActivity(set);
                        LoginUtils.netGoCallBackListener = null;
                    }
                });
                break;
            case R.id.main_autoask://智能问答
                setNetGoCallBackListener(new LoginUtils.NetGoCallBackListener() {
                    @Override
                    public void netOnClick() {
                        Intent ask = new Intent(MainActivity.this, AskActivity.class);
                        startActivity(ask);
                        LoginUtils.netGoCallBackListener = null;
                    }
                });
            default:
                break;
        }
    }

    @Override
    protected void onResume() {

        if (LoginUtils.isLogin(this)) {
            nameinfo.setText(PreferencesUtils.getString(MainActivity.this, PreferencesUtils.key_name, ""));
            name.setVisibility(View.INVISIBLE);
        } else {
            autoLogin();
        }
//          不轮播
//        autoBrocastManager.loop();
        super.onResume();
    }

    private void autoLogin() {
        if (PreferencesUtils.getString(this, PreferencesUtils.key_username, "").equals("") || PreferencesUtils.getString(this, PreferencesUtils.key_passwd, "").equals("")) {
            ToastUtil.show(this, "请先登录");
            //第一次登录
            nameinfo.setText("");
            name.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
            name.getPaint().setAntiAlias(true);//抗锯齿
            name.setVisibility(View.VISIBLE);
        } else {
            LoginUtils.login(this, PreferencesUtils.getString(this, PreferencesUtils.key_username, ""), PreferencesUtils.getString(this, PreferencesUtils.key_passwd, ""));
            getGV(lat,lng);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO 按两次返回键退出应用程序
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // 判断间隔时间 大于2秒就退出应用
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                // 应用名
                String applicationName = getResources().getString(
                        R.string.app_name);
                String msg = "再按一次返回键退出" + applicationName;
                //String msg1 = "再按一次返回键回到桌面";
                LoadingUtil.show(MainActivity.this, msg, R.drawable.radius_black_backgroud, false);
                // 计算两次返回键按下的时间差
                exitTime = System.currentTimeMillis();
            } else {
                // 关闭应用程序
                LoadingUtil.dismis();
                ActivityManager am = (ActivityManager) getSystemService(MainActivity.this.ACTIVITY_SERVICE);
                am.killBackgroundProcesses(getPackageName());
                AppManager.getAppManager().finishAllActivity();
                System.exit(0);

            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void getGV(String lat,String lng) {
        PostRequest request = OkHttpUtils.post(NetPath.GET_APP_LOCtiON)//
                .tag(getApplicationContext())//:version(当前版本号) token 两者均为必填
                .params("id", PreferencesUtils.getString(MainActivity.this, PreferencesUtils.key_id, ""))
                .params("lat", lat)
                .params("lng", lng)
                .params("token", PreferencesUtils.getString(MainActivity.this, PreferencesUtils.key_token, ""));
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

                UpdateManager updateManager = new UpdateManager(MainActivity.this);
                try {
                    String s = response.body().string();
                } catch (IOException e) {
                }

            }
        });
    }
    /**
     * 定位监听
     */
    AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation location) {
            if (null != location) {

                StringBuffer sb = new StringBuffer();
                //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
                if(location.getErrorCode() == 0){
                    sb.append("定位成功" + "\n");
                    sb.append("定位类型: " + location.getLocationType() + "\n");
                    sb.append("经    度    : " + location.getLongitude() + "\n");
                    sb.append("纬    度    : " + location.getLatitude() + "\n");
                    sb.append("精    度    : " + location.getAccuracy() + "米" + "\n");
                    sb.append("提供者    : " + location.getProvider() + "\n");

                    sb.append("速    度    : " + location.getSpeed() + "米/秒" + "\n");
                    sb.append("角    度    : " + location.getBearing() + "\n");
                    // 获取当前提供定位服务的卫星个数
                    sb.append("星    数    : " + location.getSatellites() + "\n");
                    sb.append("国    家    : " + location.getCountry() + "\n");
                    sb.append("省            : " + location.getProvince() + "\n");
                    sb.append("市            : " + location.getCity() + "\n");
                    sb.append("城市编码 : " + location.getCityCode() + "\n");
                    sb.append("区            : " + location.getDistrict() + "\n");
                    sb.append("区域 码   : " + location.getAdCode() + "\n");
                    sb.append("地    址    : " + location.getAddress() + "\n");
                    sb.append("兴趣点    : " + location.getPoiName() + "\n");
                    //定位完成的时间
                    lat = location.getLatitude()+"";
                    lng = location.getLongitude()+"";
                } else {
                    //定位失败
                    sb.append("定位失败" + "\n");
                    sb.append("错误码:" + location.getErrorCode() + "\n");
                    sb.append("错误信息:" + location.getErrorInfo() + "\n");
                    sb.append("错误描述:" + location.getLocationDetail() + "\n");
                }
                //定位之后的回调时间
//                sb.append("回调时间: " + Utils.formatUTC(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss") + "\n");
                //解析定位结果，
                String result = sb.toString();
                Log.i("info",result);
                getGV(lat,lng);
            } else {
            }
        }
    };

    /**
     * 初始化定位
     *
     * @since 2.8.0
     * @author hongming.wang
     *
     */
    private void initLocation(){
        //初始化client
        locationClient = new AMapLocationClient(this.getApplicationContext());
        locationOption = getDefaultOption();
        //设置定位参数
        locationClient.setLocationOption(locationOption);
        // 设置定位监听
        locationClient.setLocationListener(locationListener);
    }

    /**
     * 默认的定位参数
     * @since 2.8.0
     * @author hongming.wang
     *
     */
    private AMapLocationClientOption getDefaultOption(){
        AMapLocationClientOption mOption = new AMapLocationClientOption();
        mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
        mOption.setGpsFirst(false);//可选，设置是否gps优先，只在高精度模式下有效。默认关闭
        mOption.setHttpTimeOut(30000);//可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
        mOption.setInterval(2000);//可选，设置定位间隔。默认为2秒
        mOption.setNeedAddress(true);//可选，设置是否返回逆地理地址信息。默认是true
        mOption.setOnceLocation(true);//可选，设置是否单次定位。默认是false
        mOption.setOnceLocationLatest(false);//可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
        AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
        mOption.setSensorEnable(false);//可选，设置是否使用传感器。默认是false
        mOption.setWifiScan(true); //可选，设置是否开启wifi扫描。默认为true，如果设置为false会同时停止主动刷新，停止以后完全依赖于系统刷新，定位位置可能存在误差
        mOption.setLocationCacheEnable(true); //可选，设置是否使用缓存定位，默认为true
        return mOption;
    }
    /**
     * 开始定位
     *
     * @since 2.8.0
     * @author hongming.wang
     *
     */
    private void startLocation(){
        //根据控件的选择，重新设置定位参数
        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
    }

    /**
     * 获取版本号
     * @return 当前应用的版本号
     */
    public String getVersion() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "1.0";
        }
    }
}

