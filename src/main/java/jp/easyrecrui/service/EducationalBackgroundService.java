package jp.easyrecrui.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.easyrecrui.entity.EducationalBackground;
import jp.easyrecrui.entity.ResumeInfo;
import jp.easyrecrui.object.OEducationalBackground;
import jp.easyrecrui.repository.EducationalBackgroundRepository;
import jp.easyrecrui.repository.ResumeInfoRepository;
import jp.easyrecrui.utils.EntityToObj;
import jp.easyrecrui.utils.TimeDeal;

@Service
@Transactional
public class EducationalBackgroundService {

	@Autowired
	EducationalBackgroundRepository educationalBackgroundRepository;
	@Autowired
	ResumeInfoRepository resumeInfoRepository;

	/**
	 * 学歴データを取得する
	 * @param oEducationalBackground
	 * @return
	 */
	public ArrayList<OEducationalBackground> getEducationalBackground(int uid) {
		ArrayList<EducationalBackground> educationalBackground = educationalBackgroundRepository
				.findByUidOrderByEducationalIdAsc(uid);
		if (educationalBackground.size() > 0) {
			ArrayList<OEducationalBackground> oebList = EntityToObj.setEducationalBackground(educationalBackground);
			return oebList;
		} else {
			return null;
		}
	}

	/**
	 *	学歴データをIdで取得する
	 * @param id
	 * @return
	 */
	public OEducationalBackground findOneEducationalBackground(int id) {
		Optional<EducationalBackground> educationalBackgroun = educationalBackgroundRepository
				.findById(id);
		EducationalBackground eb = educationalBackgroun.get();
		OEducationalBackground oEducationalBackground = new OEducationalBackground();
		oEducationalBackground.setStartTime(eb.getStartTime());
		oEducationalBackground.setEduId(eb.getEducationalId());
		oEducationalBackground.setEndTime(eb.getEndTime());
		oEducationalBackground.setSchoolName(eb.getSchoolName());
		return oEducationalBackground;
	}

	/**
	 * 学歴を削除する
	 * @param id
	 */
	public void deleteEducationalBackground(int id) {
		educationalBackgroundRepository.deleteById(id);
	}

	/**
	 * 学歴を更新する
	 * @param userName
	 * @param educationalBackground
	 * @return
	 */
	public void updateEducationalBackground(OEducationalBackground oEducationalBackground) {
		EducationalBackground educationalBackground = new EducationalBackground();
		if (oEducationalBackground.getEduId() != 0) {
			educationalBackground.setEducationalId(oEducationalBackground.getEduId());
		}
		educationalBackground.setUid(oEducationalBackground.getUid());
		educationalBackground.setStartTime(oEducationalBackground.getStartTime());
		educationalBackground.setEndTime(oEducationalBackground.getEndTime());
		educationalBackground.setSchoolName(oEducationalBackground.getSchoolName());
		educationalBackgroundRepository.saveAndFlush(educationalBackground);
		Timestamp now = TimeDeal.nowTime();
		ResumeInfo resumeInfo = resumeInfoRepository.findByUid(oEducationalBackground.getUid());
		resumeInfo.setUpdateTime(now);
		resumeInfoRepository.saveAndFlush(resumeInfo);
	}
}
