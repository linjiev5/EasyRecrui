package jp.easyrecrui.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jp.easyrecrui.object.CompanyPageInfo;
import jp.easyrecrui.object.OCompanyPicture;
import jp.easyrecrui.object.ONotice;
import jp.easyrecrui.object.ORecruiInfo;
import jp.easyrecrui.object.OResume;
import jp.easyrecrui.object.UserRole;
import jp.easyrecrui.service.CompanyInfoService;
import jp.easyrecrui.service.CompanyPictureService;
import jp.easyrecrui.service.MessageToOperationService;
import jp.easyrecrui.service.NoticeService;
import jp.easyrecrui.service.RecruiInfoService;
import jp.easyrecrui.service.ResumeService;
import jp.easyrecrui.utils.UrlPath;

@Controller
public class IndexController {

	@Autowired
	CompanyInfoService companyInfoService;
	@Autowired
	RecruiInfoService recruiInfoService;
	@Autowired
	CompanyPictureService companyPictureService;
	@Autowired
	ResumeService resumeService;
	@Autowired
	NoticeService noticeService;
	@Autowired
	MessageToOperationService messageToOperationService;
	@Autowired
	HttpSession session;

	/**
	 * メイン画面
	 * @return
	 */
	@RequestMapping({ "/", UrlPath.INDEX_VIEW }) // url地址值
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("main/index");// path
		UserRole userRole = (UserRole) session.getAttribute("userRole");
		if (userRole == null) {
		} else {
			if (userRole.getRole().equals("company")) {
				String companyName = companyInfoService.getCompanyName(userRole.getUid());
				mav.addObject("companyName", companyName);
			}
			if (userRole.getRole().equals("admin")) {
				int messageCount = messageToOperationService.getNotReadMessageCount();
				mav.addObject("messageCount", messageCount);
			}
		}
		ArrayList<ONotice> notice = noticeService.getAllNotice();
		mav.addObject("notice", notice);
		return mav;
	}

	/**
	 * ログイン画面
	 * @param model
	 * @return
	 */
	@RequestMapping(UrlPath.LOGIN)
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView("main/login");// テンプレートHTML指定
		return mav;
	}


	/**
	 * 会社一覧
	 * @return
	 */
	@GetMapping(UrlPath.COMPANY_VIEW + "/{uid}")
	public ModelAndView companyView(@PathVariable("uid") int uid) {
		ModelAndView mav = new ModelAndView("main/companyView");
		CompanyPageInfo companyPageInfo = companyInfoService.getCompanyInfo(uid);
		UserRole userRole = (UserRole) session.getAttribute("userRole");
		if (userRole == null) {
		} else {
			if (userRole.getRole().equals("company")) {
				String companyName = companyInfoService.getCompanyName(userRole.getUid());
				mav.addObject("companyName", companyName);
			}
		}
		ArrayList<OCompanyPicture> pictures = companyPictureService.getPicture(uid);
		mav.addObject("pictures", pictures);
		mav.addObject("companyPageInfo", companyPageInfo);
		return mav;
	}

	/**
	 * 募集情報詳細画面
	 * @return
	 */
	@RequestMapping(UrlPath.JOB_INFO + "/{id}")
	public ModelAndView jobInfo(@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("main/jobInfo");// テンプレートHTML指定
		UserRole userRole = (UserRole) session.getAttribute("userRole");
		if (userRole == null) {
		} else {
			if (userRole.getRole().equals("company")) {
				String companyName = companyInfoService.getCompanyName(userRole.getUid());
				mav.addObject("companyName", companyName);
			}
			OResume oResume = resumeService.getResume(userRole.getUid(), id);
			if (oResume != null) {
				mav.addObject("resume", oResume);
			}
		}
		ORecruiInfo recruiInfo = recruiInfoService.findOneRecruiInfo(id);
		int uid = recruiInfo.getUid();
		CompanyPageInfo companyPageInfo = companyInfoService.getCompanyInfo(uid);
		mav.addObject("companyPageInfo", companyPageInfo);
		mav.addObject("recruiInfo", recruiInfo);
		return mav;
	}

}
