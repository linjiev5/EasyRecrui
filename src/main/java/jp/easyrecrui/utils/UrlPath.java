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
	public static final String UPDATE_USER_INFO = "/login/updateUserInfo";
	public static final String JOBPAGE_VIEW = "/main/jobpage";
	public static final String LOGIN = "/login";
	public static final String REGIST_VIEW = "/regist";
	public static final String LOGOUT = "/logout";
	public static final String USER_RESUME ="/resume";


}
