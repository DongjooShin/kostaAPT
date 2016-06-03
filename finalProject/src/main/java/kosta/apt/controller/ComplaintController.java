package kosta.apt.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kosta.apt.domain.Paging.PageMaker;
import kosta.apt.domain.Paging.SearchCriteria;
import kosta.apt.domain.complaint.Complaint;
import kosta.apt.domain.member.Member;
import kosta.apt.service.ComplaintService;
@Controller
public class ComplaintController {
	
	
	@Autowired
	private	ComplaintService service;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET )
	public String List(@ModelAttribute("cri") SearchCriteria cri,Model model){
		   model.addAttribute("list", service.listSearchCriteria(cri));
		   PageMaker pagemaker = new PageMaker();
		   pagemaker.setCri(cri);
		   pagemaker.setTotalCount(service.listSearchCount(cri));
		   model.addAttribute("pageMaker", pagemaker);
		
		return "complaint/list";
	}
	@RequestMapping(value = "/register", method = RequestMethod.GET )
	public String List(){
		
		return "complaint/register";
	}
	@RequestMapping(value = "/register", method = RequestMethod.POST )
	public String List(Complaint complaint,HttpSession session){
		Member member = (Member) session.getAttribute("member");
		complaint.setM_memberNo(member.getM_memberNo());
		service.create(complaint);
		return "redirect:/list";
	}
	
	   @RequestMapping(value="/readPage",method=RequestMethod.GET)
	   public String read(@RequestParam("cp_complaintNo") int cp_complaintNo,@ModelAttribute("cri") SearchCriteria cri , Model model,HttpSession session)throws Exception{
		
		   Member member = (Member)session.getAttribute("member");
		   model.addAttribute(member);
		   model.addAttribute(service.read(cp_complaintNo));
		   
		   return "complaint/readPage";
	   }
	   
	   @RequestMapping(value="/modifyPage",method=RequestMethod.POST)
	   public String modifyPage(@ModelAttribute("cri") SearchCriteria cri,Complaint complaint){
		   service.update(complaint);
		   
		   return"redirect:/list";
	   }
	   @RequestMapping("/removePage")
	   public String removePage(@RequestParam("cp_complaintNo") int cp_complaintNo){
		   service.delete(cp_complaintNo);
		   
		   return"redirect:/list";
	   }
	   
}
