package kosta.apt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MypageController {

	
	
	
	//관리자인지 맴버인지 확인후 페이지 경로 결정
	//지금은 확인불가능해서 맴버로 결정
	@RequestMapping(value = "/Mypage", method = RequestMethod.GET)
	public String SelectMypage(){
		
		return "mypage/mypageMain";
	}
}
