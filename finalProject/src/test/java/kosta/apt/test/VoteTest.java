package kosta.apt.test;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kosta.apt.domain.vote.Candidate;
import kosta.apt.persistence.VoteDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
public class VoteTest {

	private static Logger logger = LoggerFactory.getLogger(VoteTest.class);
	@Inject
	private VoteDao dao;

	/*
	@Test
	public void InsertTest() throws Exception {
		Candidate c = new Candidate("입주자대표", 5, "대졸", "주부", "전입주자대표", "잘할게요", "", 1, "");
		dao.insertCandidate(c);
	}
	 */	
	
	@Test
	public void DeleteTest() throws Exception {
		dao.deleteCandidate(4);
	}
	
	
	
	
	
	

}
