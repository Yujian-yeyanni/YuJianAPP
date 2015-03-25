package com.xiaoyu.picturelistdemo.data.bean;

import java.util.List;

/**
 * 所有客房的json数据
 * 
 * @author shwan
 * 
 */
public class AllRoomListBean extends BaseData {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<String> districtList;
	private List<RoomBean> roomList;
	private String street;
	private String cityPinyin;
	private String langCity;
	private int maxPrice;
	private String keyWord;
	private String city;
	private List<RoomBean> tuijianList;
	private String yangtai;
	private PageBean pageBean;
	private String piaochuang;
	private String duwei;
	private List<RoomBean> friendList;
	private String district;
	private String keyWordOld;
	private int qujian;
	private int minPrice;

	public List<String> getDistrictList() {
		return districtList;
	}

	public void setDistrictList(List<String> districtList) {
		this.districtList = districtList;
	}

	public List<RoomBean> getRoomList() {
		return roomList;
	}

	public void setRoomList(List<RoomBean> roomList) {
		this.roomList = roomList;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

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

	public int getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<RoomBean> getTuijianList() {
		return tuijianList;
	}

	public void setTuijianList(List<RoomBean> tuijianList) {
		this.tuijianList = tuijianList;
	}

	public String getYangtai() {
		return yangtai;
	}

	public void setYangtai(String yangtai) {
		this.yangtai = yangtai;
	}

	public PageBean getPageBean() {
		return pageBean;
	}

	public void setPageBean(PageBean pageBean) {
		this.pageBean = pageBean;
	}

	public String getPiaochuang() {
		return piaochuang;
	}

	public void setPiaochuang(String piaochuang) {
		this.piaochuang = piaochuang;
	}

	public String getDuwei() {
		return duwei;
	}

	public void setDuwei(String duwei) {
		this.duwei = duwei;
	}

	public List<RoomBean> getFriendList() {
		return friendList;
	}

	public void setFriendList(List<RoomBean> friendList) {
		this.friendList = friendList;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getKeyWordOld() {
		return keyWordOld;
	}

	public void setKeyWordOld(String keyWordOld) {
		this.keyWordOld = keyWordOld;
	}

	public int getQujian() {
		return qujian;
	}

	public void setQujian(int qujian) {
		this.qujian = qujian;
	}

	public int getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
