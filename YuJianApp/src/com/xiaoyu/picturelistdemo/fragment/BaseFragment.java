package com.xiaoyu.picturelistdemo.fragment;

import com.xiaoyu.picturelistdemo.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

public class BaseFragment extends Fragment {

	protected int layoutId;
	private View view;

	protected ViewGroup viewGroup;
	protected EditText editTextSearch;
	protected ImageView imageViewMenu;
	protected ImageView imageViewPersonal;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (layoutId != -1) {
			view = getActivity().getLayoutInflater().inflate(layoutId, null);
			findViewById(view);
			return view;
		}
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		findViewById(view);
	}

	public void findViewById(View rootView) {
		viewGroup = (ViewGroup) rootView.findViewById(R.id.include_house_list);
		editTextSearch = (EditText) viewGroup
				.findViewById(R.id.et_comm_top_search);
		imageViewMenu = (ImageView) viewGroup
				.findViewById(R.id.iv_comm_top_menu);
		imageViewPersonal = (ImageView) viewGroup
				.findViewById(R.id.iv_comm_top_personal);
		
		setLinstener();
	}

	public void setLinstener() {
	}

}
