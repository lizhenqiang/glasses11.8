package com.remote.glasses.utils;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.remote.glasses.R;

/**
 * Created by Feics on 2016/7/21.
 */
public class CircleRulerUtil {
    private Button  ensure, cancle;

    private CycleRulerView mCycleRulerView;
    private Activity activity;
    private TextView clear;

    private NumberPickerDialog mDialog;

    public CircleRulerUtil(Activity activity, NumberPickerDialog dialog) {
        this.activity = activity;
        mDialog = dialog;
    }

    public Dialog cycleRulerDialog(final EditText inputNumber) {
        if (mDialog!=null){
            if (mDialog.isShowing()){
                return null;
            }
        }
//        mDialog = new NumberPickerDialog(activity);
        View v = View.inflate(activity, R.layout.circleruler, null);
        mDialog.setContentView(v);
        mDialog.setCanceledOnTouchOutside(false);

        clear = (TextView) v.findViewById(R.id.guangzhou_clear);
        ensure = (Button) v.findViewById(R.id.guangzhou_left);
        cancle = (Button) v.findViewById(R.id.guangzhou_right);
        mCycleRulerView = (CycleRulerView) v.findViewById(R.id.guangzhou_cycleruler);
        init(inputNumber);

        ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                inputNumber.setText(mCycleRulerView.getKedu() + "");
                mDialog.dismiss();
            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                inputNumber.setText("");
            }
        });
        mDialog.getWindow().setWindowAnimations(R.style.dialog_anim);
        mDialog.show();
        return mDialog;
    }
    private void init(EditText inputNumber) {
        String value = inputNumber.getText().toString();

        if (!StringUtils.isEmpty(value)){

        }

    }
}
