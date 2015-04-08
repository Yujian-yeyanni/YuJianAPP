package com.xiaoyu.rentingdemo.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiaoyu.rentingdemo.BaseActivity;
import com.xiaoyu.rentingdemo.R;

public class BaseFragment extends Fragment implements OnClickListener,
		TextWatcher {

	protected int layoutId;
	private View view;

	protected ViewGroup viewGroup;
	protected RelativeLayout relativeLayoutTopSearch;
	protected LinearLayout layouLeftBack;
	protected EditText editTextSearch;
	protected TextView textViewTitle;
	protected ImageView imageViewPersonal;
	protected TextView textViewCity;
	protected TextView textViewSeniorSearch;
	protected View viewCommLine;
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

	/**
	 * init comm top
	 * 
	 * @param rootView
	 */
	public void initCommTop(View rootView) {
		// find comm top widget id
		viewGroup = (ViewGroup) rootView.findViewById(R.id.include_house_list);
		relativeLayoutTopSearch = (RelativeLayout) viewGroup
				.findViewById(R.id.rl_comm_top_search);
		editTextSearch = (EditText) viewGroup
				.findViewById(R.id.et_comm_top_search);
		textViewSeniorSearch = (TextView) viewGroup
				.findViewById(R.id.tv_comm_top_senior_search);
		textViewTitle = (TextView) viewGroup
				.findViewById(R.id.tv_comm_top_title);
		layouLeftBack = (LinearLayout) viewGroup
				.findViewById(R.id.comm_ll_title_leftback);
		imageViewPersonal = (ImageView) viewGroup
				.findViewById(R.id.iv_comm_top_personal);
		textViewCity = (TextView) viewGroup.findViewById(R.id.tv_comm_top_city);
		viewCommLine = viewGroup.findViewById(R.id.view_comm_top_line);
		textViewSeniorSearch.setVisibility(View.VISIBLE);
	}

	/**
	 * set listener for widget
	 */
	public void setLinstener() {
		layouLeftBack.setOnClickListener(this);
		imageViewPersonal.setOnClickListener(this);
		editTextSearch.addTextChangedListener(this);
		editTextSearch.setOnClickListener(this);
		textViewSeniorSearch.setOnClickListener(this);
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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.comm_ll_title_leftback:
			fragmentPopStack();
			break;
		case R.id.iv_comm_top_personal:
			skipToFragment(new PersonalFragment(), R.id.fl_content, true);
			break;
		case R.id.tv_comm_top_senior_search:
			skipToFragment(new SearchFragment(), R.id.fl_content, true);
			break;
		default:
			break;
		}
	}

	@Override
	public void afterTextChanged(Editable editable) {
	}

	@Override
	public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
			int arg3) {
	}

	@Override
	public void onTextChanged(CharSequence s, int arg1, int arg2, int arg3) {
		// add change while text update in edittext
		if (s.length() > 0) {
			textViewSeniorSearch.setVisibility(View.GONE);
			viewCommLine.setVisibility(View.GONE);
		} else {
			textViewSeniorSearch.setVisibility(View.VISIBLE);
			viewCommLine.setVisibility(View.VISIBLE);
		}
	}
}
