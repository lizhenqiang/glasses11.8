package com.remote.glasses.activity.framents.mangerOrder;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.AbsCallback;
import com.lzy.okhttputils.request.PostRequest;
import com.remote.glasses.R;
import com.remote.glasses.activity.ManagerOrderActivity;
import com.remote.glasses.activity.YunDanActivity;
import com.remote.glasses.base.BaseActivity;
import com.remote.glasses.base.BaseFragment;
import com.remote.glasses.bean.OrderList;
import com.remote.glasses.set.NetPath;
import com.remote.glasses.utils.ActivityUtil;
import com.remote.glasses.utils.DateTimePickDialogUtil;
import com.remote.glasses.utils.LoginUtils;
import com.remote.glasses.utils.PreferencesUtils;
import com.remote.glasses.utils.TimeUtils;
import com.remote.glasses.utils.ToastUtil;
import com.remote.glasses.utils.ToolsCallback;
import com.remote.glasses.widget.MyGridView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

/**
 * Created by admin on 2016/7/8.
 */
public class ListOrderFragment extends BaseFragment implements View.OnClickListener{

    private int page = 1;//默认页
    private int size = 20;//每页条目数
    private boolean isMore = true;
    private List<OrderList.ListsBean> list = new ArrayList<OrderList.ListsBean>();
    private PullToRefreshListView lvOrderTrip;
    private OrderTripCAdapter orderTripCAdapter;//适配器
    private TextView begin;
    private TextView end;

    private boolean isTimeTrue = false;
    private String timeFalseMsg = "请选择时间";
    private ManagerOrderActivity managerOrderActivity;
    private EditText phone;
    private String phoneStr;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_order, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        managerOrderActivity = (ManagerOrderActivity) getActivity();

        phone = (EditText) getView().findViewById(R.id.manage_phone);
        LinearLayout beginTime = (LinearLayout) getView().findViewById(R.id.manage_begin_time);
        beginTime.setOnClickListener(this);
        LinearLayout endTime = (LinearLayout) getView().findViewById(R.id.manage_end_time);
        endTime.setOnClickListener(this);

        Button serch = (Button) getView().findViewById(R.id.manage_search);
        serch.setOnClickListener(this);

        begin = (TextView) getView().findViewById(R.id.manage_begin_timemsg);
        end = (TextView) getView().findViewById(R.id.manage_end_timemsg);

        lvOrderTrip = (PullToRefreshListView) getView().findViewById(R.id.manage_refresh_list);
        lvOrderTrip.setEmptyView(View.inflate(getActivity(), R.layout.empty_show_msg, null));
        orderTripCAdapter = new OrderTripCAdapter();
        //设置数适配
        lvOrderTrip.setAdapter(orderTripCAdapter);
        //设置刷新方式
        lvOrderTrip.setMode(PullToRefreshBase.Mode.BOTH);

        lvOrderTrip.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page = 1;
                isMore = true;
                loadFirst(1);
                new FinishRefresh().execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;
                loadFirst(0);
                new FinishRefresh().execute();
            }
        });
        lvOrderTrip.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0&&position!=list.size()+1){
                    managerOrderActivity.toDetatil(list.get(position-1));
                }
            }
        });

    }



    @Override
    public void onResume() {
        //加载数据
        loadFirst(1);
        super.onResume();
    }

    /**
     * 加载数据
     */
    private void loadFirst(final int index) {

        setNetGoCallBackListener(new LoginUtils.NetGoCallBackListener() {
            @Override
            public void netOnClick() {
                if (!isMore) {
                    ToastUtil.show(getActivity(), "没有更多数据");
                    return;
                }

                /**
                 * token  必填 默认按订单日期降序排列
                 shopid 店铺 ID 必填
                 phone 查询条件手机号 非必填
                 b_date 查询条件日期 开
                 始
                 非必填
                 e_date 查询条件日期 结
                 束
                 非必填
                 pagesize 页记录
                 cpage 当前页  返
                 */
                PostRequest request = OkHttpUtils.post(NetPath.QUERY_ORDER)//
                        .tag(getActivity().getApplicationContext())
                        .params("token", PreferencesUtils.getString(getActivity(), PreferencesUtils.key_token, ""))
                        .params("shopid", PreferencesUtils.getString(getActivity(), PreferencesUtils.key_id, ""))
                        .params("cpage", page + "")
                        .params("pagesize", size + "");

                phoneStr = phone.getText().toString().trim();
                if (ActivityUtil.isMobileNO(phoneStr)) {
                    request.params("phone", phoneStr);
                }

                if (isTimeTrue) {
                    request.params("b_date", begin.getText().toString().trim());
                    request.params("e_date", end.getText().toString().trim());
                }

                request.execute(new ToolsCallback<Object>(getActivity()) {
                    @Override
                    public Object parseNetworkResponse(Response response) {
                        return null;
                    }

                    @Override
                    public void onResponse(boolean isFromCache, Object o, okhttp3.Request request, okhttp3.Response response) {
                        if (isFromCache) {
                            return;
                        }
                        String responseData = null;
                        String status = "";
                        try {
                            responseData = response.body().string();
                            JSONObject jsonObject = new JSONObject(responseData);
                            status = jsonObject.optString("status", "");

                            if (status.equals("206")) {
                                Gson gson = new Gson();
                                OrderList orderList = gson.fromJson(responseData, OrderList.class);
                                List<OrderList.ListsBean> listsBean = orderList.getLists();
                                if (index == 1) {
                                    list.clear();
                                }
                                if (listsBean != null) {
                                    if (listsBean.size() > 0 && listsBean.size() == size) {
                                        list.addAll(listsBean);
                                    } else if (listsBean.size() > 0) {
                                        list.addAll(listsBean);
                                        isMore = false;
                                    } else {
                                        isMore = false;
                                        //无数据时提示信息
                                        ToastUtil.show(getActivity(),"没有更多信息");
                                    }
                                } else {
                                    //无数据时提示信息
                                    ToastUtil.show(getActivity(),"没有更多信息");
                                }

                            } else {
                                //无数据时提示信息
                                ToastUtil.show(getActivity(),"没有更多信息");
                            }

                            //完成刷新
                            lvOrderTrip.onRefreshComplete();
                            orderTripCAdapter.notifyDataSetChanged();

                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View v) {
        final String b = begin.getText().toString();
        final String e = end.getText().toString();
        switch (v.getId()){
            case R.id.manage_begin_time:

                DateTimePickDialogUtil picker = new DateTimePickDialogUtil(getActivity(),b.equals("开始日期")
                        ? TimeUtils.getTime(System.currentTimeMillis(),TimeUtils.DATE_FORMAT_DATE)+"":b);
                picker.setDateCompleteFace(new DateTimePickDialogUtil.DateCompleteFace() {
                    @Override
                    public void dateCompleteListener(boolean flag) {
                        if(flag){//点击确定后
                            timeIS(begin.getText().toString(),end.getText().toString());
                        }
                    }
                });
                picker.dateTimePicKDialog(begin, 0);

                break;

            case R.id.manage_end_time:

                DateTimePickDialogUtil picker1 = new DateTimePickDialogUtil(getActivity(),e.equals("结束日期")
                        ? TimeUtils.getTime(System.currentTimeMillis(), TimeUtils.DATE_FORMAT_DATE)+"":e);
                picker1.setDateCompleteFace(new DateTimePickDialogUtil.DateCompleteFace() {
                    @Override
                    public void dateCompleteListener(boolean flag) {
                        if(flag){//点击确定后
                            timeIS(begin.getText().toString(),end.getText().toString());
                        }
                    }
                });
                picker1.dateTimePicKDialog(end, 0);

                break;
            case R.id.manage_search:
                page = 1;
                isMore = true;
                loadFirst(1);

                break;
            default:
                break;
        }
    }


    private void timeIS(String b,String e){
        if(!b.equals("开始日期")&&!e.equals("结束日期")){
            if(TimeUtils.isT1T2(b,e,TimeUtils.DATE_FORMAT_DATE)){
                ToastUtil.show(getActivity(),"开始日期不得大于结束日期");
                timeFalseMsg = "开始日期不得大于结束日期";
                isTimeTrue = false;
            }else {
                isTimeTrue = true;
            }
        }else {
            timeFalseMsg = "请选择时间";
            isTimeTrue = false;
        }
    }

    /**
     * 适配器
     */
    class OrderTripCAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int v) {
            return 0;
        }

        @Override
        public View getView(int position, View convertview, ViewGroup viewGroup) {
            ViewHolder viewHolder=null;
            if(convertview == null){
                convertview = View.inflate(getActivity(),R.layout.list_order_imp,null);
                viewHolder = new ViewHolder();
                viewHolder.order_list_title17 = (LinearLayout)convertview.findViewById(R.id.order_list_title17);
                viewHolder.content1 = (TextView) convertview.findViewById(R.id.order_list_content1);
                viewHolder.title1 = (TextView) convertview.findViewById(R.id.order_list_title1);

                viewHolder.content2 = (TextView) convertview.findViewById(R.id.order_list_content2);
                viewHolder.title2 = (TextView) convertview.findViewById(R.id.order_list_title2);

                viewHolder.content3 = (TextView) convertview.findViewById(R.id.order_list_content3);
                viewHolder.title3 = (TextView) convertview.findViewById(R.id.order_list_title3);

                viewHolder.content4 = (TextView) convertview.findViewById(R.id.order_list_content4);
                viewHolder.title4 = (TextView) convertview.findViewById(R.id.order_list_title4);

                viewHolder.content5 = (TextView) convertview.findViewById(R.id.order_list_content5);
                viewHolder.title5 = (TextView) convertview.findViewById(R.id.order_list_title5);

                viewHolder.content6 = (TextView) convertview.findViewById(R.id.order_list_content6);
                viewHolder.title6 = (TextView) convertview.findViewById(R.id.order_list_title6);

                viewHolder.content7 = (TextView) convertview.findViewById(R.id.order_list_content7);
                viewHolder.title7 = (TextView) convertview.findViewById(R.id.order_list_title7);

                viewHolder.content8 = (TextView) convertview.findViewById(R.id.order_list_content8);
                viewHolder.title8 = (TextView) convertview.findViewById(R.id.order_list_title8);

                viewHolder.content9 = (TextView) convertview.findViewById(R.id.order_list_content9);
                viewHolder.title9 = (TextView) convertview.findViewById(R.id.order_list_title9);

                viewHolder.content10 = (TextView) convertview.findViewById(R.id.order_list_content10);
                viewHolder.title10 = (TextView) convertview.findViewById(R.id.order_list_title10);

                viewHolder.content11 = (TextView) convertview.findViewById(R.id.order_list_content11);
                viewHolder.title11 = (TextView) convertview.findViewById(R.id.order_list_title11);

                viewHolder.content12 = (TextView) convertview.findViewById(R.id.order_list_content12);
                viewHolder.title12 = (TextView) convertview.findViewById(R.id.order_list_title12);

                viewHolder.content13 = (TextView) convertview.findViewById(R.id.order_list_content13);
                viewHolder.title13 = (TextView) convertview.findViewById(R.id.order_list_title13);

                viewHolder.content14 = (TextView) convertview.findViewById(R.id.order_list_content14);
                viewHolder.title14 = (TextView) convertview.findViewById(R.id.order_list_title14);

                viewHolder.content15 = (TextView) convertview.findViewById(R.id.order_list_content15);
                viewHolder.title15 = (TextView) convertview.findViewById(R.id.order_list_title15);

                viewHolder.content16 = (TextView) convertview.findViewById(R.id.order_list_content16);
                viewHolder.title16 = (TextView) convertview.findViewById(R.id.order_list_title16);

                viewHolder.price = (TextView) convertview.findViewById(R.id.list_price);
                convertview.setTag(viewHolder);

                viewHolder.orderid = (TextView) convertview.findViewById(R.id.list_orderid);
            }else {
                viewHolder = (ViewHolder) convertview.getTag();
            }

            final OrderList.ListsBean bean = list.get(position);

            if(bean.getOrderType()!=null){
                if(bean.getOrderType().equals("2")&&bean.getPayFlag().equals("2")){
                    viewHolder.order_list_title17.setVisibility(View.VISIBLE);
                }else {
                    viewHolder.order_list_title17.setVisibility(View.GONE);
                }
            }

            viewHolder.price.setText("￥"+sumPrice(bean));
            viewHolder.orderid.setText(bean.getOrderid());
            viewHolder.order_list_title17.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i  = new Intent(getActivity(), YunDanActivity.class);
                    i.putExtra("id",bean.getId());
                    startActivity(i);
                }
            });
            ArrayList<String> strs = initOrderDate(bean);

            viewHolder.title1.setText(titles[0]);
            viewHolder.content1.setText(strs.get(0));

            viewHolder.title2.setText(titles[1]);
            viewHolder.content2.setText(strs.get(1));

            viewHolder.title3.setText(titles[2]);
            viewHolder.content3.setText(strs.get(2));

            viewHolder.title4.setText(titles[3]);
            viewHolder.content4.setText(strs.get(3));

            viewHolder.title5.setText(titles[4]);
            viewHolder.content5.setText(strs.get(4));

            viewHolder.title6.setText(titles[5]);
            viewHolder.content6.setText(strs.get(5));


            viewHolder.title7.setText(titles[6]);
            viewHolder.content7.setText(strs.get(6));

            viewHolder.title8.setText(titles[7]);
            viewHolder.content8.setText(strs.get(7));

            viewHolder.title9.setText(titles[8]);
            viewHolder.content9.setText(strs.get(8));

            viewHolder.title10.setText(titles[9]);
//            viewHolder.content10.setText(strs.get(9));
            viewHolder.content10.setText("");

            viewHolder.title11.setText(titles[10]);
            viewHolder.content11.setText(strs.get(10));

            viewHolder.title12.setText(titles[11]);
            viewHolder.content12.setText(strs.get(11));

            viewHolder.title13.setText(titles[12]);
            viewHolder.content13.setText(strs.get(12));

            viewHolder.title14.setText(titles[13]);
            viewHolder.content14.setText(strs.get(13));

            viewHolder.title15.setText(titles[14]);
            viewHolder.content15.setText(strs.get(14));

            viewHolder.title16.setText(titles[15]);
            viewHolder.content16.setText(strs.get(15));




            return convertview;
        }

        class ViewHolder{
            TextView title1,content1,title2,content2,title3,content3,title4,content4,
                    title5,content5,title6,content6,title7,content7,title8,content8,
                    title9,content9,title10,content10,title11,content11,title12,content12,
                    title13,content13,title14,content14,title15,content15,title16,content16;
            TextView price;
            TextView orderid;
            LinearLayout order_list_title17;
        }
    }
    String[] titles = new String[]{"品牌:","类别:","型号:","数量:",
            "颜色:","套餐:","","原价:",
            "验光费:","","","优惠价:",
            "姓名:","手机:","",""
    };
    private ArrayList initOrderDate(OrderList.ListsBean listsBean){

        String f = "￥";

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add(listsBean.getGoodBrand());//品牌
        arrayList.add(listsBean.getGoodCategory());//类别
        arrayList.add(listsBean.getGoodModel());//型号
        arrayList.add(listsBean.getGoodNum()+"");//数量

        arrayList.add(listsBean.getGoodColor());//颜色
        arrayList.add(listsBean.getGoodPackage());//套餐
        arrayList.add("");//
        arrayList.add("￥"+listsBean.getFee());//原价

        arrayList.add("￥"+listsBean.getOptometryFee());//验光费
        arrayList.add("￥"+Math.abs(listsBean.getOtherFee()));//引正费
        arrayList.add("");//
        arrayList.add("￥"+(listsBean.getRatesFee()));//优惠价

        arrayList.add(listsBean.getCustName());//姓名
        arrayList.add(listsBean.getPhone());//电话
        arrayList.add("");//
        arrayList.add("");//

        return arrayList;
    }

    private double sumPrice(OrderList.ListsBean listsBean){
        //优惠价 * 数量 +验光费+引正费
        return  listsBean.getRatesFee()*listsBean.getGoodNum()+Math.abs(listsBean.getOtherFee())+listsBean.getOptometryFee();
    }




    //加载完成
    private class FinishRefresh extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(1000);//延时一秒加载
            } catch (InterruptedException e) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            lvOrderTrip.onRefreshComplete();
            orderTripCAdapter.notifyDataSetChanged();
        }
    }

}
