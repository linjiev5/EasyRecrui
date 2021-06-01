package jp.easyrecrui.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jp.easyrecrui.entity.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
	UserInfo findByUid(int uid);

	@Query(value = "select * from user_info where name != '' and uid > 1 order by uid asc", nativeQuery = true)
	ArrayList<UserInfo> getUserInfo();


}
