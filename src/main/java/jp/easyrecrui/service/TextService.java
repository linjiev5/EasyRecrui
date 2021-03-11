package jp.easyrecrui.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TextService {

	public String getName(String name) {
	return name;
	}
}
