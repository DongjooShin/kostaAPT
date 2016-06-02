package kosta.apt.mapper;

import java.util.HashMap;
import java.util.List;

import kosta.apt.domain.member.Member;
import kosta.apt.domain.vote.Candidate;
import kosta.apt.domain.vote.Voter;


public interface VoterMapper {

	public Member selectOneMember(String id);
	public Member selectGroupPresi(int groupNo);
	/*
	public Member selectBuildingPresi(HashMap<String, Integer> hm);
	public List<Candidate> selectAllEachCandi(Candidate c);
	public int levelDownGroupPresi(String candi);
	public Integer searchSymbol(int s);
	public int maxCandiNo();
	public int maxVoterNo();
	public int insertCandidate(Candidate c);
	public Integer selectOneCandi(String id);
	public int updateVflag(Member m);
	public int updateV2flag(Member m);
	public int totalGroupNum(int groupNo);
	public int totalGPVoterNum(int groupNo);
	public int totalBPVoterNum(int groupNo);
	public int updateAllResiVflag(HashMap<String, Integer> hash);
	public int updateAllResiV2flag(HashMap<String, Integer> hash);
	public int deleteCandidate(int candidateNo);
	public int updateHit(int hit);
	public String selectTopGroupCandi(int groupNo);
	public String selectBuildingGroupCandi(HashMap<String, Integer> hm);
	public int updateGPGrade(String mid);
	public int updateBPGrade(String mid);
	public int insertVoter(Voter v);
	public List<Voter> selectAllVoter(Voter v);
	public int selectTotalBuildingNum(int groupNo);
	public List<Integer> AllBuildingNo(int groupNo);
	public int levelDownBuildingPresi(int groupNo);

*/	
	

}
