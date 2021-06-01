package jp.easyrecrui.service;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.easyrecrui.entity.EducationalBackground;
import jp.easyrecrui.entity.ResumeInfo;
import jp.easyrecrui.entity.UserInfo;
import jp.easyrecrui.entity.WorkExperience;
import jp.easyrecrui.object.OEducationalBackground;
import jp.easyrecrui.object.OResumeInfo;
import jp.easyrecrui.object.OWorkExperience;
import jp.easyrecrui.object.ResumeView;
import jp.easyrecrui.repository.EducationalBackgroundRepository;
import jp.easyrecrui.repository.ResumeInfoRepository;
import jp.easyrecrui.repository.UserInfoRepository;
import jp.easyrecrui.repository.WorkExperienceRepository;
import jp.easyrecrui.utils.EntityToObj;
import jp.easyrecrui.utils.TimeDeal;

@Service
@Transactional
public class ResumeInfoService {

	@Autowired
	ResumeInfoRepository resumeInfoRepository;
	@Autowired
	UserInfoRepository userInfoRepository;
	@Autowired
	EducationalBackgroundRepository educationalBackgroundRepository;
	@Autowired
	WorkExperienceRepository workExperienceRepository;

	/**
	 *履歴書のデータをすべて取得
	 * @param uid
	 * @return
	 */
	public ResumeView getResume(int uid) {
		ResumeInfo resumeInfo = resumeInfoRepository.findByUid(uid);
		ArrayList<EducationalBackground> educationalBackground = educationalBackgroundRepository
				.findByUidOrderByEducationalIdAsc(uid);
		ArrayList<WorkExperience> workExperience = workExperienceRepository.findByUidOrderByWorkIdAsc(uid);
		if (resumeInfo != null) {
			ResumeView resume = new ResumeView();
			resume = EntityToObj.toOResumeInfo(resumeInfo);
			if (educationalBackground.size() > 0) {
				ArrayList<OEducationalBackground> oebList = EntityToObj.setEducationalBackground(educationalBackground);
				resume.setEducationalBackground(oebList);
			}
			if (workExperience.size() > 0) {
				ArrayList<OWorkExperience> owe = EntityToObj.setWorkExperience(workExperience);
				resume.setWorkExperience(owe);
			}
			UserInfo userInfo = userInfoRepository.findByUid(uid);
			resume.setName(userInfo.getName());
			resume.setSex(userInfo.getSex());
			resume.setTel(userInfo.getTel());
			resume.setMail(userInfo.getMail());
			resume.setBirthday(userInfo.getBirthday());
			resume.setAdress(userInfo.getAdress());
			return resume;
		} else {
			return null;
		}
	}

	/**
	 * 履歴の基本情報を保存、変更する
	 * @param uid
	 * @param oResume
	 * @return
	 */
	public String updateProfile(int uid, OResumeInfo oResume) {
		ResumeInfo resume = resumeInfoRepository.findByUid(uid);
		resume.setNameKatakana(oResume.getNameKatakana());
		resume.setNationality(oResume.getNationality());
		resume.setHobbiesAndSkills(oResume.getHobbiesAndSkills());
		resume.setLicenceOrQualification(oResume.getLicenceOrQualification());
		resume.setNearestStation(oResume.getNearestStation());
		resume.setMotivation(oResume.getMotivation());
		resume.setSelfPublicRelations(oResume.getSelfPublicRelations());
		Timestamp now = TimeDeal.nowTime();
		resume.setUpdateTime(now);
		resumeInfoRepository.saveAndFlush(resume);
		return "基本情報保存成功しました";
	}

	/**
	 * iconを更新する
	 * @param uid
	 * @param icon
	 */
	public void saveIcon(int uid, String icon) {
		ResumeInfo resume = resumeInfoRepository.findByUid(uid);
		icon = "../user/resumeIcon/" + icon;
		resume.setIcon(icon);
		Timestamp now = TimeDeal.nowTime();
		resume.setUpdateTime(now);
		resumeInfoRepository.saveAndFlush(resume);
	}

}
