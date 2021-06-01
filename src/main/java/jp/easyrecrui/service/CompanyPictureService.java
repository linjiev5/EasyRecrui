package jp.easyrecrui.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.easyrecrui.entity.CompanyPicture;
import jp.easyrecrui.object.OCompanyPicture;
import jp.easyrecrui.repository.CompanyPictureRepository;

@Service
@Transactional
public class CompanyPictureService {

	@Autowired
	CompanyPictureRepository companyPictureRepository;

	/**
	 * 会社写真情報を取得する
	 * @param userName
	 * @return
	 */
	public ArrayList<OCompanyPicture> getPicture(int uid) {
		ArrayList<CompanyPicture> pictures = companyPictureRepository.findByUidOrderByPicIdAsc(uid);
		ArrayList<OCompanyPicture> companyPictures = new ArrayList<OCompanyPicture>();
		if (pictures.size() > 0) {
			for (int x = 0, y = pictures.size(); x < y; x++) {
				OCompanyPicture pics = new OCompanyPicture();
				pics.setPicId(pictures.get(x).getPicId());
				pics.setUid(uid);
				pics.setPicture(pictures.get(x).getPicture());
				companyPictures.add(x, pics);
			}
		}
		return companyPictures;
	}

	/**
	 * 写真を保存する
	 * @param userName
	 * @param pics
	 */
	public void savePictures(int uid, ArrayList<String> pics) {
		CompanyPicture companyPicture = new CompanyPicture();
		companyPicture.setUid(uid);
		for (int a = 0, b = pics.size(); a < b; a++) {
			String pic = "../company/picture/" + pics.get(a);
			companyPicture.setPicture(pic);
			companyPictureRepository.saveAndFlush(companyPicture);
		}
	}

	/**
	 * idで写真を取得する
	 * @param id
	 * @return
	 */
	public OCompanyPicture findOnePictureById(int id) {
		Optional<CompanyPicture> companyPicture = companyPictureRepository.findById(id);
		CompanyPicture cp = companyPicture.get();
		OCompanyPicture oCompanyPicture = new OCompanyPicture();
		oCompanyPicture.setPicId(cp.getPicId());
		oCompanyPicture.setPicture(cp.getPicture());
		oCompanyPicture.setUid(cp.getUid());
		return oCompanyPicture;
	}

	/**
	 * 写真を保存する
	 * @param userName
	 * @param pics
	 */
	public void savePicturesById(int id, String picture) {
		Optional<CompanyPicture> findById = companyPictureRepository.findById(id);
		CompanyPicture companyPicture = findById.get();
		String pic = "../company/picture/" + picture;
		companyPicture.setPicture(pic);
		companyPictureRepository.saveAndFlush(companyPicture);
	}

	/**
	 * icon
	 * @param userName
	 * @return
	 */
	public String getIcon(int id) {
		return companyPictureRepository.findById(id).get().getPicture();
	}

	public void deletePicture(Integer id) {
		companyPictureRepository.deleteById(id);
	}



}
