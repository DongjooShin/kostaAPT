package kosta.apt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosta.apt.domain.Property.Property;
import kosta.apt.persistence.PropertyDAO;

@Service
public class PropertyService {

	private PropertyDAO proDao;
	
	@Autowired
	public void setProDao(PropertyDAO proDao) {
		this.proDao = proDao;
	}
	
	
	public void APTSaleInsert(Property property){
		proDao.APTSaleInsert(property);
	}


	public List<Property> APTlist() {
		List<Property> list = null;
		list = proDao.APTlist();
		return list;
	}
	
	
}

