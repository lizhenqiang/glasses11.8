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
 * Created by Feics on 2016/7/22.
 */
public class PDNumUtil {
    private WheelView numberA;
    private WheelView numberB;
    private Activity activity;
    private String[] ArrayNumberA = {"20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39","40"};
    private String[] ArrayNumberB = {"0.00","0.25","0.50"};
    private String shuA = "";
    private String shuB = "";
    private NumberPickerDialog mDialog;



    public PDNumUtil(Activity activity, NumberPickerDialog dialog) {
        this.activity = activity;
        mDialog = dialog;
    }

    public Dialog showDialog(final EditText inputNumber) {
        if (mDialog != null) {
            if (mDialog.isShowing()) {
                return null;
            }
        }
//        mDialog = new NumberPickerDialog(activity);
        View v = activity.getLayoutInflater().inflate(R.layout.pdnum, null);
        mDialog.setContentView(v);
        mDialog.setCanceledOnTouchOutside(false);

        numberA = (WheelView) v.findViewById(R.id.pd_num);
        numberB = (WheelView) v.findViewById(R.id.pd_num1);
        Button btnConfirm = (Button) v.findViewById(R.id.pd_left);
        Button btnCancle = (Button) v.findViewById(R.id.pd_right);

        TextView btnClear = (TextView) v.findViewById(R.id.pd_clear);
        init(inputNumber);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int Current = numberA.getCurrentItem();
//                shuA = ArrayNumberA[Current];
                shuA = numberA.getSeletedItem();
                shuB = numberB.getSeletedItem();
//                int bCurrent = numberB.getCurrentItem();
//                shuB = ArrayNumberB[bCurrent];
                java.text.DecimalFormat df = new java.text.DecimalFormat("0.00");
                String text = df.format(Double.valueOf(shuA) + Double.valueOf(shuB));
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
//        numberA.setViewAdapter(new ArrayWheelAdapter<String>(activity, ArrayNumberA));
//        numberA.setCurrentItem(0);
//        numberA.setVisibleItems(9);
//        numberB.setViewAdapter(new ArrayWheelAdapter<String>(activity, ArrayNumberB));
//        numberB.setCurrentItem(0);
//        numberB.setVisibleItems(5);
        numberA.setOffset(2);
        numberA.setItems(Arrays.asList(ArrayNumberA));
        numberA.setSeletion(0);
        numberB.setOffset(2);
        numberB.setItems(Arrays.asList(ArrayNumberB));
        numberB.setSeletion(0);
        if (!StringUtils.isEmpty(value)) {
            String[] values = value.split("\\.");
            if (values.length == 2) {
                numberA.setSeletion(Integer.valueOf(values[0]) - 20);
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
                }
            }
        }


    }
}
