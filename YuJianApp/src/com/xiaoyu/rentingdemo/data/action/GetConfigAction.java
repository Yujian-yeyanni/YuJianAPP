package com.xiaoyu.rentingdemo.data.action;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.xiaoyu.rentingdemo.R;
import com.xiaoyu.rentingdemo.network.NoticHandler;
import com.xiaoyu.rentingdemo.util.DataSource;
import com.xiaoyu.rentingdemo.util.MLog;
import com.xiaoyu.rentingdemo.util.Constants.HTTPRequest;

public class GetConfigAction extends BaseAction {
	private static final String TAG = GetConfigAction.class.getName();
	private Context context;

	public GetConfigAction(NoticHandler handler, Context context) {
		super(handler, context);
		this.context = context;
	}

	public void getConfig() {
		sendHttpRequest(null, HTTPRequest.GET, GET_CONFIG, true,
				R.string.str_loading_message);
	}

	@Override
	public void parseJSON(String json) {
		JSONObject jsonObject = null;
		try {
			jsonObject = new JSONObject(json);
			if (jsonObject != null) {
				String imageServer = jsonObject.optString(KEY_IMAGE_SERVER);
				DataSource.setImage_server(imageServer);
				MLog.e(TAG, imageServer);
			}
		} catch (JSONException e) {
			MLog.e(TAG, e);
		}
		super.parseJSON(json);
	}

}
