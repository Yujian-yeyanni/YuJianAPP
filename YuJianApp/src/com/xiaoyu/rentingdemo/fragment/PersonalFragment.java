package com.xiaoyu.rentingdemo.fragment;

import com.xiaoyu.rentingdemo.R;
import com.xiaoyu.rentingdemo.util.MLog;
import com.xiaoyu.rentingdemo.widget.ScaleImageView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * personal page
 * 
 * @author shwan
 * 
 */
public class PersonalFragment extends BaseFragment {

	private static final String TAG = PersonalFragment.class.getName();

	private RelativeLayout layoutUnlogin; // unlogin layout
	private EditText editTextUsername;
	private EditText editTextPassword;
	private Button buttonLogin;
	private TextView textViewRegister;
	private RelativeLayout layoutLogin; // have login layout
	private ScaleImageView imageViewHome;
	private ImageView personalPhoto;
	private ListView listViewMyConfig;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		layoutId = R.layout.fragment_personal;
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void findViewById(View rootView) {
		super.findViewById(rootView);
		layoutUnlogin = (RelativeLayout) rootView
				.findViewById(R.id.rl_login_page_unlogin);
		editTextUsername = (EditText) rootView
				.findViewById(R.id.et_login_username);
		editTextPassword = (EditText) rootView
				.findViewById(R.id.et_login_password);
		textViewRegister = (TextView) rootView.findViewById(R.id.tv_register);
		buttonLogin = (Button) rootView.findViewById(R.id.btn_login);
		layoutLogin = (RelativeLayout) rootView
				.findViewById(R.id.rl_have_login);
		imageViewHome = (ScaleImageView) rootView
				.findViewById(R.id.siv_personal_page_house_image);
		imageViewPersonal = (ImageView) rootView
				.findViewById(R.id.iv_personal_page_photo);
		listViewMyConfig = (ListView) rootView
				.findViewById(R.id.lv_personal_page);
		layoutUnlogin.setVisibility(View.VISIBLE);
		layoutLogin.setVisibility(View.GONE);
		setLinstener();
	}

	@Override
	public void initCommTop(View rootView) {
		super.initCommTop(rootView);
		textViewCity.setVisibility(View.GONE);
		textViewTitle.setVisibility(View.GONE);
		imageViewPersonal.setVisibility(View.GONE);
		relativeLayoutTopSearch.setVisibility(View.GONE);
		layouLeftBack.setVisibility(View.VISIBLE);
	}

	@Override
	public void setLinstener() {
		super.setLinstener();
		buttonLogin.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_login:
			layoutLogin.setVisibility(View.VISIBLE);
			layoutUnlogin.setVisibility(View.GONE);
			MLog.e(TAG,"yidianji");
			break;
		default:
			break;
		}
	}
}
