package kosta.apt.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kosta.apt.domain.Paging.SearchCriteria;
import kosta.apt.domain.complaint.Complaint;
import kosta.apt.domain.management.ManagementFee;
import kosta.apt.domain.member.Member;
import kosta.apt.domain.publicmanage.PublicManagementFee;
import kosta.apt.persistence.ComplaintDao;
import kosta.apt.persistence.PublicManageDao;


@RunWith(SpringJUnit4ClassRunner. class)

@ContextConfiguration (locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class JunitTest {

	@Autowired
//	private ComplaintDao dao;
	private PublicManageDao dao;
	
	
/*	@Test
	public void test() {
		
		SearchCriteria cri = new SearchCriteria();
		List<Complaint> list =dao.listSearchCriteria(cri);
		
		System.out.println(list.size());
	}*/
	
	
/*	@Test
	public void test(){
	List<ManagementFee> list=	dao.appropriation("rleo91");
	System.out.println(list.size());
	}*/
/*	@Test
	public void test(){
		List<ManagementFee> list=	dao.DataAppropriation(1);
		System.out.println(list.toString());
	}*/
	@Test
	public void test(){
		List<PublicManagementFee> list=	dao.getCityGraph(1);
		System.out.println(list.toString());
	}

}
