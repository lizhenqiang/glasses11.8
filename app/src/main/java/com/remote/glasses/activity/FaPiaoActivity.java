package com.remote.glasses.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.remote.glasses.R;
import com.remote.glasses.base.BaseActivity;

public class FaPiaoActivity extends BaseActivity {

    private ImageView back;
    private Button btnsave;
    private LinearLayout line1;
    private TextView textView;
    private RadioGroup radioGroup;
    private EditText danwei;


    @Override
    protected void initCreate(Bundle savedInstanceState) {

    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_fa_piao);
        back = (ImageView)findViewById(R.id.back);
        danwei = (EditText)findViewById(R.id.danwei);
        line1 = (LinearLayout)findViewById(R.id.line1);
        textView = (TextView)findViewById(R.id.tv1);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.check1:
                        line1.setVisibility(View.GONE);
                        textView.setVisibility(View.GONE);
                        break;
                    case R.id.check2:
                        line1.setVisibility(View.VISIBLE);
                        textView.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
        btnsave = (Button)findViewById(R.id.btnsave);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("title",danwei.getText().toString());
                setResult(100,intent);
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("title",danwei.getText().toString());
                setResult(100,intent);
                finish();
            }
        });
    }
}
