package com.xiaoyu.picturelistdemo.network;

import java.util.List;


import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.apache.http.cookie.Cookie;

import android.util.Log;

public class HttpRequestControler {

	private static final String TAG = "HttpRequestControler";
	private static final int HTTP_TIME_OUT = 1000 * 10;
	private static HttpRequestControler controler = null;
	private static FinalHttp finalHttp = null;

	private HttpRequestControler() {

	}

	public static HttpRequestControler getInstance() {
		if (finalHttp == null) {
			finalHttp = new FinalHttp();
			finalHttp.addHeader("Accept-Charset", "UTF-8");// 配置http请求头
			finalHttp.configCharset("UTF-8");
			finalHttp.configRequestExecutionRetryCount(3);// 请求错误重试次数
			finalHttp.configTimeout(HTTP_TIME_OUT);// 超时时间
		}
		if (controler == null) {
			controler = new HttpRequestControler();
		}
		return controler;
	}

	public void get(String url, AjaxParams params, final HttpHandler handler) {
		Log.e("test", "+++"+url+"/"+params);
		finalHttp.get(url, params, new AjaxCallBack<String>() {
			@Override
			public void onSuccess(String t) {
				super.onSuccess(t);
				handler.receive(t);
				processJson(t);
			}

			@Override
			public void onFailure(Throwable t, int errorNo, String strMsg) {
				super.onFailure(t, errorNo, strMsg);
				handler.NoticHttpFailure();
			}
		});
	}

	public void post(String url, AjaxParams params, final HttpHandler handler) {
		Log.e("test", "+++"+url+"/"+params);
		finalHttp.getHttpClient();
		finalHttp.post(url, params, new AjaxCallBack<String>() {

			@Override
			public void onSuccess(String t) {
				super.onSuccess(t);
				handler.receive(t);
				processJson(t);
			}

			@Override
			public void onFailure(Throwable t, int errorNo, String strMsg) {
				super.onFailure(t, errorNo, strMsg);
				handler.NoticHttpFailure();
			}
		});
	}

	private boolean processJson(String jsonStr) {
		return false;
	}
}
