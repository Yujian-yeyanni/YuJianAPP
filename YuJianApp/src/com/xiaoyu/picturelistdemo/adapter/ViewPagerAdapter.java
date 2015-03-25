package com.xiaoyu.picturelistdemo.adapter;

import java.util.List;

import com.xiaoyu.picturelistdemo.util.MLog;
import com.xiaoyu.picturelistdemo.widget.ScaleImageView;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * 
 * @author annieye
 * 
 */

public  class ViewPagerAdapter extends PagerAdapter implements OnPageChangeListener  {
	
	private List<ScaleImageView> mListViews;
	private TextView textView;

	public ViewPagerAdapter(List<ScaleImageView> mListViews,TextView textViewNowcount) {
		this.mListViews = mListViews;
		this.textView = textViewNowcount;
		textView.setText(String.valueOf(1));
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		if (position >= getCount())
			return;
		container.removeView(mListViews.get(position));
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		if (position >= getCount())
			return null;
		container.addView(mListViews.get(position), 0);
		return mListViews.get(position);
	}

	@Override
	public int getCount() {
		if (mListViews == null)
			return 0;
		return mListViews.size();
	}

	@Override
	public boolean isViewFromObject(View v, Object o) {
		return v == o;
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		
	}

	@Override
	public void onPageSelected(int nowCount) {
		
		textView.setText(nowCount + 1 + "");
	}
}
