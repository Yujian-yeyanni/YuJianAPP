package com.xiaoyu.rentingdemo;



import com.baidu.mapapi.SDKInitializer;
import com.xiaoyu.rentingdemo.util.ResourcesManager;

import android.app.Application;

public class RentingApp extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		
		ResourcesManager.initConfig(getApplicationContext());
		SDKInitializer.initialize(this);
		
	}
}
