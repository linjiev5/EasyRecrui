package jp.easyrecrui.contoller;

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
import org.springframework.web.servlet.ModelAndView;

import jp.easyrecrui.object.OUserInfo;
import jp.easyrecrui.object.OUserLogin;
import jp.easyrecrui.service.LoginService;
import jp.easyrecrui.service.ResumeService;
import jp.easyrecrui.service.UserInfoService;
import jp.easyrecrui.utils.CodeType;
import jp.easyrecrui.utils.Md5Code;
import jp.easyrecrui.utils.UrlPath;

@Controller
public class UserContoller {

	@Autowired
	UserInfoService userInfoService;
	@Autowired
	LoginService loginService;
	@Autowired
	ResumeService resumeService;

	/**
	 * 個人情報画面
	 * @param session
	 * @return
	 */
	@RequestMapping(UrlPath.MYPAGE_VIEW)
	public ModelAndView mypage(@RequestParam(name = "icon")String icon,HttpSession session) {
		ModelAndView mav = new ModelAndView("user/myPage");// テンプレートHTML指定
		OUserInfo oUserInfo = (OUserInfo) session.getAttribute("loginUser");
		String userName=oUserInfo.getUserName();
		resumeService.getResume(userName);
		return mav;
	}

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
	public ModelAndView regist(@RequestParam(name = "userName") String userName,
			@RequestParam(name = "userPwd") String userPwd, @RequestParam(name = "name") String name,
			@RequestParam(name = "mail") String mail, @RequestParam(name = "tel") String tel,
			@RequestParam(name = "sex") int sex, @RequestParam(name = "adress") String adress,
			@RequestParam(name = "nearestStation") String nearestStation,
			@RequestParam(name = "birthday") Date birthday, Map<String, Object> message) {
		ModelAndView mav = new ModelAndView("login/regist");
		// 空欄あるかどうかを確認
		if ((sex == 1 || sex == 0)
				&& !userName.toString().equals("") && !userPwd.toString().equals("") && !name.toString().equals("")
				&& !mail.toString().equals("") && !tel.toString().equals("") && !adress.toString().equals("")
				&& !nearestStation.toString().equals("")
				&& !birthday.toString().equals("")) {
			// ユーザ存在するかどうかを判断する
			System.out.println(userName);
			int result = loginService.findUser(userName);
			if (result == CodeType.USER_NOT_EXIST.getCode()) {
				// ユーザが重複してなければ登録する
				OUserLogin oUserLogin = new OUserLogin();
				userPwd = Md5Code.md5Code(userPwd);
				oUserLogin.setUserName(userName);
				oUserLogin.setPassword(userPwd);
				// userLogin表に追加
				int userLoginResult = loginService.addUserLogin(oUserLogin);
				OUserInfo oUserInfo = new OUserInfo();
				oUserInfo.setAdress(adress);
				oUserInfo.setMail(mail);
				oUserInfo.setName(name);
				oUserInfo.setSex(sex);
				oUserInfo.setTel(tel);
				oUserInfo.setUserName(userName);
				oUserInfo.setNearestStation(nearestStation);
				oUserInfo.setBirthday(birthday);
				Timestamp updateTime = new Timestamp(System.currentTimeMillis());
				oUserInfo.setUpdateTime(updateTime);
				// 情報追加
				int userInfoResult = userInfoService.addUserInfo(oUserInfo);
				if (userLoginResult == CodeType.REGIST_SUCESS.getCode()
						&& userInfoResult == CodeType.REGIST_SUCESS.getCode()) {
					message.put("message", CodeType.REGIST_SUCESS.getMessage());
					name = null;
					userPwd = null;
					adress = null;
					mail = null;
					tel = null;
					userName = null;
					birthday = null;
					mav.setViewName("login/login");
					return mav;
				} else {
					message.put("message", CodeType.ERROR.getMessage());
					return mav;
				}
			} else {
				// ユーザが存在すれば画面に提示する
				message.put("message", CodeType.USER_EXIST.getMessage());
				return mav;
			}
		} else {
			// それぞれ未入力の項目の提示
			if (userName.toString().trim().equals("")) {
				message.put("message", "ユーザ名を入力してください");
			} else if (userPwd.toString().trim().equals("")) {
				message.put("message", "パスワードを入力してください");
			} else if (name.toString().trim().equals("")) {
				message.put("message", "名前を入力してください");
			} else if (mail.toString().trim().equals("")) {
				message.put("message", "メールを入力してください");
			} else if (tel.toString().trim().equals("")) {
				message.put("message", "電話番号を入力してください");
			} else if (sex != 1 || sex != 0) {
				message.put("message", "性別を選択してください");
			} else if (adress.toString().trim().equals("")) {
				message.put("message", "住所を入力してください");
			} else if (nearestStation.toString().trim().equals("")) {
				message.put("message", "最寄駅を入力してください");
			} else if (birthday.toString().trim().equals("")) {
				message.put("message", "生年月日を選択してください");
			} else {
			}
			return mav;
		}
	}
	/**
	 *
	 * @param model
	 * @param hs
	 * @return
	 */
	@RequestMapping(UrlPath.UPDATE_USER_INFO)
	public ModelAndView list(Model model, HttpSession hs) {
		ModelAndView mav = new ModelAndView("user/update");
		OUserInfo oUserInfo= (OUserInfo) hs.getAttribute("loginUser");
		//System.out.println(applyUser.getUserId());
		model.addAttribute("applyUserInfo", oUserInfo);
		return mav;
	}
	@RequestMapping("/withdrawal")// 退会
	public String withdrawal(HttpSession session) {
		OUserInfo oUserInfo= (OUserInfo) hs.getAttribute("loginUser");
		int result = userInfoService.f(applyUser.getUserId());
		if(result == CodeType.USER_EXIST.getCode()) {
			applyUserService.withdrawal(applyUser.getUserId());
		}else {
		}
		session.removeAttribute("loginUser");
		return "redirect:"+UrlPath.MAIN_VIEW;
	}

}
