package jp.easyrecrui.object;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OCompanyInfo {

	private int uid;
	private String companyName;
	private String mail;
	private String tel;
	private String info;
	private Date foundedTime;
	private String icon;
	private String adress;
	private Timestamp updateTime;
	private String role;
	private String userName;
	private boolean rocked;

}
