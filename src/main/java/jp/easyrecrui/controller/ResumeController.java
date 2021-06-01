package jp.easyrecrui.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.easyrecrui.object.CheckUserResume;
import jp.easyrecrui.object.OResume;
import jp.easyrecrui.object.OResumeInfo;
import jp.easyrecrui.object.ResumeView;
import jp.easyrecrui.object.UserRole;
import jp.easyrecrui.service.ResumeInfoService;
import jp.easyrecrui.service.ResumeService;
import jp.easyrecrui.service.UserInfoService;
import jp.easyrecrui.utils.UrlPath;

@Controller
public class ResumeController {

	@Autowired
	ResumeInfoService resumeInfoService;
	@Autowired
	UserInfoService userInfoService;
	@Autowired
	ResumeService resumeService;
	@Autowired
	HttpSession session;

	/**
	 * My履歴書画面
	 * @return
	 */
	@RequestMapping(UrlPath.USER_RESUME)
	public String myResume(Model model, RedirectAttributes redirectAttributes) {
		UserRole userRole = (UserRole) session.getAttribute("userRole");
		ResumeView resume = resumeInfoService.getResume(userRole.getUid());
		// iconデータがない時に
		if (resume.getIcon() == null) {
			// 性別を判断する
			if (resume.getSex() == 1) {
				resume.setIcon("../user/man_icon.png");
			} else {
				resume.setIcon("../user/woman_icon.png");
			}
		}
		model.addAttribute("resume", resume);
		return "user/resume";
	}

	/**
	 * 履歴の基本情報の保存
	 * @param nameKatakana
	 * @param nationality
	 * @param educationalBackground
	 * @param hobbiesAndSkills
	 * @param licenceOrQualification
	 * @param nearestStation
	 * @param session
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/updateProfile")
	public String updateProfile(@RequestParam(name = "nameKatakana") String nameKatakana,
			@RequestParam(name = "nationality") String nationality,
			@RequestParam(name = "hobbiesAndSkills") String hobbiesAndSkills,
			@RequestParam(name = "licenceOrQualification") String licenceOrQualification,
			@RequestParam(name = "nearestStation") String nearestStation,
			@RequestParam(name = "motivation") String motivation,
			@RequestParam(name = "selfPublicRelations") String selfPublicRelations,
			RedirectAttributes redirectAttributes) {
		UserRole userRole = (UserRole) session.getAttribute("userRole");
		OResumeInfo oResume = new OResumeInfo();
		oResume.setNameKatakana(nameKatakana);
		oResume.setNationality(nationality);
		oResume.setHobbiesAndSkills(hobbiesAndSkills);
		oResume.setLicenceOrQualification(licenceOrQualification);
		oResume.setNearestStation(nearestStation);
		oResume.setMotivation(motivation);
		oResume.setSelfPublicRelations(selfPublicRelations);
		String message = resumeInfoService.updateProfile(userRole.getUid(), oResume);
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:" + UrlPath.USER_RESUME;

	}

	/**
	 * icon更新
	 * @param resumeIcon
	 * @param message
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/uploadResumeImg")
	public String changeIcon(@RequestPart("resumeIcon") MultipartFile resumeIcon, RedirectAttributes redirectAttributes)
			throws IOException {
		if (!resumeIcon.isEmpty()) {
			Timestamp createTime = new Timestamp(System.currentTimeMillis());
			UserRole userRole = (UserRole) session.getAttribute("userRole");
			String originalFilename = resumeIcon.getOriginalFilename();
			String fileType = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
			String iconName = userRole.getUid()
					+ createTime.toString().replace("-", "").replace(" ", "").replace(":", "").replace(".", "")
					+ fileType;
			resumeIcon.transferTo(
					new File("C:\\ep\\pleiades\\workspace\\EasyRecrui\\src\\main\\resources\\static\\user\\resumeIcon\\"
							+ iconName));
			resumeInfoService.saveIcon(userRole.getUid(), iconName);
			redirectAttributes.addFlashAttribute("message", "アイコンを変更しました");
		} else {
			redirectAttributes.addFlashAttribute("message", "アイコンを選択してください");
		}
		return "redirect:" + UrlPath.USER_RESUME;
	}

	/**
	 *
	 * @param recruiId
	 * @param session
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("/sendResume/{recruiId}")
	public String sendResume(@PathVariable("recruiId") int recruiId,
			RedirectAttributes redirectAttributes) {
		UserRole userRole = (UserRole) session.getAttribute("userRole");
		int uid = userRole.getUid();
		resumeService.sendResume(uid, recruiId);
		redirectAttributes.addFlashAttribute("message", "履歴書発送済み");
		return "redirect:/jobInfo/" + recruiId;

	}

	/**
	 * 出した履歴書を取得する
	 * @param session
	 * @return
	 */
	@RequestMapping("/login/mySendResume")
	public ModelAndView mySendResume() {
		ModelAndView mav = new ModelAndView("user/sendResume");// path
		UserRole userRole = (UserRole) session.getAttribute("userRole");
		ArrayList<OResume> resumes = resumeService.getSendResume(userRole.getUid());
		mav.addObject("resumes", resumes);
		return mav;
	}

	/**
	 * もらった履歴書を取得する
	 * @param session
	 * @return
	 */
	@RequestMapping("/login/receivedResume")
	public ModelAndView receivedResume() {
		ModelAndView mav = new ModelAndView("company/receivedResume");// path
		UserRole userRole = (UserRole) session.getAttribute("userRole");
		ArrayList<CheckUserResume> checkUserResume = resumeService.getCheckUserResume(userRole.getUid());
		if (checkUserResume != null) {
			for (int x = 0, y = checkUserResume.size(); x < y; x++) {
				// 応募者の履歴情報を取得する
				checkUserResume.get(x)
						.setResumeView(resumeInfoService.getResume(checkUserResume.get(x).getSendUid()));
			}
			mav.addObject("resumes", checkUserResume);
		}
		return mav;
	}

	/**
	 * 求人を許可する
	 * @param resumeId
	 * @param model
	 * @return
	 */
	@RequestMapping("/accept/{id}")
	public String acceptUser(@PathVariable("id") int resumeId, Model model) {
		resumeService.setResult(resumeId, "ok");
		return "redirect:/login/receivedResume";
	}

	/**
	 * 求人を拒否する
	 * @param resumeId
	 * @param model
	 * @return
	 */
	@RequestMapping("/refuse/{id}")
	public String refuseUser(@PathVariable("id") int resumeId, Model model) {
		resumeService.setResult(resumeId, "no");
		return "redirect:/login/receivedResume";
	}
}
