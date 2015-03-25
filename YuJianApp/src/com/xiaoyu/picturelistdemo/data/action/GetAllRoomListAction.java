package com.xiaoyu.picturelistdemo.data.action;

import java.lang.reflect.Type;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiaoyu.picturelistdemo.network.NoticHandler;
import com.xiaoyu.picturelistdemo.util.Constants.HTTPRequest;
import com.xiaoyu.picturelistdemo.util.DataSource;
import com.xiaoyu.picturelistdemo.util.MLog;
import com.xiaoyu.picturelistdemo.data.bean.*;

/**
 * 获取所有客房列表
 * 
 * @author shwan
 * 
 */
public class GetAllRoomListAction extends BaseAction {
	
	private static final String TAG = GetAllRoomListAction.class.getName();

	private Context context;

	public GetAllRoomListAction(NoticHandler handler, Context context) {
		super(handler, context);
		this.context = context;
	}
	
	public void getAllRoomList(String cityPinyin){
		sendHttpRequest(null, HTTPRequest.GET, cityPinyin+"/list.json", false, "哈哈");
	}
	
	@Override
	public void parseJSON(String json) {
		JSONObject jsonObject = null;
		String jsonResult = null;
		try {
			jsonObject = new JSONObject(json);
			
			if (jsonObject != null) {
				jsonResult = jsonObject.toString();
				Type type = new TypeToken<AllRoomListBean>() {
					}.getType();
				AllRoomListBean allRoomListBean = new Gson().fromJson(jsonResult, type);
				DataSource.setAllRoomListBean(allRoomListBean);
			}
			MLog.e(TAG, jsonResult);
		} catch (JSONException e) {
			MLog.e(TAG, e);
		}
		super.parseJSON(json);
	}

}
