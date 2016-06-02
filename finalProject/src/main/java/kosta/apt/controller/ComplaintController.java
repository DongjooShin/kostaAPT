package kosta.apt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ComplaintController {

	
	@RequestMapping(value = "/complaint", method = RequestMethod.GET )
	public String Complaint(){
		
		
		return "complaint/mypageMain";
	}
	
	@RequestMapping("/SmartEditor2Skin.html")
	public String cc(){
		return "smart/SmartEditor2Skin";
	}
}
