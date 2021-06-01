package jp.easyrecrui.object;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyPageInfo {

	private int uid;
	private String password;
	private String role;
	private String name;
	private String icon;
	private String mail;
	private String tel;
	private Integer sex;
	private String adress;
	private Date birthday;
	private Timestamp updateTime;
}
