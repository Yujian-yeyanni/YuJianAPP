package com.xiaoyu.rentingdemo.util;

import java.util.ArrayList;
import java.util.List;

import android.widget.TextView;

/**
 * 工具类
 * @author shwan
 * 
 */
public class Utils {

	private static final List<String> imageUrl = new ArrayList<String>();
	/**
	 * 获取客房结构（室/厅/卫）
	 * 
	 * @param strDate
	 * @param getHouseType
	 * @return
	 */
	public static String getHouseShape(String houseShape, int getHouseType) {
		if (StringUtil.isNull(houseShape))
			return null;
		String[] shapeStr = houseShape.split("\\/");
		String room = shapeStr[0];
		String livingRoom = shapeStr[1];
		String Toilet = shapeStr[2];
		switch (getHouseType) {
		case Constants.HOUSE_ROOM:
			return room;
		case Constants.HOUSE_LIVING_ROOM:
			return livingRoom;
		case Constants.HOUSE_TOILET:
			return Toilet;
		default:
			return "";
		}
	}
	/**
	 * test used
	 * @return
	 */
	public static List<String> getImageurl() {
		imageUrl.clear();
		imageUrl.add("http://f.hiphotos.baidu.com/image/pic/item/30adcbef76094b36f981334ca0cc7cd98d109d7b.jpg");
		imageUrl.add("http://d.hiphotos.baidu.com/image/pic/item/3b87e950352ac65cfb982072f9f2b21193138a16.jpg");
		imageUrl.add("http://f.hiphotos.baidu.com/image/w%3D230/sign=9781b4996863f6241c5d3e00b745eb32/b3b7d0a20cf431adae717c994836acaf2fdd98ea.jpg");
		imageUrl.add("http://a.hiphotos.baidu.com/image/pic/item/38dbb6fd5266d016d984a188942bd40734fa35f4.jpg");
		return imageUrl;
	}
}