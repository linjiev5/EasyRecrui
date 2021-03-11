package jp.easyrecrui.contoller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.easyrecrui.object.OUserInfo;
import jp.easyrecrui.object.OUserLogin;
import jp.easyrecrui.service.LoginService;
import jp.easyrecrui.service.UserInfoService;
import jp.easyrecrui.utils.CodeType;
import jp.easyrecrui.utils.Md5Code;
import jp.easyrecrui.utils.UrlPath;

@Controller
public class LoginContoller {
	@Autowired
	LoginService loginService;
	@Autowired
	UserInfoService userInfoService;

	/**
	 * ログインする
	 * @param userName
	 * @param userPwd
	 * @param message
	 * @param session
	 * @return
	 */
	@PostMapping(value = "/login")
	public ModelAndView login(@RequestParam(name = "userName") String userName,
			@RequestParam(name = "userPwd") String userPwd, Map<String, Object> message, HttpSession session) {
		ModelAndView mav = new ModelAndView("login/login");

		// ユーザ存在するかどうか
		int isExist = loginService.findUser(userName);
		// 存在する場合
		if (isExist == CodeType.USER_EXIST.getCode()) {
			OUserLogin oUserLogin = new OUserLogin();
			userPwd = Md5Code.md5Code(userPwd);
			oUserLogin.setUserName(userName);
			oUserLogin.setPassword(userPwd);
			// roleを取得
			String role = loginService.getRole(userName);
			// ログインする
			int result = loginService.login(oUserLogin);
			if (result == CodeType.USER_ROCKED.getCode()) {
				// アカウントが制限する場合
				message.put("message", CodeType.USER_ROCKED.getMessage());
			} else if (result == CodeType.PASSWORD_ERROR.getCode()) {
				// パスワードが間違った場合
				message.put("message", CodeType.PASSWORD_ERROR.getMessage());
			} else {
				// ログイン成功すればUser情報をセクションにセットする
				OUserInfo oUserInfo = userInfoService.getUserInfo(userName);
				session.setAttribute("loginUser", oUserInfo);
				// roleを判断してそれぞれのrole画面に遷移する
				if (role == "admin") {
					mav.setViewName("user/admin");
				} else if (role == "company") {
					mav.setViewName("company/companyPage");
				} else if (role == "user") {
					mav.setViewName("user/myPage");
				} else {
					mav.setViewName("403");
				}
			}
		} else {
			message.put("message", CodeType.USER_NOT_EXIST.getMessage());
		}
		return mav;
	}

	/**
	 * ログアウトする
	 * @param session
	 * @return
	 */
	@RequestMapping(UrlPath.LOGOUT)
	public String logout(HttpSession session) {
		session.removeAttribute("loginUser");
		return "redirect:" + UrlPath.INDEX_VIEW;

	}

}
