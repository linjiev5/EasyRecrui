package jp.easyrecrui.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jp.easyrecrui.object.ORecruiInfo;
import jp.easyrecrui.object.UserRole;
import jp.easyrecrui.service.CompanyInfoService;
import jp.easyrecrui.service.RecruiInfoService;
import jp.easyrecrui.utils.UrlPath;

@Controller
public class RecruiViewController {

	@Autowired
	CompanyInfoService companyInfoService;
	@Autowired
	RecruiInfoService recruiInfoService;
	@Autowired
	HttpSession session;

	/**
	 * 正社員求人を取得する
	 * @return
	 */
	@RequestMapping(UrlPath.JOB_PAGE)
	public ModelAndView jobPage() {
		ModelAndView mav = new ModelAndView("main/jobPage");// テンプレートHTML指定
		UserRole userRole = (UserRole) session.getAttribute("userRole");
		if (userRole == null) {
		} else {
			if (userRole.getRole().equals("company")) {
				String companyName = companyInfoService.getCompanyName(userRole.getUid());
				mav.addObject("companyName", companyName);
			}
		}
		ArrayList<ORecruiInfo> recruiInfo = recruiInfoService.getRecruiInfoByEmploymentMethod("regular");
		mav.addObject("recruiInfo", recruiInfo);
		return mav;
	}

	/**
	 * パート求人を取得する
	 * @return
	 */
	@RequestMapping("/partPage")
	public ModelAndView jobPartPage() {
		ModelAndView mav = new ModelAndView("main/jobPage");// テンプレートHTML指定
		UserRole userRole = (UserRole) session.getAttribute("userRole");
		if (userRole == null) {
		} else {
			if (userRole.getRole().equals("company")) {
				String companyName = companyInfoService.getCompanyName(userRole.getUid());
				mav.addObject("companyName", companyName);
			}
		}
		ArrayList<ORecruiInfo> recruiInfo = recruiInfoService.getRecruiInfoByEmploymentMethod("part");
		mav.addObject("recruiInfo", recruiInfo);
		return mav;
	}

	/**
	 * 正社員求人をカテゴリで検索する
	 * @param model
	 * @return
	 */
	@RequestMapping("/jobPage/{category}")
	public String regularPage(@PathVariable("category") String category, Model model) {
		UserRole userRole = (UserRole) session.getAttribute("userRole");
		if (userRole == null) {
		} else {
			if (userRole.getRole().equals("company")) {
				String companyName = companyInfoService.getCompanyName(userRole.getUid());
				model.addAttribute("companyName", companyName);
			}
		}
		ArrayList<ORecruiInfo> recruiInfo = recruiInfoService.getRecruiInfoByEmploymentMethodAndCategory("regular",
				category);
		model.addAttribute("recruiInfo", recruiInfo);
		return "main/jobPage";

	}

	/**
	 * パートをカテゴリで検索する
	 * @param category
	 * @param model
	 * @return
	 */
	@RequestMapping("/partPage/{category}")
	public String partPage(@PathVariable("category") String category, Model model) {
		UserRole userRole = (UserRole) session.getAttribute("userRole");
		if (userRole == null) {
		} else {
			if (userRole.getRole().equals("company")) {
				String companyName = companyInfoService.getCompanyName(userRole.getUid());
				model.addAttribute("companyName", companyName);
			}
		}
		ArrayList<ORecruiInfo> recruiInfo = recruiInfoService.getRecruiInfoByEmploymentMethodAndCategory("part",
				category);
		model.addAttribute("recruiInfo", recruiInfo);
		return "main/jobPage";

	}

	/**
	 * 検索
	 * @param key
	 * @param model
	 * @return
	 */
	@GetMapping("@{/searchJob}")
	public ModelAndView searchJob(@RequestParam(name = "key") String key) {
		ModelAndView mav = new ModelAndView("main/searchResult");
		UserRole userRole = (UserRole) session.getAttribute("userRole");
		if (userRole == null) {
		} else {
			if (userRole.getRole().equals("company")) {
				String companyName = companyInfoService.getCompanyName(userRole.getUid());
				mav.addObject("companyName", companyName);
			}
		}
		ArrayList<ORecruiInfo> recruiInfo = recruiInfoService.getRecruiInfoForSearch(key);
		mav.addObject("recruiInfo", recruiInfo);
		return mav;
	}
}
