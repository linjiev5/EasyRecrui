package jp.easyrecrui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.easyrecrui.entity.UserInfo;
import jp.easyrecrui.entity.UserLogin;
import jp.easyrecrui.object.OUserInfo;
import jp.easyrecrui.object.OUserLogin;
import jp.easyrecrui.repository.CompanyInfoRepository;
import jp.easyrecrui.repository.CompanyPictureRepository;
import jp.easyrecrui.repository.EducationalBackgroundRepository;
import jp.easyrecrui.repository.MessageToOperationRepository;
import jp.easyrecrui.repository.RecruiInfoRepository;
import jp.easyrecrui.repository.ResumeInfoRepository;
import jp.easyrecrui.repository.ResumeRepository;
import jp.easyrecrui.repository.UserInfoRepository;
import jp.easyrecrui.repository.UserLoginRepository;
import jp.easyrecrui.repository.WorkExperienceRepository;
import jp.easyrecrui.utils.CodeType;
import jp.easyrecrui.utils.Md5Code;

@Service
@Transactional
public class LoginService {

	@Autowired
	UserLoginRepository userLoginRepository;
	@Autowired
	UserInfoRepository userInfoRepository;
	@Autowired
	ResumeInfoRepository resumeInfoRepository;
	@Autowired
	CompanyInfoRepository companyInfoRepository;
	@Autowired
	EducationalBackgroundRepository educationalBackgroundRepository;
	@Autowired
	WorkExperienceRepository workExperienceRepository;
	@Autowired
	CompanyPictureRepository companyPictureRepository;
	@Autowired
	RecruiInfoRepository recruiInfoRepository;
	@Autowired
	ResumeRepository resumeRepository;
	@Autowired
	MessageToOperationRepository messageToOperationRepository;
	/**
	 * roleを取得する
	 * @param oUserLogin
	 * @return
	 */
	public String getRole(String userName) {
		UserLogin userLogin = userLoginRepository.findByUserName(userName);
		String role = userLogin.getUserRole();
		if (role.equals("admin")) {
			return "admin";
		} else if (role.equals("company")) {
			return "company";
		} else if (role.equals("user")) {
			return "user";
		} else {
			return null;
		}
	}

	/**
	 * ログインする
	 * @param oUserLogin
	 * @return
	 */
	public int login(OUserLogin oUserLogin) {
		// ユーザが存在するかどうかを判断する
		UserLogin findByUserName = userLoginRepository.findByUserName(oUserLogin.getUserName());
		if (findByUserName == null) {// 存在しない場合ログイン失敗する
			return CodeType.USER_NOT_EXIST.getCode();
		} else {
			if (findByUserName.isRocked()) {// アカウントがロックされた場合
				return CodeType.USER_ROCKED.getCode();
			} else if (findByUserName.getPassword().equals(oUserLogin.getPassword())) {// パスワード合う場合
				return CodeType.LOGIN_SUCESS.getCode();
			} else {
				return CodeType.PASSWORD_ERROR.getCode();
			}
		}
	}

	/**
	 * パスワード変更
	 * @param userName
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	public int savePassword(int uid, String oldPassword, String newPassword) {
		UserLogin userLogin = userLoginRepository.findByUid(uid);
		if (userLogin.getPassword().equals(oldPassword)) {
			userLogin.setPassword(newPassword);
			userLoginRepository.saveAndFlush(userLogin);
			return CodeType.SAVE_PASSWORD_SUCESS.getCode();
		} else {
			return CodeType.PASSWORD_ERROR.getCode();
		}
	}

	/**
	 * パスワードをリセットする
	 * @param oUserInfo
	 * @return
	 */
	public String authentication(String userName, OUserInfo oUserInfo) {
		UserLogin userLogin = userLoginRepository.findByUserName(userName);
		if (userLogin == null) {
			return "ユーザは存在しません";
		} else {
			UserInfo userInfo = userInfoRepository.findByUid(userLogin.getUid());
			if (userInfo.getName().equals(oUserInfo.getName()) && userInfo.getTel().equals(oUserInfo.getTel())
					&& userInfo.getBirthday().equals(oUserInfo.getBirthday())) {
				String password = Md5Code.md5Code(oUserInfo.getTel());
				userLogin.setPassword(password);
				return "パスワードは電話番号にリセットしました";
			} else {
				return "正しい情報を入力してください";
			}

		}
	}

	/**
	 * 退会する
	 * @param userName
	 * @return
	 */
	public void deleteUser(int uid) {
		userLoginRepository.deleteById(uid);
		userInfoRepository.deleteById(uid);
		resumeInfoRepository.deleteById(uid);
		companyInfoRepository.deleteByUid(uid);
		if (educationalBackgroundRepository.findByUidOrderByEducationalIdAsc(uid) != null) {
			educationalBackgroundRepository.deleteByUid(uid);
		}
		if (workExperienceRepository.findByUidOrderByWorkIdAsc(uid) != null) {
			workExperienceRepository.deleteByUid(uid);
		}
		if (companyPictureRepository.findByUidOrderByPicIdAsc(uid) != null) {
			companyPictureRepository.deleteByUid(uid);
		}
		if (recruiInfoRepository.findByUidOrderByRecruiIdAsc(uid) != null) {
			recruiInfoRepository.deleteByUid(uid);
		}
		if (resumeRepository.findByRecevieUidOrderByResumeIdDesc(uid) != null) {
			resumeRepository.deleteByRecevieUid(uid);
		}
		if (resumeRepository.findBySendUidOrderByResumeIdDesc(uid) != null) {
			resumeRepository.deleteBySendUid(uid);
		}
		if(messageToOperationRepository.findByUid(uid)!=null) {
			messageToOperationRepository.deleteByUid(uid);
		}
	}

	/**
	 * uidを返却する
	 * @param userName
	 * @return
	 */
	public int getUid(String userName) {
		return userLoginRepository.findByUserName(userName).getUid();
	}

	/**
	 * UserNameを返却
	 * @param uid
	 * @return
	 */
	public String getUserName(int uid) {
		return userLoginRepository.findByUid(uid).getUserName();
	}

	/**
	 * ユーザを会社アカウントにする
	 * @param uid
	 */
	public void setUserBecomeCompany(int uid) {
		UserLogin userLogin = userLoginRepository.findById(uid).get();
		userLogin.setUserRole("company");
		userLoginRepository.saveAndFlush(userLogin);
	}

	/**
	 * ユーザ存在するかどうか
	 * @param userName
	 * @return
	 */
	public boolean userExist(String userName) {
		if (userLoginRepository.findByUserName(userName) == null) {
			return false;
		} else {
			return true;
		}

	}





}
