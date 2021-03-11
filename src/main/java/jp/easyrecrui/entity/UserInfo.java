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
@Table(name = "userInfo")
public class UserInfo {
	@Id
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
