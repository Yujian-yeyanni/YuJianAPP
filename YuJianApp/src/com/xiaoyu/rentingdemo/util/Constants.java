package com.xiaoyu.rentingdemo.util;

import java.io.File;

import com.xiaoyu.rentingdemo.R;

import android.os.Environment;

/**
 * This class provides general constants
 * 
 * @author shwan
 * 
 */
public class Constants {
	// method of http request
	public enum HTTPRequest {
		POST, GET
	}

	public static final int COMM_PAGE_SIZE = 10;
	public static final boolean DEBUG_ENABLE = true;
	//
	public static final int HOUSE_ROOM = 1001;
	public static final int HOUSE_LIVING_ROOM = 1002;
	public static final int HOUSE_TOILET = 1003;

	// set the picture size
	public static final String photosSize = "?imageView2/0/w/480/h/320";

	// CACHE PATH
	public static final String DiskCachePath = new File(
			Environment.getExternalStorageDirectory(), "/AfinalCachePath")
			.toString();

	// baidumap static image url with label
	public static final String BaiduImageURL = "http://api.map.baidu.com/staticimage?width=%1$s&height=%2$s&zoom=17&center=%3$s&labels=%4$s&labelStyles=%5$s,2,15,0xffffff,0xffa500,2&markers=%6$s";
	// baidumap static image url without label
	public static final String BaiDuStaticImageUrl = "http://api.map.baidu.com/staticimage?center=%1$s&width=%2$s&height=%3$s&zoom=18&markers=%4$s&markerStyles=-1";

	public static final String KEY_POI_ADDRESS = "poi_address";
	public static final String KEY_POI_CITY = "poi_city";
	public static final String KEY_POI_VILLAGE_NAME = "poi_village_name";

	public static final int MAX_SCREEN_WIDTH = 1080;

	public static final int CHANGED_SCREEN_WIDTH = 1000;

	// IMAGEVIEW IN VIEWPAGE BE CLICKED
	public static final int HANDLER_MESSAGE_VIEWPAGE_CLICK = 0001;

	public static final int[] MAP_ICON_ID = { R.drawable.ic_metro,
			R.drawable.ic_bus, R.drawable.ic_restaurant,
			R.drawable.ic_hospital, R.drawable.ic_market,
			R.drawable.ic_entertainment };

}
