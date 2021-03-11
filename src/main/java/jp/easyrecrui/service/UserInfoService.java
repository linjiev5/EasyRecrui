package jp.easyrecrui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.easyrecrui.entity.UserInfo;
import jp.easyrecrui.object.OUserInfo;
import jp.easyrecrui.repository.UserInfoRepository;
import jp.easyrecrui.utils.CodeType;
import jp.easyrecrui.utils.EntityToObj;
import jp.easyrecrui.utils.ObjToEntity;

@Service
@Transactional
public class UserInfoService {

	@Autowired
	UserInfoRepository userInfoRepository;

	/**
	 * ユーザのデータを取得
	 * @param userName
	 * @return
	 */
	public OUserInfo getUserInfo(String userName) {
		UserInfo userInfo = userInfoRepository.findByUserName(userName);
		OUserInfo oUserInfo = EntityToObj.toOUserInfo(userInfo);
		return oUserInfo;
	}
	/**
	 * ユーザ情報を保存する
	 * @param oUserInfo
	 * @return
	 */
	public int addUserInfo(OUserInfo oUserInfo) {
		if (oUserInfo != null) {
			UserInfo userInfo = new UserInfo();
			userInfo = ObjToEntity.toUserInfo(oUserInfo);
			userInfoRepository.saveAndFlush(userInfo);
			return CodeType.REGIST_SUCESS.getCode();
		} else {
			return CodeType.ERROR.getCode();
		}
	}
}
