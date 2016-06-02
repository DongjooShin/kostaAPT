package kosta.apt.persistence;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import kosta.apt.domain.vote.APTGroup;
import kosta.apt.mapper.AptMapper;


public class APTGroupDao {
	public static APTGroupDao dao = new APTGroupDao();

	public static APTGroupDao getInstance() {
		return dao;
	}

	public SqlSessionFactory getSqlSessionFactory() {
		String resource = "mybatis-config.xml";

		InputStream input = null;

		try {

			input = Resources.getResourceAsStream(resource);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new SqlSessionFactoryBuilder().build(input);
	}

	public APTGroup selectOneAPT(int gno) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		
		APTGroup apt = null;
		
		try {
			apt = sqlSession.getMapper(AptMapper.class).selectOneAPT(gno);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return apt;
	}

	public void updateGroupMainImage(APTGroup apt) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		
		int re=0;
		
		try {
			re = sqlSession.getMapper(AptMapper.class).updateGroupMainImage(apt);
			if(re>0){
				sqlSession.commit();
			}else{
				sqlSession.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		
	}
	
	
	
	
}
