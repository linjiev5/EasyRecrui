package jp.easyrecrui.object;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OWorkExperience {
	private int workId;
	private int uid;
	private Date startTime;
	private Date endTime;
	private String companyName;
	private String position;
}
