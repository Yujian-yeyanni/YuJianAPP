/**
 * 通知每个活动网络请求的回调
 * @author wangmaoxin
 * */
package com.xiaoyu.rentingdemo.network;

public interface NoticHandler {

	/**
	 * 通知请求页面，请求成功
	 * */
	public abstract void HttpSuccess(HttpHandler handler);
	
	/**
	 * 通知请求页面，请求失败
	 * */
	public abstract void HttpError(String warningMessage);
	
	/**
	 * 通知请求页面，网络连接失败
	 * */
	public abstract void HttpFailure();
}
