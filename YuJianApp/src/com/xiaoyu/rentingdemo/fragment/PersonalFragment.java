package com.xiaoyu.rentingdemo.fragment;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

import com.xiaoyu.rentingdemo.R;
import com.xiaoyu.rentingdemo.adapter.PersonalAdapter;
import com.xiaoyu.rentingdemo.data.bean.PersonalInfoBean;
import com.xiaoyu.rentingdemo.util.Constants;
import com.xiaoyu.rentingdemo.util.MLog;
import com.xiaoyu.rentingdemo.util.PullParserPersonalInfo;
import com.xiaoyu.rentingdemo.util.ToastUtils;
import com.xiaoyu.rentingdemo.util.Utils;
import com.xiaoyu.rentingdemo.widget.ScaleImageView;

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

	private List<PersonalInfoBean> personalInfoBeans = new ArrayList<PersonalInfoBean>();

	private PersonalAdapter personalAdapter;

	private PopupWindow popupWindow; // choose picture popup window

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

		// add personal list
		addPersonalInfoList();
		setLinstener();
		initPopuWindow();
		personalAdapter = new PersonalAdapter(getActivity(), personalInfoBeans);
		listViewMyConfig.setAdapter(personalAdapter);
		personalAdapter.notifyDataSetChanged();
	}

	/**
	 * pull parse the data of personal info text
	 */
	private void addPersonalInfoList() {
		InputStream inputStream;
		PullParserPersonalInfo personalInfo = new PullParserPersonalInfo();
		try {
			inputStream = getResources().getAssets().open("file_personal.xml");
			personalInfoBeans = personalInfo.getpersonalInfor(inputStream);
		} catch (Exception e) {
			MLog.e(TAG, e);
		}
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
		imageViewHome.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_login:
			layoutLogin.setVisibility(View.VISIBLE);
			layoutUnlogin.setVisibility(View.GONE);
			break;
		case R.id.siv_personal_page_house_image:
			popupWindow.showAtLocation(imageViewHome, Gravity.BOTTOM, 0, 0);
			break;
		case R.id.btn_photo:
			popupWindow.dismiss();
			Intent picture = new Intent(Intent.ACTION_PICK,
					Media.EXTERNAL_CONTENT_URI);
			startActivityForResult(picture, Constants.PHOTO);
			break;
		case R.id.btn_take_photo:
			popupWindow.dismiss();
			Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(camera, Constants.CAMERA);
			break;
		default:
			break;
		}
	}

	/**
	 * INIT POP WINDOW
	 */
	private void initPopuWindow() {
		LayoutInflater inflater = LayoutInflater.from(getActivity());
		View view = inflater.inflate(R.layout.layout_pop_window, null);
		Button buttonPhoto, buttonTakePhoto;
		buttonPhoto = (Button) view.findViewById(R.id.btn_photo);
		buttonTakePhoto = (Button) view.findViewById(R.id.btn_take_photo);
		buttonPhoto.setOnClickListener(this);
		buttonTakePhoto.setOnClickListener(this);
		ColorDrawable dw = new ColorDrawable(0xe0000000);
		popupWindow = new PopupWindow(view, LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT, true);
		popupWindow.setBackgroundDrawable(dw);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Bitmap bm = null;
		try {
			ContentResolver resolver = getActivity().getContentResolver();
			if (resultCode != getActivity().RESULT_OK) {
				return;
			} else if (requestCode == Constants.PHOTO) {
				Uri originalUri = data.getData();
				if (originalUri != null) {
					bm = MediaStore.Images.Media.getBitmap(resolver,
							originalUri);
					//TODO change the set bitmap funciton
					imageViewHome.setImageBitmap(Utils.zoomImage(bm,
							screenWidth, Utils.Dp2Px(getActivity(), 150)));
					Toast.makeText(getActivity(), "设置成功", Toast.LENGTH_SHORT)
							.show();
				}
			} else if (requestCode == Constants.CAMERA
					&& resultCode == getActivity().RESULT_OK) {
				Bundle bundle = data.getExtras();
				if (bundle != null) {
					bm = (Bitmap) bundle.get("data");
					//TODO change the set bitmap funciton
					imageViewHome.setImageBitmap(Utils.zoomImage(bm,
							screenWidth, Utils.Dp2Px(getActivity(), 150)));
					Toast.makeText(getActivity(), "设置成功", Toast.LENGTH_SHORT)
							.show();
				}
			}
		} catch (Exception e) {
			Toast.makeText(getActivity(), "选择图片错误，图片只能为jpg格式",
					Toast.LENGTH_SHORT).show();
		}
	}

}
