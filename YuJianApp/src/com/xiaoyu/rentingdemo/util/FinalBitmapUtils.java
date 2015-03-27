package com.xiaoyu.rentingdemo.util;


import com.xiaoyu.rentingdemo.MainActivity;

import net.tsz.afinal.FinalBitmap;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.widget.ImageView;

public class FinalBitmapUtils {
	
	private static FinalBitmap 			finalBitmap 		= null;
	private static FinalBitmapUtils 	finalBitmapUtils 	= null;
	
	private FinalBitmapUtils(){
		
	}
	
	public static FinalBitmapUtils getInstance() {
	       if (finalBitmapUtils == null) {
	    	   finalBitmapUtils = new FinalBitmapUtils();
	       }
	       return finalBitmapUtils;
	    }
	
	private FinalBitmap getFinalBitmap(){
    	if(finalBitmap == null){
    		if(MainActivity.getContext() == null)
    			return null;
    		finalBitmap = FinalBitmap.create(MainActivity.getContext());
    		finalBitmap.configBitmapLoadThreadSize(3);
    		finalBitmap.configDiskCachePath(Constants.DiskCachePath);
    		finalBitmap.configDiskCacheSize(1024 * 1024 * 10);
    		finalBitmap.configRecycleImmediately(true);
    	}
		return finalBitmap;
	}
	
	
	public void setLoadPicture(int id){
		finalBitmap.configLoadfailImage(id);
		finalBitmap.configLoadingImage(id);
	}
	
	public void displayImage(String url,ImageView imageview,Bitmap lodingBitmap,Bitmap longfailBitmap){
		if(getFinalBitmap() != null){
			getFinalBitmap().display(imageview, url,lodingBitmap,longfailBitmap);
		}
	}
	
	public void displayImage(String url,ImageView imageview){
		if(getFinalBitmap() != null){
			getFinalBitmap().display(imageview, url);
		}
	}
	
	public void displayImage(String url,ImageView imageview,int width,int height){
		if(getFinalBitmap() != null){
			getFinalBitmap().display(imageview, url,width,height);
		}
	}
	
	public Bitmap getBitmap(String key){
		return finalBitmap.getBitmapFromCache(key);
	}
	
	public void clearMemoryCache(){
		if(getFinalBitmap() != null){
			getFinalBitmap().clearMemoryCache();
		}
	}
}
