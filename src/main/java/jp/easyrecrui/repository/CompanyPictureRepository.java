package jp.easyrecrui.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.easyrecrui.entity.CompanyPicture;
@Repository
public interface CompanyPictureRepository extends JpaRepository<CompanyPicture, Integer>{

	ArrayList<CompanyPicture> findByUidOrderByPicIdAsc(int uid);

	void deleteByUid(int uid);


}
