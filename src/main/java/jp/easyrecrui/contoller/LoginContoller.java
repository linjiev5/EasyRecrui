package jp.easyrecrui.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.easyrecrui.utils.UrlPath;

@Controller
public class LoginContoller {

	/**
	 * 登録画面
	 * @param model
	 * @return
	 */
	@RequestMapping(UrlPath.REGIST_VIEW)
	public ModelAndView regist(Model model) {
		ModelAndView mav = new ModelAndView("login/regist");
		return mav;
	}

}
