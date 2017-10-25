package com.remote.glasses.utils;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.EditText;

import com.remote.glasses.R;

/**
 * Created by Feics on 2016/7/21.
 */
public class JiDiUtil {
    private Activity activity;
    private NumberPickerDialog mDJ;

    //    private static JiDiUtil jiDiUtil;    //声明静态的单例对象的变量
//
//    public static JiDiUtil getSingle(Activity activity){    //外部通过此方法可以获取对象
//        if(jiDiUtil == null){
//            synchronized (JiDiUtil.class) {   //保证了同一时间只能只能有一个对象访问此同步块
//                if(jiDiUtil == null){
//                    jiDiUtil = new JiDiUtil(activity);
//                }
//            }
//        }
//        return jiDiUtil;   //返回创建好的对象
//    }
    public JiDiUtil(Activity activity, NumberPickerDialog dialog) {
        this.activity = activity;
        mDJ = dialog;
    }

    public Dialog diJiDialog(final EditText mEt) {
        if (mDJ != null) {
            if (mDJ.isShowing()) {
                return null;
            }
        }
//        mDJ = new NumberPickerDialog(activity);
        View v = View.inflate(activity, R.layout.dialog_jidi, null);
        mDJ.setContentView(v);
        mDJ.setCanceledOnTouchOutside(false);

        v.findViewById(R.id.bt_top).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  //上
                mEt.setBackgroundResource(R.drawable.top_bg);
                mEt.setText("上");
                mDJ.dismiss();
            }
        });
        v.findViewById(R.id.bt_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  //左
                mEt.setBackgroundResource(R.drawable.left_bg);
                mEt.setText("左");
                mDJ.dismiss();
            }
        });
        v.findViewById(R.id.bt_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  //右
                mEt.setBackgroundResource(R.drawable.right_bg);
                mEt.setText("右");
                mDJ.dismiss();
            }
        });
        v.findViewById(R.id.bt_bottom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  //下
                mEt.setBackgroundResource(R.drawable.bottom_bg);
                mEt.setText("下");
                mDJ.dismiss();
            }
        });
        v.findViewById(R.id.bt_cancle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEt.setBackgroundResource(0);
                mEt.setText("");
                mDJ.dismiss();
            }
        });

        mDJ.getWindow().setWindowAnimations(R.style.dialog_anim);
        mDJ.show();
        return mDJ;
    }


}
