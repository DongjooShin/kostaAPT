package kosta.apt.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kosta.apt.domain.publicmanage.PublicManagementFee;
import kosta.apt.mapper.PublicManagementFeeMapper;

@Repository
public class PublicManagementFeeDao {

	private SqlSession sqlSession;

	@Autowired
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public void publicManagementFeeInsert(PublicManagementFee publicManagementFee) {
		sqlSession.getMapper(PublicManagementFeeMapper.class).publicManagementFeeInsert(publicManagementFee);
	}
	
	
}
