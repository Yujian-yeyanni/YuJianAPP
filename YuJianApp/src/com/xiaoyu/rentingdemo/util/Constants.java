package com.xiaoyu.rentingdemo.util;

import java.io.File;

import android.os.Environment;

/**
 * This class provides general constants
 * 
 * @author shwan
 * 
 */
public class Constants {
	public enum HTTPRequest {
		POST, GET
	}

	public static final boolean DEBUG_ENABLE = true;

	public static final int HOUSE_ROOM = 1001;
	public static final int HOUSE_LIVING_ROOM = 1002;
	public static final int HOUSE_TOILET = 1003;

	// set the picture size
	public static final String photosSize = "?imageView2/0/w/480/h/320";
	
	public static final String DiskCachePath = new File(
			Environment.getExternalStorageDirectory(), "/AfinalCachePath")
			.toString();
}
