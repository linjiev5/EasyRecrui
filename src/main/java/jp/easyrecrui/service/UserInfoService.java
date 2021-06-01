package jp.easyrecrui.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.easyrecrui.entity.CompanyInfo;
import jp.easyrecrui.entity.ResumeInfo;
import jp.easyrecrui.entity.UserInfo;
import jp.easyrecrui.entity.UserLogin;
import jp.easyrecrui.object.MyPageInfo;
import jp.easyrecrui.object.OUserInfo;
import jp.easyrecrui.repository.CompanyInfoRepository;
import jp.easyrecrui.repository.ResumeInfoRepository;
import jp.easyrecrui.repository.UserInfoRepository;
import jp.easyrecrui.repository.UserLoginRepository;
import jp.easyrecrui.utils.CodeType;
import jp.easyrecrui.utils.EntityToObj;
import jp.easyrecrui.utils.ObjToEntity;
import jp.easyrecrui.utils.TimeDeal;

@Service
@Transactional
public class UserInfoService {

	@Autowired
	UserInfoRepository userInfoRepository;
	@Autowired
	UserLoginRepository userLoginRepository;
	@Autowired
	ResumeInfoRepository resumeInfoRepository;
	@Autowired
	CompanyInfoRepository companyInfoRepository;

	/**
	 * 	ユーザ追加
	 * @param oLoginUser
	 * @return
	 */
	public int addUser(String userName, MyPageInfo oLoginInfo) {
		// ユーザが存在するかどうかを判断する
		UserLogin findByUserName = userLoginRepository.findByUserName(userName);
		if (findByUserName == null) {// 存在しない場合追加する
			UserLogin userLogin = new UserLogin();
			userLogin.setUserName(userName);
			userLogin.setPassword(oLoginInfo.getPassword());
			userLogin.setRocked(false);
			userLogin.setUserRole(oLoginInfo.getRole());
			Timestamp now = TimeDeal.nowTime();
			userLogin.setCreateTime(now);
			userLogin.setUpdateTime(now);
			userLoginRepository.saveAndFlush(userLogin);
			UserInfo userInfo = ObjToEntity.forRegist(oLoginInfo);
			userInfoRepository.saveAndFlush(userInfo);
			ResumeInfo resumeInfo = new ResumeInfo();
			resumeInfo.setUpdateTime(now);
			resumeInfoRepository.saveAndFlush(resumeInfo);
			CompanyInfo companyInfo = new CompanyInfo();
			companyInfo.setUpdateTime(now);
			companyInfoRepository.saveAndFlush(companyInfo);
			return CodeType.REGIST_SUCESS.getCode();
		} else {
			return CodeType.USER_EXIST.getCode();
		}
	}

	/**
	 * ユーザのデータを取得
	 * @param userName
	 * @return
	 */
	public OUserInfo getUserInfo(int uid) {
		UserInfo userInfo = userInfoRepository.findByUid(uid);
		OUserInfo oUserInfo = EntityToObj.toOUserInfo(userInfo);
		oUserInfo.setUserName(userLoginRepository.findByUid(uid).getUserName());
		return oUserInfo;
	}

	/**
	 *  UserNameを返却する
	 * @param uid
	 * @return
	 */
	public String getUserName(int uid) {
		return userLoginRepository.getOne(uid).getUserName();
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

	/**
	 * ユーザのデータを更新する
	 * @param oUserInfo
	 * @return
	 */
	public String updateUserInfo(OUserInfo oUserInfo) {
		UserInfo userInfo = userInfoRepository.findByUid(oUserInfo.getUid());
		int count = 0; // 変更したデータの数を確認する
		if (!oUserInfo.getName().equals(userInfo.getName())) {
			userInfo.setName(oUserInfo.getName());
			count++;
		}
		if (!oUserInfo.getMail().equals(userInfo.getMail())) {
			userInfo.setMail(oUserInfo.getMail());
			count++;
		}
		if (!oUserInfo.getSex().equals(userInfo.getSex())) {
			userInfo.setSex(oUserInfo.getSex());
			count++;
		}
		if (!oUserInfo.getTel().equals(userInfo.getTel())) {
			userInfo.setTel(oUserInfo.getTel());
			count++;
		}
		if (!oUserInfo.getAdress().equals(userInfo.getAdress())) {
			userInfo.setAdress(oUserInfo.getAdress());
			count++;
		}
		if (!oUserInfo.getBirthday().equals(userInfo.getBirthday())) {
			userInfo.setBirthday(oUserInfo.getBirthday());
			count++;
		}
		if (count == 0) {
			return "データの変更はありません";
		}
		Timestamp now = TimeDeal.nowTime();
		userInfo.setUpdateTime(now);
		userInfoRepository.saveAndFlush(userInfo);
		return count + "コア更新しました";
	}

	/**
	 * アイコンを保存する
	 * @param uid
	 * @param icon
	 */
	public void saveIcon(int uid, String icon) {
		UserInfo userInfo = userInfoRepository.findByUid(uid);
		icon = "../user/icon/" + icon;
		userInfo.setIcon(icon);
		Timestamp now = TimeDeal.nowTime();
		userInfo.setUpdateTime(now);
		userInfoRepository.saveAndFlush(userInfo);
	}

	/**
	 * icon
	 * @param uid
	 * @return
	 */
	public String getIcon(int uid) {
		return userInfoRepository.findByUid(uid).getIcon();
	}

}
