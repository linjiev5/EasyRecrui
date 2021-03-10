package jp.easyrecrui.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.easyrecrui.utils.UrlPath;

@Controller
public class IndexContoller {
	/**
	 * メイン画面
	 * @return
	 */
	@RequestMapping({ "/", UrlPath.INDEX_VIEW }) // url地址值
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("main/index");// path,指向templates中的文件路径
		return mav;
	}

	/**
	 * 募集情報画面
	 * @param model
	 * @return
	 */
	@RequestMapping(UrlPath.JOBPAGE_VIEW)
	public ModelAndView jobpage(Model model) {
		ModelAndView mav = new ModelAndView("main/jobPage");// テンプレートHTML指定
		return mav;
	}

	/**
	 * ログイン画面
	 * @param model
	 * @return
	 */
	@RequestMapping(UrlPath.LOGIN)
	public ModelAndView login(Model model) {
		ModelAndView mav = new ModelAndView("login/login");// テンプレートHTML指定
		return mav;
	}

}
