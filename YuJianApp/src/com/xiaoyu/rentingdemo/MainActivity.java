package com.xiaoyu.rentingdemo;

import android.content.Context;
import android.os.Bundle;

import com.xiaoyu.rentingdemo.data.action.GetAllRoomListAction;
import com.xiaoyu.rentingdemo.data.action.GetConfigAction;
import com.xiaoyu.rentingdemo.fragment.HouseListFragment;
import com.xiaoyu.rentingdemo.network.HttpHandler;
import com.xiaoyu.rentingdemo.network.NoticHandler;

public class MainActivity extends BaseActivity implements NoticHandler {

	private GetAllRoomListAction allRoomListAction;
	private GetConfigAction getConfigAction;
	public static Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		allRoomListAction = new GetAllRoomListAction(this, this);
		getConfigAction = new GetConfigAction(this, this);
		if (allRoomListAction == null || getConfigAction == null) {
			return;
		}
		allRoomListAction.getAllRoomList("shanghai");
		getConfigAction.getConfig();
		mContext = this;
	}

	public static Context getContext() {
		return mContext;
	}

	@Override
	public void HttpSuccess(HttpHandler handler) {
		if (handler instanceof GetAllRoomListAction) {
			// SKIP TO PICTURE SHOW PAGE
			skipToFragmentByContentId(new HouseListFragment(),
					R.id.fl_content, false);
		}
	}

	@Override
	public void HttpError(String warningMessage) {

	}

	@Override
	public void HttpFailure() {

	}

}
