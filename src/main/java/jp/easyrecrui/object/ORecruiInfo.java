package jp.easyrecrui.object;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ORecruiInfo {

	private String name;// 会社の名前
	private String category;// アルバイト、正社員
	private String position;// 職種
	private String title;
	private String salary;// 給与
	private String wellfare;// 福利厚生
	private String jobDescription;// 募集詳細
	private Timestamp updateTime;

}
