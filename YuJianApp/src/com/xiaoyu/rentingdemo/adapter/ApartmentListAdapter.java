package com.xiaoyu.rentingdemo.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;

import com.xiaoyu.rentingdemo.R;
import com.xiaoyu.rentingdemo.data.bean.RoomBean;
import com.xiaoyu.rentingdemo.listener.MyPagerChangeListener;
import com.xiaoyu.rentingdemo.util.Constants;
import com.xiaoyu.rentingdemo.util.DataSource;
import com.xiaoyu.rentingdemo.util.FinalBitmapUtils;
import com.xiaoyu.rentingdemo.util.Utils;
import com.xiaoyu.rentingdemo.widget.ScaleImageView;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ApartmentListAdapter extends BaseAdapter {
	private List<RoomBean> roomBeans;
	private Context context;
	private int width; // screen width
	private int height; // screen height

	public ApartmentListAdapter(Context context, List<RoomBean> roomBeans) {
		this.context = context;
		this.roomBeans = roomBeans;
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		width = wm.getDefaultDisplay().getWidth();
		height = wm.getDefaultDisplay().getHeight();
	}

	@Override
	public int getCount() {
		if (roomBeans == null) {
			return 0;
		}
		return roomBeans.size();
	}

	@Override
	public Object getItem(int position) {
		return roomBeans.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View conventView, ViewGroup viewpGroup) {
		ViewHolder holder = null;
		if (conventView == null) {
			conventView = LayoutInflater.from(context).inflate(
					R.layout.item_apartment_list, null);
			holder = new ViewHolder();

			holder.textViewHouseAddress = (TextView) conventView
					.findViewById(R.id.tv_item_apartment_house_address);
			holder.textViewHouseArea = (TextView) conventView
					.findViewById(R.id.tv_item_apartment_house_area);
			holder.textViewHousePrice = (TextView) conventView
					.findViewById(R.id.tv_item_apartment_house_price);

			holder.viewGroup = (ViewGroup) conventView
					.findViewById(R.id.include_item_house_viewpager);
			holder.viewPager = (ViewPager) holder.viewGroup
					.findViewById(R.id.vp_item_apartment_list);
			holder.textViewNowCount = (TextView) holder.viewGroup
					.findViewById(R.id.tv_image_now_count);
			holder.textViewAllCount = (TextView) holder.viewGroup
					.findViewById(R.id.tv_image_all_count);
			holder.scaleImageView = (ScaleImageView) holder.viewGroup
					.findViewById(R.id.siv_viewpager);
			conventView.setTag(holder);
		} else {
			holder = (ViewHolder) conventView.getTag();
		}
		// set viewpager height
		holder.viewPager.getLayoutParams().height = (int) (height / 3.72);
		// default image url
		// "http://7qn8hl.com1.z0.glb.clouddn.com/E0BC579C72B249A5AAF89BBBB4F84653.jpg"
		if (roomBeans == null) {
			return null;
		}
		RoomBean roomBean = roomBeans.get(position);
		List<String> roomPictureList = roomBean.getRoomPictureArray();
		setHouseListData(roomBean, holder);
		setImageView(roomPictureList, holder);
		return conventView;
	}

	/**
	 * add data to adapter
	 * 
	 * @param roomBean
	 * @param holder
	 */
	private void setHouseListData(RoomBean roomBean, ViewHolder holder) {
		String straddressFormat = context.getText(
				R.string.str_apartment_address_desc).toString();
		String strAddressInfo = String.format(straddressFormat,
				roomBean.getH_district(), roomBean.getH_street(),
				roomBean.getH_villageName(), roomBean.getRoomName());
		holder.textViewHouseAddress.setText(strAddressInfo);
		String strAreaFromat = context
				.getText(R.string.str_apartment_area_desc).toString();
		String strAreaInfo = String.format(strAreaFromat, roomBean
				.getRoomArea(), roomBean.getTowards(), Utils.getHouseShape(
				roomBean.getH_hourseShape(), Constants.HOUSE_ROOM), roomBean
				.getH_floor());
		holder.textViewHouseArea.setText(strAreaInfo);

		holder.textViewHousePrice.setText("￥" + roomBean.getMinPrice() / 100);
	}

	/**
	 * load viewpager image
	 * 
	 * @param imagetDesc
	 * @param holder
	 */
	private void setImageView(List<String> imagetDesc, ViewHolder holder) {
		List<ScaleImageView> imageViews = new ArrayList<ScaleImageView>();

		if (imagetDesc == null) {
			return;
		}
		holder.scaleImageView.setImageWidth(width);
		holder.scaleImageView.setImageHeight((int) (height / 3.5));
		holder.scaleImageView.setVisibility(View.VISIBLE);
		FinalBitmapUtils.getInstance().displayImage(
				DataSource.getImage_server() + imagetDesc.get(0)
						+ Constants.photosSize, holder.scaleImageView);

		for (int i = 0; i < imagetDesc.size(); i++) {
			String photosDesc = imagetDesc.get(i);
			ScaleImageView image = new ScaleImageView(context);
			image.setImageWidth(width);
			image.setImageHeight((int) (height / 3.5));
			FinalBitmapUtils.getInstance().displayImage(
					DataSource.getImage_server() + photosDesc
							+ Constants.photosSize, image);
			imageViews.add(image);
		}
		holder.textViewAllCount.setText(String.valueOf(imagetDesc.size()));
		holder.viewPager.setAdapter(new ViewPagerAdapter(imageViews,
				holder.textViewNowCount, context, holder.scaleImageView));
		// add viewpager scroll lintener
		holder.viewPager.setOnPageChangeListener(new ViewPagerAdapter(
				imageViews, holder.textViewNowCount, context,
				holder.scaleImageView));
		// set viewpager can roll cycle
		holder.viewPager.setCurrentItem(imageViews.size() * 100);

	}

	class ViewHolder {
		ViewPager viewPager;
		TextView textViewHouseAddress;
		TextView textViewHouseArea;
		TextView textViewHousePrice;
		TextView textViewNowCount;
		TextView textViewAllCount;
		ViewGroup viewGroup;
		ScaleImageView scaleImageView;
	}

}
