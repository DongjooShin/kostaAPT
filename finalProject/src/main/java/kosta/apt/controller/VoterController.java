package kosta.apt.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import kosta.apt.domain.member.Member;
import kosta.apt.domain.vote.Candidate;
import kosta.apt.domain.vote.ImageUtil;
import kosta.apt.domain.vote.VoteResult;
import kosta.apt.domain.vote.Voter;
import kosta.apt.service.VoteService;

@Controller
@RequestMapping("/voter/*")
public class VoterController {

	@Resource(name = "uploadPath")
	private String uploadPath;
	@Inject
	private VoteService voteService;

	
//-------------------------case : group presi----------------------------------
	@RequestMapping(value = "groupPresiMain", method = RequestMethod.GET)
	public String managerGroupPresi(Model model) {

		Member member = voteService.selectOneMemberService("resident01");
		Member gpm = voteService.selectGroupPresiService(member.getApt_APTGNo());
		
		Candidate c = new Candidate();
		c.setApt_APTGNo(1);
		c.setCd_group("입주자대표");
		List<Candidate> groupPresi = voteService.selectAllEachCandiService(c);
		List<VoteResult> vr = new ArrayList<>();
		
		int groupNum = voteService.groupGPNumService(1);
	
		getVoteRating(model);

		for(int i=0; i<groupPresi.size(); i++){
			VoteResult temp = new VoteResult();
			String name = voteService.getMemberName(groupPresi.get(i).getM_memberNo());
			temp.setName(name);
			temp.setPercent(groupPresi.get(i).getCd_voteNum()*100/groupNum);
			vr.add(temp);
		}
		
		model.addAttribute("vr",vr);
		//---------manager-------------------------	
		if(member.getM_grade()==2){
			if (member.getV_flag() == 2) {
				model.addAttribute("m", member);
				model.addAttribute("gpm", gpm);
				return managerGroupPresiRegist(model);
			} else {
				model.addAttribute("m", member);
				return "/votes/groupPresiMain";
			}
			//----------------------------------
		}else{
			//----------resident-------------
			model.addAttribute("m", member);
			model.addAttribute("gpm", gpm);
			model.addAttribute("groupPresi", groupPresi);
			return "/votes/groupPresiMain";
		}
	}

	@RequestMapping(value = "groupPresiRegist", method = RequestMethod.GET)
	public String managerGroupPresiRegist(Model model) {
		Member member = voteService.selectOneMemberService("manager01");
		Member gpm = voteService.selectGroupPresiService(member.getApt_APTGNo());

		Candidate c = new Candidate();
		c.setCd_group("입주자대표");
		c.setApt_APTGNo(member.getApt_APTGNo());

		List<Candidate> clist = voteService.selectAllEachCandiService(c);
		
		if (clist != null) {
			voteService.updateVflagService(2, member.getM_memberNo());
		} else {
			voteService.updateVflagService(1, member.getM_memberNo());
		}

		model.addAttribute("m", member);
		model.addAttribute("gpm", gpm);
		model.addAttribute("clist", clist);
		return "/votes/groupPresiRegist";
	}

	@RequestMapping(value = "insertCandidate", method = RequestMethod.POST)
	public String insertGPCandidate(Candidate c, MultipartFile file, Model model) throws Exception {
		Member member = voteService.selectOneMemberService("manager01");
		Member gpm = voteService.selectGroupPresiService(member.getApt_APTGNo());
		List<Candidate> clist = voteService.selectAllEachCandiService(c);
		model.addAttribute("m", member);
		model.addAttribute("gpm", gpm);
		model.addAttribute("clist", clist);
		
		
		if (clist != null) {
			voteService.updateVflagService(2, member.getM_memberNo());
		} else {
			voteService.updateVflagService(1, member.getM_memberNo());
		}
		
		if (gpm != null) {
			model.addAttribute("GPmessage", "현 입주자대표의 권한이 하향되지 않았습니다.");
		} else {
			if (voteService.searchSymbolService(c.getCd_symbol()) != null) {
				model.addAttribute("GPmessage", "이미 존재하는 기호입니다.");
			} else {
				if (voteService.selectOneCandiService(c.getM_memberNo()) != null) {
					model.addAttribute("GPmessage", "이미 존재하는 후보입니다.");
				} else {
					String b_fname = null;
					if (!file.isEmpty()) {
						b_fname = file.getOriginalFilename();
					}else{
						b_fname = "non_image.png";
					}
					
					file.transferTo(new File(uploadPath,b_fname));
					// aaa.gif => aaa_small.gif
					String pattern = b_fname.substring(b_fname.indexOf(".") + 1);
					String headName = b_fname.substring(0, b_fname.indexOf("."));
					String imagePath = uploadPath + "\\" + b_fname;
						
					File src = new File(imagePath);
					String thumImagePath = uploadPath + "\\" + headName + "_small." + pattern;
					File dest = new File(thumImagePath);
					

					if (pattern.equals("jpg") || pattern.equals("png") || pattern.equals("png")) {
						ImageUtil.resize(src, dest, 210, ImageUtil.RATIO);
					}

					c.setCd_imageName(headName + "_small." + pattern);
					
					
					c.setApt_APTGNo(member.getApt_APTGNo());
					if(voteService.maxCandiNoService() != null)
					{	
						c.setCandidateNo(voteService.maxCandiNoService() + 1);
					}else{
						c.setCandidateNo(1);
					}
					voteService.insertCandidateService(c);
				}
			}
		}

		return managerGroupPresiRegist(model);
	}

	@RequestMapping(value = "levelDownGP")
	public String levelDownGP(Model model) {
		Member member = voteService.selectOneMemberService("manager01");
		Member gpm = voteService.selectGroupPresiService(member.getApt_APTGNo());

		voteService.levelDownGroupPresiService(gpm.getM_memberNo());
		return managerGroupPresiRegist(model);
	}

	@RequestMapping(value="deleteGP",method=RequestMethod.POST)
	public String deleteGP(@ModelAttribute("cno")int cno, Model model){
		voteService.deleteCandidateService(cno);
		
		return managerGroupPresiRegist(model);
	}
	
	@RequestMapping(value="startVote",method=RequestMethod.GET)
	public String startVote(Model model){
		voteService.updateAllVflagService(2,1);
	
		getVoteRating(model);
		
		Member member = voteService.selectOneMemberService("manager01");
		voteService.updateVflagService(3, member.getM_memberNo());
		
		return  managerGroupPresi(model);
	}
	
	public void getVoteRating(Model model){
		int voterNum = voteService.voterGPNumService(1,"입주자대표");
		int groupNum = voteService.groupGPNumService(1);
		int voterate = voterNum*100/groupNum;
	
		model.addAttribute("groupNum", groupNum);
		model.addAttribute("voterNum", voterNum);
		model.addAttribute("voterate", voterate);
	}
	@RequestMapping(value="closeVote",method=RequestMethod.GET)
	public String closeVote(Model model){
		voteService.updateAllVflagService(4, 1);
		voteService.updateVflagService(4, "manager01");
		return managerGroupPresi(model);
	}
	@RequestMapping(value="returnFirst",method=RequestMethod.GET)
	public String returnFirst(Model model){
		voteService.updateAllVflagService(1, 1);
		voteService.updateVflagService(1, "manager01");
		return managerGroupPresi(model);
	}
	
	@RequestMapping(value="voteSubmitOk",method=RequestMethod.POST)
	public String voteSubmitOk(@ModelAttribute("pass")String pass,@ModelAttribute("onechoose")String[] id,Model model){
		Member m = voteService.selectOneMemberService("resident01");
		
		if(m.getM_pass().equals(pass)){
			
			HashMap<String, Integer> map = new HashMap<>();
			map.put("buildingNo", m.getM_buildingNo());
			map.put("roomNo", m.getM_roomNo());
			Voter vcheck = voteService.selectExistVoterService(map);
			
			if(vcheck != null){
				String message = "이미 투표한 호수입니다.";
				model.addAttribute("msg", message);
				voteService.updateVflagService(3, "resident01");
			}else{
			
				for(int i=0; i<id.length; i++){
					System.out.println(id[i]);
				}
				int hit=0;
				if(id != null){
					hit = Integer.parseInt(id[0]);
				}
				voteService.updateHitService(hit);
				voteService.updateVflagService(3, "resident01");
				Voter v = new Voter(voteService.maxVoterNoService()+1, m.getM_buildingNo(), m.getM_roomNo(), m.getM_memberNo(), m.getApt_APTGNo(), "입주자대표");
			
				voteService.insertVoterService(v);
			}
		}else{
			String message = "비밀번호 오류";
			model.addAttribute("msg", message);
		}
		return managerGroupPresi(model);
	}
	
//---------------------------------------------------------------------------------------
	
//-----------------------------case : building presi-------------------------------------
	
	@RequestMapping(value="buildingPresiMain",method=RequestMethod.GET)
	public String buildingPresiMain(){
		
		
		return "/votes/buildingPresiMain";
	}
	
	
	
	
	
	
	
	
}


	
	
	
	
	
	
