package jp.easyrecrui.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.easyrecrui.entity.RecruiInfo;

@Repository
public interface RecruiInfoRepository extends JpaRepository<RecruiInfo, String> {

}
