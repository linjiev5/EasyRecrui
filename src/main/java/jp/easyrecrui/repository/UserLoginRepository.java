package jp.easyrecrui.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.easyrecrui.entity.UserLogin;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin, Integer> {
	UserLogin findByUserName(String userName);
	UserLogin findByUid(int uid);
}
