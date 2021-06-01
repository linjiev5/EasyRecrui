package jp.easyrecrui.object;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OResume {

	private int resumeId;
	private int recruiId;
	private int sendUid;
	private int recevieUid;
	private String companyName;
	private String title;
	private String result;
	private Timestamp updateTime;
}
