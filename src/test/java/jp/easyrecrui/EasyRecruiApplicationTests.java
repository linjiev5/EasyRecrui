package jp.easyrecrui;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jp.easyrecrui.service.LoginService;

@SpringBootTest
class EasyRecruiApplicationTests {

	@Autowired
	LoginService loginService;

	/**
	 * adminのロールを取得する
	 */
	@Test
	public void testGetAdminRole() {
		assertEquals("admin", loginService.getRole("admin"));
	}

	/**
	 * ユーザのロールを取得する
	 */
	@Test
	public void testGetAaaRole() {
		assertEquals("user", loginService.getRole("aaa"));
	}

	/**
	 * ビジネスユーザを取得する
	 */
	@Test
	public void testGetCompanyRole() {
		assertEquals("company", loginService.getRole("linjie"));
	}

	/**
	 * ビジネスユーザを取得する
	 */
	@Test()
	public void testNoRole() throws IOException{
		assertNull(null, loginService.getRole("nobody"));
	}
}
