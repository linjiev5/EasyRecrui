package jp.easyrecrui.controller;

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

import jp.easyrecrui.object.ONotice;
import jp.easyrecrui.service.NoticeService;

@Controller
public class NoticeController {
	@Autowired
	NoticeService noticeService;
	@Autowired
	HttpSession session;

	/**
	 * メイン画面に表示するお知らせ
	 * @param redirectAttributes
	 * @param model
	 * @return
	 */
	@RequestMapping("/login/notice")
	public ModelAndView noticeView(RedirectAttributes redirectAttributes, Model model) {
		ModelAndView mav = new ModelAndView("admin/notice");
		ArrayList<ONotice> notice = noticeService.getAllNotice();
		mav.addObject("notice", notice);
		return mav;
	}

	/**
	 *
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/changeNotice/{id}")
	public String changeNoticeByid(@PathVariable("id") Integer id, Model model) {
		ONotice notice = noticeService.getNotice(id);
		model.addAttribute("notice", notice);
		return "admin/updateNotice";
	}

	/**
	 *
	 * @param id
	 * @param title
	 * @param notice
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/updateNotice")
	public String changeNotice(@RequestParam(name = "title") String title,
			@RequestParam(name = "notice") String notice, RedirectAttributes redirectAttributes) {
		ONotice oNotice = new ONotice();
		oNotice.setTitle(title);
		oNotice.setNotice(notice);
		String msg = noticeService.updateNotice(oNotice);
		redirectAttributes.addFlashAttribute("message", msg);
		return "redirect:/login/notice";
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteNotice/{id}")
	public String deleteNotice(@PathVariable("id") Integer id) {
		noticeService.deleteById(id);
		return "redirect:/login/notice";
	}
}
