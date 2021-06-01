package jp.easyrecrui.object;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 履歴書情報
 * @author user
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OResumeInfo {

	private int resumeInfoId;
	private int uid;
	private String icon;
	private String nationality;// 国籍
	private String nameKatakana;
	private String nearestStation;// 最寄駅
	private String motivation;// 希望動機
	private String selfPublicRelations;// 自己PR
	private String hobbiesAndSkills;// 趣味・特技
	private String licenceOrQualification;// 免許・資格
	private Timestamp updateTime;

}
