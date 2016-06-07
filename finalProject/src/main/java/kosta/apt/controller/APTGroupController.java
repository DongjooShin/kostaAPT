package kosta.apt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/aptGroup/*")
public class APTGroupController {

	@RequestMapping(value="Main",method=RequestMethod.GET)
	public String aptgroupMain(){
		return "/Community/APTGroupMain";
	}
}
