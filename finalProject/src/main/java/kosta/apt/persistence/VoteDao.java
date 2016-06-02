package kosta.apt.persistence;


import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kosta.apt.domain.member.Member;
import kosta.apt.domain.vote.Candidate;
import kosta.apt.domain.vote.Voter;
import kosta.apt.mapper.VoterMapper;

@Repository
public class VoteDao {
	
	public static VoteDao dao = new VoteDao();

	private SqlSession sqlSession;//세션테플레이트 생성
	
	@Autowired
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
		
	}
	
	
	public Member selectOneMember(String id){
		Member m = new Member();
		
		m = sqlSession.getMapper(VoterMapper.class).selectOneMember(id);
		
		return m;
	}

	public Member selectGroupPresi(int groupNo) {
		Member m = null;

		m = sqlSession.getMapper(VoterMapper.class).selectGroupPresi(groupNo);
		
		return m;
	}
	/*
	 public Member selectBuildingPresi(int groupNo,int buildingNo) {
		 SqlSession sqlSession = getSqlSessionFactory().openSession();
		 Member list = null;
		 HashMap<String, Integer> hm = new HashMap<String,Integer>();
		 hm.put("groupNo", groupNo);
		 hm.put("buildingNo", buildingNo);
		 try {
			list = sqlSession.getMapper(VoterMapper.class).selectBuildingPresi(hm);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		 
		return list; 
	 }

	public List<Candidate> selectAllEachCandi(Candidate c){
		List<Candidate> list=null;
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		
		try {
			list = sqlSession.getMapper(VoterMapper.class).selectAllEachCandi(c);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return list;
	}
	
	public void levelDownGroupPresi(String candi){
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int re=0;
		try { 
			re = sqlSession.getMapper(VoterMapper.class).levelDownGroupPresi(candi); 
			if(re>0){
				   sqlSession.commit();
			}else{
				sqlSession.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
	}
	public void levelDownBuildingPresi(int groupNo) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int re=0;
		try { 
			re = sqlSession.getMapper(VoterMapper.class).levelDownBuildingPresi(groupNo); 
			if(re>0){
				   sqlSession.commit();
			}else{
				sqlSession.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		
	}
	public Integer searchSymbol(int s){
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		Integer re = 0;
		
		try {
			re = sqlSession.getMapper(VoterMapper.class).searchSymbol(s);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		
		return re;
	}
	public int maxCandiNo(){
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int re = 0;
		
		try {
			re = sqlSession.getMapper(VoterMapper.class).maxCandiNo();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		
		return re;
	}
	public int maxVoterNo(){
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int re = 0;
		
		try {
			re = sqlSession.getMapper(VoterMapper.class).maxVoterNo();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		
		return re;
	}
	
	public void insertCandidate(Candidate c){
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int re=0;
		try {
			re = sqlSession.getMapper(VoterMapper.class).insertCandidate(c);
			if(re>0){
				sqlSession.commit();
			}else{
				sqlSession.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
	}
	public Integer selectOneCandi(String id){
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int re=0;
		
		try {
			re = sqlSession.getMapper(VoterMapper.class).selectOneCandi(id);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return re;
	}

	public void updateVflag(int v,String id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int re=0;
		Member m = new Member();
		m.setM_memberNo(id);
		m.setV_flag(v);
		try {
			re=sqlSession.getMapper(VoterMapper.class).updateVflag(m);
			if(re>0){
				sqlSession.commit();
			}else{
				sqlSession.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		
	}
	public void updateV2flag(int v, String id) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int re=0;
		Member m = new Member();
		m.setM_memberNo(id);
		m.setV2_flag(v);
		try {
			re=sqlSession.getMapper(VoterMapper.class).updateV2flag(m);
			if(re>0){
				sqlSession.commit();
			}else{
				sqlSession.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		
	}
	public int totalGroupNum(int groupNo) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int count =0;
		
		try {
			count = sqlSession.getMapper(VoterMapper.class).totalGroupNum(groupNo);
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return count;
	}

	public int totalGPVoterNum(int groupNo) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int count =0;
		
		try {
			count = sqlSession.getMapper(VoterMapper.class).totalGPVoterNum(groupNo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return count;
	}

	public int totalBPVoterNum(int groupNo) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int count =0;
		
		try {
			count = sqlSession.getMapper(VoterMapper.class).totalBPVoterNum(groupNo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return count;
	}

	public void updateAllResiVflag(HashMap<String, Integer> hash) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int re=0;
		
		try {
			re = sqlSession.getMapper(VoterMapper.class).updateAllResiVflag(hash);
			if(re>0){
				sqlSession.commit();
			}else{
				sqlSession.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		
	}
	public void updateAllResiV2flag(HashMap<String, Integer> hash){
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int re=0;
		
		try {
			re = sqlSession.getMapper(VoterMapper.class).updateAllResiV2flag(hash);
			if(re>0){
				sqlSession.commit();
			}else{
				sqlSession.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
	}
	public void deleteCandidate(int candidateNo) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int re=0;
		
		try {
			re = sqlSession.getMapper(VoterMapper.class).deleteCandidate(candidateNo);
			if(re>0){
				sqlSession.commit();
			}else{
				sqlSession.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		
	}

	public void updateHit(int hit) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int re=0;
		try {
			re = sqlSession.getMapper(VoterMapper.class).updateHit(hit);
			if(re>0){
				sqlSession.commit();
			}else{
				sqlSession.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
	}

	public String selectTopGroupCandi(int groupNo) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		String re=null;
		try {
			re = sqlSession.getMapper(VoterMapper.class).selectTopGroupCandi(groupNo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close(); 
		}
			
		return re;
	}
	
	public int selectTotalBuildingNum(int groupNo) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int re=0;
		try {
			re = sqlSession.getMapper(VoterMapper.class).selectTotalBuildingNum(groupNo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close(); 
		}
			
		return re;
	}
	public String selectTopBuildingCandi(int groupNo) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		String re=null;
		
		List<String> list = new ArrayList<String>();
		List<Integer> temp = new ArrayList<Integer>();
		List<Integer> wholeBuil = new ArrayList<Integer>();
		
		temp=sqlSession.getMapper(VoterMapper.class).AllBuildingNo(groupNo);
		
		for(int i=0; i<temp.size(); i++){
			boolean flag=false;
			
			for(int j=0; j<wholeBuil.size(); j++){
				if(wholeBuil.get(j) == temp.get(i)){
					flag=true;
				}
			}
			if(flag == false){
				wholeBuil.add(temp.get(i));
			}
		}
		
		HashMap<String, Integer> hm = new HashMap<String,Integer>();
		hm.put("groupNo", groupNo);
		
		try {
			for(int i=0; i<wholeBuil.size(); i++){
				hm.put("builNo", wholeBuil.get(i));
				re = sqlSession.getMapper(VoterMapper.class).selectBuildingGroupCandi(hm);
				list.add(re);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close(); 
		}
			
		return re;
	}
	public void updateGPGrade(String mid) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int re=0;
		try {
			re = sqlSession.getMapper(VoterMapper.class).updateGPGrade(mid);
			if(re>0){
				sqlSession.commit();
			}else{
				sqlSession.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
	}
	public void updateBPGrade(String mid) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int re=0;
		try {
			re = sqlSession.getMapper(VoterMapper.class).updateBPGrade(mid);
			if(re>0){
				sqlSession.commit();
			}else{
				sqlSession.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
	}
	public void insertVoter(Voter v) {
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		int re=0;
		try {
			re = sqlSession.getMapper(VoterMapper.class).insertVoter(v);
			if(re>0){
				sqlSession.commit();
			}else{
				sqlSession.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		
	}

	public List<Voter> selectAllVoter(Voter v) {
		List<Voter> vlist = null;
		SqlSession sqlSession = getSqlSessionFactory().openSession();
		
		try {
			vlist = sqlSession.getMapper(VoterMapper.class).selectAllVoter(v);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			sqlSession.close();
		}
		return vlist;
	}

	
*/

	





}
