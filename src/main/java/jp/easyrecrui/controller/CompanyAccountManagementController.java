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

import jp.easyrecrui.object.OCompanyInfo;
import jp.easyrecrui.object.ORecruiInfo;
import jp.easyrecrui.service.AdminService;
import jp.easyrecrui.service.MessageToOperationService;
import jp.easyrecrui.service.RecruiInfoService;
import jp.easyrecrui.utils.UrlPath;

@Controller
public class CompanyAccountManagementController {

	@Autowired
	AdminService adminService;
	@Autowired
	MessageToOperationService messageToOperationService;
	@Autowired
	RecruiInfoService recruiInfoService;

	/**
	 * 会社アカウント管理画面
	 * @return
	 */
	@RequestMapping(UrlPath.COMPANY_MANAGEMENT)
	public String companyMane(RedirectAttributes redirectAttributes, Model model) {
		ArrayList<OCompanyInfo> oCompanyInfos = adminService.getCompanyInfo();
		model.addAttribute("companyInfos", oCompanyInfos);
		int messageCount = messageToOperationService.getNotReadMessageCount();
		model.addAttribute("messageCount", messageCount);
		return "admin/companyMane";

	}

	/**
	 * 会社のデータを編集する画面
	 * @param id
	 * @return
	 */
	@RequestMapping("/changeCompanyInfo/{id}")
	public ModelAndView changeCompanyInfoById(@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("admin/updateCompanyInfo");// テンプレートHTML指定
		OCompanyInfo companyInfo = adminService.getCompanyInfoForAdmin(id);
		mav.addObject("companyInfo", companyInfo);
		int messageCount = messageToOperationService.getNotReadMessageCount();
		mav.addObject("messageCount", messageCount);
		return mav;
	}

	/**
	 * 会社の情報を変更する
	 * @param uid
	 * @param userName
	 * @param companyName
	 * @param mail
	 * @param tel
	 * @param adress
	 * @param foundedTime
	 * @param role
	 * @param rocked
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/updatecompanyInfoForAdmin")
	public String updatecompanyInfoForAdmin(@RequestParam(name = "uid") int uid,
			@RequestParam(name = "userName") String userName,
			@RequestParam(name = "companyName") String companyName, @RequestParam(name = "mail") String mail,
			@RequestParam(name = "tel") String tel,
			@RequestParam(name = "adress") String adress, @RequestParam(name = "foundedTime") Date foundedTime,
			@RequestParam(name = "role") String role,
			@RequestParam(name = "rocked") boolean rocked, RedirectAttributes redirectAttributes, Model model) {
		OCompanyInfo oCompanyInfo = new OCompanyInfo();
		oCompanyInfo.setUid(uid);
		oCompanyInfo.setUserName(userName);
		oCompanyInfo.setCompanyName(companyName);
		oCompanyInfo.setMail(mail);
		oCompanyInfo.setTel(tel);
		oCompanyInfo.setAdress(adress);
		oCompanyInfo.setFoundedTime(foundedTime);
		oCompanyInfo.setRocked(rocked);
		oCompanyInfo.setRole(role);
		String result = adminService.saveCompanyInfo(oCompanyInfo);
		redirectAttributes.addFlashAttribute("message", result);
		int messageCount = messageToOperationService.getNotReadMessageCount();
		model.addAttribute("messageCount", messageCount);
		return "redirect:" + UrlPath.COMPANY_MANAGEMENT;
	}

	/**
	 * 求人情報管理画面
	 * @param redirectAttributes
	 * @param model
	 * @return
	 */
	@RequestMapping("/login/recruiManaForAdmin")
	public ModelAndView recruiManaForAdmin(RedirectAttributes redirectAttributes) {
		ModelAndView mav = new ModelAndView("admin/recruiManeForAdmin");
		int messageCount = messageToOperationService.getNotReadMessageCount();
		mav.addObject("messageCount", messageCount);
		ArrayList<ORecruiInfo> recruiInfos = recruiInfoService.getAllRecruiInfo();
		mav.addObject("recruiInfos", recruiInfos);
		return mav;

	}

	/**
	 * 求人情報を削除する
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteRecruiForAdmin/{id}")
	public String deleteRecruiInfo(@PathVariable("id") int id, Model model) {
		recruiInfoService.deleteRecruiInfo(id);
		int messageCount = messageToOperationService.getNotReadMessageCount();
		model.addAttribute("messageCount", messageCount);
		return "redirect:/login/recruiManaForAdmin";
	}
}
