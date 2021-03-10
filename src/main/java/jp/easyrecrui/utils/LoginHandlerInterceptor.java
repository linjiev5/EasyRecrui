package jp.easyrecrui.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
/**
 *  ログイン状態を確認する
 * @author user
 *
 */
public class LoginHandlerInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// ログイン成功したら、ユーザのセッションを保存する
		Object loginUser = request.getSession().getAttribute("loginUser");

		if(loginUser == null) {// ログインしてない場合
			request.setAttribute("message", CodeType.USER_NOT_LOGIN.getMessage());
			request.getRequestDispatcher(UrlPath.LOGIN).forward(request, response);
			return false;
		}else {
			return true;
		}
	}
}
