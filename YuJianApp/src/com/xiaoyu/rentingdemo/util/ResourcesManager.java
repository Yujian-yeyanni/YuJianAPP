package com.xiaoyu.rentingdemo.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;

/**
 * this class is provider resource for project
 * 
 * @author shwan
 * 
 */
public class ResourcesManager {

	/**
	 * this context for appliction
	 */
	private static Context mContext = null;

	/**
	 * resource refrenerce
	 */
	private static Resources mRessurces = null;

	/**
	 * 
	 * @param context
	 */
	public static void initConfig(Context context) {
		mContext = context;
		mRessurces = mContext.getResources();
	};

	/**
	 * Provide color by resource id
	 * 
	 * @param colorResId
	 * @return
	 */
	public static int getColor(int colorResId) {
		return mRessurces.getColor(colorResId);
	};
   
	public static String getString(int colorResId) {
		return mRessurces.getString(colorResId);
	};
	/**
	 * prodide drawable by resource id
	 * 
	 * @param drawableResId
	 * @return
	 */
	public static Drawable getDrawable(int drawableResId) {
		return mRessurces.getDrawable(drawableResId);
	};

	public static LayoutInflater getLayoutInflater() {
		return (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	/**
	 * provider arra by arr resource id
	 * 
	 * @param arrResId
	 */
	public static String[] getResourceArr(int arrResId) {
		return mRessurces.getStringArray(arrResId);
	}
	
	public static Context getContext(){
		return mContext;
	}

	public static Resources getmRessurces() {
		return mRessurces;
	}

	public static void setmRessurces(Resources mRessurces) {
		ResourcesManager.mRessurces = mRessurces;
	}

	
}
