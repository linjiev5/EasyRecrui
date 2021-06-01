package jp.easyrecrui.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.easyrecrui.entity.ResumeInfo;
import jp.easyrecrui.entity.WorkExperience;
import jp.easyrecrui.object.OWorkExperience;
import jp.easyrecrui.repository.ResumeInfoRepository;
import jp.easyrecrui.repository.WorkExperienceRepository;
import jp.easyrecrui.utils.EntityToObj;
import jp.easyrecrui.utils.TimeDeal;

@Service
@Transactional
public class WorkExperienceService {
	@Autowired
	WorkExperienceRepository workExperienceRepository;
	@Autowired
	ResumeInfoRepository resumeInfoRepository;

	/**
	 * 職歴を更新する
	 * @param userName
	 * @param workExperience
	 * @return
	 */
	public void updateWorkExperience(OWorkExperience oWorkExperience) {
		WorkExperience workExperience = new WorkExperience();
		if (oWorkExperience.getWorkId() != 0) {
			workExperience.setWorkId(oWorkExperience.getWorkId());
		}
		workExperience.setUid(oWorkExperience.getUid());
		workExperience.setStartTime(oWorkExperience.getStartTime());
		workExperience.setEndTime(oWorkExperience.getEndTime());
		workExperience.setConpanyName(oWorkExperience.getCompanyName());
		workExperience.setPosition(oWorkExperience.getPosition());
		workExperienceRepository.saveAndFlush(workExperience);
		ResumeInfo resumeInfo = resumeInfoRepository.findByUid(oWorkExperience.getUid());
		Timestamp now = TimeDeal.nowTime();
		resumeInfo.setUpdateTime(now);
		resumeInfoRepository.saveAndFlush(resumeInfo);
	}

	/**
	 * workIdで職歴取得する
	 * @param id
	 * @return
	 */
	public OWorkExperience findOneWorkExperience(int id) {
		Optional<WorkExperience> workExperience = workExperienceRepository.findById(id);
		WorkExperience we = workExperience.get();
		OWorkExperience oWorkExperience = new OWorkExperience();
		oWorkExperience.setStartTime(we.getStartTime());
		oWorkExperience.setWorkId(we.getWorkId());
		oWorkExperience.setEndTime(we.getEndTime());
		oWorkExperience.setCompanyName(we.getConpanyName());
		oWorkExperience.setPosition(we.getPosition());
		return oWorkExperience;
	}

	/**
	 * 職歴データを取得する
	 * @param oWorkExperience
	 * @return
	 */
	public ArrayList<OWorkExperience> getWorkExperience(int uid) {
		ArrayList<WorkExperience> workExperience = workExperienceRepository
				.findByUidOrderByWorkIdAsc(uid);
		if (workExperience.size() > 0) {
			ArrayList<OWorkExperience> owe = EntityToObj.setWorkExperience(workExperience);
			return owe;
		} else {
			return null;
		}
	}

	/**
	 * 職歴を削除する
	 * @param id
	 */
	public void deleteWorkExperience(int id) {
		workExperienceRepository.deleteById(id);
	}
}
