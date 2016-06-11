package kosta.apt.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kosta.apt.domain.Paging.SearchCriteria;
import kosta.apt.domain.member.Member;
import kosta.apt.service.ComplaintService;

@Controller
public class MypageController {

	@Autowired
	private ComplaintService service;
	
	
	//관리자인지 맴버인지 확인후 페이지 경로 결정
	//지금은 확인불가능해서 맴버로 결정
	@RequestMapping(value = "/Mypage", method = RequestMethod.GET)
	public String SelectMypage(HttpSession session,Model model){
		
	      Member member =	(Member)session.getAttribute("member");
	    if(member!=null){
	    	
	    	if(member.getM_grade()>0){
	    		
	    		switch (member.getM_grade()) {
				case 1:
					model.addAttribute("grade", "마이페이지");
					
					break;
				case 2:
					model.addAttribute("grade", "관리소 마이페이지");
					
					break;
				case 3:
					model.addAttribute("grade", "입주민대표 마이페이지");
					
					break;

				default:
					model.addAttribute("grade", "동대표 마이페이지");
					break;
				}
	    		model.addAttribute("complaint",service.mypageComplaint());
	    		return "mypage/mypageMain";
	    	}
	    }
	    	return "redirect:/";
	    
	}
	      
}
