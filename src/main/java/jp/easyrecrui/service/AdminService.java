package jp.easyrecrui.service;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.easyrecrui.entity.CompanyInfo;
import jp.easyrecrui.entity.UserInfo;
import jp.easyrecrui.entity.UserLogin;
import jp.easyrecrui.object.OCompanyInfo;
import jp.easyrecrui.object.OUserInfo;
import jp.easyrecrui.repository.CompanyInfoRepository;
import jp.easyrecrui.repository.UserInfoRepository;
import jp.easyrecrui.repository.UserLoginRepository;
import jp.easyrecrui.utils.EntityToObj;
import jp.easyrecrui.utils.TimeDeal;

@Service
public class AdminService {

	@Autowired
	UserInfoRepository userInfoRepository;
	@Autowired
	UserLoginRepository userLoginRepository;
	@Autowired
	CompanyInfoRepository companyInfoRepository;

	/**
	 * すべてのユーザ情報
	 * @return
	 */
	public ArrayList<OUserInfo> getUserInfo() {
		ArrayList<UserInfo> userInfos = userInfoRepository.getUserInfo();
		ArrayList<OUserInfo> oUserInfos = new ArrayList<OUserInfo>();
		if (userInfos.size() >= 1) {
			for (int a = 0, b = userInfos.size(); a < b; a++) {
				OUserInfo oUserInfo = EntityToObj.toOUserInfo(userInfos.get(a));
				UserLogin userLogin = userLoginRepository.findById(oUserInfo.getUid()).get();
				oUserInfo.setUserName(userLogin.getUserName());
				oUserInfo.setRole(userLogin.getUserRole());
				oUserInfo.setRocked(userLogin.isRocked());
				oUserInfos.add(oUserInfo);
			}
		}
		return oUserInfos;
	}

	/**
	 * ユーザのデータを取得
	 * @param userName
	 * @return
	 */
	public OUserInfo getUserInfoForAdmin(int uid) {
		UserInfo userInfo = userInfoRepository.findByUid(uid);
		OUserInfo oUserInfo = EntityToObj.toOUserInfo(userInfo);
		UserLogin userLogin = userLoginRepository.findById(oUserInfo.getUid()).get();
		oUserInfo.setUserName(userLogin.getUserName());
		oUserInfo.setRole(userLogin.getUserRole());
		oUserInfo.setRocked(userLogin.isRocked());
		return oUserInfo;
	}

	/**
	 *
	 * @param userInfo
	 * @return
	 */
	public String saveUserInfo(OUserInfo oUserInfo) {
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
		UserLogin userLogin = userLoginRepository.findById(oUserInfo.getUid()).get();
		if (!oUserInfo.getUserName().equals(userLogin.getUserName())) {
			userLogin.setUserName(oUserInfo.getUserName());
			count++;
		}
		if (!oUserInfo.getRole().equals(userLogin.getUserRole())) {
			userLogin.setUserRole(oUserInfo.getRole());
			count++;
		}
		if (oUserInfo.isRocked()) {
			userLogin.setRocked(true);
			Timestamp now = TimeDeal.nowTime();
			userLogin.setUpdateTime(now);
			userLoginRepository.saveAndFlush(userLogin);
			userInfo.setUpdateTime(now);
			userInfoRepository.saveAndFlush(userInfo);
			return "アカウントを制限しました";
		} else {
			if (oUserInfo.isRocked() != userLogin.isRocked()) {

				userLogin.setRocked(false);
				count++;
			}
		}
		if (count == 0) {
			return "データの変更はありません";
		}
		Timestamp now = TimeDeal.nowTime();
		userLogin.setUpdateTime(now);
		userLoginRepository.saveAndFlush(userLogin);
		userInfo.setUpdateTime(now);
		userInfoRepository.saveAndFlush(userInfo);
		return count + "コア更新しました";
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	public OCompanyInfo getCompanyInfoForAdmin(int id) {
		OCompanyInfo oCompanyInfo = new OCompanyInfo();
		CompanyInfo companyInfo = companyInfoRepository.findByUid(id);
		oCompanyInfo.setUid(companyInfo.getUid());
		oCompanyInfo.setCompanyName(companyInfo.getCompanyName());
		oCompanyInfo.setMail(companyInfo.getMail());
		oCompanyInfo.setTel(companyInfo.getTel());
		oCompanyInfo.setInfo(companyInfo.getInfo());
		oCompanyInfo.setFoundedTime(companyInfo.getFoundedTime());
		oCompanyInfo.setIcon(companyInfo.getIcon());
		oCompanyInfo.setAdress(companyInfo.getAdress());
		oCompanyInfo.setUpdateTime(companyInfo.getUpdateTime());
		UserLogin userLogin = userLoginRepository.findByUid(id);
		oCompanyInfo.setRole(userLogin.getUserRole());
		oCompanyInfo.setUserName(userLogin.getUserName());
		oCompanyInfo.setRocked(userLogin.isRocked());
		return oCompanyInfo;
	}

	/**
	 *
	 * @return
	 */
	public ArrayList<OCompanyInfo> getCompanyInfo() {
		ArrayList<CompanyInfo> companyInfos = companyInfoRepository.getCompanyInfo();
		ArrayList<OCompanyInfo> oCompanyInfos = new ArrayList<OCompanyInfo>();
		if (companyInfos.size() >= 1) {
			for (int a = 0, b = companyInfos.size(); a < b; a++) {
				OCompanyInfo oCompanyInfo = EntityToObj.toOCompanyInfo(companyInfos.get(a));
				UserLogin userLogin = userLoginRepository.findById(oCompanyInfo.getUid()).get();
				oCompanyInfo.setUserName(userLogin.getUserName());
				oCompanyInfo.setRole(userLogin.getUserRole());
				oCompanyInfo.setRocked(userLogin.isRocked());
				oCompanyInfos.add(oCompanyInfo);
			}
		}
		return oCompanyInfos;
	}

	/**
	 * 会社情報編集する
	 * @param oCompanyInfo
	 * @return
	 */
	public String saveCompanyInfo(OCompanyInfo oCompanyInfo) {
		CompanyInfo companyInfo = companyInfoRepository.findByUid(oCompanyInfo.getUid());
		int count = 0; // 変更したデータの数を確認する
		if (!oCompanyInfo.getCompanyName().equals(companyInfo.getCompanyName())) {
			companyInfo.setCompanyName(oCompanyInfo.getCompanyName());
			count++;
		}
		if (!oCompanyInfo.getMail().equals(companyInfo.getMail())) {
			companyInfo.setMail(oCompanyInfo.getMail());
			count++;
		}

		if (!oCompanyInfo.getTel().equals(companyInfo.getTel())) {
			companyInfo.setTel(oCompanyInfo.getTel());
			count++;
		}
		if (!oCompanyInfo.getAdress().equals(companyInfo.getAdress())) {
			companyInfo.setAdress(oCompanyInfo.getAdress());
			count++;
		}
		if (!oCompanyInfo.getFoundedTime().equals(companyInfo.getFoundedTime())) {
			companyInfo.setFoundedTime(oCompanyInfo.getFoundedTime());
			count++;
		}
		UserLogin userLogin = userLoginRepository.findById(oCompanyInfo.getUid()).get();
		if (!oCompanyInfo.getUserName().equals(userLogin.getUserName())) {
			userLogin.setUserName(oCompanyInfo.getUserName());
			count++;
		}
		if (!oCompanyInfo.getRole().equals(userLogin.getUserRole())) {
			userLogin.setUserRole(oCompanyInfo.getRole());
			count++;
		}
		if (oCompanyInfo.isRocked()) {
			userLogin.setRocked(true);
			Timestamp now = TimeDeal.nowTime();
			userLogin.setUpdateTime(now);
			userLoginRepository.saveAndFlush(userLogin);
			companyInfo.setUpdateTime(now);
			companyInfoRepository.saveAndFlush(companyInfo);
			return "アカウントを制限しました";
		} else {
			if (oCompanyInfo.isRocked() != userLogin.isRocked()) {
				count++;
				userLogin.setRocked(false);
			}
		}
		if (count == 0) {
			return "データの変更はありません";
		}
		Timestamp now = TimeDeal.nowTime();
		userLogin.setUpdateTime(now);
		userLoginRepository.saveAndFlush(userLogin);
		companyInfo.setUpdateTime(now);
		companyInfoRepository.saveAndFlush(companyInfo);
		return count + "コア更新しました";
	}
}
