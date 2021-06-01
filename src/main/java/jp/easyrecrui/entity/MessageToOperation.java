package jp.easyrecrui.entity;

import java.sql.Timestamp;

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
@Table(name = "messageToOperation")
public class MessageToOperation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int messageId;
	private int uid;
	private String message;
	private boolean read;
	private Timestamp updateTime;
}
