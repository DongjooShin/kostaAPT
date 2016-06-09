package kosta.apt.controller;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kosta.apt.domain.management.Budget;
import kosta.apt.domain.management.ManagementFee;
import kosta.apt.domain.member.Member;
import kosta.apt.service.ManagementFeeService;

@Controller
public class ManagementFeeController {
	
	private ManagementFeeService mntFeeService;

	@Autowired
	public void setMntFeeService(ManagementFeeService mntFeeService) {
		this.mntFeeService = mntFeeService;
	}

	@RequestMapping("/managementFeeInsertForm")
	public String managementFeeInsertForm(){
		
		return "/managementFee/managementFee";
	}
	
	@RequestMapping("/managementFeeInsert")
	public String managementFeeInsert(ManagementFee managementFee, 
			int buildingNo, int roomNo, String year, String month){
		System.out.println(buildingNo+"/"+roomNo);
		List<Member> list = mntFeeService.selectMember(buildingNo, roomNo);
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).toString());
		} //id넘어오는지 확인.

		
		//날짜구함
		String date = year+month;
		System.out.println(date);
		
		String [] m_memberNo = new String [list.size()];
		for(int i=0;i<list.size();i++){
			 m_memberNo[i] = list.get(i).getM_memberNo();
			 System.out.println(m_memberNo[i]);
		}
		
		HashMap<String, ManagementFee> map1 = new HashMap<String, ManagementFee>();
		HashMap<String, String>        map2 = new HashMap<String, String>();
		for(int i=0;i<m_memberNo.length;i++){
			map1.put("mf", managementFee);
			map2.put("m_memberNo", m_memberNo[i]);
			map2.put("mf_date", date);
			mntFeeService.managementFeeInsert(map1, map2);
		}	
		return "/main";
	}
	
	
	@RequestMapping("managementFeeList")
	public String managementFeeList(Model model){
		Calendar calendar = new GregorianCalendar(Locale.KOREA);
		String thisMonth = "";
		thisMonth += calendar.get(Calendar.MONTH)+1;
		
		model.addAttribute("date", thisMonth);
		
		
		//로그인한 아이디를 가져옴.
		List<ManagementFee> list = mntFeeService.selectManagementFee("kkt891230");
		
		model.addAttribute("mnt_list", list);
		
		return "/managementFee/managementFeeList";
		
	}

	
}
