package jp.easyrecrui.controller;

import java.sql.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.easyrecrui.object.OMessage;
import jp.easyrecrui.object.OUserInfo;
import jp.easyrecrui.object.OUserLogin;
import jp.easyrecrui.object.UserRole;
import jp.easyrecrui.service.CompanyInfoService;
import jp.easyrecrui.service.LoginService;
import jp.easyrecrui.service.MessageToOperationService;
import jp.easyrecrui.service.UserInfoService;
import jp.easyrecrui.utils.CodeType;
import jp.easyrecrui.utils.Md5Code;
import jp.easyrecrui.utils.UrlPath;

@Controller
public class LoginController {
	@Autowired
	LoginService loginService;
	@Autowired
	UserInfoService userInfoService;
	@Autowired
	CompanyInfoService companyInfoService;
	@Autowired
	MessageToOperationService messageToOperationService;
	@Autowired
	HttpSession session;

	/**
	 * ログインする
	 * @param userName
	 * @param userPwd
	 * @param message
	 * @param session
	 * @return
	 */
	@PostMapping(value = "/login")
	public String login(@RequestParam(name = "userName") String userName,
			@RequestParam(name = "userPwd") String userPwd,
			Model model, RedirectAttributes redirectAttributes) {
		OUserLogin oUserLogin = new OUserLogin();
		userPwd = Md5Code.md5Code(userPwd);
		oUserLogin.setUserName(userName);
		oUserLogin.setPassword(userPwd);
		int loginResult = loginService.login(oUserLogin);
		if (loginResult == CodeType.USER_NOT_EXIST.getCode()) {
			redirectAttributes.addFlashAttribute("message", CodeType.USER_NOT_EXIST.getMessage());
			return "redirect:" + UrlPath.LOGIN;
		} else {
			if (loginResult == CodeType.USER_ROCKED.getCode()) {
				redirectAttributes.addFlashAttribute("message", "「"+userName+"」"+CodeType.USER_ROCKED.getMessage());
				return "redirect:" + UrlPath.LOGIN;
			} else if (loginResult == CodeType.PASSWORD_ERROR.getCode()) {
				redirectAttributes.addFlashAttribute("message", CodeType.PASSWORD_ERROR.getMessage());
				return "redirect:" + UrlPath.LOGIN;
			} else {
				// ユーザの権限を確認する
				String role = loginService.getRole(userName);
				if (role.equals("user") || role.equals("admin") || role.equals("company")) {
					UserRole userRole = new UserRole();
					int uid = loginService.getUid(userName);
					userRole.setUid(uid);
					userRole.setName(userInfoService.getUserInfo(uid).getName());
					userRole.setRole(role);
					session.setAttribute("userRole", userRole);
					if (role.equals("company")) {
						String companyName = companyInfoService.getCompanyName(uid);
						model.addAttribute("companyName", companyName);
					}
				} else {
					// 権限なしの場合
					redirectAttributes.addFlashAttribute("message", CodeType.ERROR.getMessage());
					return "redirect:" + UrlPath.LOGIN;
				}
			}
			return "redirect:" + UrlPath.INDEX_VIEW;
		}

	}

	/**
	 * ログアウトする
	 * @param session
	 * @return
	 */
	@RequestMapping(UrlPath.LOGOUT)
	public String logout() {
		session.removeAttribute("userRole");
		return "redirect:" + UrlPath.INDEX_VIEW;

	}

	/**
	 * パスワードをリセットする
	 * @param redirectAttributes
	 * @param userNameInfo
	 * @param nameInfo
	 * @param telInfo
	 * @param birthdayInfo
	 * @return
	 */
	@PostMapping("/sendInfo")
	public String authentication(RedirectAttributes redirectAttributes,
			@RequestParam(name = "userNameInfo") String userNameInfo, @RequestParam(name = "nameInfo") String nameInfo,
			@RequestParam(name = "telInfo") String telInfo, @RequestParam(name = "birthdayInfo") Date birthdayInfo) {
		OUserInfo oUserInfo = new OUserInfo();
		oUserInfo.setName(nameInfo);
		oUserInfo.setTel(telInfo);
		oUserInfo.setBirthday(birthdayInfo);
		String infoMessage = loginService.authentication(userNameInfo, oUserInfo);
		redirectAttributes.addFlashAttribute("message", infoMessage);
		return "redirect:" + UrlPath.LOGIN;
	}

	@PostMapping("/sendMessage")
	public String sendMessage(@RequestParam(name = "userName") String userName,
			@RequestParam(name = "message") String message, RedirectAttributes redirectAttributes) {
		if (loginService.userExist(userName)) {
			int uid = loginService.getUid(userName);
			OMessage oMessage = new OMessage();
			oMessage.setUid(uid);
			oMessage.setMessage(message);
			String result = messageToOperationService.sendMessage(oMessage);
			redirectAttributes.addFlashAttribute("message", result);// 連絡をお待ちください。
		} else {
			redirectAttributes.addFlashAttribute("message", "ユーザ名は存在しません");
		}
		return "redirect:" + UrlPath.LOGIN;

	}
}
