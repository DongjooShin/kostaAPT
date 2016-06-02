package kosta.apt.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kosta.apt.domain.member.AddressCity;
import kosta.apt.domain.member.AddressState;
import kosta.apt.domain.member.LoginCheck;
import kosta.apt.domain.member.Member;
import kosta.apt.persistence.MemberDao;
import kosta.apt.service.MemberService;

@Controller

@RequestMapping("/member/*")

public class MemberController {

	private MemberService memberService;

	@Autowired
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	@RequestMapping(value = "/loginCheck", method = RequestMethod.GET)
	public void loginCheck(Locale locale, Model model) {

	}

	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public void mapGet(Model model) {
		List<AddressState> stateList = memberService.getStateService();
		model.addAttribute("stateList", stateList);

	}

	@RequestMapping(value = "/map", method = RequestMethod.POST)
	public void mapPost(Model model, String selectState, String city) {
		String address = " ";
		int state = 0;
		List<String> APTnameList = null;
		List<AddressCity> cityList = null;
		List<AddressState> stateList = null;

		if (city == null) {
			stateList = memberService.getStateService();
			state = memberService.getStateNumService(selectState);
			cityList = memberService.getCityService(state);

		} else {

			address = selectState + " " + city + "%";
			APTnameList = memberService.getAptNameService(address);
			if (APTnameList != null) {
				model.addAttribute("APTnameList", APTnameList);
			}
		}

		model.addAttribute("stateList", stateList);
		model.addAttribute("cityList", cityList);
		model.addAttribute("getState", selectState);
		model.addAttribute("getCity", city);

	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public void signupGet(Model model, String state, String city, String aptname) {

		String fullAddres = memberService.getAddressService(state + " " + city, aptname);
		int  apt_aptgno = memberService.getAptNumService(state + " " + city, aptname);
		model.addAttribute("fullAddres", fullAddres);
		model.addAttribute("apt_aptgno",apt_aptgno);

	}

	@RequestMapping(value = "/confirmId", method = RequestMethod.GET)
	public void confirmIdPost(Model model, @RequestParam("id") String id) {
		System.out.println(id);
		int check = memberService.checkMemberIdService(id);
		model.addAttribute("id", id);
		model.addAttribute("check", check);
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signupPost(Member member, Model model, @RequestParam("id") String id) {
		member.setM_memberNo(id);
		memberService.inserMemberService(member);
		return "redirect:/main";
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public void testPost() {

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void loginGet() {

	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginPost(@RequestParam("formUsername") String id, @RequestParam("formPassword") String password,
												Model model,HttpSession session) {
		LoginCheck loginCheck = new LoginCheck();
		Member member=null;
		int num;
		loginCheck.setM_memberNo(id);
		loginCheck.setM_pass(password);
		 num= memberService.loginCheckService(loginCheck);
		 
		if(num==0){
			model.addAttribute("check", 10);
			
			return"member/login";
		}else{
			member = memberService.getMemberService(id);
			session.setAttribute("member", member);
			return "redirect:/main";
		}

	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session,Model model){
		session.invalidate();
		model.addAttribute("loginOn", 0);
		return "redirect:/main";

	}
	@RequestMapping(value="/changeMemberInfo", method = RequestMethod.GET)
	public void changeMemberInfoGet(HttpSession session,Model model){
		Member member = (Member)session.getAttribute("member");

		
		String phone= member.getM_homeTel(); 
		member.setS_phone1(phone.substring(0, 3));
		member.setS_phone2(phone.substring(3, 6));
		member.setS_phone3(phone.substring(6, phone.length()));
		
		String mphone= member.getM_tel();
		member.setS_mphone1(mphone.substring(0, 3));
		member.setS_mphone2(mphone.substring(3, 7));
		member.setS_mphone3(mphone.substring(7, mphone.length()));


		String emailArr[] = member.getM_email().split("@");
		member.setM_domain(emailArr[1]);
		member.setM_email(emailArr[0]);

		
		model.addAttribute("member", member);
		model.addAttribute("address", memberService.getAPTAddressService(member.getApt_APTGNo()));
		
	}

	@RequestMapping(value="/changeMemberInfo", method = RequestMethod.POST)
	public String changeMemberInfoPost(Member member,@RequestParam("id") String id){
		member.setM_memberNo(id);
		System.out.println(member.toString());
		member.sumTellNum();
		member.sumPhoneNum();
		member.sumEmail(member.getM_email(), member.getM_domain());
		memberService.updateMemberService(member);
		return "redirect:/main";
	}
	
	@RequestMapping(value="/aptNews", method = RequestMethod.GET)
	public void aptNewsGet(HttpSession session,Model model){
		
	}
}
