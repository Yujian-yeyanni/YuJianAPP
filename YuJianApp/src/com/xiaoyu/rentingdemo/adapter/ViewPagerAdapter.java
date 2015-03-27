package com.xiaoyu.rentingdemo.adapter;

import java.util.List;

import com.xiaoyu.rentingdemo.util.MLog;
import com.xiaoyu.rentingdemo.widget.ScaleImageView;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 
 * @author annieye
 * 
 */

public class ViewPagerAdapter extends PagerAdapter implements
		OnPageChangeListener {

	private static final String TAG = ViewPagerAdapter.class.getName();

	private List<ScaleImageView> mListViews;
	private TextView textView;
	private Context context;
	private ScaleImageView imageView;

	public ViewPagerAdapter(List<ScaleImageView> mListViews,
			TextView textViewNowcount, Context context,
			ScaleImageView scaleImageView) {
		this.mListViews = mListViews;
		this.textView = textViewNowcount;
		this.context = context;
		this.imageView = scaleImageView;
		textView.setText(String.valueOf(1));
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// container.removeView(mListViews.get(position % mListViews.size()));
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		try {
			container.addView(mListViews.get(position % mListViews.size()), 0);
		} catch (Exception e) {
			// handler something
			MLog.e(TAG, e);
		}
		return mListViews.get(position % mListViews.size());
	}

	@Override
	public int getCount() {
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean isViewFromObject(View v, Object o) {
		return v == o;
	}

	@Override
	public void onPageScrollStateChanged(int state) {
		System.out.println("yyn");
		imageView.setVisibility(View.GONE);
	}

	@Override
	public void onPageScrolled(int position, float arg1, int arg2) {
//		imageView.setVisibility(View.GONE);
		System.out.println("yynshwan");
	}

	@Override
	public void onPageSelected(int position) {
		int nowCount = position % mListViews.size();
		textView.setText(nowCount + 1 + "");
	}
}
