package jp.easyrecrui.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * UrlPathを読みやすいように設置する
 * @author user
 *
 */
@Data
@AllArgsConstructor
public class UrlPath {

	public static final String INDEX_VIEW = "/index";
	public static final String MYPAGE_VIEW = "/login/mypage";
	public static final String LOGIN = "/login";
	public static final String REGIST_VIEW = "/regist";
	public static final String LOGOUT = "/logout";
	public static final String USER_RESUME = "/login/resume";
	public static final String CHANGE_PASSWORD = "/login/changePassword";
	public static final String COMPANY_INFO = "/login/companyInfo";
	public static final String RESUME_MANAGEMENT = "/login/resumeManagement";
	public static final String USER_MANAGEMENT = "/login/userManagement";
	public static final String COMPANY_MANAGEMENT = "/login/companyManagement";
	public static final String RECRUI_MANAGEMENT = "/login/recruiManagement";
	public static final String MESSAGE_VIEW = "/login/message";
	public static final String JOB_INFO = "/jobInfo";
	public static final String JOB_PAGE = "/jobPage";
	public static final String COMPANY_VIEW = "/companyView";
	public static final String COMPANY_REGIST_VIEW = "/companyRegistView";
	public static final String EDUCATIONAL_BACKGROUND = "/login/educationalBackground";
	public static final String WORK_EXPERIENCE = "/login/workExperience";
}
