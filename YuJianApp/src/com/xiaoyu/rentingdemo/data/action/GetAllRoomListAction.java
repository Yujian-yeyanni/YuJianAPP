package com.xiaoyu.rentingdemo.data.action;

import java.lang.reflect.Type;

import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiaoyu.rentingdemo.R;
import com.xiaoyu.rentingdemo.data.bean.*;
import com.xiaoyu.rentingdemo.network.NoticHandler;
import com.xiaoyu.rentingdemo.util.DataSource;
import com.xiaoyu.rentingdemo.util.MLog;
import com.xiaoyu.rentingdemo.util.Constants.HTTPRequest;

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

	public void getAllRoomList(String cityPinyin, int pageIndex,boolean isShowDialog) {
		AjaxParams params = new AjaxParams();
		params.put(KEY_PAGE_INDEX, String.valueOf(pageIndex));
		sendHttpRequest(params, HTTPRequest.GET, cityPinyin + "/list.json",
				isShowDialog, R.string.str_loading_message);
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
				AllRoomListBean allRoomListBean = new Gson().fromJson(
						jsonResult, type);
				DataSource.setAllRoomListBean(allRoomListBean);
			}
			MLog.e(TAG, jsonResult);
		} catch (JSONException e) {
			MLog.e(TAG, e);
		}
		super.parseJSON(json);
	}

}
