package com.xiaoyu.rentingdemo.fragment;

import java.util.ArrayList;
import java.util.List;

import com.xiaoyu.rentingdemo.R;
import com.xiaoyu.rentingdemo.adapter.ApartmentListAdapter;
import com.xiaoyu.rentingdemo.data.action.GetAllRoomListAction;
import com.xiaoyu.rentingdemo.data.action.GetRoomDetailAction;
import com.xiaoyu.rentingdemo.data.bean.AllRoomListBean;
import com.xiaoyu.rentingdemo.data.bean.RoomBean;
import com.xiaoyu.rentingdemo.network.HttpHandler;
import com.xiaoyu.rentingdemo.network.NoticHandler;
import com.xiaoyu.rentingdemo.util.Constants;
import com.xiaoyu.rentingdemo.util.DataSource;
import com.xiaoyu.rentingdemo.util.StringUtil;
import com.xiaoyu.rentingdemo.util.ToastUtils;
import com.xiaoyu.rentingdemo.widget.MyListView;
import com.xiaoyu.rentingdemo.widget.pullrefresh.XListView.IXListViewListener;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

/**
 * 首页list
 * 
 * @author shwan
 * 
 */
public class HouseListFragment extends BaseFragment implements
		OnEditorActionListener, OnItemClickListener, NoticHandler,
		IXListViewListener {

	private static final String TAG = HouseListFragment.class.getName();

	private MyListView listViewPictures;
	private List<RoomBean> roomBeans = new ArrayList<RoomBean>();
	private ApartmentListAdapter apartmentListAdapter;

	private GetRoomDetailAction getRoomDetailAction;
	private GetAllRoomListAction getAllRoomListAction;

	private Handler handler;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getRoomDetailAction = new GetRoomDetailAction(this, getActivity());
		getAllRoomListAction = new GetAllRoomListAction(this, getActivity());
		if (DataSource.getAllRoomListBean() != null) {
			// GET ROOM LIST
			roomBeans = DataSource.getAllRoomListBean().getRoomList();
		}
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
		//imageview in the viewpager be clicked
		handler = new Handler() {
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case Constants.HANDLER_MESSAGE_VIEWPAGE_CLICK:
					int roomId = roomBeans.get(msg.arg1).getId();
					getRoomDetailAction.getRoomDetail("shanghai", roomId);
					break;
				default:
					break;
				}
			};
		};
		apartmentListAdapter = new ApartmentListAdapter(getActivity(), handler);
		refreshHouseList(roomBeans);
		super.findViewById(rootView);
		setLinstener();
	}

	private void refreshHouseList(List<RoomBean> roomBeans) {
		//refresh house list show
		apartmentListAdapter.setRoomBeanList(roomBeans);
		listViewPictures.setAdapter(apartmentListAdapter);
		apartmentListAdapter.notifyDataSetChanged();
	}

	@Override
	public void initCommTop(View rootView) {
		super.initCommTop(rootView);
		textViewTitle.setVisibility(View.GONE);
		layouLeftBack.setVisibility(View.GONE);
		textViewCity.setVisibility(View.VISIBLE);
	}

	@Override
	public void setLinstener() {
		super.setLinstener();
		editTextSearch.setOnEditorActionListener(this);// ADD ONTEXTCHANGE
														// LISTENER
		listViewPictures.setFocusable(true);
		listViewPictures.setOnItemClickListener(this);
		listViewPictures.setPullRefreshEnable(true);
		listViewPictures.setPullLoadEnable(false);
		listViewPictures.setXListViewListener(this);
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
		int roomId = roomBeans.get(position - 1).getId();
		// SEND REQUEST
		getRoomDetailAction.getRoomDetail("shanghai", roomId);
	}

	@Override
	public void HttpSuccess(HttpHandler handler) {
		if (handler == null) {
			return;
		}
		if (handler instanceof GetRoomDetailAction) {
			skipToFragment(new HouseDetailFragment(), R.id.fl_content, true);
		} else if (handler instanceof GetAllRoomListAction) {
			refreshHouseList(DataSource.getAllRoomListBean().getRoomList());
			listViewPictures.setRefreshTime(StringUtil.formatTime(System
					.currentTimeMillis()));
		}
	}

	@Override
	public void HttpError(String warningMessage) {

	}

	@Override
	public void HttpFailure() {

	}

	@Override
	public void onRefresh() {
		getAllRoomListAction.getAllRoomList("shanghai");
	}

	@Override
	public void onLoadMore() {

	}

}
