package com.wonhigh.i18n.ms.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;

/**
 * 日期工具类
 */
public class DateUtil {

	public static long getCurrentTimestampUTC() {
		DateTime dt = new DateTime();
		return dt.toLocalDateTime().toDate().getTime();
	}

	public static String getDateStr() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(new Date());
	}

	public static void main(String[] args) {
		System.out.println(getCurrentTimestampUTC());
		System.out.println(new Date());
		System.out.println(getDateStr());
	}

	/**
	 * 获取过去第几天的日期
	 * 
	 * @param past
	 * @return
	 */
	public static String getPastDate(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR)
				- past);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(today);
	}

}
