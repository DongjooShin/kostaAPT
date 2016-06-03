package finalProject;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kosta.apt.domain.Paging.SearchCriteria;
import kosta.apt.domain.complaint.Complaint;
import kosta.apt.persistence.ComplaintDao;


@RunWith(SpringJUnit4ClassRunner. class)

@ContextConfiguration (locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class asdasdsadd {

	@Autowired
	private ComplaintDao dao;
	
	
	@Test
	public void test() {
		
		SearchCriteria cri = new SearchCriteria();
		List<Complaint> list =dao.listSearchCriteria(cri);
		
		System.out.println(list.size());
	}

}
