package jp.easyrecrui.object;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ONotice {

	private int noticeId;
	private String title;
	private String notice;
	private Timestamp updateTime;

}
