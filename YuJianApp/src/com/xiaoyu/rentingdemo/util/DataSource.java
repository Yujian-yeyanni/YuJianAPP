package com.xiaoyu.rentingdemo.util;

import com.xiaoyu.rentingdemo.data.bean.AllRoomListBean;
import com.xiaoyu.rentingdemo.data.bean.RoomDetailBean;

public class DataSource {

	// 所有客房
	private static AllRoomListBean allRoomListBean;

	// 图片服务器
	private static String image_server;

	// 客房详情
	private static RoomDetailBean roomDetailBean;

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

	public static RoomDetailBean getRoomDetailBean() {
		return roomDetailBean;
	}

	public static void setRoomDetailBean(RoomDetailBean roomDetailBean) {
		DataSource.roomDetailBean = roomDetailBean;
	}

}
