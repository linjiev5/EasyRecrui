package jp.easyrecrui.utils;

import jp.easyrecrui.entity.UserInfo;
import jp.easyrecrui.object.OUserInfo;

public class ObjToEntity {

	public static UserInfo toUserInfo(OUserInfo oUserInfo) {
		UserInfo userInfo = new UserInfo();
		userInfo.setUserName(oUserInfo.getUserName());
		userInfo.setName(oUserInfo.getName());
		userInfo.setMail(oUserInfo.getMail());
		userInfo.setTel(oUserInfo.getTel());
		userInfo.setSex(oUserInfo.getSex());
		userInfo.setAdress(oUserInfo.getAdress());
		userInfo.setNearestStation(oUserInfo.getNearestStation());
		userInfo.setBirthday(oUserInfo.getBirthday());
		userInfo.setUpdateTime(oUserInfo.getUpdateTime());
		return userInfo;


	}


}
