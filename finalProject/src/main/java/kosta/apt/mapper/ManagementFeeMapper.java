package kosta.apt.mapper;

import java.util.HashMap;
import java.util.List;

import kosta.apt.domain.management.ManagementFee;
import kosta.apt.domain.member.Member;

public interface ManagementFeeMapper {

	public List<Member> selectMember(int buildingNo, int roomNo);
	void managementFeeInsert(HashMap<String, ManagementFee> map1, HashMap<String, String> map2);
	public List<ManagementFee> selectManagementFee(String m_memberNo);
	
	//public int insertManagementFee(HashMap map1, HashMap map2);
	//public int insertPublicMntFee(PublicManagementFee publicManagementFee);
	//public ManagementFee selectMntFee(String date);
	//public PublicManagementFee selectPublicMntFee(int date);
	//public List selectTotalMntFee();
	//public List<ManagementFee> selectElectricFee(int date);
	//public List<ManagementFee> selectWaterFee(int parseInt);
	//public List<ManagementFee> selectGasFee(int parseInt);
	
	
}
