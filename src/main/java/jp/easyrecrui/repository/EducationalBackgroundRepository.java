package jp.easyrecrui.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.easyrecrui.entity.EducationalBackground;

@Repository
public interface EducationalBackgroundRepository extends JpaRepository<EducationalBackground, Integer> {

	ArrayList<EducationalBackground> findByUidOrderByEducationalIdAsc(int uid);

	void deleteByUid(int uid);

}
