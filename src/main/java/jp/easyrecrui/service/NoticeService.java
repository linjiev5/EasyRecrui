package jp.easyrecrui.service;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.easyrecrui.entity.Notice;
import jp.easyrecrui.object.ONotice;
import jp.easyrecrui.repository.NoticeRepository;

@Service
@Transactional
public class NoticeService {

	@Autowired
	NoticeRepository noticeRepository;

	/**
	 *
	 *
	 * @return
	 */
	public ArrayList<ONotice> getAllNotice() {
		ArrayList<Notice> notices = noticeRepository.findAllByOrderByNoticeIdDesc();
		ArrayList<ONotice> oNotices = new ArrayList<ONotice>();
		if (notices.size() >= 1) {
			for (int a = 0, b = notices.size(); a < b; a++) {
				ONotice oNotice = new ONotice();
				oNotice.setNoticeId(notices.get(a).getNoticeId());
				oNotice.setTitle(notices.get(a).getTitle());
				oNotice.setNotice(notices.get(a).getNotice());
				oNotice.setUpdateTime(notices.get(a).getUpdateTime());
				oNotices.add(oNotice);
			}
			return oNotices;
		} else {
			return null;
		}
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	public ONotice getNotice(int id) {
		Notice notice = noticeRepository.findById(id).get();
		if (notice != null) {
			ONotice oNotice = new ONotice();
			oNotice.setNoticeId(notice.getNoticeId());
			oNotice.setTitle(notice.getTitle());
			oNotice.setNotice(notice.getNotice());
			oNotice.setUpdateTime(notice.getUpdateTime());
			return oNotice;
		} else {
			return null;
		}
	}

	/**
	 *
	 * @param id
	 */
	public void deleteById(Integer id) {
		noticeRepository.deleteById(id);
	}

	/**
	 *
	 * @param oNotice
	 * @return
	 */
	public String updateNotice(ONotice oNotice) {
		Notice notice = new Notice();
		notice.setTitle(oNotice.getTitle());
		notice.setNotice(oNotice.getNotice());
		Timestamp now = new Timestamp(System.currentTimeMillis());
		notice.setUpdateTime(now);
		noticeRepository.saveAndFlush(notice);
		return null;
	}

}
