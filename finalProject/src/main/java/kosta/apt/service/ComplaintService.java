package kosta.apt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kosta.apt.domain.Paging.SearchCriteria;
import kosta.apt.domain.complaint.Complaint;
import kosta.apt.persistence.ComplaintDao;

@Service
public class ComplaintService {
	
	
	@Autowired
	private	ComplaintDao dao;
	
	public void create(Complaint complaint){
		dao.create(complaint);
	}

	public List<Complaint> listSearchCriteria(SearchCriteria cri) {
		// TODO Auto-generated method stub
		return dao.listSearchCriteria(cri);
	}

	public int listSearchCount(SearchCriteria cri) {
		// TODO Auto-generated method stub
		return dao.listSearchCount(cri);
	}

	public Complaint read(int cp_complaintNo) {
		// TODO Auto-generated method stub
		return dao.readComplaint(cp_complaintNo);
	}

	public void update(Complaint complaint) {
		// TODO Auto-generated method stub
		dao.update(complaint);
	}

	public void delete(int cp_complaintNo) {
		// TODO Auto-generated method stub
		dao.delete(cp_complaintNo);
		
	}

	public List<Complaint> mypageComplaint() {
		// TODO Auto-generated method stub
		return dao.mypageComplaint();
	}

}
