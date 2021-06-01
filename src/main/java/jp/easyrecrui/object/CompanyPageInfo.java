package jp.easyrecrui.object;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyPageInfo {

	private int uid;
	private String password;
	private String role;
	private String companyName;
	private String mail;
	private String tel;
	private String info;
	private Date foundedTime;
	private String icon;
	private String adress;
	private Timestamp updateTime;

}
