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

import jp.easyrecrui.object.OEducationalBackground;
import jp.easyrecrui.object.UserRole;
import jp.easyrecrui.service.EducationalBackgroundService;
import jp.easyrecrui.service.ResumeInfoService;
import jp.easyrecrui.utils.UrlPath;

@Controller
public class EducationalBackgroundController {

	@Autowired
	EducationalBackgroundService educationalBackgroundService;
	@Autowired
	ResumeInfoService resumeInfoService;
	@Autowired
	HttpSession session;

	/**
	 *学歴画面
	 * @return
	 */
	@RequestMapping(UrlPath.EDUCATIONAL_BACKGROUND)
	public ModelAndView eduPage() {
		ModelAndView mav = new ModelAndView("user/educationalBackground");// テンプレートHTML指定
		UserRole userRole = (UserRole) session.getAttribute("userRole");
		ArrayList<OEducationalBackground> oEducationalBackgrounds = educationalBackgroundService
				.getEducationalBackground(userRole.getUid());
		mav.addObject("educationalBackground", oEducationalBackgrounds);
		return mav;
	}

	/**
	 * 学歴編集
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/changeEducationalBackground/{id}")
	public String editEducationalBackground(@PathVariable("id") Integer id, Model model) {
		OEducationalBackground educationalBackground = educationalBackgroundService.findOneEducationalBackground(id);
		model.addAttribute("educationalBackground", educationalBackground);
		return "user/updateEducationalBackground";
	}

	/**
	 * 学歴を追加する
	 * @param educationalBackground
	 * @param session
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/addEducationalBackground")
	public String addEducationalBackground(
			@RequestParam(name = "schoolStart") Date schoolStart, @RequestParam(name = "schoolEnd") Date schoolEnd,
			@RequestParam(name = "schoolName") String schoolName,
			RedirectAttributes redirectAttributes) {
		UserRole userRole = (UserRole) session.getAttribute("userRole");
		OEducationalBackground oEducationalBackground = new OEducationalBackground();
		oEducationalBackground.setUid(userRole.getUid());
		oEducationalBackground.setStartTime(schoolStart);
		oEducationalBackground.setEndTime(schoolEnd);
		oEducationalBackground.setSchoolName(schoolName);
		educationalBackgroundService.updateEducationalBackground(oEducationalBackground);
		return "redirect:" + UrlPath.EDUCATIONAL_BACKGROUND;
	}

	/**
	 *	学歴更新
	 * @param educationalBackground
	 * @param model
	 * @return
	 */
	@PostMapping("/changeEducationalBackground")
	public String changeEducationalBackground(@RequestParam(name = "id") int id,
			@RequestParam(name = "schoolStart") Date schoolStart, @RequestParam(name = "schoolEnd") Date schoolEnd,
			@RequestParam(name = "schoolName") String schoolName, Model model) {
		UserRole userRole = (UserRole) session.getAttribute("userRole");
		OEducationalBackground educationalBackground = new OEducationalBackground();
		educationalBackground.setEduId(id);
		educationalBackground.setUid(userRole.getUid());
		educationalBackground.setEduId(id);
		educationalBackground.setStartTime(schoolStart);
		educationalBackground.setEndTime(schoolEnd);
		educationalBackground.setSchoolName(schoolName);
		educationalBackgroundService.updateEducationalBackground(educationalBackground);
		return "redirect:" + UrlPath.EDUCATIONAL_BACKGROUND;
	}

	/**
	 *	学歴削除
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteEducationalBackground/{id}")
	public String deleteEducationalBackground(@PathVariable("id") int id) {
		educationalBackgroundService.deleteEducationalBackground(id);
		return "redirect:" + UrlPath.EDUCATIONAL_BACKGROUND;
	}
}
