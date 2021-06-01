package jp.easyrecrui.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jp.easyrecrui.entity.CompanyInfo;

@Repository
public interface CompanyInfoRepository extends JpaRepository<CompanyInfo, Integer> {

	CompanyInfo findByUid(int uid);

	void deleteByUid(int uid);

	@Query(value = "select * from company_info where company_name != '' and uid > 1 order by uid asc", nativeQuery = true)
	ArrayList<CompanyInfo> getCompanyInfo();


}
