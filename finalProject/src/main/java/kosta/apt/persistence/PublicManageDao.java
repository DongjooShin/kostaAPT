package kosta.apt.persistence;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kosta.apt.domain.publicmanage.PublicManagementFee;



@Repository
public class PublicManageDao {

	
	@Autowired
	private SqlSession sqlSession;//세션테플레이트 생성
	
	private final static String namespace ="apt.mapper.Management";
	
	public List<PublicManagementFee> selectMonthPublicmanage(){
		
		 Calendar calendar = new GregorianCalendar(Locale.KOREA);
		 String day= "";
		 day += calendar.get(Calendar.YEAR);
		 int   month = calendar.get(Calendar.MONTH);
		 month++;
		 if(month<10){
			 day +="0"+month;
		 }else{
			 day += month;
		 }
		 return 
				 sqlSession.selectList(namespace+".selectMonthPublicmanage", day);
	}
	public List<PublicManagementFee> selectPublicmanage(){
		
		return sqlSession.selectList(namespace+".selectPublicmanage");
	}
}
