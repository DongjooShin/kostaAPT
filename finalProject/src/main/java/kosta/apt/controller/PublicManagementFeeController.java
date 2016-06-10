package kosta.apt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kosta.apt.domain.publicmanage.PublicManagementFee;
import kosta.apt.service.PublicManagementFeeService;

@Controller
public class PublicManagementFeeController {
	
	private PublicManagementFeeService publicMntService;

	@Autowired
	public void setPublicMntService(PublicManagementFeeService publicMntService) {
		this.publicMntService = publicMntService;
	}
	
	@RequestMapping("/publicManagementFeeInsertForm")
	public String publicManagementFeeInsertForm(){
		return "managementFee/publicManagementFeeInsert";
	}
	
	@RequestMapping("/publicManagementFeeInsert")
	public String publicManagementFeeInsert(String year, String month, PublicManagementFee publicManagementFee){
		String date  = year+month;
		publicManagementFee.setPm_publicFeeNo(date);
		publicMntService.publicManagementFeeInsert(publicManagementFee);
		return "/main";
	}
	
	
}
