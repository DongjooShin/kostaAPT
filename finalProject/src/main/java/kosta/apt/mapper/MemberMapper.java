package kosta.apt.mapper;

import java.util.List;
import java.util.Map;

import kosta.apt.domain.member.AddressCity;
import kosta.apt.domain.member.AddressState;
import kosta.apt.domain.member.LoginCheck;
import kosta.apt.domain.member.Member;

public interface MemberMapper {

	public int insertMember(Member member);
	public Integer checkMemberId(String id);
	public Integer checkMemberPass(LoginCheck loginCheck);
	public void updateMember(Member member);
	public List<AddressState> getState();
	public int getStateNum(String state);
	public List<AddressCity> getCity(int state);
	public List<String> getAptName(String address);
	public String getAddress(Map<String, String> map);
	public Member getMamber(String id);
	public String getAPTAddress(int apt_APTGNo);
	public int getAptNum(Map<String, String> map);
	
	
	
	

	//경운
/*	
	public int insertAPTsale(Property property);
	public int countSale(Search search);
	public int countSale2(Search2 search);
	public List<Property> listSale(RowBounds rowBounds, Search search); //매물리스트
	public List<Message> listSale2(RowBounds rowBounds, Search2 search);//받은메세지
	public List<Message> listSale3(RowBounds rowBounds, Search2 search);//보낸메세지
	public Property selectAPT(int pr_propertyNo);
	public Integer selectPr_id();
	public Property selectM_id(String m_id);
	public int insertMsg2(Message message);
	public Integer countBoard();
	public Message selectMsg(int m_no);
	public void updateState(Message message);
	public void delectsender(int mg_messageNo);
	public int insertSurvey(Survey survey);	 //서베이에 넣기
	public int insertSurveyDB(SurveyDB surveyDB); //서베이DB에넣기
	public Integer updategroup(); //현재 그룹번호의최고번호
	public Integer updateSurveyGru(); //현재 서베이DB의 고유번호의 최고번호 
	public List<Survey> surveyDeliver(Survey survey);
	public int countsurvey(Survey survey);
	public Integer seyveyapt(Integer sysdate2);
	public SurveyDB surveyDB1(SurveyDB surveyDB);
	public Member selectAPTNo(String id);*/

	
}
