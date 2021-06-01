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
@Table(name = "resumeInfo")
public class ResumeInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int uid;
	private String icon;
	private String nationality; // 国籍
	private String nameKatakana; // カタカナ
	private String nearestStation; // 最寄駅
	private String motivation; // 希望動機
	private String selfPublicRelations; // 自己ＰＲ
	private String hobbiesAndSkills; // 趣味・特技
	private String licenceOrQualification; // 免許・資格
	private Timestamp updateTime;

}
