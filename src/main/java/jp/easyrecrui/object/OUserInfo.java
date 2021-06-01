package jp.easyrecrui.object;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OUserInfo {
	private int uid;
	private String userName;
	private String name;
	private String icon;
	private String mail;
	private String tel;
	private Integer sex;
	private String role;
	private boolean rocked;
	private String adress;
	private Date birthday;
	private Timestamp updateTime;
	private ResumeView resumeView;
}
