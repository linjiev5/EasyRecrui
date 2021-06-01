package jp.easyrecrui.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.easyrecrui.entity.ResumeInfo;

@Repository
public interface ResumeInfoRepository extends JpaRepository<ResumeInfo, Integer> {

	ResumeInfo findByUid(int uid);

}
