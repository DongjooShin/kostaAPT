package kosta.apt.persistence;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kosta.apt.domain.management.ManagementFee;
import kosta.apt.domain.member.Member;
import kosta.apt.mapper.ManagementFeeMapper;

@Repository
public class ManagementFeeDao {

	private SqlSession sqlSession;

	@Autowired
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public List<Member> selectMember(int buildingNo, int roomNo) {
		System.out.println(buildingNo +"/"+ roomNo);
		return sqlSession.getMapper(ManagementFeeMapper.class).selectMember(buildingNo, roomNo);
	}

	public void managementFeeInsert(HashMap<String, ManagementFee> map1, HashMap<String, String> map2) {
		sqlSession.getMapper(ManagementFeeMapper.class).managementFeeInsert(map1, map2);
	}

	public List<ManagementFee> selectManagementFee(String m_memberNo) {
		return sqlSession.getMapper(ManagementFeeMapper.class).selectManagementFee(m_memberNo);
	}
	
	
}
