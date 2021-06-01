package jp.easyrecrui.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.easyrecrui.entity.WorkExperience;

@Repository
public interface WorkExperienceRepository extends JpaRepository<WorkExperience, Integer> {

	ArrayList<WorkExperience> findByUidOrderByWorkIdAsc(int uid);

	void deleteByUid(int uid);
}
