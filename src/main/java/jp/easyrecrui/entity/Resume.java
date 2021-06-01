package jp.easyrecrui.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "resume")
public class Resume {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int resumeId;
	private int recruiId;
	private int sendUid;
	private int recevieUid;
	private String result;
	private Timestamp updateTime;

}
