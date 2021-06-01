package jp.easyrecrui.object;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OUserLogin {
	private int uid;
	private String userName;
	private String password;
	private boolean rocked;
	private String userRole;
	private Timestamp createTime;
	private Timestamp updateTime;
}
