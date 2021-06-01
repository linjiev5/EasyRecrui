package jp.easyrecrui.controller;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.easyrecrui.object.ORecruiInfo;
import jp.easyrecrui.object.UserRole;
import jp.easyrecrui.service.CompanyInfoService;
import jp.easyrecrui.service.RecruiInfoService;
import jp.easyrecrui.utils.UrlPath;

@Controller
public class RecruiInfoController {

	@Autowired
	RecruiInfoService recruiInfoService;
	@Autowired
	CompanyInfoService companyInfoService;
	@Autowired
	HttpSession session;

	/**
	 * 求人情報画面
	 * @return
	 */
	@RequestMapping(UrlPath.RECRUI_MANAGEMENT)
	public ModelAndView recruiMana() {
		ModelAndView mav = new ModelAndView("company/recruiMane");// テンプレートHTML指定
		UserRole userRole = (UserRole) session.getAttribute("userRole");
		ArrayList<ORecruiInfo> oRecruiInfos = recruiInfoService
				.getRecruiInfo(userRole.getUid());
		mav.addObject("companyName", companyInfoService.getCompanyName(userRole.getUid()));
		mav.addObject("recruiInfos", oRecruiInfos);
		return mav;
	}

	/**
	 *	求人情報編集画面
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/changeRecrui/{id}")
	public String editWorkExperience(@PathVariable("id") int id, Model model) {
		ORecruiInfo recruiInfo = recruiInfoService.findOneRecruiInfo(id);
		model.addAttribute("recruiInfo", recruiInfo);
		return "company/updateRecruiInfo";
	}

	/**
	 * 求人情報を追加する
	 * @param title
	 * @param salary
	 * @param station
	 * @param welfare
	 * @param employmentMethod
	 * @param category
	 * @param jobDetails
	 * @param session
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/addRegular")
	public String addRegular(@RequestParam(name = "title") String title,
			@RequestParam(name = "salary") String salary, @RequestParam(name = "station") String station,
			@RequestParam(name = "welfare") String welfare,
			@RequestParam(name = "category") String category, @RequestParam(name = "jobDetails") String jobDetails,
			RedirectAttributes redirectAttributes) {
		UserRole userRole = (UserRole) session.getAttribute("userRole");
		ORecruiInfo oRecruiInfo = new ORecruiInfo();
		oRecruiInfo.setUid(userRole.getUid());
		oRecruiInfo.setTitle(title);
		oRecruiInfo.setSalary(salary + "(月給)");
		oRecruiInfo.setStation(station);
		oRecruiInfo.setWelfare(welfare);
		oRecruiInfo.setEmploymentMethod("regular");
		oRecruiInfo.setCategory(category);
		oRecruiInfo.setJobDetails(jobDetails);
		recruiInfoService.updateRecruiInfo(oRecruiInfo);
		return "redirect:" + UrlPath.RECRUI_MANAGEMENT;
	}

	/**
	 * パート求人を追加する
	 * @param title
	 * @param salary
	 * @param station
	 * @param welfare
	 * @param category
	 * @param jobDetails
	 * @param session
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/addPart")
	public String addRecruiInfo(@RequestParam(name = "title") String title,
			@RequestParam(name = "salary") String salary, @RequestParam(name = "station") String station,
			@RequestParam(name = "welfare") String welfare,
			@RequestParam(name = "category") String category, @RequestParam(name = "jobDetails") String jobDetails,
			RedirectAttributes redirectAttributes) {
		UserRole userRole = (UserRole) session.getAttribute("userRole");
		ORecruiInfo oRecruiInfo = new ORecruiInfo();
		oRecruiInfo.setUid(userRole.getUid());
		oRecruiInfo.setTitle(title);
		oRecruiInfo.setSalary(salary + "(時給)");
		oRecruiInfo.setStation(station);
		oRecruiInfo.setWelfare(welfare);
		oRecruiInfo.setEmploymentMethod("part");
		oRecruiInfo.setCategory(category);
		oRecruiInfo.setJobDetails(jobDetails);
		recruiInfoService.updateRecruiInfo(oRecruiInfo);
		return "redirect:" + UrlPath.RECRUI_MANAGEMENT;
	}

	/**
	 * 求人情報を編集する
	 * @param id
	 * @param title
	 * @param salary
	 * @param station
	 * @param welfare
	 * @param employmentMethod
	 * @param category
	 * @param jobDetails
	 * @param model
	 * @param session
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/changeRecruiInfo")
	public String changeRecruiInfo(@RequestParam(name = "id") Integer id, @RequestParam(name = "title") String title,
			@RequestParam(name = "salary") String salary, @RequestParam(name = "station") String station,
			@RequestParam(name = "welfare") String welfare,
			@RequestParam(name = "employmentMethod") String employmentMethod,
			@RequestParam(name = "category") String category, @RequestParam(name = "jobDetails") String jobDetails,
			Model model, RedirectAttributes redirectAttributes) {
		UserRole userRole = (UserRole) session.getAttribute("userRole");
		ORecruiInfo oRecruiInfo = new ORecruiInfo();
		oRecruiInfo.setUid(userRole.getUid());
		oRecruiInfo.setRecruiId(id);
		oRecruiInfo.setTitle(title);
		oRecruiInfo.setSalary(salary);
		oRecruiInfo.setStation(station);
		oRecruiInfo.setWelfare(welfare);
		oRecruiInfo.setEmploymentMethod(employmentMethod);
		oRecruiInfo.setCategory(category);
		oRecruiInfo.setJobDetails(jobDetails);
		Timestamp now = new Timestamp(System.currentTimeMillis());
		oRecruiInfo.setUpdateTime(now);
		recruiInfoService.updateRecruiInfo(oRecruiInfo);
		return "redirect:" + UrlPath.RECRUI_MANAGEMENT;
	}

	/**
	 * 求人情報削除
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteRecrui/{id}")
	public String deleteRecruiInfo(@PathVariable("id") int id) {
		recruiInfoService.deleteRecruiInfo(id);
		return "redirect:" + UrlPath.RECRUI_MANAGEMENT;
	}
}
