package com.xiaoyu.rentingdemo.adapter;

import java.util.List;

import com.xiaoyu.rentingdemo.util.Constants;
import com.xiaoyu.rentingdemo.util.MLog;
import com.xiaoyu.rentingdemo.widget.ScaleImageView;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * adapter and pagechangelistener for viewpager 
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

	private int nowItemPosition;
	private Handler handler;

	//init viewpageradapter
	public ViewPagerAdapter(List<ScaleImageView> mListViews,
			TextView textViewNowcount, Context context,
			ScaleImageView scaleImageView, int nowItemPosition, Handler handler) {
		this.mListViews = mListViews;
		this.textView = textViewNowcount;
		this.context = context;
		this.imageView = scaleImageView;
		textView.setText(String.valueOf(1));
		this.nowItemPosition = nowItemPosition;
		this.handler = handler;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// container.removeView(mListViews.get(position % mListViews.size()));
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		try {
			container.addView(mListViews.get(position % mListViews.size()), 0);

			mListViews.get(position % mListViews.size()).setOnClickListener(
					new OnClickListener() {

						@Override
						public void onClick(View v) {
							Message message = new Message();
							message.what = Constants.HANDLER_MESSAGE_VIEWPAGE_CLICK;
							message.arg1 = nowItemPosition;
							if (handler == null) {
								return;
							}
							handler.sendMessage(message);
						}
					});
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
		if (imageView == null) {
			return;
		}
		imageView.setVisibility(View.GONE);
	}

	@Override
	public void onPageScrolled(int position, float arg1, int arg2) {
	}

	@Override
	public void onPageSelected(int position) {
		int nowCount = position % mListViews.size();
		textView.setText(nowCount + 1 + "");
	}
}
