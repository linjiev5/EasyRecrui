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
	private String userName;
	private String name;
	private String mail;
	private String tel;
	private Integer sex;
	private String adress;
	private String nearestStation;
	private Date birthday;
	private Timestamp updateTime;
}
