package com.xiaoyu.rentingdemo.data.bean;

import java.util.List;

/**
 * room detail
 * 
 * @author shwan
 * 
 */
public class RoomDetailBean extends BaseData {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String cityPinyin;
	private String langCity;
	private RoomBean roomBean;
	private List<RoomBean> hourseRoomList;
	private List<RoomBean> friendList;
	private String city;

	public String getCityPinyin() {
		return cityPinyin;
	}

	public void setCityPinyin(String cityPinyin) {
		this.cityPinyin = cityPinyin;
	}

	public String getLangCity() {
		return langCity;
	}

	public void setLangCity(String langCity) {
		this.langCity = langCity;
	}

	public RoomBean getRoomBean() {
		return roomBean;
	}

	public void setRoomBean(RoomBean roomBean) {
		this.roomBean = roomBean;
	}

	public List<RoomBean> getHourseRoomList() {
		return hourseRoomList;
	}

	public void setHourseRoomList(List<RoomBean> hourseRoomList) {
		this.hourseRoomList = hourseRoomList;
	}

	public List<RoomBean> getFriendList() {
		return friendList;
	}

	public void setFriendList(List<RoomBean> friendList) {
		this.friendList = friendList;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
