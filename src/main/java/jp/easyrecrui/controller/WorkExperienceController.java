package jp.easyrecrui.controller;

import java.sql.Date;
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

import jp.easyrecrui.object.OWorkExperience;
import jp.easyrecrui.object.UserRole;
import jp.easyrecrui.service.ResumeInfoService;
import jp.easyrecrui.service.WorkExperienceService;
import jp.easyrecrui.utils.UrlPath;

@Controller
public class WorkExperienceController {

	@Autowired
	WorkExperienceService workExperienceService;
	@Autowired
	ResumeInfoService resumeInfoService;
	@Autowired
	HttpSession session;

	/**
	 * 職歴画面
	 * @param model
	 * @param redirectAttributes
	 * @param session
	 * @return
	 */
	@RequestMapping(UrlPath.WORK_EXPERIENCE)
	public ModelAndView workPage() {
		ModelAndView mav = new ModelAndView("user/workExperience");// テンプレートHTML指定
		UserRole userRole = (UserRole) session.getAttribute("userRole");
		ArrayList<OWorkExperience> workExperiences = workExperienceService
				.getWorkExperience(userRole.getUid());
		mav.addObject("workExperiences", workExperiences);
		return mav;
	}

	/**
	 *	職歴編集画面
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/changeWorkExperience/{id}")
	public String editWorkExperience(@PathVariable("id") Integer id, Model model) {
		OWorkExperience workExperience = workExperienceService.findOneWorkExperience(id);
		model.addAttribute("workExperience", workExperience);
		return "user/updateWorkExperience";
	}

	/**
	 * 職歴追加
	 * @param workExperience
	 * @param session
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/addWorkExperience")
	public String addWorkExperience(@RequestParam(name = "workStart") Date workStart,
			@RequestParam(name = "workEnd") Date workEnd, @RequestParam(name = "companyName") String companyName,
			@RequestParam(name = "position") String position,
			RedirectAttributes redirectAttributes) {
		UserRole userRole = (UserRole) session.getAttribute("userRole");
		OWorkExperience oWorkExperience = new OWorkExperience();
		oWorkExperience.setUid(userRole.getUid());
		oWorkExperience.setStartTime(workStart);
		oWorkExperience.setEndTime(workEnd);
		oWorkExperience.setCompanyName(companyName);
		oWorkExperience.setPosition(position);
		workExperienceService.updateWorkExperience(oWorkExperience);
		return "redirect:" + UrlPath.WORK_EXPERIENCE;

	}

	/**
	 * 職歴更新
	 * @param educationalBackground
	 * @param model
	 * @return
	 */
	@PostMapping("/changeWorkExperience")
	public String changeEducationalBackground(@RequestParam(name = "id") Integer id,
			@RequestParam(name = "schoolStart") Date schoolStart, @RequestParam(name = "schoolEnd") Date schoolEnd,
			@RequestParam(name = "companyName") String companyName, @RequestParam(name = "position") String position,
			Model model, RedirectAttributes redirectAttributes) {
		UserRole userRole = (UserRole) session.getAttribute("userRole");
		OWorkExperience workExperience = new OWorkExperience();
		workExperience.setUid(userRole.getUid());
		workExperience.setWorkId(id);
		workExperience.setStartTime(schoolStart);
		workExperience.setEndTime(schoolEnd);
		workExperience.setCompanyName(companyName);
		workExperience.setPosition(position);
		workExperienceService.updateWorkExperience(workExperience);
		return "redirect:" + UrlPath.WORK_EXPERIENCE;
	}

	/**
	 *	学歴削除
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteWorkExperience/{id}")
	public String deleteEducationalBackground(@PathVariable("id") int id) {
		workExperienceService.deleteWorkExperience(id);
		return "redirect:" + UrlPath.WORK_EXPERIENCE;
	}

}
