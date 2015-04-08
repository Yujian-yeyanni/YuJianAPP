package com.xiaoyu.rentingdemo.data.bean;

/**
 * personal page bean
 * 
 * @author shwan
 * 
 */
public class PersonalInfoBean extends BaseData {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int typeId;
	private String typeString;

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getTypeString() {
		return typeString;
	}

	public void setTypeString(String typeString) {
		this.typeString = typeString;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
