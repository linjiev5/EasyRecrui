package jp.easyrecrui.utils;

import jp.easyrecrui.entity.UserInfo;
import jp.easyrecrui.object.OUserInfo;

public class EntityToObj {

	public static OUserInfo toOUserInfo(UserInfo userInfo) {
		OUserInfo oUserInfo = new OUserInfo();
		oUserInfo.setUserName(userInfo.getUserName());
		oUserInfo.setName(userInfo.getName());
		oUserInfo.setMail(userInfo.getMail());
		oUserInfo.setTel(userInfo.getTel());
		oUserInfo.setSex(userInfo.getSex());
		oUserInfo.setAdress(userInfo.getAdress());
		oUserInfo.setNearestStation(userInfo.getNearestStation());
		oUserInfo.setBirthday(userInfo.getBirthday());
		oUserInfo.setUpdateTime(userInfo.getUpdateTime());
		return null;

	}
}
