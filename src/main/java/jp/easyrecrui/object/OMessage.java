package jp.easyrecrui.object;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OMessage {

	private int messageId;
	private int uid;
	private String userName;
	private String message;
	private boolean read;
	private Timestamp updateTime;

}
