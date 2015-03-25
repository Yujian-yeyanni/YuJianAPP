package com.xiaoyu.picturelistdemo.listener;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.TextView;

public class MyPagerChangeListener implements OnPageChangeListener {
	private TextView textView;

	public MyPagerChangeListener(TextView textView) {
		this.textView = textView;
		textView.setText(String.valueOf(1));
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int arg0) {
		textView.setText(String.valueOf(arg0));
	}

}
