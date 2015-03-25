package com.xiaoyu.picturelistdemo.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;

import com.xiaoyu.picturelistdemo.R;
import com.xiaoyu.picturelistdemo.data.bean.RoomBean;
import com.xiaoyu.picturelistdemo.listener.MyPagerChangeListener;
import com.xiaoyu.picturelistdemo.util.Constants;
import com.xiaoyu.picturelistdemo.util.DataSource;
import com.xiaoyu.picturelistdemo.util.FinalBitmapUtils;
import com.xiaoyu.picturelistdemo.util.Utils;
import com.xiaoyu.picturelistdemo.widget.ScaleImageView;

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
	private int width;
	private int height;

	public ApartmentListAdapter(Context context, List<RoomBean> roomBeans) {
		this.context = context;
		this.roomBeans = roomBeans;
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		width = wm.getDefaultDisplay().getWidth(); // 手机屏幕的宽度
		height = wm.getDefaultDisplay().getHeight(); // 手机屏幕的高度
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
			conventView.setTag(holder);
		} else {
			holder = (ViewHolder) conventView.getTag();
		}
		// 设置viewpager高度
		holder.viewPager.getLayoutParams().height = (int) (height / 3.5);
		// 默认图片链接"http://7qn8hl.com1.z0.glb.clouddn.com/E0BC579C72B249A5AAF89BBBB4F84653.jpg"
		if (roomBeans == null) {
			return null;
		}
		RoomBean roomBean = roomBeans.get(position);
		setHouseListData(roomBean, holder);
		setImageView(roomBean.getRoomPictureArray(), holder);
		return conventView;
	}

	/**
	 * adapter中填入数据
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

	private void setImageView(List<String> imagetDesc, ViewHolder holder) {
		List<ScaleImageView> imageViews = new ArrayList<ScaleImageView>();
		if (imagetDesc == null) {
			return;
		}
		for (int i = 0; i < imagetDesc.size(); i++) {
			String photosDesc = imagetDesc.get(i);
			// 设置图片尺寸
			String photosSize = "?imageView2/0/w/480/h/320";
			ScaleImageView image = new ScaleImageView(context);
			image.setImageWidth(width);
			image.setImageHeight((int) (height / 3.5));
			FinalBitmapUtils.getInstance().displayImage(
					DataSource.getImage_server() + photosDesc + photosSize,
					image);
			imageViews.add(image);
		}
		holder.textViewAllCount.setText(String.valueOf(imagetDesc.size()));
		holder.viewPager.setAdapter(new ViewPagerAdapter(imageViews,
				holder.textViewNowCount));
		// 添加viewpager滑动变化
		holder.viewPager.setOnPageChangeListener(new ViewPagerAdapter(
				imageViews, holder.textViewNowCount));
	}

	class ViewHolder {
		ViewPager viewPager;
		TextView textViewHouseAddress;
		TextView textViewHouseArea;
		TextView textViewHousePrice;
		TextView textViewNowCount;
		TextView textViewAllCount;
		ViewGroup viewGroup;
	}

}
