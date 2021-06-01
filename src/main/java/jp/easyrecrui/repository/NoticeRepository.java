package jp.easyrecrui.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.easyrecrui.entity.Notice;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Integer> {

	ArrayList<Notice> findAllByOrderByNoticeIdDesc();

}
