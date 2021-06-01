package jp.easyrecrui.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @Description: 時間のフォーマット
 */
public class TimeDeal {
	/**
	 *
	 * @return “年-月-日”で表示する
	 */
	public static String getFormatDateForThree() {

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return now.format(format);
	}

	/**
	 * @return “年-月-日 时:分:秒”で表示する
	 */
	public static String getNow() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return now.format(format);
	}

	public static Timestamp nowTime() {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf3.format(now);
		return now;

	}

	/**
	 * @return “年-月-日 时:分”で表示する
	 */
	public static String getFormatDateForFive() {

		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return now.format(format);
	}

	/**
	 * “年-月-日”に転換する
	 * @param date 日期
	 * @return
	 */
	public static LocalDate getParseDateForThree(String date) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return LocalDate.parse(date, format);
	}

	/**
	 * 	“年-月-日 时:分:秒”に転換する
	 * @param date
	 * @return
	 */
	public static LocalDate getParseDateForSix(String date) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return LocalDate.parse(date, format);
	}

	/**
	 * 获得当前时间的时间戳
	 * @return
	 */
	public static long getLongTime() {
		Date now = new Date();
		return now.getTime() / 1000;
	}

	/**
	 * 时间中横杆转换为年
	 * @param str
	 * @return
	 */
	public static String timeWhippletreeToYear(String str) {
		StringBuilder s = new StringBuilder();
		s.append(str.substring(0, 4));
		s.append("年");
		s.append(str.substring(5, 7));
		s.append("月");
		return String.valueOf(s);
	}

	/**
	 * 时间中的年转换为横杠
	 * @param str
	 * @return
	 */
	public static String timeYearToWhippletree(String str) {
		StringBuilder s = new StringBuilder();
		s.append(str.substring(0, 4));
		s.append("-");
		s.append(str.substring(5, 7));
		return String.valueOf(s);
	}

	/**
	 * String To date
	 */
	public static Date stringToDateThree(String date) {
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return format1.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
