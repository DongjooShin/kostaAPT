package kosta.apt.persistence;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kosta.apt.domain.SiteNotice.QnA;
import kosta.apt.domain.SiteNotice.SNSearchCriteria;
import kosta.apt.domain.SiteNotice.SiteNotice;
import kosta.apt.mapper.SiteNoticeMapper;

@Repository
public class SiteNoticeDao {

	public static SiteNoticeDao sndao = new SiteNoticeDao();
	
	private SqlSession sqlSession;

	@Autowired
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;

	}
	
	public Integer maxSnNo(){
		return sqlSession.getMapper(SiteNoticeMapper.class).maxSnNo();
	}

	public void insertSN(SiteNotice SiteNotice) {
		// TODO Auto-generated method stub
		System.out.println("GN dao 들어옴");
		System.out.println(SiteNotice.toString());
		
		sqlSession.getMapper(SiteNoticeMapper.class).insertSN(SiteNotice);
	}
	
	public List<SiteNotice> listSearchCriteria(SNSearchCriteria cri) {
		System.out.println("searchCri list=> "+cri.toString());
		// TODO Auto-generated method stub
		return sqlSession.getMapper(SiteNoticeMapper.class).selectList(cri,new RowBounds(cri.getPageStart(), cri.getPerPageNum()));
	}
	public int listSearchCount(SNSearchCriteria cri) {
		System.out.println("searchCri => "+cri.toString());
		return sqlSession.getMapper(SiteNoticeMapper.class).selectOne(cri);
	}

	public SiteNotice snRead(int sn_SiteNoticeNo) {
		System.out.println("GN dao listAll 들어옴");
		return sqlSession.getMapper(SiteNoticeMapper.class).snRead(sn_SiteNoticeNo);
	}	
	public void snHitCount(int sn_SiteNoticeNo) {
		sqlSession.getMapper(SiteNoticeMapper.class).snHitCount(sn_SiteNoticeNo);
		
	}
	
	public void snModify(SiteNotice sn) {
		System.out.println("GN dao update 들어옴");
		sqlSession.getMapper(SiteNoticeMapper.class).snModify(sn);
	}
	public void snDelete(int sn_siteNoticeNo) {
		System.out.println("GN dao update 들어옴");
		sqlSession.getMapper(SiteNoticeMapper.class).snDelete(sn_siteNoticeNo);
	}

	
//--------------------------1:1--------------------------
	
	public void insertInquiry(QnA qna) {
		System.out.println(qna.toString());
		sqlSession.getMapper(SiteNoticeMapper.class).inquiryInsert(qna);	
	}

	public Integer maxQnAno() {
		return sqlSession.getMapper(SiteNoticeMapper.class).maxQnANo();
	}

	public List<QnA> selectAllQnAList() {
		return sqlSession.getMapper(SiteNoticeMapper.class).selectAllQnAList();
	}
		
	
	
	
}
