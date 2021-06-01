package jp.easyrecrui.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.easyrecrui.object.OMessage;
import jp.easyrecrui.service.MessageToOperationService;
import jp.easyrecrui.utils.Md5Code;

@Controller
public class ResetPasswordRequestController {
	@Autowired
	HttpSession session;
	@Autowired
	MessageToOperationService messageToOperationService;

	/**
	 * メッセージ画面
	 * @param redirectAttributes
	 * @param model
	 * @return
	 */
	@RequestMapping("/login/message")
	public ModelAndView messageView(RedirectAttributes redirectAttributes) {
		ModelAndView mav = new ModelAndView("admin/message");
		int messageCount = messageToOperationService.getNotReadMessageCount();
		mav.addObject("messageCount", messageCount);
		ArrayList<OMessage> messages = messageToOperationService.getAllMessage();
		mav.addObject("messages", messages);
		return mav;
	}

	/**
	 * パスワードを変更する
	 * @param uid
	 * @param password
	 * @return
	 */
	@PostMapping("/changePassword")
	public String changePassword(@RequestParam(name = "messageId") int messageId, @RequestParam(name = "uid") int uid,
			@RequestParam(name = "password") String password, RedirectAttributes redirectAttributes) {
		String nPassword = Md5Code.md5Code(password);
		String result = password + " に" + messageToOperationService.changePassword(messageId, uid, nPassword);
		redirectAttributes.addFlashAttribute("message", result);
		return "redirect:/login/message";
	}

	/**
	 * パスワード変更するリクエストを拒否する
	 * @param id
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping("/refuseUser/{id}")
	public String refuseUser(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
		String result = messageToOperationService.setRead(id);
		redirectAttributes.addFlashAttribute("message", result);
		return "redirect:/login/message";
	}

}
