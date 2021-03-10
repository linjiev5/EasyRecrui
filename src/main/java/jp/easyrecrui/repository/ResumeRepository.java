package jp.easyrecrui.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.easyrecrui.entity.Resume;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, String> {

}
