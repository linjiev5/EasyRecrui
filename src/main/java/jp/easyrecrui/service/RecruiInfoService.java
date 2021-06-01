package jp.easyrecrui.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.easyrecrui.entity.RecruiInfo;
import jp.easyrecrui.object.ORecruiInfo;
import jp.easyrecrui.repository.CompanyInfoRepository;
import jp.easyrecrui.repository.RecruiInfoRepository;
import jp.easyrecrui.utils.EntityToObj;

@Service
@Transactional
public class RecruiInfoService {

	@Autowired
	RecruiInfoRepository recruiInfoRepository;
	@Autowired
	CompanyInfoRepository companyInfoRepository;

	/**
	 * 求人詳細追加
	 * @param oRecruiInfo
	 */
	public void updateRecruiInfo(ORecruiInfo oRecruiInfo) {
		RecruiInfo recruiInfo = new RecruiInfo();
		recruiInfo.setRecruiId(oRecruiInfo.getRecruiId());
		recruiInfo.setUid(oRecruiInfo.getUid());
		recruiInfo.setEmploymentMethod(oRecruiInfo.getEmploymentMethod());
		recruiInfo.setCategory(oRecruiInfo.getCategory());
		recruiInfo.setTitle(oRecruiInfo.getTitle());
		recruiInfo.setSalary(oRecruiInfo.getSalary());
		recruiInfo.setStation(oRecruiInfo.getStation());
		recruiInfo.setWelfare(oRecruiInfo.getWelfare());
		recruiInfo.setJobDetails(oRecruiInfo.getJobDetails());
		Timestamp now = new Timestamp(System.currentTimeMillis());
		recruiInfo.setUpdateTime(now);
		recruiInfoRepository.saveAndFlush(recruiInfo);
	}

	/**
	 * 求人情報を取得する
	 * @param uid
	 * @return
	 */
	public ArrayList<ORecruiInfo> getRecruiInfo(int uid) {
		ArrayList<RecruiInfo> recruiInfo = recruiInfoRepository
				.findByUidOrderByRecruiIdAsc(uid);
		if (recruiInfo.size() >= 0) {
			ArrayList<ORecruiInfo> oRecruiInfos = EntityToObj.setORecruiInfos(recruiInfo);
			return oRecruiInfos;
		} else {
			return null;
		}
	}

	/**
	 * idで一つの求人情報を取得する
	 * @param id
	 * @return
	 */
	public ORecruiInfo findOneRecruiInfo(int id) {
		Optional<RecruiInfo> recruiInfo = recruiInfoRepository.findById(id);
		RecruiInfo rc = recruiInfo.get();
		ORecruiInfo oRecruiInfo = EntityToObj.setORecruiInfo(rc);
		return oRecruiInfo;
	}

	/**
	 * idで求人情報を削除する
	 * @param id
	 */
	public void deleteRecruiInfo(int id) {
		recruiInfoRepository.deleteById(id);
	}

	/**
	 * すべての求人情報を取得する
	 * @return
	 */
	public ArrayList<ORecruiInfo> getAllRecruiInfo() {
		ArrayList<RecruiInfo> recruiInfos = recruiInfoRepository.findAllByOrderByUpdateTimeDesc();
		if (recruiInfos.size() >= 1) {
			ArrayList<ORecruiInfo> oRecruiInfos = EntityToObj.setORecruiInfos(recruiInfos);
			return oRecruiInfos;
		} else {
			return null;
		}
	}

	/**
	 *
	 * @param string
	 * @return
	 */
	public ArrayList<ORecruiInfo> getRecruiInfoByEmploymentMethod(String employmentMethod) {
		ArrayList<RecruiInfo> recruiInfos = recruiInfoRepository
				.findByEmploymentMethodOrderByUpdateTimeDesc(employmentMethod);
		if (recruiInfos.size() >= 1) {
			ArrayList<ORecruiInfo> oRecruiInfos = EntityToObj.setORecruiInfos(recruiInfos);
			return oRecruiInfos;
		} else {
			return null;
		}

	}

	/**
	 *
	 * @param employmentMethod
	 * @param category
	 * @return
	 */
	public ArrayList<ORecruiInfo> getRecruiInfoByEmploymentMethodAndCategory(String employmentMethod, String category) {
		ArrayList<RecruiInfo> recruiInfos = recruiInfoRepository
				.findByEmploymentMethodAndCategoryOrderByUpdateTimeDesc(employmentMethod, category);
		if (recruiInfos.size() >= 1) {
			ArrayList<ORecruiInfo> oRecruiInfos = EntityToObj.setORecruiInfos(recruiInfos);
			return oRecruiInfos;
		} else {
			return null;
		}
	}

	/**
	 * 検索する結果
	 * @param key
	 * @return
	 */
	public ArrayList<ORecruiInfo> getRecruiInfoForSearch(String key) {
		ArrayList<RecruiInfo> recruiInfos = recruiInfoRepository
				.findByKey(key);
		if (recruiInfos.size() >= 1) {
			ArrayList<ORecruiInfo> oRecruiInfos = EntityToObj.setORecruiInfos(recruiInfos);
			return oRecruiInfos;
		} else {
			return null;
		}

	}

}
