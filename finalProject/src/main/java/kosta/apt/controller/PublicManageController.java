package kosta.apt.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kosta.apt.domain.publicmanage.PublicManagementFee;
import kosta.apt.service.PublicManageService;

@Controller
public class PublicManageController {

	@Autowired
	private PublicManageService service;
	
	
	@RequestMapping("/PublicManageFee")
	public String publicManage(Model model){
		List<PublicManagementFee> list =service.selectMonthPublicmanage();
		model.addAttribute("p", list.get(0));
		model.addAttribute("p2", list.get(1));
		
		return "/publicManagementFee/PublicManageFee";
	}
	@RequestMapping("/getExcel")
	public String getExcel(Model model,RedirectAttributes attr,HttpServletResponse response){
		
		response.setHeader("Content-Disposition", "attachment; filename=getexcel.xls");
		response.setHeader("Content-Description", "JSP Generated Data");
	List<PublicManagementFee> list =	service.selectPublicmanage();
		
	model.addAttribute("list", list);
		
		return "/publicManagementFee/getExcel";
	}
	

}
