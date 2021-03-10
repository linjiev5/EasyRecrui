package jp.easyrecrui.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginHandlerInterceptor())
		.addPathPatterns(UrlPath.MYPAGE_VIEW,UrlPath.UPDATE_APPLY_USER,UrlPath.APPLY_USER_RESUME).excludePathPatterns(UrlPath.JOBPAGE_VIEW,UrlPath.LOGIN,UrlPath.MAIN_VIEW,UrlPath.REGIST_VIEW,"../js/**","../img/**","../css/**");
	}
}
