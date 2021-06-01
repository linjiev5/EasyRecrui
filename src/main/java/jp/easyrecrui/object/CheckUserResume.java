package jp.easyrecrui.object;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckUserResume {

	private int resumeId;
	private String name;
	private String title;
	private int sendUid;
	private Timestamp updateTime;
	private ResumeView resumeView;
	private String result;
}
