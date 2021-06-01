package jp.easyrecrui.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jp.easyrecrui.object.CompanyPageInfo;
import jp.easyrecrui.object.OCompanyInfo;
import jp.easyrecrui.object.UserRole;
import jp.easyrecrui.service.CompanyInfoService;
import jp.easyrecrui.service.CompanyPictureService;
import jp.easyrecrui.utils.CodeType;
import jp.easyrecrui.utils.Md5Code;
import jp.easyrecrui.utils.UrlPath;

@Controller
public class CompanyController {

	@Autowired
	CompanyInfoService companyInfoService;
	@Autowired
	CompanyPictureService companyPictureService;
	@Autowired
	HttpSession session;

	/**
	 * 会社情報画面
	 * @return
	 */
	@RequestMapping(UrlPath.COMPANY_INFO)
	public ModelAndView companyPage() {
		ModelAndView mav = new ModelAndView("company/companyPage");// テンプレートHTML指定
		UserRole userRole = (UserRole) session.getAttribute("userRole");
		CompanyPageInfo companyPageInfo = companyInfoService.getCompanyInfo(userRole.getUid());
		if (companyPageInfo.getIcon() == null) {
			companyPageInfo.setIcon("../user/default.jpg");
		}
		mav.addObject("companyPageInfo", companyPageInfo);
		return mav;
	}

	/**
	 * 会社情報保存する
	 * @param userName
	 * @param companyName
	 * @param mail
	 * @param tel
	 * @param foundedTime
	 * @param adress
	 * @param nearestStation
	 * @param info
	 * @param redirectAttributes
	 * @param session
	 * @return
	 */
	@PostMapping("/saveCompanyInfo")
	public String saveUserInfo(@RequestParam(name = "uid") int uid,
			@RequestParam(name = "companyName") String companyName,
			@RequestParam(name = "mail") String mail, @RequestParam(name = "tel") String tel,
			@RequestParam(name = "foundedTime") Date foundedTime, @RequestParam(name = "adress") String adress,
			@RequestParam(name = "info") String info,
			RedirectAttributes redirectAttributes) {
		OCompanyInfo oCompanyInfo = new OCompanyInfo();
		oCompanyInfo.setUid(uid);
		oCompanyInfo.setCompanyName(companyName);
		oCompanyInfo.setMail(mail);
		oCompanyInfo.setTel(tel);
		oCompanyInfo.setFoundedTime(foundedTime);
		oCompanyInfo.setAdress(adress);
		oCompanyInfo.setInfo(info);
		String msg = companyInfoService.updateCompanyInfo(oCompanyInfo);
		redirectAttributes.addFlashAttribute("message", msg);
		return "redirect:" + UrlPath.COMPANY_INFO;
	}

	/**
	 * 会社登録画面
	 * @return
	 */
	@RequestMapping(UrlPath.COMPANY_REGIST_VIEW)
	public ModelAndView companyLogin() {
		ModelAndView mav = new ModelAndView("company/companyRegist");// テンプレートHTML指定
		return mav;
	}

	/**
	 * 新規登録
	 * @param userName
	 * @param password
	 * @param companyName
	 * @param mail
	 * @param tel
	 * @param adress
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("/companyRegist")
	public String companyRegist(@RequestParam(name = "userName") String userName,
			@RequestParam(name = "password") String password, @RequestParam(name = "companyName") String companyName,
			@RequestParam(name = "mail") String mail, @RequestParam(name = "tel") String tel,
			@RequestParam(name = "adress") String adress, RedirectAttributes redirectAttributes) {
		password = Md5Code.md5Code(password);
		CompanyPageInfo companyPageInfo = new CompanyPageInfo();
		companyPageInfo.setPassword(password);
		companyPageInfo.setCompanyName(companyName);
		companyPageInfo.setMail(mail);
		companyPageInfo.setTel(tel);
		companyPageInfo.setAdress(adress);
		companyPageInfo.setRole("company");
		int registResult = companyInfoService.addCompanyUser(userName, companyPageInfo);
		if (registResult == CodeType.REGIST_SUCESS.getCode()) {
			redirectAttributes.addFlashAttribute("message", "会社" + CodeType.REGIST_SUCESS.getMessage());
		} else if (registResult == CodeType.USER_EXIST.getCode()) {
			redirectAttributes.addFlashAttribute("message", CodeType.USER_EXIST.getMessage());
		}
		return "redirect:" + UrlPath.LOGIN;
	}

	/**
	 * icon変更
	 * @param icon
	 * @param redirectAttributes
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@PostMapping("/uploadCompanyImg")
	public String changeIcon(@RequestPart("icon") MultipartFile icon, RedirectAttributes redirectAttributes,
			HttpSession session) throws IOException {
		if (!icon.isEmpty()) {
			Timestamp createTime = new Timestamp(System.currentTimeMillis());
			UserRole userRole = (UserRole) session.getAttribute("userRole");
			String originalFilename = icon.getOriginalFilename();
			String fileType = originalFilename.substring(originalFilename.lastIndexOf("."), originalFilename.length());
			String iconName = userRole.getUid()
					+ createTime.toString().replace("-", "").replace(" ", "").replace(":", "").replace(".", "")
					+ fileType;
			icon.transferTo(
					new File("C:\\ep\\pleiades\\workspace\\EasyRecrui\\src\\main\\resources\\static\\company\\icon\\"
							+ iconName));
			String img = companyInfoService.getIcon(userRole.getUid());
			String imgPath = "C:\\ep\\pleiades\\workspace\\EasyRecrui\\src\\main\\resources\\static\\company\\icon\\"
					+ img;
			if (iconName != null) {
				imgPath = imgPath.substring(16);
			}
			File file = new File(imgPath);
			if (file.exists()) {
				file.delete();
			}
			companyInfoService.saveIcon(userRole.getUid(), iconName);
			redirectAttributes.addFlashAttribute("message", "アイコンを変更しました");
		} else {
			redirectAttributes.addFlashAttribute("message", "アイコンを選択してください");
		}
		return "redirect:" + UrlPath.COMPANY_INFO;
	}

}
