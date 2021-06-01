package jp.easyrecrui.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.easyrecrui.entity.MessageToOperation;

@Repository
public interface MessageToOperationRepository extends JpaRepository<MessageToOperation, Integer> {

	ArrayList<MessageToOperation> findAllByOrderByUpdateTimeDesc();

	ArrayList<MessageToOperation> findByReadFalse();

	ArrayList<MessageToOperation> findByUid(int uid);

	void deleteByUid(int uid);
}
