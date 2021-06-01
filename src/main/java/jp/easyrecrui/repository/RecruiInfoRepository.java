package jp.easyrecrui.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jp.easyrecrui.entity.RecruiInfo;

@Repository
public interface RecruiInfoRepository extends JpaRepository<RecruiInfo, Integer> {

	ArrayList<RecruiInfo> findByUidOrderByRecruiIdAsc(int uid);

	void deleteByUid(int uid);

	ArrayList<RecruiInfo> findAllByOrderByUpdateTimeDesc();

	ArrayList<RecruiInfo> findByEmploymentMethodOrderByUpdateTimeDesc(String employmentMethod);

	ArrayList<RecruiInfo> findByEmploymentMethodAndCategoryOrderByUpdateTimeDesc(String employmentMethod,
			String category);

	ArrayList<RecruiInfo> findByUidOrderByRecruiIdDesc(int uid);

	/**
	 * 検索Query
	 * @param key
	 * @return
	 */
	@Query(value = "select * from recrui_info "
			+ "left outer join company_info on company_info.uid = recrui_info.uid where recrui_info.title like concat(concat('%', :key ),'%') or company_info.company_name like concat(concat('%', :key ),'%') order by recrui_id desc", nativeQuery = true)
	ArrayList<RecruiInfo> findByKey(String key);
}
