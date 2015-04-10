package com.xiaoyu.rentingdemo.util;

import net.tsz.afinal.FinalBitmap;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.xiaoyu.rentingdemo.MainActivity;

public class FinalBitmapUtils {

	private static FinalBitmap finalBitmap = null;
	private static FinalBitmapUtils finalBitmapUtils = null;
	private static final int loadImageSize = 5;

	private FinalBitmapUtils() {

	}

	public static FinalBitmapUtils getInstance() {
		if (finalBitmapUtils == null) {
			finalBitmapUtils = new FinalBitmapUtils();
		}
		return finalBitmapUtils;
	}

	private FinalBitmap getFinalBitmap() {
		if (finalBitmap == null) {
			if (MainActivity.getContext() == null)
				return null;
			finalBitmap = FinalBitmap.create(MainActivity.getContext());
			finalBitmap.configBitmapLoadThreadSize(loadImageSize);
			finalBitmap.configDiskCachePath(Constants.DiskCachePath);
			finalBitmap.configDiskCacheSize(1024 * 1024 * 10);
			finalBitmap.configRecycleImmediately(false); // 是否自动清除缓存
		}
		return finalBitmap;
	}

	public void setLoadPicture(int id) {
		getFinalBitmap().configLoadingImage(id);
	}

	public void setFailPicture(int id) {
		getFinalBitmap().configLoadfailImage(id);
	}

	public void displayImage(String url, ImageView imageview,
			Bitmap lodingBitmap, Bitmap longfailBitmap) {
		if (getFinalBitmap() != null) {
			getFinalBitmap().display(imageview, url, lodingBitmap,
					longfailBitmap);
		}
	}

	public void displayImage(String url, ImageView imageview) {
		if (getFinalBitmap() != null) {
			getFinalBitmap().display(imageview, url);
		}
	}

	public void displayImage(String url, ImageView imageview, int width,
			int height) {
		if (getFinalBitmap() != null) {
			getFinalBitmap().display(imageview, url, width, height);
		}
	}

	public Bitmap getBitmap(String key) {
		return finalBitmap.getBitmapFromCache(key);
	}

	public void clearMemoryCache() {
		if (getFinalBitmap() != null) {
			getFinalBitmap().clearMemoryCache();
		}
	}
}
