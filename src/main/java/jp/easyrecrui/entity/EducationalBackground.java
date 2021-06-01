package jp.easyrecrui.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "educationalBackground")
public class EducationalBackground {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer educationalId;
	private int uid;
	private Date startTime;
	private Date endTime;
	private String schoolName;

}
