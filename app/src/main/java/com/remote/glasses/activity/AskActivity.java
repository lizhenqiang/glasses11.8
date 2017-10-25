package com.remote.glasses.activity;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.remote.glasses.R;
import com.remote.glasses.base.BaseActivity;
import com.remote.glasses.bean.QuestionsBean;
import com.remote.glasses.utils.PullParserUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AskActivity extends BaseActivity {
    private RadioGroup askRg1;
    private RadioButton ask;
//    private RadioButton mesure;
    private RadioButton diagnose;
    private RadioButton prescription;
    private RadioGroup askRg2;
    private RadioButton question1;
    private RadioButton question2;
    private RadioButton question3;
    private RadioButton question4;
    private RadioGroup askRg3;
    private RadioButton selection1;
    private RadioButton selection2;
    private RadioButton selection3;
    private RadioButton selection4;
    private TextView askTips;
    private Button askReplay;

    private int rg1=0;
    private int rg2=0;
    private int rg3=0;
    private List<QuestionsBean> mList = new ArrayList<>();
    private MediaPlayer player;

//    private Handler handler=new Handler(){
//        public void handleMessage(android.os.Message msg) {
//            mList=(List<QuestionsBean>) msg.obj;
//        };
//    };

    @Override
    protected void initCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_ask);

        player = new MediaPlayer();
    }

    @Override
    public void initView() {

        askRg1 = (RadioGroup) findViewById(R.id.ask_rg1);
        ask = (RadioButton) findViewById(R.id.ask);
//        mesure = (RadioButton) findViewById(R.id.mesure);
        diagnose = (RadioButton) findViewById(R.id.diagnose);
        prescription = (RadioButton) findViewById(R.id.prescription);
        askRg2 = (RadioGroup) findViewById(R.id.ask_rg2);
        question1 = (RadioButton) findViewById(R.id.question1);
        question2 = (RadioButton) findViewById(R.id.question2);
        question3 = (RadioButton) findViewById(R.id.question3);
        question4 = (RadioButton) findViewById(R.id.question4);
        askRg3 = (RadioGroup) findViewById(R.id.ask_rg3);
        selection1 = (RadioButton) findViewById(R.id.selection1);
        selection2 = (RadioButton) findViewById(R.id.selection2);
        selection3 = (RadioButton) findViewById(R.id.selection3);
        selection4 = (RadioButton) findViewById(R.id.selection4);
        askTips = (TextView) findViewById(R.id.ask_tips);
        askReplay = (Button) findViewById(R.id.ask_replay);
        askReplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (!selection2.isChecked()&&!selection1.isChecked()&&!selection3.isChecked()){
                    playMusic(0);

//                }else {
//                    playMusic(1);
//                }
            }
        });
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
        //获取xml数据
        InputStream is = null;
        try {
            is = getResources().getAssets().open("questions.xml");
            mList = PullParserUtils.parserXmlByPull(is);
            question1.setText(mList.get(0).getQuestion().get(0).getTitle());
            question2.setText(mList.get(0).getQuestion().get(1).getTitle());
            selection1.setText(mList.get(0).getQuestion().get(0).getOptions().get(0).getDesc());
            selection2.setText(mList.get(0).getQuestion().get(0).getOptions().get(1).getDesc());
            selection3.setText(mList.get(0).getQuestion().get(0).getOptions().get(2).getDesc());
//       Message msg=handler.obtainMessage();
//       msg.obj=mList;
//       handler.sendMessage(msg);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
//            }
//        });

        askRg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                rg2=0;
                question2.setChecked(false);
                question3.setChecked(false);
                question4.setChecked(false);
                askTips.setText("提示内容");
                switch (checkedId) {
                    case R.id.ask:
                        rg1=0;
                        question1.setChecked(true);
                        selection4.setChecked(true);
                        playMusic(0);

                        question1.setText(mList.get(0).getQuestion().get(0).getTitle());
                        question2.setText(mList.get(0).getQuestion().get(1).getTitle());
                        question3.setText("");
                        question4.setText("");
                        selection1.setText(mList.get(rg1).getQuestion().get(rg2).getOptions().get(0).getDesc());
                        selection2.setText(mList.get(rg1).getQuestion().get(rg2).getOptions().get(1).getDesc());
                        switch (rg2){
                            case 0:
                                selection3.setVisibility(View.VISIBLE);
                                selection3.setText(mList.get(rg1).getQuestion().get(rg2).getOptions().get(2).getDesc());
                                break;
                            case 1:
                                selection3.setVisibility(View.INVISIBLE);
                                break;
                        }
                        break;
//                    case R.id.mesure:
//                        rg1=0;
//                        question1.setChecked(true);
//                        selection4.setChecked(true);
//                        playMusic(0);
//
//                        question1.setText(mList.get(0).getQuestion().get(0).getTitle());
//                        question2.setText(mList.get(0).getQuestion().get(1).getTitle());
//                        question3.setText("");
//                        question4.setText("");
//                        selection1.setText(mList.get(rg1).getQuestion().get(rg2).getOptions().get(0).getDesc());
//                        selection2.setText(mList.get(rg1).getQuestion().get(rg2).getOptions().get(1).getDesc());
//                        switch (rg2){
//                            case 0:
//                                selection3.setVisibility(View.VISIBLE);
//                                selection3.setText(mList.get(rg1).getQuestion().get(rg2).getOptions().get(2).getDesc());
//                                break;
//                            case 1:
//                                selection3.setVisibility(View.INVISIBLE);
//                                break;
//                        }
//                        break;
                    case R.id.diagnose:
                        rg1=1;
                        question1.setChecked(true);
                        selection4.setChecked(true);
                        playMusic(0);

                        question1.setText(mList.get(1).getQuestion().get(0).getTitle());
                        question2.setText(mList.get(1).getQuestion().get(1).getTitle());
                        question3.setText(mList.get(1).getQuestion().get(2).getTitle());
                        question4.setText(mList.get(1).getQuestion().get(3).getTitle());
                        selection1.setText(mList.get(rg1).getQuestion().get(rg2).getOptions().get(0).getDesc());
                        selection2.setText(mList.get(rg1).getQuestion().get(rg2).getOptions().get(1).getDesc());
                        switch (rg2){
                            case 0:case 1:
                                selection3.setVisibility(View.INVISIBLE);
                                break;
                            case 2:case 3:
                                selection3.setText(mList.get(rg1).getQuestion().get(rg2).getOptions().get(2).getDesc());
                                selection3.setVisibility(View.VISIBLE);
                                break;
                        }
                        break;
                    case R.id.prescription:
                        rg1=2;
                        question1.setChecked(true);
                        selection4.setChecked(true);
                        playMusic(0);

                        question1.setText(mList.get(2).getQuestion().get(0).getTitle());
                        question2.setText(mList.get(2).getQuestion().get(1).getTitle());
                        question3.setText(mList.get(2).getQuestion().get(2).getTitle());
                        question4.setText(mList.get(2).getQuestion().get(3).getTitle());
                        selection1.setText(mList.get(rg1).getQuestion().get(rg2).getOptions().get(0).getDesc());
                        selection2.setText(mList.get(rg1).getQuestion().get(rg2).getOptions().get(1).getDesc());
                        selection1.setText(mList.get(rg1).getQuestion().get(rg2).getOptions().get(0).getDesc());
                        selection2.setText(mList.get(rg1).getQuestion().get(rg2).getOptions().get(1).getDesc());

                        switch (rg2){
                            case 0:case 1:
                                selection3.setVisibility(View.INVISIBLE);
                                break;
                            case 2:case 3:
                                selection3.setText(mList.get(rg1).getQuestion().get(rg2).getOptions().get(2).getDesc());
                                selection3.setVisibility(View.VISIBLE);
                                break;
                        }
                        break;

                }
            }
        });
        askRg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                askTips.setText("提示内容");
                selection4.setChecked(true);
                switch (checkedId) {
                    case R.id.question1:
                        rg2=0;
                        selection1.setText(mList.get(rg1).getQuestion().get(0).getOptions().get(0).getDesc());
                        selection2.setText(mList.get(rg1).getQuestion().get(0).getOptions().get(1).getDesc());
                        switch (rg1){
                            case 0:
                                selection3.setVisibility(View.VISIBLE);
                                selection3.setText(mList.get(rg1).getQuestion().get(0).getOptions().get(2).getDesc());
                                break;
                            case 1:case 2:
                                selection3.setVisibility(View.INVISIBLE);
                                break;
                        }

                        playMusic(0);
                        break;
                    case R.id.question2:
                        rg2=1;
                        selection1.setText(mList.get(rg1).getQuestion().get(1).getOptions().get(0).getDesc());
                        selection2.setText(mList.get(rg1).getQuestion().get(1).getOptions().get(1).getDesc());
                        selection3.setVisibility(View.INVISIBLE);
                        playMusic(0);
                        break;
                    case R.id.question3:
                        rg2=2;
                        selection1.setText(mList.get(rg1).getQuestion().get(2).getOptions().get(0).getDesc());
                        selection2.setText(mList.get(rg1).getQuestion().get(2).getOptions().get(1).getDesc());
                        switch (rg1){
                            case 1:
                                selection3.setText(mList.get(rg1).getQuestion().get(2).getOptions().get(2).getDesc());
                                selection3.setVisibility(View.VISIBLE);
                                break;
                            case 2:
                                selection3.setVisibility(View.INVISIBLE);
                                break;
                        }
                        playMusic(0);
                        break;

                    case R.id.question4:
                        rg2=3;
                        selection1.setText(mList.get(rg1).getQuestion().get(3).getOptions().get(0).getDesc());
                        selection2.setText(mList.get(rg1).getQuestion().get(3).getOptions().get(1).getDesc());
                        switch (rg1){
                            case 1:
                                selection3.setText(mList.get(rg1).getQuestion().get(3).getOptions().get(2).getDesc());
                                selection3.setVisibility(View.VISIBLE);
                                break;
                            case 2:
                                selection3.setVisibility(View.INVISIBLE);
                                break;
                        }
                        playMusic(0);
                        break;

                }
            }
        });
        askRg3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.selection1:
                        rg3=0;
                        askTips.setText(mList.get(rg1).getQuestion().get(rg2).getOptions().get(rg3).getRemark());
                        playMusic(1);
                        break;
                    case R.id.selection2:
                        rg3=1;
                        askTips.setText(mList.get(rg1).getQuestion().get(rg2).getOptions().get(rg3).getRemark());
                        playMusic(1);
                        break;
                    case R.id.selection3:
                        rg3=2;
                        askTips.setText(mList.get(rg1).getQuestion().get(rg2).getOptions().get(rg3).getRemark());
                        playMusic(1);
                        break;
                }

            }
        });
    }


//    public String getFromAssets(String fileName) {
//        try {
//            InputStreamReader inputReader = new InputStreamReader(getResources().getAssets().open("questions.xml"));
//            BufferedReader bufReader = new BufferedReader(inputReader);
//            String line = "";
//            String Result = "";
//            while ((line = bufReader.readLine()) != null)
//                Result += line;
//            return Result;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public void playMusic(int i){
        try {
            AssetManager assetManager = this.getAssets();
            AssetFileDescriptor afd;
            if (i==0){
                afd  = assetManager.openFd(""+rg1+rg2+".mp3");
            }else {
                afd  = assetManager.openFd(""+rg1+rg2+rg3+".mp3");
            }

            player.reset();
            player.setDataSource(afd.getFileDescriptor(),
                    afd.getStartOffset(), afd.getLength());
            player.prepare();
            player.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player!=null){
            player.stop();
            player.release();
            player =null;
        }
    }
}
