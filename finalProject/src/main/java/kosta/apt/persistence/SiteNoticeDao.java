package kosta.apt.persistence;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	
	/*

	public List<SiteNotice> gnList(int startRow, Search search, int apt) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		// search�옉 apt ->HashMap
		HashMap<String, Search> map1 = new HashMap<String, Search>();
		HashMap<String, Integer> map2 = new HashMap<String, Integer>();

		map1.put("se", search);// 遺�紐� b_step
		map2.put("a", apt);

		System.out.println("search" + search.toString());
		System.out.println("apt_no" + apt);
		return sqlSession.getMapper(GNMapper.class).gnList(new RowBounds(startRow, 10), map1, map2);
	}

	public SiteNotice selectgnDetail(int g_SiteNoticeNo) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		return sqlSession.getMapper(GNMapper.class).selectgnDetail(g_SiteNoticeNo);
	}

	public int uphit(int g_SiteNoticeNo) {
		int re = -1;

		SqlSession sqlSession = getSqlSessionFactory().openSession();

		try {
			// 1.媛��옣癒쇱� 留ㅽ띁�뿉 �벑濡�
			re = sqlSession.getMapper(GNMapper.class).uphit(g_SiteNoticeNo);

			if (re > 0) {
				sqlSession.commit();
			} else {
				sqlSession.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}

		return re;
	}

	public int updategn(SiteNotice gn) {
		int re = -1;

		SqlSession sqlSession = getSqlSessionFactory().openSession();

		try {
			// 1.媛��옣癒쇱� 留ㅽ띁�뿉 �벑濡�
			re = sqlSession.getMapper(GNMapper.class).updategn(gn);

			if (re > 0) {
				sqlSession.commit();
			} else {
				sqlSession.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return re;
	}

	public int selectgnDel(int gn) {
		int re = -1;

		SqlSession sqlSession = getSqlSessionFactory().openSession();

		try {
			// 1.媛��옣癒쇱� 留ㅽ띁�뿉 �벑濡�
			re = sqlSession.getMapper(GNMapper.class).selectgnDel(gn);

			if (re > 0) {
				sqlSession.commit();
			} else {
				sqlSession.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return re;
	}

	public int countSiteNotice(Search search, int apt) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int re = 0;
		try {
			HashMap<String, Search> map1 = new HashMap<String, Search>();
			HashMap<String, Integer> map2 = new HashMap<String, Integer>();

			map1.put("se", search);// 遺�紐� b_step
			map2.put("a", apt);

			System.out.println("Count search : " + search.toString());
			System.out.println("Count apt_no : " + apt);

			re = sqlSession.getMapper(GNMapper.class).countSiteNotice(map1, map2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}

	public Integer selectCount(int apt_APTGNo) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int re = 0;
		try {
			re = sqlSession.getMapper(GNMapper.class).selectCount(apt_APTGNo);
			System.out.println("selectCount re 媛�: " + re);
			System.out.println("selectCount apt_ATPGNo 媛�: " + re);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}



	
	*/
	
	
	
	
}
