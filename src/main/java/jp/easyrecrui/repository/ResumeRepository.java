package jp.easyrecrui.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.easyrecrui.entity.Resume;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Integer> {

	Resume findBySendUidAndRecruiId(int sendUid, int recruiId);

	ArrayList<Resume> findBySendUidOrderByResumeIdDesc(int uid);

	ArrayList<Resume> findByRecevieUidOrderByResumeIdDesc(int uid);

	void deleteByRecevieUid(int uid);

	void deleteBySendUid(int uid);



}
