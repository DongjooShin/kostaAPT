package kosta.apt.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kosta.apt.domain.member.Member;
import kosta.apt.service.VoteService;

@Controller
@RequestMapping("/voter/*")
public class VoterController {

	@Inject
	private VoteService voteService;
	
	@RequestMapping(value="groupPresiMain",method=RequestMethod.GET)
	public String managerGroupPresi(Model model){

		Member member = voteService.selectOneMemberService("manager01");
		Member gpm = voteService.selectGroupPresiService(member.getApt_APTGNo());
		
		if(member.getV_flag() == 2){
			model.addAttribute("m", member);
			model.addAttribute("gpm", gpm);
			return "/votes/groupPresiRegist";
		}else{
			model.addAttribute("m", member);
			return "/votes/groupPresiMain";
		}
	}

	@RequestMapping(value = "groupPresiRegist", method=RequestMethod.GET)
	public String managerGroupPresiRegist(Model model){
		Member member = voteService.selectOneMemberService("manager01");
		Member gpm = voteService.selectGroupPresiService(member.getApt_APTGNo());
		
		model.addAttribute("m", member);
		model.addAttribute("gpm", gpm);
		return "/votes/groupPresiRegist";
	}
	
	
	
	
	
	
	
	
	
}
