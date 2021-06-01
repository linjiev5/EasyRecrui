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
	USER_NOT_EXIST(-1, "ユーザは存在しません"),
	PASSWORD_ERROR(2, "パスワード正しくありません"),
	USER_EXIST(1,"ユーザはすでに存在します"),
	REGIST_SUCESS(3, "ユーザ登録成功しました"),
	USER_NOT_LOGIN(101, "ログインしていません"),
	USER_ROCKED(104, "は制限されました"),
	ERROR(99,"エラーが発生しました"),
	DELETE_SUCESS(202,"削除成功しました"),
	SAVE_PASSWORD_SUCESS(108,"パスワード変更しました"),
	PASSWORD_SAME(666,"新パスワードと旧パスワードが同じです"),
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