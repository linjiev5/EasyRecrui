package jp.easyrecrui.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginHandlerInterceptor())
				.addPathPatterns("/login/**", "/saveCompanyInfo", "/addPicture", "/changePicture/**",
						"/deletePicture/**", "/changeEducationalBackground/**", "/addEducationalBackground",
						"/changeEducationalBackground", "/deleteEducationalBackground/**", "/changeNotice/**",
						"/updateNotice", "/deleteNotice/**", "/changeRecrui/**", "/addRegular", "/addPart",
						"/changeRecruiInfo", "/deleteRecrui/**", "/updateProfile", "/uploadResumeImg", "/sendResume/**",
						"/accept/**", "/refuse/**", "/uploadImg", "/saveUserInfo", "/savePassword", "/withdrawal",
						"/changeWorkExperience/**", "/addWorkExperience", "/changeWorkExperience",
						"/deleteWorkExperience/**", "/changePassword", "/refuseUser/**")
				.excludePathPatterns(UrlPath.JOB_PAGE, UrlPath.LOGIN, UrlPath.INDEX_VIEW, UrlPath.REGIST_VIEW,
						"../js/**", "../img/**", "../css/**");
	}
}
