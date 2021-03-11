package jp.easyrecrui.object;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 履歴書
 * @author user
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OResume {

	private String userName;
	private String icon;
	private String nationality;// 国籍
	private String nameKatakana;
	private String educationalBackground;// 学歴
	private String nearestStation;// 最寄駅
	private String workExperience;//職歴
	private String motivation;// 希望動機
	private String selfPublicRelations;// 自己PR
	private String hobbiesAndSkills;// 趣味・特技
	private String licenceOrQualification;// 免許・資格
	private Timestamp updateTime;

}
