package jp.easyrecrui.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
/**
 * String をTimestampに変換する
 * @author user
 *
 */
public class TimeFormat {
	public static Timestamp getDate(String time) {
		Timestamp nowTime = null;
		try {
			nowTime = new Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").parse(time).getTime());
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return nowTime;
	}
}
