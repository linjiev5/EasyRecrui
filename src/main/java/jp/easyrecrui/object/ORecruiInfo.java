package jp.easyrecrui.object;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ORecruiInfo {

	private Integer recruiId;
	private int uid;
	private String employmentMethod;//雇用方式 ・fullTime ・part
	private String category;
	private String title;
	private String salary;// 給与
	private String welfare;// 福利厚生
	private String station;
	private String jobDetails;// 仕事詳細
	private Timestamp updateTime;

}
