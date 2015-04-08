package com.xiaoyu.rentingdemo.fragment;

import com.xiaoyu.rentingdemo.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * search page
 * 
 * @author shwan
 * 
 */
public class SearchFragment extends BaseFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		layoutId = R.layout.fragment_search;
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void findViewById(View rootView) {
		super.findViewById(rootView);
		setLinstener();
	}

	@Override
	public void initCommTop(View rootView) {
		super.initCommTop(rootView);
		relativeLayoutTopSearch.setVisibility(View.GONE);
		textViewCity.setVisibility(View.GONE);
		imageViewPersonal.setVisibility(View.GONE);
		layouLeftBack.setVisibility(View.VISIBLE);
		textViewTitle.setVisibility(View.VISIBLE);
		textViewTitle.setText(R.string.str_seniro_search);
	}

	@Override
	public void setLinstener() {
		super.setLinstener();
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
	}

}
