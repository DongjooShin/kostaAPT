package kosta.apt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kosta.apt.domain.publicmanage.Graph;
import kosta.apt.domain.publicmanage.PublicManagementFee;
import kosta.apt.service.PublicManageService;

@RestController
public class PublicmanageDataController {

	@Autowired
	private PublicManageService service;
	
	@RequestMapping("/publicdata")
	public List<Graph> getGraph(){
	List<PublicManagementFee> list =	service.selectPublicmanage();
	List list2 = new ArrayList();
	System.out.println(list.get(0).toString());
	for(int i=0;i<list.size();i++){
		Graph g = new Graph();
		System.out.println(list.get(i).getPm_publicFeeNo());
		g.setD(list.get(i).getPm_publicFeeNo());
		g.set관리비(list.get(i).getPm_clean()+list.get(i).getPm_general()+list.get(i).getPm_maintain()+
				list.get(i).getPm_liftMaintain()+list.get(i).getPm_security()+list.get(i).getPm_foodWaste()+
				list.get(i).getPm_fireInsurance()+list.get(i).getPm_commission()+list.get(i).getPm_meeting()+
				list.get(i).getPm_publicElectric()+list.get(i).getPm_liftElectric()+list.get(i).getPm_TVFee()+
				list.get(i).getPm_disinfection());
		list2.add(g);
	}
		return list2;
	}
}
