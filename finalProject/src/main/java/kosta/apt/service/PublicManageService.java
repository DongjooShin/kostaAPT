package kosta.apt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosta.apt.domain.publicmanage.PublicManagementFee;
import kosta.apt.persistence.PublicManageDao;

@Service
public class PublicManageService {

	
	
	@Autowired
	private PublicManageDao dao;
	
	
	public List<PublicManagementFee> selectMonthPublicmanage(){
		
		
		return dao.selectMonthPublicmanage();
	}
	public List<PublicManagementFee> selectPublicmanage(){
		return dao.selectPublicmanage();
	}
}
