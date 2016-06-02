package kosta.apt.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import kosta.apt.domain.Property.Property;
import kosta.apt.service.MemberService;
import kosta.apt.service.PropertyService;

@Controller
@RequestMapping()
public class PropertyController {
	
	
	@Resource(name="uploadPath")
	private String uploadPath;

	private PropertyService propertyService;

	@Autowired
	public void setPropertyService(PropertyService propertyService) {
		this.propertyService = propertyService;
	}
	
	@RequestMapping(value="/aptSale", method = RequestMethod.GET)
	public void aptSaleView(Model model){
		model.addAttribute("aptInsert", new Property());
		System.out.println("뷰로 이동하겠습니다.1");
		
	}
	
	//아파트 매물등록
	@RequestMapping(value="/aptSale", method = RequestMethod.POST)
	public String aptSale(@ModelAttribute("aptInsert") Property property
						 , MultipartFile file, Model model	)throws Exception{

		System.out.println("originalName:" + file.getOriginalFilename()+"파일이름이다.");
		System.out.println("size:"+ file.getSize()+"사이즈이다");
		System.out.println("contentType:"+file.getContentType());
		
		String saveName = uploadFile(file.getOriginalFilename(), file.getBytes());
		
		property.setPr_imageName(saveName);
		
	
		
		model.addAttribute("saveName",  saveName);
		return "redirect:/aptlistALL";
			
		
	}
	
	//실제파일 폴더에 저장되는 부분
	private String uploadFile(String originalName, byte[] fileDate)throws Exception{
		
		UUID uid = UUID.randomUUID();
		System.out.println(uid.toString()+"uuid의 고유이름");
		
		String savedName = uid.toString() + "_"+originalName;
		
		File target = new File(uploadPath,savedName);
		
		FileCopyUtils.copy(fileDate, target);
		
		return savedName;
	}
	
	
	//아파트매물 화면출력
	@RequestMapping(value="/aptlistALL", method = RequestMethod.GET)
	public void APTlistALL(Model model){
		List<Property> list = propertyService.APTlist();
		
		model.addAttribute("list", list);

	}

	
	@RequestMapping(value="/uploadform", method = RequestMethod.GET)
	public void uploadForom(){
		
	}

/*	@RequestMapping(value="/uploadform", method = RequestMethod.POST)
	public ModelAndView write(HttpServletRequest req,
			@ModelAttribute("property") Property property, HttpSession session,
			MultipartHttpServletRequest mhsq)throws IllegalStateException, IOException{
		
		
		
	}*/
	
/*	//기존방식 업로드
	@RequestMapping(value="/uploadform", method = RequestMethod.POST)
	public void uploadform(MultipartFile file, Model model)throws Exception{
		
		System.out.println("orinalNmae:"+file.getOriginalFilename()+"오리지날이름");
		System.out.println("size:"+file.getSize());
		System.out.println("contentType:"+file.getContentType());
		
		String saveName = uploadFile(file.getOriginalFilename(), file.getBytes());
		
		model.addAttribute("saveName",  saveName);
		
	}*/
	
	
/*
	@RequestMapping(value="/aptSale", method = RequestMethod.POST)
	public String aptSale(Property property){
		
		property.setPr_group("1");
		propertyService.APTSaleInsert();
		
		System.out.println("DB에 저장 할꺼얌");
	
		return "redirect:/controller/APTlistALL";
	}
	
	
	@RequestMapping(value="APTlistALL", method = RequestMethod.GET)
	public void listALL(Model model)throws Exception{
		
	
	}*/
	
	
}
