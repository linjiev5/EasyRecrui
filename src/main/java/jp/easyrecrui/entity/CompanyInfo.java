package jp.easyrecrui.entity;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "companyInfo")
public class CompanyInfo {
	@Id
	private String name;
	private String demand;
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
