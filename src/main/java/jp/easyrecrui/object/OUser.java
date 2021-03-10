package jp.easyrecrui.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ログイン、登録用User
 * @author user
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OUser {

	private String userName;
	private String password;

}
