package com.xiaoyu.rentingdemo.data.action;

import org.json.JSONException;
import org.json.JSONObject;

import net.tsz.afinal.http.AjaxParams;
import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.xiaoyu.rentingdemo.BaseActivity;
import com.xiaoyu.rentingdemo.R;
import com.xiaoyu.rentingdemo.fragment.BaseFragment;
import com.xiaoyu.rentingdemo.network.HttpHandler;
import com.xiaoyu.rentingdemo.network.HttpRequestControler;
import com.xiaoyu.rentingdemo.network.NoticHandler;
import com.xiaoyu.rentingdemo.util.StringUtil;
import com.xiaoyu.rentingdemo.util.Constants.HTTPRequest;

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
			String interfaceName, boolean showWaitDialog, int stringId) {
		String url = HOST_URL + interfaceName;
		if (httpRequest == HTTPRequest.GET) {
			HttpRequestControler.getInstance().get(url, params, this);
		} else if (httpRequest == HTTPRequest.POST) {
			HttpRequestControler.getInstance().post(url, params, this);
		}

		if (showWaitDialog) {
			showWaitDialog(stringId);
		}
	}

	/**
	 * 弹出加载
	 * 
	 * @param stringId
	 */
	private void showWaitDialog(int stringId) {
		BaseActivity activity = getActivity();

		if (activity == null)
			return;

		if (!(mNoticeHandler instanceof BaseFragment)) {
			return;
		}
		activity.showWait(stringId);

		BaseActivity baseActivity = (BaseActivity) ((BaseFragment) mNoticeHandler)
				.getActivity();
		if (baseActivity == null) {
			return;
		}

		baseActivity.showWait(stringId);
	}

	/**
	 * 消除加载弹框
	 */
	private void dismissDialog() {
		if (mNoticeHandler == null) {
			return;
		}
		BaseActivity activity;
		if (mNoticeHandler instanceof Activity) {
			activity = ((BaseActivity) mNoticeHandler);
		} else {
			activity = getActivity();
		}

		if (activity != null)
			activity.dismissDialog();
	}

	private BaseActivity getActivity() {
		if (mNoticeHandler == null) {
			return null;
		}

		if (mNoticeHandler instanceof Fragment) {
			return (BaseActivity) ((BaseFragment) mNoticeHandler).getActivity();
		}

		if (mNoticeHandler instanceof BaseActivity) {
			return (BaseActivity) mNoticeHandler;
		}

		return null;
	}

	@Override
	public void NoticHttpSuccess(String jsonStr) {
		mNoticeHandler.HttpSuccess(this);
		dismissDialog();
	}

	@Override
	public void NoticHttpError(String warningMessage) {
		mNoticeHandler.HttpError(warningMessage);
	}

	@SuppressWarnings("unused")
	@Override
	public void receive(String json) {
		if (StringUtil.isNull(json)) {
			// NoticHttpError(context.getText(R.st));
			dismissDialog();
		}
		JSONObject jsonObj = null;
		try {
			jsonObj = new JSONObject(json);
			if (jsonObj != null) {
				parseJSON(json);
				mNoticeHandler.HttpSuccess(this);
			} else {
				NoticHttpError(context.getText(R.string.str_loading_error_message).toString());
			}
		} catch (JSONException e) {
			e.printStackTrace();
			NoticHttpFailure();
		} finally {
			dismissDialog();
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
