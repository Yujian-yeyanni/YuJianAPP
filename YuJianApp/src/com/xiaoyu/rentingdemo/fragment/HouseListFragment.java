package com.xiaoyu.rentingdemo.fragment;

import java.util.ArrayList;
import java.util.List;

import com.xiaoyu.rentingdemo.R;
import com.xiaoyu.rentingdemo.adapter.ApartmentListAdapter;
import com.xiaoyu.rentingdemo.data.action.GetRoomDetailAction;
import com.xiaoyu.rentingdemo.data.bean.AllRoomListBean;
import com.xiaoyu.rentingdemo.data.bean.RoomBean;
import com.xiaoyu.rentingdemo.network.HttpHandler;
import com.xiaoyu.rentingdemo.network.NoticHandler;
import com.xiaoyu.rentingdemo.util.DataSource;
import com.xiaoyu.rentingdemo.util.ToastUtils;
import com.xiaoyu.rentingdemo.widget.MyListView;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class HouseListFragment extends BaseFragment implements
		OnEditorActionListener, OnItemClickListener, NoticHandler {

	private static final String TAG = HouseListFragment.class.getName();

	private MyListView listViewPictures;
	private List<RoomBean> roomBeans = new ArrayList<RoomBean>();
	private ApartmentListAdapter apartmentListAdapter;

	private GetRoomDetailAction getRoomDetailAction;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getRoomDetailAction = new GetRoomDetailAction(this, getActivity());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		layoutId = R.layout.fragment_hose_list;
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void findViewById(View rootView) {
		listViewPictures = (MyListView) rootView
				.findViewById(R.id.lv_apartment_pictures);

		if (DataSource.getAllRoomListBean() != null) {
			roomBeans = DataSource.getAllRoomListBean().getRoomList();
		}

		apartmentListAdapter = new ApartmentListAdapter(getActivity(),
				roomBeans);
		listViewPictures.setAdapter(apartmentListAdapter);
		apartmentListAdapter.notifyDataSetChanged();
		super.findViewById(rootView);
		setLinstener();
	}

	@Override
	public void initCommTop(View rootView) {
		super.initCommTop(rootView);
		textViewTitle.setVisibility(View.GONE);
	}

	@Override
	public void setLinstener() {
		super.setLinstener();
		editTextSearch.setOnEditorActionListener(this);
		listViewPictures.setFocusable(true);
		listViewPictures.setOnItemClickListener(this);
	}

	@Override
	public boolean onEditorAction(TextView textView, int actionId,
			KeyEvent event) {
		if (actionId == EditorInfo.IME_ACTION_SEND
				|| (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
			// DO SOMETHING
			return true;
		}
		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		int roomId = roomBeans.get(position).getId();
		getRoomDetailAction.getRoomDetail("shanghai", roomId);
		Toast.makeText(getActivity(), roomId + "", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void HttpSuccess(HttpHandler handler) {
		if (handler == null) {
			return;
		}
		if (handler instanceof GetRoomDetailAction) {
			skipToFragment(new HouseDetailFragment(), R.id.fl_content, true);
		}

	}

	@Override
	public void HttpError(String warningMessage) {

	}

	@Override
	public void HttpFailure() {

	}

}
