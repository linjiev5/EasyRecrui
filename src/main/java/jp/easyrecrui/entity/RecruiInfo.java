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
@Table(name = "recruiInfo")
public class RecruiInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer recruiId;
	private int uid;
	private String employmentMethod; // 雇用方式
	private String category; // 種類
	private String title;
	private String salary; // 給与
	private String station;
	private String welfare; // 福利厚生
	private String jobDetails; // 仕事詳細
	private Timestamp updateTime;
}
