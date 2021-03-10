package jp.easyrecrui.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Code {
	public static String md5Code(String pswd) {
		try {
			// メッセージダイジェストのインスタンスを生成
			MessageDigest md5 = MessageDigest.getInstance("MD5");

			byte[] result = md5.digest(pswd.getBytes());

			// 16進数に変換して桁を整える
			int[] i = new int[result.length];
			StringBuffer sb = new StringBuffer();
			for (int j = 0; j < result.length; j++) {
				i[j] = (int) result[j] & 0xff;
				if (i[j] <= 15) {
					sb.append("0");
				}
				sb.append(Integer.toHexString(i[j]));
			}
			pswd = sb.toString();
		} catch (NoSuchAlgorithmException x) {
		}
		return pswd;
	}
}
