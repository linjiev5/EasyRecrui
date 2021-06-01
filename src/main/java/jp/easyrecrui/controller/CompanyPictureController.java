package jp.easyrecrui.controller;

import java.io.File;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.easyrecrui.object.OCompanyPicture;
import jp.easyrecrui.object.UserRole;
import jp.easyrecrui.service.CompanyPictureService;

@Controller
public class CompanyPictureController {

	@Autowired
	CompanyPictureService companyPictureService;
	@Autowired
	HttpSession session;

	/**
	 * 複数写真追加
	 * @param icon
	 * @param redirectAttributes
	 * @param session
	 * @return
	 * @throws Exception
	 * @throws IOException
	 */
	@PostMapping("/addPicture")
	public String addPicture(@RequestPart(name = "icon") MultipartFile[] icon, RedirectAttributes redirectAttributes)
			throws Exception, IOException {
		if (icon.length > 0) {
			ArrayList<String> pics = new ArrayList<String>();
			Timestamp createTime = new Timestamp(System.currentTimeMillis());
			UserRole userRole = (UserRole) session.getAttribute("userRole");
			for (int x = 0, y = icon.length; x < y; x++) {
				if (!icon[x].isEmpty()) {
					String originalFilename = icon[x].getOriginalFilename();
					String fileType = originalFilename.substring(originalFilename.lastIndexOf("."),
							originalFilename.length());
					String iconName = userRole.getUid()
							+ createTime.toString().replace("-", "").replace(" ", "").replace(":", "").replace(".", "")
							+ x
							+ fileType;
					icon[x].transferTo(
							new File(
									"C:\\ep\\pleiades\\workspace\\EasyRecrui\\src\\main\\resources\\static\\company\\picture\\"
											+ iconName));
					pics.add(iconName);
				}
				companyPictureService.savePictures(userRole.getUid(), pics);
				redirectAttributes.addFlashAttribute("message", "アイコンを追加しました");
			}
		} else {
			redirectAttributes.addFlashAttribute("message", "アイコンを選択してください");
		}
		return "redirect:/login/companyPicture";
	}

	/**
	 * 写真編集
	 * @param id
	 * @param model
	 * @return
	 */
	@PostMapping("/changePicture/{id}")
	public String changePocture(@RequestPart(name = "icon") MultipartFile icon, @PathVariable("id") Integer id,
			Model model) throws IOException {
		if (!icon.isEmpty()) {
			Timestamp createTime = new Timestamp(System.currentTimeMillis());
			UserRole userRole = (UserRole) session.getAttribute("userRole");
			String originalFilename = icon.getOriginalFilename();
			String fileType = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
			String iconName = userRole.getUid()
					+ createTime.toString().replace("-", "").replace(" ", "").replace(":", "").replace(".", "")
					+ fileType;
			icon.transferTo(
					new File("C:\\ep\\pleiades\\workspace\\EasyRecrui\\src\\main\\resources\\static\\company\\picture\\"
							+ iconName));
			String img = companyPictureService.getIcon(id);
			String imgPath = "C:\\ep\\pleiades\\workspace\\EasyRecrui\\src\\main\\resources\\static\\company\\picture\\"
					+ img.substring(19);
			File file = new File(imgPath);
			if (file.exists()) {
				file.delete();
			}
			companyPictureService.savePicturesById(id, iconName);
		}
		return "redirect:/login/companyPicture";
	}

	@GetMapping("/deletePicture/{id}")
	public String deletePicture(@PathVariable("id") Integer id) {
		String img = companyPictureService.getIcon(id);
		String imgPath = "C:\\ep\\pleiades\\workspace\\EasyRecrui\\src\\main\\resources\\static\\company\\picture\\"
				+ img.substring(19);
		companyPictureService.deletePicture(id);
		File file = new File(imgPath);
		if (file.exists()) {
			file.delete();
		}
		return "redirect:/login/companyPicture";

	}

	/**
	 * 写真画面
	 * @param session
	 * @return
	 */
	@RequestMapping("/login/companyPicture")
	public ModelAndView companyPicture() {
		ModelAndView mav = new ModelAndView("company/picture");// テンプレートHTML指定
		UserRole userRole = (UserRole) session.getAttribute("userRole");
		ArrayList<OCompanyPicture> pictures = companyPictureService.getPicture(userRole.getUid());
		mav.addObject("pictures", pictures);
		return mav;
	}
}
