package kosta.apt.controller;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kosta.apt.domain.SiteNotice.SNPageMaker;
import kosta.apt.domain.SiteNotice.SNSearchCriteria;
import kosta.apt.domain.SiteNotice.SiteNotice;
import kosta.apt.domain.member.Member;
import kosta.apt.service.SiteNoticeService;

@Controller
@RequestMapping("/siteNotice/*")
public class SiteNoticeController {

	@Resource(name="uploadPath")
	private String uploadDir;
	
	private SiteNoticeService snService;

	@Autowired
	public void setSnService(SiteNoticeService snService) {
		this.snService = snService;
	}
	
	
	@RequestMapping(value="siteNoticeAllList", method=RequestMethod.GET)
	public String siteNoticeAllList(@ModelAttribute("cri")SNSearchCriteria cri,Model model,HttpSession session){
		Member m=(Member) session.getAttribute("member");
		System.out.println("list All=>search 들어옴");
		
		model.addAttribute("list",snService.listSearchCriteria(cri));
		
		SNPageMaker pageMaker=new SNPageMaker();
		pageMaker.setCri(cri);
		//pageMaker.setTotalCount(4);//총글갯수넣음
		pageMaker.setTotalCount(snService.listSearchCount(cri));//글의 총갯수 구하는 것
		
		model.addAttribute("pageMaker",pageMaker);
		
		return "/siteNotice/siteNoticeAllList";
	}
	
	
	@RequestMapping(value="siteNoticeRegist",method=RequestMethod.GET)
	public String registerGET(SiteNotice gn)throws Exception{
		System.out.println("SNregister page ok");
		return "/siteNotice/siteNoticeRegist";
		
	}
	
	@RequestMapping(value="siteNoticeRegist",method=RequestMethod.POST)
	public String registerPOST(SiteNotice sn,Model model)throws Exception{
		System.out.println("sn controller 들어옴!!!!");
	
		if(snService.maxSnNo() != null){
			sn.setSn_siteNoticeNo(snService.maxSnNo()+1);
		}else{
			sn.setSn_siteNoticeNo(1);
		}
		sn.setM_memberNo("admin");
		
		// 1.
		MultipartFile multiPartFile = sn.getUploadFile();// file객체얻어옴(upload됨)
		// 2.
		if (!multiPartFile.isEmpty()) {
			String filename = multiPartFile.getOriginalFilename();// file의 실제이름
																	// 얻어옴
			sn.setSn_fileName(filename);// DB에 file이름 저장할수있음
			System.out.println("a뭐야"+filename);
			multiPartFile.transferTo(new File(uploadDir, filename));// 파일을 업로드

		}else{
			sn.setSn_fileName("NULL");
		}
			
		snService.regist(sn);
		
		return "redirect:/siteNotice/siteNoticeAllList";
		
	}

	@RequestMapping(value="snRead",method=RequestMethod.GET)
	public String read(@RequestParam("sn_siteNoticeNo")int sn_siteNoticeNo,@ModelAttribute("cri")SNSearchCriteria cri,Model model)throws Exception{
		snService.snHitCount(sn_siteNoticeNo);
		model.addAttribute("sn",snService.snRead(sn_siteNoticeNo));	
		return "/siteNotice/siteNoticeRead";
	}
	
	@RequestMapping(value="snModify",method=RequestMethod.GET)
	public String modifyGET(int sn_siteNoticeNo,Model model)throws Exception{
		System.out.println(sn_siteNoticeNo);
		model.addAttribute("sn",snService.snRead(sn_siteNoticeNo));
		return "/siteNotice/siteNoticeModify";
	}
	@RequestMapping(value="snModify",method=RequestMethod.POST)
	public String modifyGET(SiteNotice sn,RedirectAttributes rttr)throws Exception{
		System.out.println("sn modify post controller 들어옴");
		System.out.println("객체내용"+sn.toString());
		snService.snModify(sn);
		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/siteNotice/siteNoticeAllList";
		
	}
	
	@RequestMapping(value="snDelete",method=RequestMethod.GET)
	public String read(@RequestParam("sn_siteNoticeNo")int sn_siteNoticeNo)throws Exception{
		snService.snDelete(sn_siteNoticeNo);
		
		return "redirect:/siteNotice/siteNoticeAllList";
		
	}
	
	@RequestMapping("/siteNotice_download")
	public ModelAndView fileDown(@RequestParam String filename)throws Exception{
		File file=new File(uploadDir, filename);//file객체구하기
		
		return new ModelAndView("GndownloadView","downloadFile",file);//뷰의이름,가져갈객체 키값,데이터값
	}
	
//    
//	model.addAttribute("msg", "문의가 접수되었습니다.");
//	model.addAttribute("url", "siteNoticeRegist.jsp");
//	return "/siteNotice/registComplete";
//


}
