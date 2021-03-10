package jp.easyrecrui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.easyrecrui.repository.UserInfoRepository;

@Service
@Transactional
public class UserInfoService {

	@Autowired
	UserInfoRepository userInfoRepository;


}
