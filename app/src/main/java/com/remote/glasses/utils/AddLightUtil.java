package com.remote.glasses.utils;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.remote.glasses.R;
import com.remote.glasses.widget.WheelView;

import java.util.Arrays;

/**
 * Created by Feics on 2016/7/20.
 */
public class AddLightUtil {
    private WheelView numberSign;
    private WheelView numberA;
    private WheelView numberB;
    private Activity activity;
    private String[] ArrayNumberB = {"0.00", "0.25", "0.50", "0.75"};
    private String[] Sign = {"+"};
    private String[] ArrayNumberA = { "0.00", "1.00", "2.00", "3.00", "4.00", "5.00", "6.00"};
    private String shuA = "";
    private String shuB = "";
//    private static AddLightUtil addlightUtil;    //声明静态的单例对象的变量
    private NumberPickerDialog mDialog;
//
//    public static AddLightUtil getSingle(Activity activity) {    //外部通过此方法可以获取对象
//        if (addlightUtil == null) {
//            synchronized (NumberPickerUtil.class) {   //保证了同一时间只能只能有一个对象访问此同步块
//                if (addlightUtil == null) {
//                    addlightUtil = new AddLightUtil(activity);
//                }
//            }
//        }
//        return addlightUtil;   //返回创建好的对象
//    }

    public AddLightUtil(Activity activity,NumberPickerDialog dialog) {  //私有构造方法
        this.activity = activity;
        this.mDialog = dialog;
    }

    public Dialog showDialog(final EditText inputNumber) {
        if (mDialog != null) {
            if (mDialog.isShowing()) {
                return null;
            }
        }
//        mDialog = new NumberPickerDialog(activity);
        View v = activity.getLayoutInflater().inflate(R.layout.addlight, null);
        mDialog.setContentView(v);
        mDialog.setCanceledOnTouchOutside(false);

        numberSign = (WheelView) v.findViewById(R.id.addlight_NumberA);
        numberA = (WheelView) v.findViewById(R.id.addlight_NumberB);
        numberB = (WheelView) v.findViewById(R.id.addlight_NumberC);
        Button btnConfirm = (Button) v.findViewById(R.id.addlight_left);
        Button btnCancle = (Button) v.findViewById(R.id.addlight_right);

        TextView btnClear = (TextView) v.findViewById(R.id.addlight_shutdown);
        init(inputNumber);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shuA = numberA.getSeletedItem();
                shuB = numberB.getSeletedItem();
                java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
                String text = "+" + df.format(Double.valueOf(shuA) + Double.valueOf(shuB));
                inputNumber.setText(text);
                mDialog.dismiss();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputNumber.setText("");
                mDialog.dismiss();
            }
        });
        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        mDialog.getWindow().setWindowAnimations(R.style.dialog_anim);
        mDialog.show();
        return mDialog;

    }

    private void init(EditText inputNumber) {

        String value = inputNumber.getText().toString();
        numberSign.setOffset(0);
        numberSign.setItems(Arrays.asList(Sign));
        numberSign.setSeletion(0);
        numberA.setOffset(2);
        numberA.setItems(Arrays.asList(ArrayNumberA));
        numberA.setSeletion(0);
        numberB.setOffset(2);
        numberB.setItems(Arrays.asList(ArrayNumberB));
        numberB.setSeletion(2);
        if (!StringUtils.isEmpty(value)) {
            String[] values = value.substring(1).split("\\.");
            if (values.length == 2) {
                numberA.setSeletion(Integer.parseInt(values[0]));
                switch (values[1]) {
                    case "00":
                        numberB.setSeletion(0);
                        break;
                    case "25":
                        numberB.setSeletion(1);
                        break;
                    case "50":
                        numberB.setSeletion(2);
                        break;
                    case "75":
                        numberB.setSeletion(3);
                        break;
                }
            }
        }

        //selectedIndex+offset
        numberA.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                if (numberB.getSeletedIndex() < 2 && selectedIndex == 2) {
                    numberA.setSeletion(1);
                }
                if (selectedIndex == 8) {
                    numberB.setSeletion(0);
                }
            }
        });
        numberB.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                if (numberA.getSeletedIndex() == 0 && selectedIndex < 5) {
                    numberB.setSeletion(2);
                }
            }
        });

    }

}
