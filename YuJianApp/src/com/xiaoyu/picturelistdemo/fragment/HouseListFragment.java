package com.xiaoyu.picturelistdemo.fragment;

import java.util.ArrayList;
import java.util.List;

import com.xiaoyu.picturelistdemo.R;
import com.xiaoyu.picturelistdemo.adapter.ApartmentListAdapter;
import com.xiaoyu.picturelistdemo.data.bean.AllRoomListBean;
import com.xiaoyu.picturelistdemo.data.bean.RoomBean;
import com.xiaoyu.picturelistdemo.util.DataSource;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class HouseListFragment extends BaseFragment implements OnEditorActionListener {

	private static final String TAG = HouseListFragment.class.getName();
	
	private ListView listViewPictures;
	private List<RoomBean> roomBeans = new ArrayList<RoomBean>();
	private ApartmentListAdapter apartmentListAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		layoutId = R.layout.fragment_apartment_pictures;
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void findViewById(View rootView) {
		super.findViewById(rootView);
		listViewPictures = (ListView) rootView
				.findViewById(R.id.lv_apartment_pictures);

		if (DataSource.getAllRoomListBean() != null) {
			roomBeans = DataSource.getAllRoomListBean().getRoomList();
		}

		apartmentListAdapter = new ApartmentListAdapter(getActivity(),
				roomBeans);
		listViewPictures.setAdapter(apartmentListAdapter);
		apartmentListAdapter.notifyDataSetChanged();
	}
	
	@Override
	public void setLinstener() {
		super.setLinstener();
		editTextSearch.setOnEditorActionListener(this);
	}

	@Override
	public boolean onEditorAction(TextView textView, int actionId,
			KeyEvent event) {
		if (actionId == EditorInfo.IME_ACTION_SEND
				|| (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
			//DO SOMETHING
			return true;
		}
		return false;
	}

}
