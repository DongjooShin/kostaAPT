package kosta.apt.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kosta.apt.domain.Property.Property;
import kosta.apt.mapper.PropertyMapper;

@Repository
public class PropertyDAO {

	private SqlSession sqlSession;

	@Autowired
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public void APTSaleInsert(Property property){
	
		property.setM_memberNo("테스트하자");
		
		sqlSession.getMapper(PropertyMapper.class).APTSaleInsert(property);
	}

	public List<Property> APTlist() {
		List<Property> list = null;
		list= sqlSession.getMapper(PropertyMapper.class).APTlist();
		return list;
	}
	
}
