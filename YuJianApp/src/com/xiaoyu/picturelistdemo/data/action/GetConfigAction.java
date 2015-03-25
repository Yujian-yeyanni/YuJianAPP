package com.xiaoyu.picturelistdemo.data.action;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.xiaoyu.picturelistdemo.network.NoticHandler;
import com.xiaoyu.picturelistdemo.util.DataSource;
import com.xiaoyu.picturelistdemo.util.MLog;
import com.xiaoyu.picturelistdemo.util.Constants.HTTPRequest;

public class GetConfigAction extends BaseAction {
	private static final String TAG = GetConfigAction.class.getName();
	private Context context;

	public GetConfigAction(NoticHandler handler, Context context) {
		super(handler, context);
		this.context = context;
	}
	
	public void getConfig(){
		sendHttpRequest(null, HTTPRequest.GET, GET_CONFIG, true, "哈哈");
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
