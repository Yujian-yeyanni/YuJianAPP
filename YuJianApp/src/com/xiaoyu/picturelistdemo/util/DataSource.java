package com.xiaoyu.picturelistdemo.util;

import com.xiaoyu.picturelistdemo.data.bean.AllRoomListBean;

public class DataSource {

	//所有客房
	private static AllRoomListBean allRoomListBean;
	
	//图片服务器
	private static String image_server;

	public static AllRoomListBean getAllRoomListBean() {
		return allRoomListBean;
	}

	public static void setAllRoomListBean(AllRoomListBean allRoomListBean) {
		DataSource.allRoomListBean = allRoomListBean;
	}

	public static String getImage_server() {
		return image_server;
	}

	public static void setImage_server(String image_server) {
		DataSource.image_server = image_server;
	}

}
