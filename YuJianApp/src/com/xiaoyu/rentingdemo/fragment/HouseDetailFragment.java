package com.xiaoyu.rentingdemo.fragment;

import java.util.ArrayList;
import java.util.List;

import com.xiaoyu.rentingdemo.R;
import com.xiaoyu.rentingdemo.adapter.ViewPagerAdapter;
import com.xiaoyu.rentingdemo.data.bean.RoomBean;
import com.xiaoyu.rentingdemo.util.Constants;
import com.xiaoyu.rentingdemo.util.DataSource;
import com.xiaoyu.rentingdemo.util.FinalBitmapUtils;
import com.xiaoyu.rentingdemo.util.Utils;
import com.xiaoyu.rentingdemo.widget.ScaleImageView;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HouseDetailFragment extends BaseFragment implements
		OnClickListener {

	private ViewGroup viewGroupPager;
	// view pager
	private ViewPager viewPager;
	private TextView textViewNowCount;
	private TextView textViewAllCount;
	private ScaleImageView scaleImageView;

	private TextView textViewRoomDesc;
	private TextView textViewRoomPrice;
	private TextView textViewRoomType;
	private TextView textViewRoomAddress;
	private LinearLayout layoutRoomFeature;
	private TextView textViewPublicFacilities;
	private TextView textViewRoomArea;
	private TextView textViewTel;
	private TextView textViewAddress;
	private LinearLayout layoutHouseCondition;

	// TODO add mapview

	private List<String> roomPictureList;
	private RoomBean roomBean;
	private List<RoomBean> houseRoomList;

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
		scaleImageView = (ScaleImageView) viewGroupPager
				.findViewById(R.id.siv_viewpager);

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

		setData();

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
		String strTypeInfo = String.format(strTypeFormat,
				roomBean.getRoomName());
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
			if (roomBean.getId() == this.roomBean.getId()) {
				relativeLayout.setBackgroundResource(R.color.red);
			}
			String strConditionFormat = getActivity().getText(
					R.string.str_format_house_condition).toString();
			String strConditonInfo = String.format(strConditionFormat,
					roomBean.getRoomName(), roomBean.getTowards(),
					roomBean.getRoomArea());
			textViewRoomCondition.setText(strConditonInfo);
			textViewRoomPrice.setText("￥" + roomBean.getMinPrice() / 100);
			layoutHouseCondition.addView(view);
		}

	}

	@Override
	public void initCommTop(View rootView) {
		super.initCommTop(rootView);
		editTextSearch.setVisibility(View.GONE);
		imageViewPersonal.setVisibility(View.GONE);
		imageViewMenu.setVisibility(View.VISIBLE);
		imageViewMenu.setBackgroundResource(R.drawable.icon_back);
	}

	@Override
	public void setLinstener() {
		super.setLinstener();
		imageViewMenu.setOnClickListener(this);
		initViewPager();
	}

	private void initViewPager() {
		List<ScaleImageView> imageViews = new ArrayList<ScaleImageView>();
		scaleImageView.setVisibility(View.GONE);
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

		viewPager.setCurrentItem(imageViews.size() * 100);
		viewPager.setAdapter(new ViewPagerAdapter(imageViews, textViewNowCount,
				getActivity(), scaleImageView));
		viewPager.setOnPageChangeListener(new ViewPagerAdapter(imageViews,
				textViewNowCount, getActivity(), scaleImageView));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_comm_top_menu:
			// TODO GO BACK
			fragmentPopStack();
			break;
		default:
			break;
		}

	}

}
