package jp.easyrecrui.object;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeView {

	private int uid;//id
	private String name;
	private String nameKatakana;
	private Integer sex;//userInfo
	private String icon;//履歴書の画像
	private String nationality;// 国籍
	private String tel;//userInfo
	private String mail;//userInfo
	private Date birthday;//userInfo
	private String adress;//userInfo
	private String nearestStation;// 最寄駅
	private String hobbiesAndSkills;// 趣味・特技
	private String licenceOrQualification;// 免許・資格

	private ArrayList<OEducationalBackground> educationalBackground;// 学歴
	private ArrayList<OWorkExperience> workExperience;//職歴
	private String motivation;// 希望動機
	private String selfPublicRelations;// 自己PR

	private Timestamp updateTime;

}
