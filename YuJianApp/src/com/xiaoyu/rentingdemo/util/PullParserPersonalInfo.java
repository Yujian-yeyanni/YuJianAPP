package com.xiaoyu.rentingdemo.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import com.xiaoyu.rentingdemo.data.bean.PersonalInfoBean;

public class PullParserPersonalInfo {

	private static final String TAG = PullParserPersonalInfo.class.getName();
	private static final String GRAPHICS = "graphics";
	private static final String PROJECT = "project";
	private static final String TYPESTRING = "typestring";
	private static final String TYPEID = "typeid";

	private List<PersonalInfoBean> personList = null;

	public List<PersonalInfoBean> getpersonalInfor(InputStream inputStream)
			throws XmlPullParserException {
		XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();
		XmlPullParser parser = parserFactory.newPullParser();

		PersonalInfoBean personInfor = null;
		parser.setInput(inputStream, "UTF-8");
		try {
			int event = parser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT) {
				String parserName = parser.getName();
				switch (event) {
				case XmlPullParser.START_DOCUMENT:
					break;
				case XmlPullParser.START_TAG:
					if (PROJECT.equals(parserName)) {
						personList = new ArrayList<PersonalInfoBean>();
					} else if (TYPEID.equals(parserName)) {
						personInfor = new PersonalInfoBean();
						personInfor.setTypeId(Integer.valueOf(parser.next()));
					} else if (TYPESTRING.equals(parserName)) {
						personInfor.setTypeString(parser.nextText());
						personList.add(personInfor);
					}
					break;
				case XmlPullParser.END_TAG:
					if (GRAPHICS.equals(parserName)) {

					}
					break;
				default:
					break;
				}
				event = parser.next();
			}
		} catch (IOException e) {
			MLog.e(TAG, e);
		}
		return personList;
	}
}
