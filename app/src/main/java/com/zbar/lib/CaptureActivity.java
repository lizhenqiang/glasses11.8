package com.zbar.lib;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.request.GetRequest;
import com.remote.glasses.R;
import com.remote.glasses.base.BaseActivity;
import com.remote.glasses.bean.GlassesBean;
import com.remote.glasses.set.NetPath;
import com.remote.glasses.utils.GlobalDialog;
import com.remote.glasses.utils.PreferencesUtils;
import com.remote.glasses.utils.ToolsCallback;
import com.zbar.lib.camera.CameraManager;
import com.zbar.lib.decode.CaptureActivityHandler;
import com.zbar.lib.decode.InactivityTimer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLDecoder;

import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者: 陈涛(1076559197@qq.com)
 * 
 * 时间: 2014年5月9日 下午12:25:31
 * 
 * 版本: V_1.0.0
 * 
 * 描述: 扫描界面
 */
public class CaptureActivity extends BaseActivity implements Callback {

	private CaptureActivityHandler handler;
	private boolean hasSurface;
	private InactivityTimer inactivityTimer;
	private MediaPlayer mediaPlayer;
	private boolean playBeep;
	private static final float BEEP_VOLUME = 0.50f;
	private boolean vibrate;
	private int x = 0;
	private int y = 0;
	private int cropWidth = 0;
	private int cropHeight = 0;
	private RelativeLayout mContainer = null;
	private RelativeLayout mCropLayout = null;
	private boolean isNeedCapture = false;
    private GlassesBean mGlassesBean;
	private int scrrenLight = 0;
	
	public boolean isNeedCapture() {
		return isNeedCapture;
	}

	public void setNeedCapture(boolean isNeedCapture) {
		this.isNeedCapture = isNeedCapture;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getCropWidth() {
		return cropWidth;
	}

	public void setCropWidth(int cropWidth) {
		this.cropWidth = cropWidth;
	}

	public int getCropHeight() {
		return cropHeight;
	}

	public void setCropHeight(int cropHeight) {
		this.cropHeight = cropHeight;
	}



	@Override
	protected void initCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_qr_scan);
		// 初始化 CameraManager
		CameraManager.init(getApplication());
		hasSurface = false;
		inactivityTimer = new InactivityTimer(this);

		mContainer = (RelativeLayout) findViewById(R.id.capture_containter);
		mCropLayout = (RelativeLayout) findViewById(R.id.capture_crop_layout);

		ImageView mQrLineView = (ImageView) findViewById(R.id.capture_scan_line);
		TranslateAnimation mAnimation = new TranslateAnimation(TranslateAnimation.ABSOLUTE, 0f, TranslateAnimation.ABSOLUTE, 0f,
				TranslateAnimation.RELATIVE_TO_PARENT, 0f, TranslateAnimation.RELATIVE_TO_PARENT, 0.9f);
		mAnimation.setDuration(1500);
		mAnimation.setRepeatCount(-1);
		mAnimation.setRepeatMode(Animation.REVERSE);
		mAnimation.setInterpolator(new LinearInterpolator());
		mQrLineView.setAnimation(mAnimation);
		scrrenLight = getScreenBrightness();
		setScreenBrightness(255);
	}

	/**
	 * 保存当前的屏幕亮度值，并使之生效
	 */
	private void setScreenBrightness(int paramInt){
		Window localWindow = getWindow();
		WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
		float f = paramInt / 255.0F;
		localLayoutParams.screenBrightness = f;
		localWindow.setAttributes(localLayoutParams);
	}

	/**
	 * 设置当前屏幕亮度值 0--255
	 */
	private void saveScreenBrightness(int paramInt){
		try{
			Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, paramInt);
		}
		catch (Exception localException){
			localException.printStackTrace();
		}
	}

	/**
	 * 获得当前屏幕亮度值 0--255
	 */
	private int getScreenBrightness(){
		int screenBrightness=255;
		try{
			screenBrightness = Settings.System.getInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
		}
		catch (Exception localException){

		}
		return screenBrightness;
	}

	@Override
	public void initView() {

	}

	boolean flag = true;

	protected void light() {
		if (flag == true) {
			flag = false;
			// 开闪光灯
			CameraManager.get().openLight();
		} else {
			flag = true;
			// 关闪光灯
			CameraManager.get().offLight();
		}

	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onResume() {
		super.onResume();
		SurfaceView surfaceView = (SurfaceView) findViewById(R.id.capture_preview);
		SurfaceHolder surfaceHolder = surfaceView.getHolder();
		if (hasSurface) {
			initCamera(surfaceHolder);
		} else {
			surfaceHolder.addCallback(this);
			surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		}
		playBeep = true;
		AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
		if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
			playBeep = false;
		}
		initBeepSound();
		vibrate = true;
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (handler != null) {
			handler.quitSynchronously();
			handler = null;
		}
		setScreenBrightness(scrrenLight);
		CameraManager.get().closeDriver();
	}

	@Override
	protected void onDestroy() {
		inactivityTimer.shutdown();
		super.onDestroy();
	}

	public void handleDecode(final String result) {
		inactivityTimer.onActivity();
		playBeepSoundAndVibrate();
		Log.e("info",result);

		JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject(URLDecoder.decode(result, "UTF-8"));
			if (jsonObject!=null  ){
				if (!TextUtils.isEmpty(jsonObject.optString("goodModel"))){
					final GlobalDialog delDialog = new GlobalDialog(this);
					delDialog.setCanceledOnTouchOutside(false);
					delDialog.setLeftBtnText("取消");
					delDialog.setRightBtnText("确定");
					delDialog.getBrand().setText(jsonObject.optString("goodBrand"));
					delDialog.getCategory().setText(jsonObject.optString("goodCategory"));
					delDialog.getModel().setText(jsonObject.optString("goodModel"));
					delDialog.getColor().setText(jsonObject.optString("goodColor"));
					delDialog.getGoodPackage().setText(jsonObject.optString("goodPackage"));
					delDialog.getAmount().setText(jsonObject.optString("amount"));
					delDialog.getFee().setText(jsonObject.optString("fee")+"×"+jsonObject.optInt("amount",1));
					delDialog.getRatefee().setText(jsonObject.optString("rateFee")+"×"+jsonObject.optInt("amount",1));
					delDialog.setLeftOnclick(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							delDialog.dismiss();
							finish();
						}
					});
					delDialog.setRightOnclick(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							delDialog.dismiss();
							Intent intent = CaptureActivity.this.getIntent();
							intent.putExtra("glassesInfo", result);
							intent.putExtra("type","0");//镜宴
							CaptureActivity.this.setResult(RESULT_OK, intent);
							finish();
						}
					});
					delDialog.show();
				}else {
					Toast.makeText(CaptureActivity.this, "识别失败！非本产品相关二维码！", Toast.LENGTH_SHORT).show();
					CaptureActivity.this.finish();
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
//			Toast.makeText(CaptureActivity.this, "识别失败！非本产品相关二维码！", Toast.LENGTH_SHORT).show();
//			CaptureActivity.this.finish();
			GetRequest postRequest = OkHttpUtils.get(NetPath.GET_APP_VTO)
					.params("frame_code", result);
			postRequest.execute(new ToolsCallback<Object>(CaptureActivity.this) {
				@Override
				public Object parseNetworkResponse(Response response) {
					return null;
				}

				@Override
				public void onResponse(boolean isFromCache, Object o, Request request, @Nullable Response response) {
					try {
						String s = response.body().string();
						JSONObject jsonObject = new JSONObject(s);
						String status = jsonObject.optString("status");
						if(status.equals("206")){
							JSONObject jsonObject1 = jsonObject.getJSONObject("data");
							mGlassesBean = new GlassesBean();
							mGlassesBean.setPhone(PreferencesUtils.getString(CaptureActivity.this, PreferencesUtils.key_current_user_phone));
							mGlassesBean.setBrand(jsonObject1.optString("brandName"));
							mGlassesBean.setCategory(jsonObject1.optString("isSuglasses").equals("0")?"太阳镜":"");
							mGlassesBean.setModel("");
							mGlassesBean.setColor(jsonObject1.optString("frameColor"));
							mGlassesBean.setGoodPackage("");
							mGlassesBean.setAmount(1);
							mGlassesBean.setFee(jsonObject1.optString("price"));
							mGlassesBean.setRatefee(jsonObject1.optString("price"));
							mGlassesBean.setUserId("");
							Intent intent = CaptureActivity.this.getIntent();
							intent.putExtra("glassesInfo", s);
							intent.putExtra("type","1");//VTO
							intent.putExtra("framId",result);
							CaptureActivity.this.setResult(RESULT_OK, intent);
							finish();
						}else {
							Toast.makeText(CaptureActivity.this, "识别失败！非本产品相关二维码！", Toast.LENGTH_SHORT).show();
							CaptureActivity.this.finish();
						}
					} catch (JSONException e) {
						e.printStackTrace();
						//-------------------------------------------------


					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
		}

		// 连续扫描，不发送此消息扫描一次结束后就不能再次扫描
//		 handler.sendEmptyMessage(R.id.restart_preview);
	}

	private void initCamera(SurfaceHolder surfaceHolder) {
		try {
			CameraManager.get().openDriver(surfaceHolder);

			Point point = CameraManager.get().getCameraResolution();
			int width = point.y;
			int height = point.x;

			int x = mCropLayout.getLeft() * width / mContainer.getWidth();
			int y = mCropLayout.getTop() * height / mContainer.getHeight();
			;
			int cropWidth = mCropLayout.getWidth() * width / mContainer.getWidth();
			int cropHeight = mCropLayout.getHeight() * height / mContainer.getHeight();



			setX(width/20*3);
			setY(height/20*3);
			setCropWidth(width/10*7);//自定义识别区域
			setCropHeight(height/10*7);//自定义识别区域
			// 设置是否需要截图
			setNeedCapture(true);
			

		} catch (IOException ioe) {
			return;
		} catch (RuntimeException e) {
			return;
		}
		if (handler == null) {
			handler = new CaptureActivityHandler(CaptureActivity.this);
		}
	}
	private boolean flag1 = true;
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (!hasSurface) {
			hasSurface = true;
			initCamera(holder);
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		hasSurface = false;

	}

	public Handler getHandler() {
		return handler;
	}

	private void initBeepSound() {
		if (playBeep && mediaPlayer == null) {
			setVolumeControlStream(AudioManager.STREAM_MUSIC);
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setOnCompletionListener(beepListener);

			AssetFileDescriptor file = getResources().openRawResourceFd(R.raw.beep);
			try {
				mediaPlayer.setDataSource(file.getFileDescriptor(), file.getStartOffset(), file.getLength());
				file.close();
				mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
				mediaPlayer.prepare();
			} catch (IOException e) {
				mediaPlayer = null;
			}
		}
	}

	private static final long VIBRATE_DURATION = 200L;

	private void playBeepSoundAndVibrate() {
		if (playBeep && mediaPlayer != null) {
			mediaPlayer.start();
		}
		if (vibrate) {
			Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
			vibrator.vibrate(VIBRATE_DURATION);
		}
	}

	private final OnCompletionListener beepListener = new OnCompletionListener() {
		public void onCompletion(MediaPlayer mediaPlayer) {
			mediaPlayer.seekTo(0);
		}
	};
}