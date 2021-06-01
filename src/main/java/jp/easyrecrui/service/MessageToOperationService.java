package jp.easyrecrui.service;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.easyrecrui.entity.MessageToOperation;
import jp.easyrecrui.entity.UserLogin;
import jp.easyrecrui.object.OMessage;
import jp.easyrecrui.repository.MessageToOperationRepository;
import jp.easyrecrui.repository.UserLoginRepository;
import jp.easyrecrui.utils.TimeDeal;

@Service
@Transactional
public class MessageToOperationService {

	@Autowired
	MessageToOperationRepository messageToOperationRepository;
	@Autowired
	UserInfoService userInfoService;
	@Autowired
	UserLoginRepository userLoginRepository;

	/**
	 *
	 * @return
	 */
	public ArrayList<OMessage> getAllMessage() {
		ArrayList<MessageToOperation> messages = messageToOperationRepository.findAllByOrderByUpdateTimeDesc();
		ArrayList<OMessage> oMessages = new ArrayList<OMessage>();
		if (messages.size() >= 1) {
			for (int a = 0, b = messages.size(); a < b; a++) {
				int uid = messages.get(a).getUid();
				OMessage oMessage = new OMessage();
				oMessage.setUid(uid);
				oMessage.setMessageId(messages.get(a).getMessageId());
				oMessage.setMessage(messages.get(a).getMessage());
				oMessage.setUpdateTime(messages.get(a).getUpdateTime());
				oMessage.setRead(messages.get(a).isRead());
				oMessage.setUserName(userInfoService.getUserName(uid));
				oMessages.add(oMessage);
			}
			return oMessages;
		} else {
			return null;
		}

	}

	/**
	 *
	 * @param oMessage
	 * @return
	 */
	public String sendMessage(OMessage oMessage) {
		MessageToOperation messageToOperation = new MessageToOperation();
		messageToOperation.setMessage(oMessage.getMessage());
		messageToOperation.setUid(oMessage.getUid());
		messageToOperation.setRead(false);
		Timestamp now = TimeDeal.nowTime();
		messageToOperation.setUpdateTime(now);
		messageToOperationRepository.saveAndFlush(messageToOperation);
		return "連絡をお待ちください。";
	}

	/**
	 *
	 * @return
	 */
	public int getNotReadMessageCount() {
		return messageToOperationRepository.findByReadFalse().size();
	}

	/**
	 *
	 * @param uid
	 * @param password
	 */
	public String changePassword(int messageId, int uid, String password) {
		UserLogin userLogin = userLoginRepository.findById(uid).get();
		userLogin.setPassword(password);
		userLoginRepository.saveAndFlush(userLogin);
		MessageToOperation messageToOperation = messageToOperationRepository.findById(messageId).get();
		messageToOperation.setRead(true);
		messageToOperationRepository.saveAndFlush(messageToOperation);
		return "変更しました";
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	public String setRead(int id) {
		MessageToOperation messageToOperation = messageToOperationRepository.findById(id).get();
		messageToOperation.setRead(true);
		messageToOperationRepository.saveAndFlush(messageToOperation);
		return "拒否しました";
	}

}
