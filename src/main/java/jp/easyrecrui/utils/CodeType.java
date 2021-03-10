package jp.easyrecrui.utils;

/**
 *
 * @author lin
 *
 */
public enum CodeType {
	/**
	 * 状態のタイプを設置する
	 */
	LOGIN_SUCESS(0, "ログイン成功"),
	USERNAME_NOT_EXIST(-1, "ユーザは存在しない"),
	PASSWORD_ERROR(2,"パスワード正しくない"),
	USER_EXIST(1,"ユーザはすでに存在します"),
	REGIST_SUCESS(3,"ユーザ登録成功しました"),

	USER_NOT_LOGIN(101,"ログインしてない");
	;

	private int code;

	private String message;

	CodeType(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}