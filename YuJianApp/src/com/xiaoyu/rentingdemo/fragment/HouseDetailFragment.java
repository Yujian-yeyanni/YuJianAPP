package com.xiaoyu.rentingdemo.fragment;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xiaoyu.rentingdemo.R;
import com.xiaoyu.rentingdemo.adapter.ViewPagerAdapter;
import com.xiaoyu.rentingdemo.data.bean.RoomBean;
import com.xiaoyu.rentingdemo.util.Constants;
import com.xiaoyu.rentingdemo.util.DataSource;
import com.xiaoyu.rentingdemo.util.FinalBitmapUtils;
import com.xiaoyu.rentingdemo.util.MLog;
import com.xiaoyu.rentingdemo.util.Utils;
import com.xiaoyu.rentingdemo.widget.QualityLabelView;
import com.xiaoyu.rentingdemo.widget.ScaleImageView;

public class HouseDetailFragment extends BaseFragment implements
		OnClickListener {

	private static final String TAG = HouseDetailFragment.class.getName();

	private ViewGroup viewGroupPager;
	// view pager
	private ViewPager viewPager;
	private TextView textViewNowCount;
	private TextView textViewAllCount;

	private TextView textViewRoomDesc;
	private TextView textViewRoomPrice;
	private TextView textViewRoomType;
	private TextView textViewRoomAddress;
	private LinearLayout layoutRoomFeature;// add house label layout
	private TextView textViewPublicFacilities;
	private TextView textViewRoomArea;
	private TextView textViewTel;
	private TextView textViewAddress;
	private LinearLayout layoutHouseCondition;

	private RelativeLayout layoutOrderTel; // order layout

	private List<String> roomPictureList;
	private RoomBean roomBean;
	private List<RoomBean> houseRoomList;

	// 显示地图
	private ScaleImageView scaleImageView;

	// private String imageString ;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (DataSource.getRoomDetailBean() == null) {
			return;
		}
		roomBean = DataSource.getRoomDetailBean().getRoomBean();
		roomPictureList = DataSource.getRoomDetailBean().getRoomBean()
				.getRoomPictureArray();
		houseRoomList = DataSource.getRoomDetailBean().getHourseRoomList();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		layoutId = R.layout.fragment_house_detail;
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void findViewById(View rootView) {
		super.findViewById(rootView);
		viewGroupPager = (ViewGroup) rootView
				.findViewById(R.id.include_house_detail_viewpager);
		viewPager = (ViewPager) viewGroupPager
				.findViewById(R.id.vp_item_apartment_list);
		textViewNowCount = (TextView) viewGroupPager
				.findViewById(R.id.tv_image_now_count);
		textViewAllCount = (TextView) viewGroupPager
				.findViewById(R.id.tv_image_all_count);

		textViewRoomDesc = (TextView) rootView
				.findViewById(R.id.tv_house_detail_desc);
		textViewRoomPrice = (TextView) rootView
				.findViewById(R.id.tv_house_detail_price);
		textViewRoomType = (TextView) rootView
				.findViewById(R.id.tv_house_detail_type);
		textViewRoomAddress = (TextView) rootView
				.findViewById(R.id.tv_house_detail_address);
		layoutRoomFeature = (LinearLayout) rootView
				.findViewById(R.id.ll_house_detail_room_feature);
		textViewPublicFacilities = (TextView) rootView
				.findViewById(R.id.tv_house_detail_public_facilities_content);

		textViewRoomArea = (TextView) rootView
				.findViewById(R.id.tv_house_detail_area);
		textViewTel = (TextView) rootView
				.findViewById(R.id.tv_house_detail_tel);
		textViewAddress = (TextView) rootView
				.findViewById(R.id.tv_house_detail_house_address);
		layoutHouseCondition = (LinearLayout) rootView
				.findViewById(R.id.ll_house_detail_house_basic_info);
		scaleImageView = (ScaleImageView) rootView
				.findViewById(R.id.siv_house_detail_map_image);

		layoutOrderTel = (RelativeLayout) rootView
				.findViewById(R.id.rl_house_detail_bottom);

		setData();
		initMapView();
		setLinstener();
	}

	private void setData() {

		String strRoomDescFormat = getActivity().getText(
				R.string.str_house_detail_house_des).toString();
		String strRoomDescInfo = String.format(strRoomDescFormat,
				getText(R.string.str_house_detail_default_desc),
				roomBean.getH_district(), roomBean.getH_street());
		textViewRoomDesc.setText(strRoomDescInfo);
		textViewRoomPrice.setText("￥" + roomBean.getMinPrice() / 100);
		String strTypeFormat = getActivity().getText(
				R.string.str_format_house_type).toString();
		// TODO change room name show
		String strTypeInfo = String.format(strTypeFormat,
				Utils.getUpperCase(roomBean.getRoomName()));
		textViewRoomType.setText(strTypeInfo);
		textViewRoomAddress.setText(roomBean.getH_address());
		textViewPublicFacilities.setText("储物区" + " " + "餐桌" + " " + "餐椅" + " "
				+ "冰箱" + " " + "热水器" + " " + "微波炉" + " " + "浴霸" + " " + "公共厨房"
				+ " " + "扫帚" + " " + "拖把");
		String strHouseAreaFormat = getActivity().getText(
				R.string.str_format_house_area_desc).toString();
		String strHouseAreaInfo = String.format(strHouseAreaFormat, roomBean
				.getH_hourseArea(), Utils.getHouseShape(
				roomBean.getH_hourseShape(), Constants.HOUSE_ROOM), roomBean
				.getH_floor());
		textViewRoomArea.setText(strHouseAreaInfo);
		textViewAddress.setText(roomBean.getH_district()
				+ roomBean.getH_street() + roomBean.getH_villageName());

		QualityLabelView labelView = new QualityLabelView(getActivity());
		// TODO add label
		labelView.setLabelView(layoutRoomFeature, 5);
		addHouseRoomList(layoutHouseCondition, houseRoomList);
	}

	/**
	 * add house room basic condition
	 * 
	 * @param layoutHouseCondition
	 * @param houseRoomList
	 */
	private void addHouseRoomList(LinearLayout layoutHouseCondition,
			List<RoomBean> houseRoomList) {
		if (layoutHouseCondition == null) {
			return;
		}
		layoutHouseCondition.removeAllViews();
		LayoutInflater inflater = LayoutInflater.from(getActivity());
		for (int i = 0; i < houseRoomList.size(); i++) {
			View view = inflater.inflate(
					R.layout.item_house_detail_room_condition,
					layoutHouseCondition, false);
			RelativeLayout relativeLayout = (RelativeLayout) view
					.findViewById(R.id.rl_house_detail_house_room_condition);
			TextView textViewRoomCondition = (TextView) view
					.findViewById(R.id.tv_room_condition);
			TextView textViewRoomPrice = (TextView) view
					.findViewById(R.id.tv_room_price);
			RoomBean roomBean = houseRoomList.get(i);
			// update home background
			if (roomBean.getId() == this.roomBean.getId()) {
				relativeLayout
						.setBackgroundResource(R.drawable.bg_room_list_red);
			}
			String strConditionFormat = getActivity().getText(
					R.string.str_format_house_condition).toString();
			String strConditonInfo = String.format(strConditionFormat,
					Utils.getUpperCase(roomBean.getRoomName()),
					roomBean.getTowards(), roomBean.getRoomArea());
			textViewRoomCondition.setText(strConditonInfo);
			textViewRoomPrice.setText("￥" + roomBean.getMinPrice() / 100);
			layoutHouseCondition.addView(view);
		}

	}

	/**
	 * config baidumap
	 */
	private void initMapView() {
		int imageWith = 0;
		scaleImageView.setVisibility(View.VISIBLE);
		scaleImageView.setImageWidth(screenWidth);
		scaleImageView.setImageHeight(320); // set image height
		if (screenWidth >= Constants.MAX_SCREEN_WIDTH) {
			imageWith = Constants.CHANGED_SCREEN_WIDTH;
		}
		String imageUrlFormat = Constants.BaiduImageURL;
		String imageUrlInfo = String.format(
				imageUrlFormat,
				String.valueOf(imageWith),
				String.valueOf(320),
				Utils.toUtf8String(roomBean.getH_city()
						+ roomBean.getH_villageName()),
				Utils.toUtf8String(roomBean.getH_city()
						+ roomBean.getH_villageName()),
				Utils.toUtf8String(roomBean.getH_villageName()),
				Utils.toUtf8String(roomBean.getH_city()
						+ roomBean.getH_villageName()));
		FinalBitmapUtils.getInstance().displayImage(imageUrlInfo,
				scaleImageView);
		MLog.e(TAG, imageUrlInfo);
	}

	@Override
	public void initCommTop(View rootView) {
		super.initCommTop(rootView);
		relativeLayoutTopSearch.setVisibility(View.GONE);
		textViewTitle.setVisibility(View.GONE);
		imageViewPersonal.setVisibility(View.GONE);
		textViewCity.setVisibility(View.GONE);
		layouLeftBack.setVisibility(View.VISIBLE);
	}

	@Override
	public void setLinstener() {
		super.setLinstener();
		scaleImageView.setOnClickListener(this);
		layoutOrderTel.setOnClickListener(this);
		initViewPager();
	}

	/**
	 * INIT VIEWPAGER
	 */
	private void initViewPager() {
		List<ScaleImageView> imageViews = new ArrayList<ScaleImageView>();
		if (roomPictureList == null) {
			return;
		}
		for (int i = 0; i < roomPictureList.size(); i++) {
			String photosDesc = roomPictureList.get(i);
			ScaleImageView image = new ScaleImageView(getActivity());
			image.setImageWidth(screenWidth);
			image.setImageHeight((int) (screenHeight / 3));
			FinalBitmapUtils.getInstance().displayImage(
					DataSource.getImage_server() + photosDesc
							+ Constants.photosSize, image);
			imageViews.add(image);
		}
		viewPager.getLayoutParams().height = (int) (screenHeight / 3);
		textViewAllCount.setText(String.valueOf(roomPictureList.size()));

		MLog.e(TAG, String.valueOf(imageViews.size()) + "yyn");

		viewPager.setAdapter(new ViewPagerAdapter(imageViews, textViewNowCount,
				getActivity(), null, 0, null));
		viewPager.setOnPageChangeListener(new ViewPagerAdapter(imageViews,
				textViewNowCount, getActivity(), null, 0, null));
		viewPager.setCurrentItem(imageViews.size() * 100);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.siv_house_detail_map_image:
			// SKIP TO ADDRESS AROUND FRAGMNET
			AddressAroundFragment aroundFragment = new AddressAroundFragment();
			Bundle bundle = new Bundle();
			bundle.putString(Constants.KEY_POI_CITY, roomBean.getH_city());
			bundle.putString(Constants.KEY_POI_VILLAGE_NAME,
					roomBean.getH_villageName());
			aroundFragment.setArguments(bundle);
			addToFragment(aroundFragment, R.id.fl_content, true);
			break;
		case R.id.rl_house_detail_bottom:
			// prompt the call confirm dialog
			showConfirmDialog(getText(R.string.str_confirm_call).toString());
			break;
		default:
			break;
		}
	}

	/**
	 * confirm call dialog
	 * 
	 * @param promptMessage
	 */
	private void showConfirmDialog(String promptMessage) {
		Dialog alertDialog = new AlertDialog.Builder(getActivity())
				// .setTitle(promptMessage)
				.setMessage(promptMessage)
				.setIcon(R.drawable.icon_phone)
				.setPositiveButton(R.string.str_confirm,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								Intent intent = new Intent(Intent.ACTION_CALL,
										Uri.parse(Constants.TEL));
								startActivity(intent);
							}
						})
				.setNegativeButton(R.string.str_cancel,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
							}
						}).create();
		alertDialog.setCanceledOnTouchOutside(false);
		alertDialog.show();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onResume() {
		super.onResume();
		// setCurrentFragment(new HouseDetailFragment());
	}

	@Override
	public void onPause() {
		super.onPause();
	}

}
