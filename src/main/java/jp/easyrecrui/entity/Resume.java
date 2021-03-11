package jp.easyrecrui.entity;

import java.sql.Timestamp;

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
@Table(name = "resume")
public class Resume {
	@Id
	private String userName;
	private String icon;
	private String nationality; // 国籍
	private String namaKatakana; // カタカナ
	private String educationalBackground; // 学歴
	private String nearestStation; // 最寄駅
	private String workExperience; // 職歴
	private String motivation; // 希望動機
	private String selfPublicRelations; // 自己ＰＲ
	private String hobbiesAndSkills; // 趣味・特技
	private String licenceOrQualification; // 免許・資格
	private Timestamp updateTime;

}
