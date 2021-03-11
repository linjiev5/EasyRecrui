package jp.easyrecrui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.easyrecrui.object.OResume;
import jp.easyrecrui.repository.ResumeRepository;

@Service
@Transactional
public class ResumeService {

	@Autowired
	ResumeRepository resumeRepository;

	/**
	 *
	 * @param userName
	 * @return
	 */
	public OResume getResume(String userName) {
		OResume oResume = new OResume();
		return oResume;
	}

}
