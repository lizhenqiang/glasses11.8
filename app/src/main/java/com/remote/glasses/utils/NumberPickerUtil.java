package com.remote.glasses.utils;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
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
public class NumberPickerUtil {
    private WheelView numberSign;
    private WheelView numberA;
    private WheelView numberB;
    private Activity activity;
    private String[] mSign = {"-", "+"};
    private String[] ArrayNumberA = {"0.00", "1.00", "2.00", "3.00", "4.00", "5.00", "6.00", "7.00", "8.00", "9.00", "10.00", "11.00", "12.00"};
    private String[] ArrayNumberB = {"0.00", "0.25", "0.50", "0.75"};
    private String fuhao = "";
    private String shuA = "";
    private String shuB = "";
    private NumberPickerDialog mDialog;

    public NumberPickerUtil(Activity activity, NumberPickerDialog dialog) {
        this.activity = activity;
        mDialog = dialog;
    }

    public Dialog numberPicKDialog(final EditText inputNumber) {
        if (mDialog != null) {
            if (mDialog.isShowing()) {
                return null;
            }
        }
//        mDialog = new NumberPickerDialog(activity);
        View v = activity.getLayoutInflater().inflate(R.layout.numberpicker, null);
        mDialog.setContentView(v);
        mDialog.setCanceledOnTouchOutside(false);

        numberSign = (WheelView) v.findViewById(R.id.id_NumberSign);
        numberA = (WheelView) v.findViewById(R.id.id_NumberA);
        numberB = (WheelView) v.findViewById(R.id.id_NumberB);
        Button btnConfirm = (Button) v.findViewById(R.id.number_left);
        Button btnCancle = (Button) v.findViewById(R.id.number_right);

        TextView btnClear = (TextView) v.findViewById(R.id.number_shutdown);
        init(inputNumber);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int sCurrent = numberSign.getCurrentItem();
//                fuhao = mSign[sCurrent];
//                int aCurrent = numberA.getCurrentItem();
//                shuA = ArrayNumberA[aCurrent];
//                int bCurrent = numberB.getCurrentItem();
//                shuB = ArrayNumberB[bCurrent];
                fuhao = numberSign.getSeletedItem();
                shuA = numberA.getSeletedItem();
                shuB = numberB.getSeletedItem();
                java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
                //方法二：
                String text = fuhao + df.format(Double.valueOf(shuA) + Double.valueOf(shuB));
                int len = text.length();
                SpannableStringBuilder style=new SpannableStringBuilder(text);
//                style.setSpan(new BackgroundColorSpan(Color.RED),1,len, Spannable.SPAN_EXCLUSIVE_INCLUSIVE); //设置指定位置textview的背景颜色
                if("+".equals(fuhao)){
                    style.setSpan(new ForegroundColorSpan(Color.GREEN),0,1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE); //设置指定位置文字的颜色
                }else {
                    style.setSpan(new ForegroundColorSpan(Color.RED),0,1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE); //设置指定位置文字的颜色
                }
                inputNumber.setText(style);
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
//        numberSign.setViewAdapter(new ArrayWheelAdapter<String>(activity, mSign));
//        numberSign.setVisibleItems(3);
//        numberA.setViewAdapter(new ArrayWheelAdapter<String>(activity, ArrayNumberA));
//        numberA.setCurrentItem(0);
//        numberA.setVisibleItems(9);
//        numberB.setViewAdapter(new ArrayWheelAdapter<String>(activity, ArrayNumberB));
//        numberB.setVisibleItems(7);
//        numberB.setCurrentItem(0);
        numberSign.setOffset(2);
        numberSign.setItems(Arrays.asList(mSign));
        numberSign.setSeletion(0);
        numberA.setOffset(2);
        numberA.setItems(Arrays.asList(ArrayNumberA));
        numberA.setSeletion(0);
        numberB.setOffset(2);
        numberB.setItems(Arrays.asList(ArrayNumberB));
        numberB.setSeletion(0);
        if (!StringUtils.isEmpty(value)) {
            String sign = value.substring(0, 1);
            if ("-".equals(sign)) {
                numberSign.setSeletion(0);
            } else {
                numberSign.setSeletion(1);
            }

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

    }

}
