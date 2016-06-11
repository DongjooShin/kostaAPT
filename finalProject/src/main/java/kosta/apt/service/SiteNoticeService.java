package kosta.apt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosta.apt.domain.SiteNotice.QnA;
import kosta.apt.domain.SiteNotice.SNSearchCriteria;
import kosta.apt.domain.SiteNotice.SiteNotice;
import kosta.apt.persistence.SiteNoticeDao;

@Service
public class SiteNoticeService {

	private SiteNoticeDao snDao;
	
	@Autowired
	public void setSnDao(SiteNoticeDao snDao) {
		this.snDao = snDao;
	}
	
	public Integer maxSnNo(){
		return snDao.maxSnNo();
	}

	public void regist(SiteNotice sn) {
		System.out.println("SN service 들어옴");
		snDao.insertSN(sn);
	}
 
	public List<SiteNotice> listSearchCriteria(SNSearchCriteria cri) {
		System.out.println("GN service list cri들어옴");
		
		return snDao.listSearchCriteria(cri);
		//return gnDao.listSearchCriteria(cri);
	}

	public int listSearchCount(SNSearchCriteria cri) {
		System.out.println("GN service list count들어옴");

		return snDao.listSearchCount(cri);
		//return gnDao.listSearchCount(cri);
	}

	public SiteNotice snRead(int sn_siteNoticeNo) {
		System.out.println("GN service read들어옴");
		//gnDao.hitCount(g_groupNoticeNo);
		return snDao.snRead(sn_siteNoticeNo);
	}
	public void snHitCount(int sn_siteNoticeNo) {
		snDao.snHitCount(sn_siteNoticeNo);
		
	}
	
	public void snModify(SiteNotice sn) {
		System.out.println("GN service modify들어옴");
		snDao.snModify(sn);
	}

	public void snDelete(int sn_siteNoticeNo) {
		System.out.println("GN service delete들어옴");
		snDao.snDelete(sn_siteNoticeNo);
		
	}

	
//----------------- 1:1--------------------------
	
	public void insertInquiry(QnA qna) {
		
		snDao.insertInquiry(qna);
	}

	public Integer maxQnANo() {
		return snDao.maxQnAno();
	}

	public List<QnA> selectAllQnAList() {
		return snDao.selectAllQnAList();
	}

	
	
	
	
}
