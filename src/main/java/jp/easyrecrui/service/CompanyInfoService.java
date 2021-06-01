package jp.easyrecrui.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.easyrecrui.entity.CompanyInfo;
import jp.easyrecrui.entity.ResumeInfo;
import jp.easyrecrui.entity.UserInfo;
import jp.easyrecrui.entity.UserLogin;
import jp.easyrecrui.object.CompanyPageInfo;
import jp.easyrecrui.object.OCompanyInfo;
import jp.easyrecrui.repository.CompanyInfoRepository;
import jp.easyrecrui.repository.ResumeInfoRepository;
import jp.easyrecrui.repository.UserInfoRepository;
import jp.easyrecrui.repository.UserLoginRepository;
import jp.easyrecrui.utils.CodeType;
import jp.easyrecrui.utils.TimeDeal;

@Service
public class CompanyInfoService {

	@Autowired
	CompanyInfoRepository companyInfoRepository;
	@Autowired
	UserLoginRepository userLoginRepository;
	@Autowired
	UserInfoRepository userInfoRepository;
	@Autowired
	ResumeInfoRepository resumeInfoRepository;

	/**
	 * 会社を登録する
	 * @param oCompanyInfo
	 * @return
	 */
	public int addCompanyUser(String userName, CompanyPageInfo companyPageInfo) {
		// ユーザが存在するかどうかを判断する
		UserLogin findByUserName = userLoginRepository.findByUserName(userName);
		if (findByUserName == null) {// 存在しない場合追加する
			UserLogin userLogin = new UserLogin();
			userLogin.setUserName(userName);
			userLogin.setPassword(companyPageInfo.getPassword());
			userLogin.setRocked(false);
			userLogin.setUserRole(companyPageInfo.getRole());
			Timestamp now = TimeDeal.nowTime();
			userLogin.setCreateTime(now);
			userLogin.setUpdateTime(now);
			userLoginRepository.saveAndFlush(userLogin);
			UserInfo userInfo = new UserInfo();
			userInfo.setSex(1);
			userInfoRepository.saveAndFlush(userInfo);
			CompanyInfo companyInfo = new CompanyInfo();
			companyInfo.setCompanyName(companyPageInfo.getCompanyName());
			companyInfo.setMail(companyPageInfo.getMail());
			companyInfo.setTel(companyPageInfo.getTel());
			companyInfo.setAdress(companyPageInfo.getAdress());
			companyInfo.setUpdateTime(now);
			companyInfoRepository.saveAndFlush(companyInfo);
			ResumeInfo resumeInfo = new ResumeInfo();
			resumeInfo.setUpdateTime(now);
			resumeInfoRepository.saveAndFlush(resumeInfo);
			return CodeType.REGIST_SUCESS.getCode();
		} else {
			return CodeType.USER_EXIST.getCode();
		}
	}

	/**
	 * 会社名を取得する
	 * @param userName
	 * @return
	 */
	public String getCompanyName(int uid) {
		CompanyInfo companyInfo = companyInfoRepository.findByUid(uid);
		return companyInfo.getCompanyName();
	}

	/**
	 * 会社情報を取得する
	 * @param uid
	 * @return
	 */
	public CompanyPageInfo getCompanyInfo(int uid) {
		CompanyPageInfo companyPageInfo = new CompanyPageInfo();
		CompanyInfo companyInfo = companyInfoRepository.findByUid(uid);
		companyPageInfo.setUid(companyInfo.getUid());
		companyPageInfo.setCompanyName(companyInfo.getCompanyName());
		companyPageInfo.setMail(companyInfo.getMail());
		companyPageInfo.setTel(companyInfo.getTel());
		companyPageInfo.setInfo(companyInfo.getInfo());
		companyPageInfo.setFoundedTime(companyInfo.getFoundedTime());
		companyPageInfo.setIcon(companyInfo.getIcon());
		companyPageInfo.setAdress(companyInfo.getAdress());
		companyPageInfo.setUpdateTime(companyInfo.getUpdateTime());
		UserLogin userLogin = userLoginRepository.findByUid(uid);
		companyPageInfo.setPassword(userLogin.getPassword());
		companyPageInfo.setRole(userLogin.getUserRole());
		return companyPageInfo;

	}

	/**
	 * icon変更
	 * @param userName
	 * @param icon
	 */
	public void saveIcon(int uid, String icon) {
		CompanyInfo companyInfo = companyInfoRepository.findByUid(uid);
		icon = "../company/icon/" + icon;
		companyInfo.setIcon(icon);
		Timestamp now = TimeDeal.nowTime();
		companyInfo.setUpdateTime(now);
		companyInfoRepository.saveAndFlush(companyInfo);

	}

	/**
	 * 会社iconを取得する
	 * @param userName
	 * @return
	 */
	public String getIcon(int uid) {
		return companyInfoRepository.findByUid(uid).getIcon();
	}

	/**
	 * companyInfo更新
	 * @param oCompanyInfo
	 * @return
	 */
	public String updateCompanyInfo(OCompanyInfo oCompanyInfo) {
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
		if (!oCompanyInfo.getFoundedTime().equals(companyInfo.getFoundedTime())) {
			companyInfo.setFoundedTime(oCompanyInfo.getFoundedTime());
			count++;
		}
		if (!oCompanyInfo.getAdress().equals(companyInfo.getAdress())) {
			companyInfo.setAdress(oCompanyInfo.getAdress());
			count++;
		}
		if (!oCompanyInfo.getInfo().equals(companyInfo.getInfo())) {
			companyInfo.setInfo(oCompanyInfo.getInfo());
			count++;
		}
		if (count == 0) {
			return "データの変更はありません";
		}
		Timestamp now = TimeDeal.nowTime();
		companyInfo.setUpdateTime(now);
		companyInfoRepository.saveAndFlush(companyInfo);
		return count + "コア更新しました";
	}

}
