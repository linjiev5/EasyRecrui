package jp.easyrecrui.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.easyrecrui.object.MyPageInfo;
import jp.easyrecrui.object.OUserInfo;
import jp.easyrecrui.object.UserRole;
import jp.easyrecrui.service.LoginService;
import jp.easyrecrui.service.UserInfoService;
import jp.easyrecrui.utils.CodeType;
import jp.easyrecrui.utils.Md5Code;
import jp.easyrecrui.utils.UrlPath;

@Controller
public class UserInfoController {

	@Autowired
	LoginService loginService;
	@Autowired
	UserInfoService userInfoService;
	@Autowired
	HttpSession session;

	/**
	 * 登録する

	 * @param userId
	 * @param userPwd
	 * @param name
	 * @param mail
	 * @param tel
	 * @param sex
	 * @param adress
	 * @param birth
	 * @param message
	 * @return
	 */
	@PostMapping(value = "/regist")
	public String regist(@RequestParam(name = "userName") String userName,
			@RequestParam(name = "userPwd") String userPwd, @RequestParam(name = "name") String name,
			@RequestParam(name = "mail") String mail, @RequestParam(name = "tel") String tel,
			@RequestParam(name = "sex") int sex, @RequestParam(name = "adress") String adress,
			@RequestParam(name = "birthday") Date birthday, RedirectAttributes redirectAttributes,
			Map<String, Object> msg) {
		// 空欄あるかどうかを確認
		if ((sex == 1 || sex == 0)
				&& !userName.toString().equals("") && !userPwd.toString().equals("") && !name.toString().equals("")
				&& !mail.toString().equals("") && !tel.toString().equals("") && !adress.toString().equals("")
				&& !birthday.toString().equals("")) {
			userPwd = Md5Code.md5Code(userPwd);
			MyPageInfo myPageInfo = new MyPageInfo();
			myPageInfo.setPassword(userPwd);
			myPageInfo.setName(name);
			myPageInfo.setMail(mail);
			myPageInfo.setTel(tel);
			myPageInfo.setSex(sex);
			myPageInfo.setAdress(adress);
			myPageInfo.setBirthday(birthday);
			myPageInfo.setRole("user");
			int registResult = userInfoService.addUser(userName, myPageInfo);
			if (registResult == CodeType.REGIST_SUCESS.getCode()) {
				redirectAttributes.addFlashAttribute("message", CodeType.REGIST_SUCESS.getMessage());
			} else {
				redirectAttributes.addFlashAttribute("message", CodeType.USER_EXIST.getMessage());
			}
		}
		return "redirect:" + UrlPath.LOGIN;
	}

	/**
	 * 個人情報画面
	 * @param session
	 * @return
	 */
	@RequestMapping(UrlPath.MYPAGE_VIEW)
	public ModelAndView mypage() {
		ModelAndView mav = new ModelAndView("user/myPage");// テンプレートHTML指定
		UserRole userRole = (UserRole) session.getAttribute("userRole");
		OUserInfo userInfo = userInfoService.getUserInfo(userRole.getUid());
		if (userInfo.getIcon() == null) {
			userInfo.setIcon("../user/default.jpg");
		} else {
		}
		mav.addObject("userInfo", userInfo);
		return mav;
	}

	/**
	 * アイコンを変更する
	 * @param icon
	 * @param message
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/uploadImg")
	public String changeIcon(@RequestPart("icon") MultipartFile icon, RedirectAttributes redirectAttributes)
			throws IOException {
		if (!icon.isEmpty()) {
			Timestamp createTime = new Timestamp(System.currentTimeMillis());
			UserRole userRole = (UserRole) session.getAttribute("userRole");
			String originalFilename = icon.getOriginalFilename();
			String fileType = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
			String iconName = userRole.getUid()
					+ createTime.toString().replace("-", "").replace(" ", "").replace(":", "").replace(".", "")
					+ fileType;
			icon.transferTo(
					new File("C:\\ep\\pleiades\\workspace\\EasyRecrui\\src\\main\\resources\\static\\user\\icon\\"
							+ iconName));
			String userIcon = userInfoService.getIcon(userRole.getUid());
			String imgPath = "C:\\ep\\pleiades\\workspace\\EasyRecrui\\src\\main\\resources\\static\\user\\icon\\"
					+ userIcon;
			if (userIcon != null) {
				imgPath = imgPath.substring(16);
			}
			File file = new File(imgPath);
			if (file.exists()) {
				file.delete();
			}
			userInfoService.saveIcon(userRole.getUid(), iconName);
			redirectAttributes.addFlashAttribute("message", "アイコンを変更しました");
		} else {
			redirectAttributes.addFlashAttribute("message", "アイコンを選択してください");
		}
		return "redirect:" + UrlPath.MYPAGE_VIEW;
	}

	/**
	 * 個人情報のデータを更新する
	 * @param userName
	 * @param name
	 * @param mail
	 * @param tel
	 * @param sex
	 * @param adress
	 * @param birthday
	 * @param message
	 * @param session
	 * @return
	 */
	@PostMapping("/saveUserInfo")
	public String saveUserInfo(@RequestParam(name = "uid") int uid,
			@RequestParam(name = "name") String name,
			@RequestParam(name = "mail") String mail, @RequestParam(name = "tel") String tel,
			@RequestParam(name = "sex") int sex, @RequestParam(name = "adress") String adress,
			@RequestParam(name = "birthday") Date birthday, RedirectAttributes redirectAttributes) {
		//ModelAndView mav = new ModelAndView("user/myPage");
		OUserInfo oUserInfo = new OUserInfo();
		oUserInfo.setUid(uid);
		oUserInfo.setName(name);
		oUserInfo.setMail(mail);
		oUserInfo.setSex(sex);
		oUserInfo.setTel(tel);
		oUserInfo.setAdress(adress);
		oUserInfo.setBirthday(birthday);
		String msg = userInfoService.updateUserInfo(oUserInfo);
		UserRole userRole = (UserRole) session.getAttribute("userRole");
		if (!userRole.getName().equals(name)) {
			userRole.setName(name);
			session.setAttribute("userRole", userRole);
		}
		//mav.setViewName("user/myPage");
		//		message.put("message", msg);
		redirectAttributes.addFlashAttribute("message", msg);

		return "redirect:" + UrlPath.MYPAGE_VIEW;
	}

	/**
	 * パスワード変更処理
	 * @param oldPswd
	 * @param newPswd
	 * @param model
	 * @param message
	 * @param session
	 * @return
	 */
	@PostMapping("/savePassword")
	public String savePassword(@RequestParam(name = "password") String oldPswd,
			@RequestParam(name = "newPassword") String newPswd, Model model, RedirectAttributes redirectAttributes) {
		UserRole userRole = (UserRole) session.getAttribute("userRole");
		oldPswd = Md5Code.md5Code(oldPswd);
		newPswd = Md5Code.md5Code(newPswd);
		int result = loginService.savePassword(userRole.getUid(), oldPswd, newPswd);
		if (result == CodeType.PASSWORD_ERROR.getCode()) {
			redirectAttributes.addFlashAttribute("message", CodeType.PASSWORD_ERROR.getMessage());
			return "redirect:" + UrlPath.MYPAGE_VIEW;
		} else {
			redirectAttributes.addFlashAttribute("message", "パスワード変更しました");
			session.removeAttribute("usreRole");
			return "redirect:" + UrlPath.LOGIN;
		}

	}

	/**
	 * 退会
	 * @param session
	 * @return
	 */
	@RequestMapping("/withdrawal")
	public String withdrawal() {
		UserRole userRole = (UserRole) session.getAttribute("userRole");
		loginService.deleteUser(userRole.getUid());
		session.removeAttribute("userRole");
		return "redirect:" + UrlPath.INDEX_VIEW;
	}

	@RequestMapping("/becomeCompany")
	public String becomeCompany() {
		UserRole userRole = (UserRole) session.getAttribute("userRole");
		loginService.setUserBecomeCompany(userRole.getUid());
		return "redirect:" + UrlPath.LOGOUT;
	}

}
