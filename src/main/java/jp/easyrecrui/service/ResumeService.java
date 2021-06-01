package jp.easyrecrui.service;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.easyrecrui.entity.Resume;
import jp.easyrecrui.object.CheckUserResume;
import jp.easyrecrui.object.OResume;
import jp.easyrecrui.repository.CompanyInfoRepository;
import jp.easyrecrui.repository.RecruiInfoRepository;
import jp.easyrecrui.repository.ResumeRepository;
import jp.easyrecrui.repository.UserInfoRepository;

@Service
@Transactional
public class ResumeService {

	@Autowired
	ResumeRepository resumeRepository;
	@Autowired
	RecruiInfoRepository recruiInfoRepository;
	@Autowired
	CompanyInfoRepository companyInfoRepository;
	@Autowired
	UserInfoRepository userInfoRepository;

	/**
	 *
	 * @param uid
	 * @param companyUid
	 */
	public void sendResume(int uid, int recruiId) {
		Resume resume = new Resume();
		resume.setRecruiId(recruiId);
		resume.setSendUid(uid);
		int companyUid = recruiInfoRepository.findById(recruiId).get().getUid();
		resume.setRecevieUid(companyUid);
		Timestamp now = new Timestamp(System.currentTimeMillis());
		resume.setUpdateTime(now);
		resumeRepository.saveAndFlush(resume);
	}

	/**
	 * 履歴書出す記録を取得する
	 * @param sendUid
	 * @param recruiId
	 * @return
	 */
	public OResume getResume(int sendUid, int recruiId) {
		Resume resume = resumeRepository.findBySendUidAndRecruiId(sendUid, recruiId);
		if (resume == null) {
			return null;
		} else {
			OResume oResume = new OResume();
			oResume.setRecevieUid(resume.getRecevieUid());
			oResume.setRecruiId(resume.getRecruiId());
			oResume.setResult(resume.getResult());
			oResume.setResumeId(resume.getResumeId());
			oResume.setSendUid(resume.getSendUid());
			oResume.setUpdateTime(resume.getUpdateTime());
			return oResume;
		}
	}

	/**
	 * 出した履歴書を取得する
	 * @param uid
	 * @return
	 */
	public ArrayList<OResume> getSendResume(int uid) {
		ArrayList<OResume> oResumes = new ArrayList<OResume>();
		ArrayList<Resume> resumes = resumeRepository.findBySendUidOrderByResumeIdDesc(uid);
		if (resumes.size() > 0) {
			for (int x = 0, y = resumes.size(); x < y; x++) {
				OResume oResume = new OResume();
				oResume.setRecevieUid(resumes.get(x).getRecevieUid());
				// 会社uidで会社名を取得する
				oResume.setCompanyName(
						companyInfoRepository.findByUid(resumes.get(x).getRecevieUid()).getCompanyName());
				// タイトルを取得する
				oResume.setTitle(recruiInfoRepository.findById(resumes.get(x).getRecruiId()).get().getTitle());
				oResume.setRecruiId(resumes.get(x).getRecruiId());
				oResume.setResult(resumes.get(x).getResult());
				oResume.setResumeId(resumes.get(x).getResumeId());
				oResume.setSendUid(resumes.get(x).getSendUid());
				oResume.setUpdateTime(resumes.get(x).getUpdateTime());
				oResumes.add(oResume);
			}
			return oResumes;
		} else {
			return null;
		}
	}

	/**
	 * もらった履歴書を取得する
	 * @param uid
	 * @return
	 */
	public ArrayList<CheckUserResume> getCheckUserResume(int recevieUid) {
		ArrayList<CheckUserResume> CheckUserResumes = new ArrayList<CheckUserResume>();
		ArrayList<Resume> resumes = resumeRepository.findByRecevieUidOrderByResumeIdDesc(recevieUid);
		if (resumes.size() > 0) {
			for (int x = 0, y = resumes.size(); x < y; x++) {
				CheckUserResume checkUserResume = new CheckUserResume();
				checkUserResume.setResumeId(resumes.get(x).getResumeId());
				// 応募者の名前を取得する
				checkUserResume.setName(userInfoRepository.findByUid(resumes.get(x).getSendUid()).getName());
				// タイトルを取得する
				checkUserResume.setTitle(recruiInfoRepository.findById(resumes.get(x).getRecruiId()).get().getTitle());
				checkUserResume.setSendUid(resumes.get(x).getSendUid());
				checkUserResume.setResult(resumes.get(x).getResult());
				checkUserResume.setUpdateTime(resumes.get(x).getUpdateTime());
				CheckUserResumes.add(checkUserResume);
			}
			return CheckUserResumes;
		} else {
			return null;
		}
	}

	/**
	 * 結果を設置する
	 * @param uid
	 * @param result
	 */
	public void setResult(int resumeId, String result) {
		resumeRepository.findById(resumeId).get().setResult(result);
	}

}
