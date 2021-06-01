package jp.easyrecrui.utils;

import java.sql.Timestamp;

import jp.easyrecrui.entity.UserInfo;
import jp.easyrecrui.object.MyPageInfo;
import jp.easyrecrui.object.OUserInfo;

public class ObjToEntity {
	/**
	 * ユーザ情報Ｏｂｊをentityに移る
	 * @param oUserInfo
	 * @return
	 */
	public static UserInfo toUserInfo(OUserInfo oUserInfo) {
		UserInfo userInfo = new UserInfo();
		userInfo.setUid(oUserInfo.getUid());
		userInfo.setName(oUserInfo.getName());
		userInfo.setIcon(oUserInfo.getIcon());
		userInfo.setMail(oUserInfo.getMail());
		userInfo.setTel(oUserInfo.getTel());
		userInfo.setSex(oUserInfo.getSex());
		userInfo.setAdress(oUserInfo.getAdress());
		userInfo.setBirthday(oUserInfo.getBirthday());
		Timestamp now = new Timestamp(System.currentTimeMillis());
		userInfo.setUpdateTime(now);
		return userInfo;
	}

	/**
	 * 登録用情報保存
	 * @param myPageInfo
	 * @return
	 */
	public static UserInfo forRegist(MyPageInfo myPageInfo) {
		UserInfo userInfo = new UserInfo();
		userInfo.setName(myPageInfo.getName());
		userInfo.setIcon(myPageInfo.getIcon());
		userInfo.setMail(myPageInfo.getMail());
		userInfo.setTel(myPageInfo.getTel());
		userInfo.setSex(myPageInfo.getSex());
		userInfo.setAdress(myPageInfo.getAdress());
		userInfo.setBirthday(myPageInfo.getBirthday());
		Timestamp now = TimeDeal.nowTime();
		userInfo.setUpdateTime(now);
		return userInfo;
	}

}
