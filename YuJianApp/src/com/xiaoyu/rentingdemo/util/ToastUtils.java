package com.xiaoyu.rentingdemo.util;

import android.widget.Toast;

/**
 * toast util
 * 
 * @author shwan
 * 
 */
public class ToastUtils {

	/**
	 * show toast by str
	 * 
	 * @param toastStr
	 */
	public static void showToast(String toastStr) {
		Toast.makeText(ResourcesManager.getContext(), toastStr,
				Toast.LENGTH_SHORT).show();
	}

	/**
	 * show toast by strid
	 * 
	 * @param strResId
	 */
	public static void shoToast(int strResId) {
		Toast.makeText(ResourcesManager.getContext(), strResId,
				Toast.LENGTH_SHORT).show();
	}
}
