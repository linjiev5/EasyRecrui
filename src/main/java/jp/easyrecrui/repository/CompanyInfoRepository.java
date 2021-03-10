package jp.easyrecrui.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jp.easyrecrui.entity.CompanyInfo;

public interface CompanyInfoRepository extends JpaRepository<CompanyInfo, String>{

}
