package com.xiaoyu.picturelistdemo.data.action;

import org.json.JSONException;
import org.json.JSONObject;

import net.tsz.afinal.http.AjaxParams;
import android.content.Context;

import com.xiaoyu.picturelistdemo.network.HttpHandler;
import com.xiaoyu.picturelistdemo.network.HttpRequestControler;
import com.xiaoyu.picturelistdemo.network.NoticHandler;
import com.xiaoyu.picturelistdemo.util.Constants.HTTPRequest;

public class BaseAction implements HttpHandler {
	private static final String TAG = BaseAction.class.getName();

	private NoticHandler mNoticeHandler = null;
	private Context context;

	private static final String HOST_URL = "http://www.yujiangongyu.com/";
	
	protected static final String GET_CONFIG = "config/list.json";
	protected static final String KEY_IMAGE_SERVER = "image_server";

	public BaseAction(NoticHandler handler, Context context) {
		this.context = context;
		this.mNoticeHandler = handler;
	}

	/**
	 * 发送网络请求
	 * */
	public void sendHttpRequest(AjaxParams params, HTTPRequest httpRequest,
			String interfaceName, boolean showWaitDialog, String waitMsg) {
		String url = HOST_URL + interfaceName;
		if (httpRequest == HTTPRequest.GET) {
			HttpRequestControler.getInstance().get(url, params, this);
		} else if (httpRequest == HTTPRequest.POST) {
			HttpRequestControler.getInstance().post(url, params, this);
		}
	}

	@Override
	public void NoticHttpSuccess(String jsonStr) {
		mNoticeHandler.HttpSuccess(this);
	}

	@Override
	public void NoticHttpError(String warningMessage) {
		mNoticeHandler.HttpError("加载错误");
	}

	@Override
	public void receive(String json) {

		JSONObject jsonObj = null;
		try {
			jsonObj = new JSONObject(json);
			if (jsonObj != null) {
				parseJSON(json);
				mNoticeHandler.HttpSuccess(this);
			} else {
				NoticHttpError("加载错误");
			}
		} catch (JSONException e) {
			e.printStackTrace();
			NoticHttpFailure();
		}
	}

	@Override
	public void NoticHttpFailure() {
		mNoticeHandler.HttpFailure();
	}

	@Override
	public void updateDataBase() {

	}

	@Override
	public void parseJSON(String json) {

	}

}
