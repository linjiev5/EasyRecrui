package jp.easyrecrui.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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

}
