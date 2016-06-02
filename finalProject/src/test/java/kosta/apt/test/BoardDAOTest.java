package kosta.apt.test;

import static org.junit.Assert.*;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
public class BoardDAOTest {

	private static Logger logger = LoggerFactory.getLogger(BoardDAOTest.class);
/*	@Inject
	
	private BoardDAO dao;

	
	 * @Test public void test() throws Exception { BoardVO board = new
	 * BoardVO(); board.setTitle("새로운글"); board.setContent("새로운글 내용");
	 * board.setWriter("user1010"); dao.create(board); }
	 

	
	 * @Test public void testListCriteria() throws Exception { Criteria cri =
	 * new Criteria(); cri.setPage(0); cri.setPerPageNum(3);
	 * 
	 * List<BoardVO> list = dao.listCriteria(cri);
	 * 
	 * for(BoardVO boardVO : list){ logger.info(boardVO.getBno() +":"+
	 * boardVO.getTitle()); }
	 * 
	 * 
	 * }
	 
	
	@Test
	public void testDynamic()throws Exception{
		SearchCriteria cri =new SearchCriteria();
		cri.setPage(1);
		cri.setKeyword("qa");
		cri.setSearchType("t");
		
		logger.info("========================");
		
		List<BoardVO> list = dao.listSearch(cri);
		for(BoardVO boardVo : list){
			logger.info(boardVo.getBno()+" : " + boardVo.getTitle());
			
		}
		

		logger.info("========================");
		
		

		logger.info("COUNT" + dao.listSearchCount(cri));
		*/
	}

