package com.xiaoyu.rentingdemo.util;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author shwan
 */
public class StringUtil {

	public static boolean isNull(String str) {
		if (str == null || str.isEmpty()) {
			return true;
		}

		if (str.equalsIgnoreCase("null")) {
			return true;
		}

		return false;
	}

	public static boolean notNull(String str) {
		return !isNull(str);
	}

	public static boolean isValidString(String searchString,
			String comparableString) {
		if (isNull(searchString))
			return true;

		if (searchString.equals("*"))
			return true;

		if (!isNull(comparableString)) {
			String lowerCaseStringSearch = searchString
					.toLowerCase(Locale.ENGLISH);
			String lowerCaseComparableString = comparableString
					.toLowerCase(Locale.ENGLISH);

			if (lowerCaseComparableString.startsWith(lowerCaseStringSearch))
				return true;

			String[] parts = lowerCaseComparableString.split(" ");
			for (String part : parts) {
				if (part.startsWith(lowerCaseStringSearch))
					return true;
			}

		}

		return false;
	}

	public static boolean isValidStringDoNotIgnoreCase(String searchString,
			String comparableString) {
		if (isNull(searchString))
			return true;

		if (searchString.equals("*"))
			return true;

		if (!isNull(comparableString)) {
			if (comparableString.startsWith(searchString))
				return true;

			String[] parts = comparableString.split(" ");
			for (String part : parts) {
				if (part.startsWith(searchString))
					return true;
			}

		}

		return false;
	}

	public static String nullToString(String str) {
		if (str == null || "".equals(str.trim())) {
			return "";
		}
		return str.trim();
	}

	/**
	 * @param src
	 * @param sp
	 * @param noEmpty
	 * @return
	 */
	public static String[] splitString(String src, String sp, boolean noEmpty) {
		if (src == null || src.trim().length() == 0)
			return null;
		String[] array = src.split(sp);
		if (array != null) {
			for (int i = 0; i < array.length; i++) {
				array[i] = array[i].trim();
			}
		}

		return array;
	}

	/**
	 * @param src
	 * @return
	 */
	public static String ascEncode(String src) {
		if (src == null || src.trim().length() == 0) {
			return null;
		} else {
			StringBuffer sb = new StringBuffer();
			char[] chars = src.toCharArray();
			for (int i = 0; i < chars.length; i++) {
				int j = chars[i];
				if (j > 31 && j < 127) {
					sb.append(chars[i]);
				} else {
					sb.append("&#" + j + ";");
				}
			}
			return sb.toString();
		}
	}

	public static String encodeString(String src, String enc) {
		try {
			return URLEncoder.encode(src, enc);
		} catch (Exception ex) {
			return src;
		}
	}

	public static String getRoomName(String str) {
		String temp = str.split("/")[0];
		if (StringUtil.notNull(temp)) {
			return temp;
		} else {
			return str;
		}
	}

	private static SimpleDateFormat chatTimeFormatDate = new SimpleDateFormat(
			"yyyy/MM/dd", Locale.ENGLISH);
	private static SimpleDateFormat chatTimeWithNoDate = new SimpleDateFormat(
			"hh:mm a", Locale.ENGLISH);
	private static SimpleDateFormat chatTimeWithYear = new SimpleDateFormat(
			"MM/dd/yy HH:mm:ss", Locale.ENGLISH);

	public static String formatTime(long chat_time) {
		if (chat_time == 0) {
			return "";
		}

		String result = "yyyy-MM-dd HH:mm:ss";
		try {
			if (chat_time > System.currentTimeMillis() * 1.5)
				return "";

			Date date = new Date(chat_time);

			result = chatTimeWithNoDate.format(date);
		} catch (Exception e) {
			result = "";
		}

		return result;
	}

	public static String formatDate(long chat_time) {
		Date date = new Date(chat_time);
		Date today = new Date();

		String incomingDay = chatTimeFormatDate.format(date);
		String currentDay = chatTimeFormatDate.format(today);
		if (incomingDay.equals(currentDay))
			return "Today";
		else
			return getFormatDate(chat_time);
	}

	private static String getFormatDate(long chat_time) {
		StringBuffer result = new StringBuffer();
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(chat_time);
		result.append((calendar.get(Calendar.MONTH) + 1));
		result.append("/");
		result.append(calendar.get(Calendar.DAY_OF_MONTH));
		result.append("/");
		result.append((calendar.get(Calendar.YEAR) - 2000));
		return result.toString();
	}

	/**
	 * Check whether the time in the same day two
	 * 
	 * @param chat_time1
	 * @param chat_time2
	 * @return
	 */
	public static boolean checkSameDay(long chat_time1, long chat_time2) {
		Date date1 = new Date(chat_time1);
		Date date2 = new Date(chat_time2);

		String incomingDay = chatTimeFormatDate.format(date1);
		String currentDay = chatTimeFormatDate.format(date2);
		return incomingDay.equals(currentDay);
	}

	public static String formatTimeFullString(String chat_time) {
		String result = "yyyy-MM-dd HH:mm:ss";
		try {
			Date date = new Date((long) Double.parseDouble(chat_time));

			result = chatTimeWithYear.format(date);
		} catch (Exception e) {
			result = "";
		}

		return result;
	}

	/**
	 * @param str
	 * @param n
	 * @param newChar
	 * @return String
	 * @throws Throwable
	 */
	public static String replace(String str, int n, String newChar)
			throws Throwable {
		String s1 = "";
		String s2 = "";
		try {
			s1 = str.substring(0, n - 1);
			s2 = str.substring(n, str.length());
		} catch (Exception ex) {
			throw (ex);
		}
		return s1 + newChar + s2;
	}
}
