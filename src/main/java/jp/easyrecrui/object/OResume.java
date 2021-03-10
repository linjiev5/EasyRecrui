package jp.easyrecrui.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * 履歴書
 * @author user
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OResume {

	private String userName;
	private String nationality;// 国籍
	private String nameKatakana;
	private String educationalBackground;// 学歴
	private String nearestStation;// 最寄駅
	private String workExperience;//職歴
	private String motivation;// 希望動機
	private String selfPublicRelations;// 自己PR
	private String hobbiesAndSkills;// 趣味・特技
	private String licenceOrQualification;// 免許・資格


}
