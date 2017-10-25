package com.remote.glasses.utils.citySelect;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.remote.glasses.R;
import com.remote.glasses.utils.AppManager;
import com.remote.glasses.utils.ToastUtil;
import com.remote.glasses.utils.citySelect.kankan.wheel.widget.OnWheelChangedListener;
import com.remote.glasses.utils.citySelect.kankan.wheel.widget.WheelView;
import com.remote.glasses.utils.citySelect.kankan.wheel.widget.adapter.ArrayWheelAdapter;


public class MainActivity extends BaseActivity implements OnClickListener, OnWheelChangedListener {
	private WheelView mViewProvince;
	private WheelView mViewCity;
	private WheelView mViewDistrict;
	private Button mBtnConfirm;
	private ArrayWheelAdapter arrayWheelAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cityselect);
		AppManager.addActivity(this);
		setUpViews();
		setUpListener();
		setUpData();
	}

	private void setUpViews() {
		mViewProvince = (WheelView) findViewById(R.id.id_province);
		mViewCity = (WheelView) findViewById(R.id.id_city);
		mViewDistrict = (WheelView) findViewById(R.id.id_district);
		mBtnConfirm = (Button) findViewById(R.id.btn_confirm);
		findViewById(R.id.city_selecty_shutdown).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	private void setUpListener() {
		// 添加change事件
		mViewProvince.addChangingListener(this);
		// 添加change事件
		mViewCity.addChangingListener(this);
		// 添加change事件
		mViewDistrict.addChangingListener(this);
		// 添加onclick事件
		mBtnConfirm.setOnClickListener(this);
	}

	private void setUpData() {
		initProvinceDatas();
		arrayWheelAdapter = new ArrayWheelAdapter<String>(MainActivity.this, mProvinceDatas);
		mViewProvince.setViewAdapter(arrayWheelAdapter);
		// 设置可见条目数量
		mViewProvince.setVisibleItems(9);
		mViewCity.setVisibleItems(9);
		mViewDistrict.setVisibleItems(9);
		updateCities();
		updateAreas();
	}

	@Override
	public void onChanged(WheelView wheel, int oldValue, int newValue) {


		if (wheel == mViewProvince) {
			updateCities();

		} else if (wheel == mViewCity) {
			updateAreas();

		} else if (wheel == mViewDistrict) {

			mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[newValue];
			mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
		}
	}

	/**
	 * 根据当前的市，更新区WheelView的信息
	 */
	private void updateAreas() {
		int pCurrent = mViewCity.getCurrentItem();
		mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
		String[] areas = mDistrictDatasMap.get(mCurrentCityName);

		if (areas == null) {
			areas = new String[] { "" };
		}
		mViewDistrict.setViewAdapter(new ArrayWheelAdapter<String>(this, areas));
		mViewDistrict.setCurrentItem(0);
		mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[0];
		mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
	}

	/**
	 * 根据当前的省，更新市WheelView的信息
	 */
	private void updateCities() {
		int pCurrent = mViewProvince.getCurrentItem();
		mCurrentProviceName = mProvinceDatas[pCurrent];
		String[] cities = mCitisDatasMap.get(mCurrentProviceName);
		if (cities == null) {
			cities = new String[] { "" };
		}
		mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(this, cities));
		mViewCity.setCurrentItem(0);
		updateAreas();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_confirm:
				showSelectedResult();
				break;
			default:
				break;
		}
	}

	private void showSelectedResult() {

		setResult(100,getIntent().putExtra("city",mCurrentProviceName+"-"+mCurrentCityName+"-" +mCurrentDistrictName));
		finish();

		ToastUtil.show(MainActivity.this, "当前选中:" + mCurrentProviceName + "." + mCurrentCityName + "."
				+ mCurrentDistrictName);
		//邮编   mCurrentZipCode
	}
}
