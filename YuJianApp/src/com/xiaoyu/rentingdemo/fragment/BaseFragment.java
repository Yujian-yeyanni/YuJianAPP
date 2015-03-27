package com.xiaoyu.rentingdemo.fragment;

import com.xiaoyu.rentingdemo.BaseActivity;
import com.xiaoyu.rentingdemo.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;

public class BaseFragment extends Fragment {

	protected int layoutId;
	private View view;

	protected ViewGroup viewGroup;
	protected EditText editTextSearch;
	protected ImageView imageViewMenu;
	protected ImageView imageViewPersonal;
	protected int screenWidth;
	protected int screenHeight;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		WindowManager wm = (WindowManager) getActivity().getSystemService(
				Context.WINDOW_SERVICE);
		screenWidth = wm.getDefaultDisplay().getWidth();
		screenHeight = wm.getDefaultDisplay().getHeight();
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
	}

	public void findViewById(View rootView) {
		initCommTop(rootView);
	}

	public void initCommTop(View rootView) {
		viewGroup = (ViewGroup) rootView.findViewById(R.id.include_house_list);
		editTextSearch = (EditText) viewGroup
				.findViewById(R.id.et_comm_top_search);
		imageViewMenu = (ImageView) viewGroup
				.findViewById(R.id.iv_comm_top_menu);
		imageViewPersonal = (ImageView) viewGroup
				.findViewById(R.id.iv_comm_top_personal);
	}

	public void setLinstener() {

	}

	/**
	 * 
	 * @param fragment
	 * @param contentId
	 * @param addToStack
	 */
	public void skipToFragment(Fragment fragment, int contentId,
			boolean addToStack) {
		Activity activity = getActivity();
		if (!(activity instanceof BaseActivity)) {
			return;
		}
		((BaseActivity) activity).skipToFragmentByContentId(fragment,
				contentId, addToStack);
	}

	/**
	 * 
	 * @param fragment
	 * @param contentId
	 * @param addToStack
	 */
	public void addToFragment(Fragment fragment, int contentId,
			boolean addToStack) {
		((BaseActivity) getActivity()).addToFragmentByContentId(fragment,
				contentId, true);
	}

	/**
	 * pop back stack
	 */
	protected void fragmentPopStack() {
		if (getFragmentManager() != null) {
			getFragmentManager().popBackStack();
		}
	}

}
