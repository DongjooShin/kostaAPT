package kosta.apt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosta.apt.domain.publicmanage.PublicManagementFee;
import kosta.apt.persistence.PublicManagementFeeDao;

@Service
public class PublicManagementFeeService {
	
	private PublicManagementFeeDao dao;

	@Autowired
	public void setDao(PublicManagementFeeDao dao) {
		this.dao = dao;
	}

	public void publicManagementFeeInsert(PublicManagementFee publicManagementFee) {
		dao.publicManagementFeeInsert(publicManagementFee);
	}
	
	
	
}
