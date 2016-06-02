package kosta.apt.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartRequest;

import kosta.apt.domain.member.Member;
import kosta.apt.domain.vote.APTGroup;
import kosta.apt.domain.vote.Candidate;
import kosta.apt.domain.vote.Voter;
import kosta.apt.persistence.APTGroupDao;
import kosta.apt.persistence.VoteDao;

@Service
public class VoteService {
	
	@Inject
	public VoteDao votedao;
	//@Inject
	//public  APTGroupDao aptdao;

	public static VoteService service = new VoteService();
	
	public Member selectOneMemberService(String id) {
		
		return votedao.selectOneMember(id);
	}
	
	public Member selectGroupPresiService(int groupNo) {
		return votedao.selectGroupPresi(groupNo);
	}
	/*
	public Member selectBuildingPresiService(int groupNo,int buildingNo) {
		return votedao.selectBuildingPresi(groupNo,buildingNo);
	}

	public List<Candidate> selectAllEachCandiService(Candidate c) {
		return votedao.selectAllEachCandi(c);
	}

	public void levelDownGroupPresiService(String candi) {
		votedao.levelDownGroupPresi(candi);
	}
	public void levelDownBuildingPresiService(int groupNo){
		votedao.levelDownBuildingPresi(groupNo);
	}
	public Integer searchSymbolService(int s) {
		return votedao.searchSymbol(s);
	}

	public int maxCandiNoService() {
		return votedao.maxCandiNo();
	}

	public int maxVoterNoService() {
		return votedao.maxVoterNo();
	}

	public void insertCandidateService(HttpServletRequest request) throws Exception {

		String uploadPath = request.getRealPath("upload");
		int size = 20 * 1024 * 1024; // 20MB

		MultipartRequest multi = new MultipartRequest(request, uploadPath, size, "utf-8",
				new DefaultFileRenamePolicy());

		String candi_id = "tndus92";
		String symbol = multi.getParameter("new_symbol");
		String eduLevel = multi.getParameter("new_eduLevel");
		String job = multi.getParameter("new_job");
		String career = multi.getParameter("new_career");
		String promise = multi.getParameter("new_promise");
		String group = multi.getParameter("group");

		int isymbol = 0;
		if (symbol != null) {
			isymbol = Integer.parseInt(symbol);
		}

		Member m = selectOneMemberService(candi_id);
		int no = 0;
		no = maxCandiNoService() + 1;
		Candidate c = new Candidate();
		System.out.println(group);
		c.setCandidateNo(no);
		c.setCd_group(group);
		c.setCd_symbol(isymbol);
		c.setCd_eduLevel(eduLevel);
		c.setCd_job(job);
		c.setCd_career(career);
		c.setCd_promise(promise);
		c.setM_memberNo(candi_id);
		c.setApt_APTGNo(m.getApt_APTGNo());

		String b_fname = null;

		if (multi.getFilesystemName("img_name") != null) {
			b_fname = multi.getFilesystemName("img_name");
		} else {
			b_fname = "non_image.png";
		}

		c.setCd_imageName(b_fname);

		// 留뚯빟 �뙆�씪�씠 �씠誘몄��씪�떆 �뜽�꽕�씪 �씠誘몄�濡� 留뚮뱾�뼱以��떎.
		// aaa.gif => aaa_small.gif�씪�뒗 �씠由꾩쑝濡� 留뚮뱾�뼱以� 寃껋씠�떎.
		String pattern = b_fname.substring(b_fname.indexOf(".") + 1); // �솗�옣�옄留�
																		// 戮묒븘�샂
		String headName = b_fname.substring(0, b_fname.indexOf(".")); // �뙆�씪�씠由�

		// �씠誘몄�=> 媛앹껜�솕
		String imagePath = uploadPath + "\\" + b_fname;
		File src = new File(imagePath); // �썝蹂명뙆�씪�쓣 �씠誘몄� 媛앹껜�솕�븯���떎.

		// �뜽�꽕�씪�씠誘몄� 媛앹껜�솕
		String thumImagePath = uploadPath + "\\" + headName + "_small." + pattern;
		File dest = new File(thumImagePath);

		// (jpg,gif,png�씤吏��븘�땶吏� 寃��궗)�뜽�꽕�씪�씠誘몄��깮�꽦
		if (pattern.equals("jpg") || pattern.equals("png") || pattern.equals("png")) {
			ImageUtil.resize(src, dest, 210, ImageUtil.RATIO);
		}

		c.setCd_imageName(headName + "_small." + pattern);

		votedao.insertCandidate(c);
	}

	public Integer selectOneCandiService(String id) {
		return votedao.selectOneCandi(id);
	}

	public void updateVflagService(int v, String id) {
		votedao.updateVflag(v, id);
	}
	public void updateV2flagService(int v, String id){
		votedao.updateV2flag(v, id);

	}
	public int totalGroupNumService(int groupNo) {
		return votedao.totalGroupNum(groupNo);
	}

	public int totalGPVoterNum(int groupNo) {
		return votedao.totalGPVoterNum(groupNo);
	}
	public int totalBPVoterNum(int groupNo){
		return votedao.totalBPVoterNum(groupNo);
	}
	public void updateAllResiVflagService(HashMap<String, Integer> hash) {
		votedao.updateAllResiVflag(hash);
	}
	public void updateAllResiV2flagService(HashMap<String, Integer> hash) {
		votedao.updateAllResiV2flag(hash);
	}
	public void deleteCandidateService(int candidateNo) {
		votedao.deleteCandidate(candidateNo);
	}

	public void updateHitService(int hit) {
		votedao.updateHit(hit);
	}

	public String selectTopGroupCandiService(int groupNo) {
		return votedao.selectTopGroupCandi(groupNo);
	}
	
	public int selectTotalBuildingNumService(int groupNo){
		return votedao.selectTotalBuildingNum(groupNo);
	}
	public String selectTopBuildingCandiService(int groupNo){
		return votedao.selectTopBuildingCandi(groupNo);
	}
	public void updateGPGradeService(String mid) {
		votedao.updateGPGrade(mid);
	}
	public void updateBPGradeService(String mid) {
		votedao.updateGPGrade(mid);
	}

	public void insertVoterService(Voter v) {
		votedao.insertVoter(v);
	}
	public List<Voter> selectAllVoterService(Voter v){
		return votedao.selectAllVoter(v);
	}
	
	
	public APTGroup selectOneAPTService(int gno){
		return aptdao.selectOneAPT(gno);
	}
	public void updateGroupMainImageService(APTGroup apt){
		aptdao.updateGroupMainImage(apt);
	}
	*/
}
