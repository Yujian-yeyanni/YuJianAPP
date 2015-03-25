package com.xiaoyu.picturelistdemo;

import com.xiaoyu.picturelistdemo.data.action.GetAllRoomListAction;
import com.xiaoyu.picturelistdemo.data.action.GetConfigAction;
import com.xiaoyu.picturelistdemo.fragment.HouseListFragment;
import com.xiaoyu.picturelistdemo.network.HttpHandler;
import com.xiaoyu.picturelistdemo.network.NoticHandler;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.widget.FrameLayout;

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
					R.id.fl_content, true);
		}
	}

	@Override
	public void HttpError(String warningMessage) {

	}

	@Override
	public void HttpFailure() {

	}

}
