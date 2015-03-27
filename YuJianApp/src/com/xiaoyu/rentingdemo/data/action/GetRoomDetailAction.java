package com.xiaoyu.rentingdemo.data.action;

import java.lang.reflect.Type;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiaoyu.rentingdemo.data.bean.AllRoomListBean;
import com.xiaoyu.rentingdemo.data.bean.RoomBean;
import com.xiaoyu.rentingdemo.data.bean.RoomDetailBean;
import com.xiaoyu.rentingdemo.network.NoticHandler;
import com.xiaoyu.rentingdemo.util.DataSource;
import com.xiaoyu.rentingdemo.util.MLog;
import com.xiaoyu.rentingdemo.util.Constants.HTTPRequest;

public class GetRoomDetailAction extends BaseAction {

	private static final String TAG = GetRoomDetailAction.class.getName();
	private Context context;

	public GetRoomDetailAction(NoticHandler handler, Context context) {
		super(handler, context);
		this.context = context;
	}

	public void getRoomDetail(String cityPinyin, int roomId) {
		sendHttpRequest(null, HTTPRequest.GET, cityPinyin + "/" + roomId
				+ ".json", false, "哈哈");
	}

	@Override
	public void parseJSON(String json) {
		JSONObject jsonObject = null;
		String jsonResult = null;
		try {
			jsonObject = new JSONObject(json);

			if (jsonObject != null) {
				jsonResult = jsonObject.toString();
				Type type = new TypeToken<RoomDetailBean>() {
				}.getType();
				RoomDetailBean roomDetailBean = new Gson().fromJson(jsonResult,
						type);
				DataSource.setRoomDetailBean(roomDetailBean);
			}
			MLog.e(TAG, jsonResult);
		} catch (JSONException e) {
			MLog.e(TAG, e);
		}
		super.parseJSON(json);
	}
}
