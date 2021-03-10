package jp.easyrecrui.entity;

import javax.persistence.Entity;
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
	private String name;// 会社の名前
	private String category; // 種類
	private String position; // 職位
	private String title;
	private String salary; // 給与
	private String welfare; // 福利厚生
	private String jobDescription; // 募集詳細
}
