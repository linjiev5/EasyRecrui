package jp.easyrecrui.object;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OUserInfo {
	private String userName;
	private String icon;
	private String mail;
	private String tel;
	private Integer sex;
	private String adress;
	private String nearestStation;
	private Date birthday;
}
