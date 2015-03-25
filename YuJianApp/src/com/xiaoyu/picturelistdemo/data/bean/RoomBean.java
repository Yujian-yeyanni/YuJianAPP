package com.xiaoyu.picturelistdemo.data.bean;

import java.util.List;

/**
 * 房源json数据
 * 
 * @author shwan
 * 
 */
public class RoomBean extends BaseData {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int hourseId;
	private String houseNo;
	private String roomNo;
	private String roomName;
	private String roomTitle;
	private String roomSubtitle;
	private String doorCard;
	private String towards;
	private String roomArea;
	private int liveNumber;
	private String paymentType;
	private int depositPrice;
	private int paymentCycle;
	private int minPrice;
	private int guidePrice;
	private String parkingAmount;
	private String parkingCycle;
	private String parkingNo;
	private String networkAmount;
	private String networkCycle;
	private String networdType;
	private String cableAmount;
	private String cableCycle;
	private String publicGoods;
	private String privateGoods;
	private String remark;
	private String intro;
	private String roomPicture;
	private List<String> roomPictureArray;
	private long createTime;
	private long updateTime;
	private int status;
	private String h_storeId;
	private String h_villageName;
	private String h_proCert;
	private String h_province;
	private String h_city;
	private String h_district;
	private String h_address;
	private String h_street;
	private String h_hourseArea;
	private String h_floor;
	private String h_hourseShape;
	private String h_decorateInfo;
	private String h_hourseAttr;
	private int h_liveNumber;
	private String h_goods;
	private String h_doorCard;
	private String h_traffic;
	private String h_shopping;
	private String h_leisure;
	private String h_entertainment;
	private String h_fuzzyAddress;
	private String h_towards;
	private String h_paymentType;
	// TODO CANFIRM THE TYPE OF PARME
	private String h_paymentCycle;
	private int h_rentalPrice;
	private int h_depositPrice;
	private String h_estateAddress;
	private String h_constactsName;
	private String h_constactsPhone;
	private String h_repairePhone;
	private String h_intro;
	private String h_remark;
	private String o_ownerName;
	private String o_moblie;
	private long oc_beginDate;
	private long oc_endDate;
	private long oc_realEndDate;
	private int oc_status;
	private String s_storeName;
	private String lng;
	private String lat;
	private String marsLng;
	private String marsLat;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHourseId() {
		return hourseId;
	}

	public void setHourseId(int hourseId) {
		this.hourseId = hourseId;
	}

	public String getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(String houseNo) {
		this.houseNo = houseNo;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomTitle() {
		return roomTitle;
	}

	public void setRoomTitle(String roomTitle) {
		this.roomTitle = roomTitle;
	}

	public String getRoomSubtitle() {
		return roomSubtitle;
	}

	public void setRoomSubtitle(String roomSubtitle) {
		this.roomSubtitle = roomSubtitle;
	}

	public String getDoorCard() {
		return doorCard;
	}

	public void setDoorCard(String doorCard) {
		this.doorCard = doorCard;
	}

	public String getTowards() {
		return towards;
	}

	public void setTowards(String towards) {
		this.towards = towards;
	}

	public int getLiveNumber() {
		return liveNumber;
	}

	public void setLiveNumber(int liveNumber) {
		this.liveNumber = liveNumber;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public int getDepositPrice() {
		return depositPrice;
	}

	public void setDepositPrice(int depositPrice) {
		this.depositPrice = depositPrice;
	}

	public int getPaymentCycle() {
		return paymentCycle;
	}

	public void setPaymentCycle(int paymentCycle) {
		this.paymentCycle = paymentCycle;
	}

	public int getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}

	public int getGuidePrice() {
		return guidePrice;
	}

	public void setGuidePrice(int guidePrice) {
		this.guidePrice = guidePrice;
	}

	public String getParkingAmount() {
		return parkingAmount;
	}

	public void setParkingAmount(String parkingAmount) {
		this.parkingAmount = parkingAmount;
	}

	public String getParkingCycle() {
		return parkingCycle;
	}

	public void setParkingCycle(String parkingCycle) {
		this.parkingCycle = parkingCycle;
	}

	public String getParkingNo() {
		return parkingNo;
	}

	public void setParkingNo(String parkingNo) {
		this.parkingNo = parkingNo;
	}

	public String getNetworkAmount() {
		return networkAmount;
	}

	public void setNetworkAmount(String networkAmount) {
		this.networkAmount = networkAmount;
	}

	public String getNetworkCycle() {
		return networkCycle;
	}

	public void setNetworkCycle(String networkCycle) {
		this.networkCycle = networkCycle;
	}

	public String getNetwordType() {
		return networdType;
	}

	public void setNetwordType(String networdType) {
		this.networdType = networdType;
	}

	public String getCableAmount() {
		return cableAmount;
	}

	public void setCableAmount(String cableAmount) {
		this.cableAmount = cableAmount;
	}

	public String getCableCycle() {
		return cableCycle;
	}

	public void setCableCycle(String cableCycle) {
		this.cableCycle = cableCycle;
	}

	public String getPublicGoods() {
		return publicGoods;
	}

	public void setPublicGoods(String publicGoods) {
		this.publicGoods = publicGoods;
	}

	public String getPrivateGoods() {
		return privateGoods;
	}

	public void setPrivateGoods(String privateGoods) {
		this.privateGoods = privateGoods;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getRoomPicture() {
		return roomPicture;
	}

	public void setRoomPicture(String roomPicture) {
		this.roomPicture = roomPicture;
	}

	public List<String> getRoomPictureArray() {
		return roomPictureArray;
	}

	public void setRoomPictureArray(List<String> roomPictureArray) {
		this.roomPictureArray = roomPictureArray;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getH_storeId() {
		return h_storeId;
	}

	public void setH_storeId(String h_storeId) {
		this.h_storeId = h_storeId;
	}

	public String getH_villageName() {
		return h_villageName;
	}

	public void setH_villageName(String h_villageName) {
		this.h_villageName = h_villageName;
	}

	public String getH_proCert() {
		return h_proCert;
	}

	public void setH_proCert(String h_proCert) {
		this.h_proCert = h_proCert;
	}

	public String getH_province() {
		return h_province;
	}

	public void setH_province(String h_province) {
		this.h_province = h_province;
	}

	public String getH_city() {
		return h_city;
	}

	public void setH_city(String h_city) {
		this.h_city = h_city;
	}

	public String getH_district() {
		return h_district;
	}

	public void setH_district(String h_district) {
		this.h_district = h_district;
	}

	public String getH_address() {
		return h_address;
	}

	public void setH_address(String h_address) {
		this.h_address = h_address;
	}

	public String getH_street() {
		return h_street;
	}

	public void setH_street(String h_street) {
		this.h_street = h_street;
	}


	public String getH_floor() {
		return h_floor;
	}

	public void setH_floor(String h_floor) {
		this.h_floor = h_floor;
	}

	public String getH_hourseShape() {
		return h_hourseShape;
	}

	public void setH_hourseShape(String h_hourseShape) {
		this.h_hourseShape = h_hourseShape;
	}

	public String getH_decorateInfo() {
		return h_decorateInfo;
	}

	public void setH_decorateInfo(String h_decorateInfo) {
		this.h_decorateInfo = h_decorateInfo;
	}

	public String getH_hourseAttr() {
		return h_hourseAttr;
	}

	public void setH_hourseAttr(String h_hourseAttr) {
		this.h_hourseAttr = h_hourseAttr;
	}

	public int getH_liveNumber() {
		return h_liveNumber;
	}

	public void setH_liveNumber(int h_liveNumber) {
		this.h_liveNumber = h_liveNumber;
	}

	public String getH_goods() {
		return h_goods;
	}

	public void setH_goods(String h_goods) {
		this.h_goods = h_goods;
	}

	public String getH_doorCard() {
		return h_doorCard;
	}

	public void setH_doorCard(String h_doorCard) {
		this.h_doorCard = h_doorCard;
	}

	public String getH_traffic() {
		return h_traffic;
	}

	public void setH_traffic(String h_traffic) {
		this.h_traffic = h_traffic;
	}

	public String getH_shopping() {
		return h_shopping;
	}

	public void setH_shopping(String h_shopping) {
		this.h_shopping = h_shopping;
	}

	public String getH_leisure() {
		return h_leisure;
	}

	public void setH_leisure(String h_leisure) {
		this.h_leisure = h_leisure;
	}

	public String getH_entertainment() {
		return h_entertainment;
	}

	public void setH_entertainment(String h_entertainment) {
		this.h_entertainment = h_entertainment;
	}

	public String getH_fuzzyAddress() {
		return h_fuzzyAddress;
	}

	public void setH_fuzzyAddress(String h_fuzzyAddress) {
		this.h_fuzzyAddress = h_fuzzyAddress;
	}

	public String getH_towards() {
		return h_towards;
	}

	public void setH_towards(String h_towards) {
		this.h_towards = h_towards;
	}

	public String getH_paymentType() {
		return h_paymentType;
	}

	public void setH_paymentType(String h_paymentType) {
		this.h_paymentType = h_paymentType;
	}

	public String getH_paymentCycle() {
		return h_paymentCycle;
	}

	public void setH_paymentCycle(String h_paymentCycle) {
		this.h_paymentCycle = h_paymentCycle;
	}

	public int getH_rentalPrice() {
		return h_rentalPrice;
	}

	public void setH_rentalPrice(int h_rentalPrice) {
		this.h_rentalPrice = h_rentalPrice;
	}

	public int getH_depositPrice() {
		return h_depositPrice;
	}

	public void setH_depositPrice(int h_depositPrice) {
		this.h_depositPrice = h_depositPrice;
	}

	public String getH_estateAddress() {
		return h_estateAddress;
	}

	public void setH_estateAddress(String h_estateAddress) {
		this.h_estateAddress = h_estateAddress;
	}

	public String getH_constactsName() {
		return h_constactsName;
	}

	public void setH_constactsName(String h_constactsName) {
		this.h_constactsName = h_constactsName;
	}

	public String getH_constactsPhone() {
		return h_constactsPhone;
	}

	public void setH_constactsPhone(String h_constactsPhone) {
		this.h_constactsPhone = h_constactsPhone;
	}

	public String getH_repairePhone() {
		return h_repairePhone;
	}

	public void setH_repairePhone(String h_repairePhone) {
		this.h_repairePhone = h_repairePhone;
	}

	public String getH_intro() {
		return h_intro;
	}

	public void setH_intro(String h_intro) {
		this.h_intro = h_intro;
	}

	public String getH_remark() {
		return h_remark;
	}

	public void setH_remark(String h_remark) {
		this.h_remark = h_remark;
	}

	public String getO_ownerName() {
		return o_ownerName;
	}

	public void setO_ownerName(String o_ownerName) {
		this.o_ownerName = o_ownerName;
	}

	public String getO_moblie() {
		return o_moblie;
	}

	public void setO_moblie(String o_moblie) {
		this.o_moblie = o_moblie;
	}

	public long getOc_beginDate() {
		return oc_beginDate;
	}

	public void setOc_beginDate(long oc_beginDate) {
		this.oc_beginDate = oc_beginDate;
	}

	public long getOc_endDate() {
		return oc_endDate;
	}

	public void setOc_endDate(long oc_endDate) {
		this.oc_endDate = oc_endDate;
	}

	public long getOc_realEndDate() {
		return oc_realEndDate;
	}

	public void setOc_realEndDate(long oc_realEndDate) {
		this.oc_realEndDate = oc_realEndDate;
	}

	public int getOc_status() {
		return oc_status;
	}

	public void setOc_status(int oc_status) {
		this.oc_status = oc_status;
	}

	public String getS_storeName() {
		return s_storeName;
	}

	public void setS_storeName(String s_storeName) {
		this.s_storeName = s_storeName;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getRoomArea() {
		return roomArea;
	}

	public void setRoomArea(String roomArea) {
		this.roomArea = roomArea;
	}

	public String getH_hourseArea() {
		return h_hourseArea;
	}

	public void setH_hourseArea(String h_hourseArea) {
		this.h_hourseArea = h_hourseArea;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getMarsLng() {
		return marsLng;
	}

	public void setMarsLng(String marsLng) {
		this.marsLng = marsLng;
	}

	public String getMarsLat() {
		return marsLat;
	}

	public void setMarsLat(String marsLat) {
		this.marsLat = marsLat;
	}

	
	
}
