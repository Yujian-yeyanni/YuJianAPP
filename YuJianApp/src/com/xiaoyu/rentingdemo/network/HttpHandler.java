
/**
 * http请求回调的接口
 * @author wangmaoxin
 * */

package com.xiaoyu.rentingdemo.network;

import java.util.List;



public interface HttpHandler {

	/**
	 * 请求成功
	 * */
	public abstract void NoticHttpSuccess(String jsonStr);
	
	/**
	 * 请求失败
	 * */
	public abstract void NoticHttpError(String warningMessage);
	
	public void receive(String json);
	
	/**
	 * 网络连接失败
	 * */
	public abstract void NoticHttpFailure();
	
	/**
	 * 更新数据库
	 * */
	public abstract void updateDataBase();
	
	/**
	 * 获取数据库数据
	 * */
//	public abstract List<BaseData> getDataBase();
	
	
	/**
	 * 解析json数据
	 * */
	public abstract void parseJSON(String json);
	

}
