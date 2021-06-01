package jp.easyrecrui.utils;

import java.util.ArrayList;

import jp.easyrecrui.entity.CompanyInfo;
import jp.easyrecrui.entity.EducationalBackground;
import jp.easyrecrui.entity.RecruiInfo;
import jp.easyrecrui.entity.ResumeInfo;
import jp.easyrecrui.entity.UserInfo;
import jp.easyrecrui.entity.WorkExperience;
import jp.easyrecrui.object.OCompanyInfo;
import jp.easyrecrui.object.OEducationalBackground;
import jp.easyrecrui.object.ORecruiInfo;
import jp.easyrecrui.object.OUserInfo;
import jp.easyrecrui.object.OWorkExperience;
import jp.easyrecrui.object.ResumeView;

public class EntityToObj {
	/**
	 * userInfoToOuserInfo
	 * ユーザデータを取得するときに使う
	 * @param userInfo
	 * @return
	 */
	public static OUserInfo toOUserInfo(UserInfo userInfo) {
		OUserInfo oUserInfo = new OUserInfo();
		oUserInfo.setUid(userInfo.getUid());
		oUserInfo.setName(userInfo.getName());
		oUserInfo.setIcon(userInfo.getIcon());
		oUserInfo.setMail(userInfo.getMail());
		oUserInfo.setTel(userInfo.getTel());
		oUserInfo.setSex(userInfo.getSex());
		oUserInfo.setAdress(userInfo.getAdress());
		oUserInfo.setBirthday(userInfo.getBirthday());
		oUserInfo.setUpdateTime(userInfo.getUpdateTime());
		return oUserInfo;
	}

	/**
	 * 履歴データを取得
	 * @param resumeInfo
	 * @return
	 */
	public static ResumeView toOResumeInfo(ResumeInfo resumeInfo) {
		ResumeView resume = new ResumeView();
		resume.setUid(resumeInfo.getUid());
		resume.setIcon(resumeInfo.getIcon());
		resume.setNationality(resumeInfo.getNationality());
		resume.setNameKatakana(resumeInfo.getNameKatakana());
		resume.setNearestStation(resumeInfo.getNearestStation());
		resume.setMotivation(resumeInfo.getMotivation());
		resume.setSelfPublicRelations(resumeInfo.getSelfPublicRelations());
		resume.setHobbiesAndSkills(resumeInfo.getHobbiesAndSkills());
		resume.setLicenceOrQualification(resumeInfo.getLicenceOrQualification());
		resume.setUpdateTime(resumeInfo.getUpdateTime());
		return resume;
	}

	/**
	 * 学歴データを取得する
	 * @param eb
	 * @return
	 */
	public static ArrayList<OEducationalBackground> setEducationalBackground(ArrayList<EducationalBackground> eb) {
		ArrayList<OEducationalBackground> educationalBackgrounds = new ArrayList<OEducationalBackground>();
		for (int x = 0, y = eb.size(); x < y; x++) {
			OEducationalBackground oEducationalBackground = new OEducationalBackground();
			oEducationalBackground.setEduId(eb.get(x).getEducationalId());
			oEducationalBackground.setUid(eb.get(x).getUid());
			oEducationalBackground.setStartTime(eb.get(x).getStartTime());
			oEducationalBackground.setEndTime(eb.get(x).getEndTime());
			oEducationalBackground.setSchoolName(eb.get(x).getSchoolName());
			educationalBackgrounds.add(x, oEducationalBackground);
		}
		return educationalBackgrounds;
	}

	/**
	 * 職歴データを取得する
	 * @param we
	 * @return
	 */
	public static ArrayList<OWorkExperience> setWorkExperience(ArrayList<WorkExperience> we) {
		ArrayList<OWorkExperience> workExperiences = new ArrayList<OWorkExperience>();
		for (int x = 0, y = we.size(); x < y; x++) {
			OWorkExperience oWorkExperience = new OWorkExperience();
			oWorkExperience.setWorkId(we.get(x).getWorkId());
			oWorkExperience.setUid(we.get(x).getUid());
			oWorkExperience.setStartTime(we.get(x).getStartTime());
			oWorkExperience.setEndTime(we.get(x).getEndTime());
			oWorkExperience.setCompanyName(we.get(x).getConpanyName());
			oWorkExperience.setPosition(we.get(x).getPosition());
			workExperiences.add(x, oWorkExperience);
		}
		return workExperiences;
	}

	/**
	 * 会社情報を取得する
	 * @param companyInfo
	 * @return
	 */
	public static OCompanyInfo toOCompanyInfo(CompanyInfo companyInfo) {
		OCompanyInfo oCompanyInfo = new OCompanyInfo();
		oCompanyInfo.setUid(companyInfo.getUid());
		oCompanyInfo.setCompanyName(companyInfo.getCompanyName());
		oCompanyInfo.setMail(companyInfo.getMail());
		oCompanyInfo.setTel(companyInfo.getTel());
		oCompanyInfo.setInfo(companyInfo.getInfo());
		oCompanyInfo.setFoundedTime(companyInfo.getFoundedTime());
		oCompanyInfo.setIcon(companyInfo.getIcon());
		oCompanyInfo.setAdress(companyInfo.getAdress());
		oCompanyInfo.setUpdateTime(companyInfo.getUpdateTime());
		return oCompanyInfo;
	}

	/**
	 * 募集情報を取得する
	 * @param recruiInfos
	 * @return
	 */
	public static ArrayList<ORecruiInfo> setORecruiInfos(ArrayList<RecruiInfo> recruiInfos) {
		ArrayList<ORecruiInfo> oRecruiInfos = new ArrayList<ORecruiInfo>();
		for (int x = 0, y = recruiInfos.size(); x < y; x++) {
			ORecruiInfo oRecruiInfo = new ORecruiInfo();
			oRecruiInfo.setUid(recruiInfos.get(x).getUid());
			oRecruiInfo.setRecruiId(recruiInfos.get(x).getRecruiId());
			oRecruiInfo.setEmploymentMethod(recruiInfos.get(x).getEmploymentMethod());
			oRecruiInfo.setCategory(recruiInfos.get(x).getCategory());
			oRecruiInfo.setTitle(recruiInfos.get(x).getTitle());
			oRecruiInfo.setSalary(recruiInfos.get(x).getSalary());
			oRecruiInfo.setWelfare(recruiInfos.get(x).getWelfare());
			oRecruiInfo.setStation(recruiInfos.get(x).getStation());
			oRecruiInfo.setJobDetails(recruiInfos.get(x).getJobDetails());
			oRecruiInfo.setUpdateTime(recruiInfos.get(x).getUpdateTime());
			oRecruiInfos.add(oRecruiInfo);
		}
		return oRecruiInfos;
	}

	/**
	 * idで一つの募集情報を取得する
	 * @param rc
	 * @return
	 */
	public static ORecruiInfo setORecruiInfo(RecruiInfo rc) {
		ORecruiInfo oRecruiInfo = new ORecruiInfo();
		oRecruiInfo.setUid(rc.getUid());
		oRecruiInfo.setRecruiId(rc.getRecruiId());
		oRecruiInfo.setEmploymentMethod(rc.getEmploymentMethod());
		oRecruiInfo.setCategory(rc.getCategory());
		oRecruiInfo.setTitle(rc.getTitle());
		oRecruiInfo.setSalary(rc.getSalary());
		oRecruiInfo.setWelfare(rc.getWelfare());
		oRecruiInfo.setStation(rc.getStation());
		oRecruiInfo.setJobDetails(rc.getJobDetails());
		oRecruiInfo.setUpdateTime(rc.getUpdateTime());
		return oRecruiInfo;
	}

}
