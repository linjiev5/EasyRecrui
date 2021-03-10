package jp.easyrecrui.contoller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.easyrecrui.utils.UrlPath;

@Controller
public class UserContoller {

	@Autowired

	/**
	 * 個人情報画面
	 * @param session
	 * @return
	 */
	@RequestMapping(UrlPath.MYPAGE_VIEW)
	public ModelAndView mypage(HttpSession session) {
		Object userLogin = session.getAttribute("loginUser");
		ModelAndView mav = new ModelAndView("user/myPage");// テンプレートHTML指定
		mav.addObject("applyUserInfo", userLogin);
		return mav;
	}
}
