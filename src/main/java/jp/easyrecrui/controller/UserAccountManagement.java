package jp.easyrecrui.controller;

import java.sql.Date;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.easyrecrui.object.OUserInfo;
import jp.easyrecrui.service.AdminService;
import jp.easyrecrui.service.LoginService;
import jp.easyrecrui.service.MessageToOperationService;
import jp.easyrecrui.service.ResumeInfoService;
import jp.easyrecrui.service.UserInfoService;
import jp.easyrecrui.utils.UrlPath;

@Controller
public class UserAccountManagement {

	@Autowired
	AdminService adminService;
	@Autowired
	UserInfoService userInfoService;
	@Autowired
	LoginService loginService;
	@Autowired
	ResumeInfoService resumeInfoService;
	@Autowired
	MessageToOperationService messageToOperationService;

	/**
	 * ユーザアカウント管理
	 * @return
	 */
	@RequestMapping(UrlPath.USER_MANAGEMENT)
	public String userMane(RedirectAttributes redirectAttributes, Model model) {
		ArrayList<OUserInfo> userInfos = adminService.getUserInfo();
		if (userInfos != null) {
			for (int x = 0, y = userInfos.size(); x < y; x++) {
				userInfos.get(x).setResumeView(resumeInfoService.getResume(userInfos.get(x).getUid()));
			}
		}
		int messageCount = messageToOperationService.getNotReadMessageCount();
		model.addAttribute("messageCount", messageCount);
		model.addAttribute("userInfos", userInfos);
		return "admin/userMane";
	}

	/**
	 * ユーザのデータを編集する画面
	 * @param id
	 * @return
	 */
	@RequestMapping("/changeUserInfo/{id}")
	public ModelAndView changeUserInfoById(@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("admin/updateUserInfo");// テンプレートHTML指定
		int messageCount = messageToOperationService.getNotReadMessageCount();
		mav.addObject("messageCount", messageCount);
		OUserInfo userInfo = adminService.getUserInfoForAdmin(id);
		mav.addObject("userInfo", userInfo);
		return mav;
	}

	/**
	 * ユーザの情報を変更する
	 * @param uid
	 * @param userName
	 * @param name
	 * @param mail
	 * @param tel
	 * @param sex
	 * @param adress
	 * @param birthday
	 * @param role
	 * @param rocked
	 * @return
	 */
	@PostMapping("/updateUserInfoForAdmin")
	public String updateUserInfoForAdmin(@RequestParam(name = "uid") int uid,
			@RequestParam(name = "userName") String userName,
			@RequestParam(name = "name") String name, @RequestParam(name = "mail") String mail,
			@RequestParam(name = "tel") String tel,
			@RequestParam(name = "sex") int sex,
			@RequestParam(name = "adress") String adress, @RequestParam(name = "birthday") Date birthday,
			@RequestParam(name = "role") String role,
			@RequestParam(name = "rocked") boolean rocked, RedirectAttributes redirectAttributes, Model model) {
		OUserInfo userInfo = new OUserInfo();
		userInfo.setUid(uid);
		userInfo.setUserName(userName);
		userInfo.setName(name);
		userInfo.setMail(mail);
		userInfo.setTel(tel);
		userInfo.setSex(sex);
		userInfo.setAdress(adress);
		userInfo.setBirthday(birthday);
		userInfo.setRole(role);
		userInfo.setRocked(rocked);
		String result = adminService.saveUserInfo(userInfo);
		redirectAttributes.addFlashAttribute("message", result);
		int messageCount = messageToOperationService.getNotReadMessageCount();
		model.addAttribute("messageCount", messageCount);
		return "redirect:" + UrlPath.USER_MANAGEMENT;

	}

	/**
	 * ユーザを削除する
	 * @param id
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable("id") int id, RedirectAttributes redirectAttributes, Model model) {
		String userName = loginService.getUserName(id);
		loginService.deleteUser(id);
		redirectAttributes.addFlashAttribute("message", userName + " を削除しました");
		int messageCount = messageToOperationService.getNotReadMessageCount();
		model.addAttribute("messageCount", messageCount);
		return "redirect:" + UrlPath.USER_MANAGEMENT;
	}
}
