package jp.easyrecrui.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.easyrecrui.entity.UserLogin;
import jp.easyrecrui.object.OUserLogin;
import jp.easyrecrui.repository.UserLoginRepository;
import jp.easyrecrui.utils.CodeType;

@Service
@Transactional
public class LoginService {

	@Autowired
	UserLoginRepository userLoginRepository;

	/**
	 * ユーザ存在するかどうかを判断する
	 * @param userName
	 * @return
	 */
	public int findUser(String userName) {
		UserLogin findByUserName = userLoginRepository.findByUserName(userName);
		if (findByUserName != null) {
			return CodeType.USER_EXIST.getCode();
		} else {
			return CodeType.USER_NOT_EXIST.getCode();
		}
	}

	/**
	 * roleを取得する
	 * @param oUserLogin
	 * @return
	 */
	public String getRole(String userName) {
		UserLogin userLogin = userLoginRepository.findByUserName(userName);
		String role = userLogin.getUserRole();
		if (role == "admin") {
			return "admin";
		} else if (role == "company") {
			return "company";
		} else if (role == "user") {
			return "user";
		} else {
			return "403";
		}
	}

	/**
	 * ログインする
	 * @param oUserLogin
	 * @return
	 */
	public int login(OUserLogin oUserLogin) {
		UserLogin userLogin = userLoginRepository.findByUserName(oUserLogin.getUserName());
		// boolean rocked = userLogin.isRocked();
		if (userLogin.isRocked()) {
			return CodeType.USER_ROCKED.getCode();
		} else if (oUserLogin.getPassword().equals(userLogin.getPassword())) {
			return CodeType.LOGIN_SUCESS.getCode();
		} else {
			return CodeType.PASSWORD_ERROR.getCode();
		}
	}

	/**
	 * ユーザログイン情報を登録
	 * @param oLoginUser
	 * @return
	 */
	public int addUserLogin(OUserLogin oLoginUser) {
		if (oLoginUser.equals(null)) {
			return CodeType.ERROR.getCode();
		} else {
			UserLogin userLogin = new UserLogin();
			userLogin.setRocked(false);
			Timestamp createTime = new Timestamp(System.currentTimeMillis());
			userLogin.setCreateTime(createTime);
			userLogin.setUpdateTime(createTime);
			userLogin.setUserRole("user");
			userLogin.setUserName(oLoginUser.getUserName());
			userLogin.setPassword(oLoginUser.getPassword());
			userLoginRepository.saveAndFlush(userLogin);
			return CodeType.REGIST_SUCESS.getCode();
		}

	}
}
