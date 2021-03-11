package jp.easyrecrui.object;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OCompanyInfo {

	private String name;
	private String demand;// 要求
	private String mail;
	private String tel;
	private String info;
	private Date foundedTime;
	private String icon;
	private String picture;
	private String adress;
	private String nearestStation;
	private Timestamp updateTime;

}
