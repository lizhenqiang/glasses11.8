package com.remote.glasses.utils;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
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
public class VisionNumUtil {
    private WheelView numberWv;
    private Activity activity;
    private String[] ArrayNumber = {"0.1", "0.2", "0.3", "0.4", "0.5", "0.6", "0.8", "1.0", "1.2", "1.5", "2.0"};
    private String shu = "";
    private NumberPickerDialog mDialog;


    public VisionNumUtil(Activity activity,NumberPickerDialog dialog) {  //私有构造方法
        this.activity = activity;
        this.mDialog = dialog;
    }

    public Dialog visionNumDialog(final EditText inputNumber) {
        if (mDialog != null) {
            if (mDialog.isShowing()) {
                return null;
            }
        }
//        mDialog = new NumberPickerDialog(activity);
//        View v = activity.getLayoutInflater().inflate(R.layout.visionnum, null);
        View v = LayoutInflater.from(activity).inflate(R.layout.visionnum, null);
        mDialog.setContentView(v);
        mDialog.setCanceledOnTouchOutside(false);

        numberWv = (WheelView) v.findViewById(R.id.vision_num);
        Button btnConfirm = (Button) v.findViewById(R.id.vision_left);
        Button btnCancle = (Button) v.findViewById(R.id.vision_right);

        TextView btnClear = (TextView) v.findViewById(R.id.vision_clear);
        init(inputNumber);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int Current = numberWv.getCurrentItem();
//                shu = ArrayNumber[Current];
                shu = numberWv.getSeletedItem();
                inputNumber.setText(shu);
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
        //        numberWv.setViewAdapter(new ArrayWheelAdapter<String>(activity, ArrayNumber));
//        numberWv.setCurrentItem(0);
//        numberWv.setVisibleItems(9);
        numberWv.setOffset(2);
        numberWv.setItems(Arrays.asList(ArrayNumber));
        numberWv.setSeletion(0);
//        numberWv.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
//            @Override
//            public void onSelected(int selectedIndex, String item) {
//
//            }
//        });
        if (!StringUtils.isEmpty(value)) {
//            String number = value.replaceAll("\\.", "");
            switch (value) {
                case "0.1":
                    numberWv.setSeletion(0);
                    break;
                case "0.2":
                    numberWv.setSeletion(1);
                    break;
                case "0.3":
                    numberWv.setSeletion(2);
                    break;
                case "0.4":
                    numberWv.setSeletion(3);
                    break;
                case "0.5":
                    numberWv.setSeletion(4);
                    break;
                case "0.6":
                    numberWv.setSeletion(5);
                    break;
                case "0.8":
                    numberWv.setSeletion(6);
                    break;
                case "1.0":
                    numberWv.setSeletion(7);
                    break;
                case "1.2":
                    numberWv.setSeletion(8);
                    break;
                case "1.5":
                    numberWv.setSeletion(9);
                    break;
                case "2.0":
                    numberWv.setSeletion(10);
                    break;

            }

        }

    }
}
