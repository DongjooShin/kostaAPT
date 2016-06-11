package kosta.apt.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kosta.apt.domain.member.AptTransactionPrice;
import kosta.apt.domain.member.LoginCheck;
import kosta.apt.domain.member.MailMail;
import kosta.apt.domain.member.Member;
import kosta.apt.domain.member.RssReader;
import kosta.apt.service.MemberService;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

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
	public void aptNewsGet(HttpSession session,Model model, @RequestParam("newsNum")int newsNum, @RequestParam("page")int page)throws Exception{
		List<Map<String, String>> newsList = null;
		RssReader rssReader = new RssReader();
		newsList = rssReader.getSynFeed(newsNum);
		int startPage = page;
		int endPage = startPage+4;
		
		model.addAttribute("listSize", newsList.size()-5);
		model.addAttribute("newsNum", newsNum);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("newsList", newsList);

		
	}
	
	@RequestMapping(value="/FileUploadForm",method = RequestMethod.GET)
	public void showForm(AptTransactionPrice aptTransactionPrice, ModelMap model) {
		model.addAttribute("excel", aptTransactionPrice);
	}

	@SuppressWarnings("resource")
	@RequestMapping(value="/FileUploadForm",method = RequestMethod.POST)
	public String processForm(@ModelAttribute("excel") AptTransactionPrice aptTransactionPrice, BindingResult result) throws IOException {
		 List<AptTransactionPrice> list = new ArrayList<AptTransactionPrice>();
		 AptTransactionPrice aptTransactionPrice1= null;
		if (!result.hasErrors()) {
			FileOutputStream outputStream = null;

			// save & load location
			String filePath = System.getProperty("java.io.tmpdir") + aptTransactionPrice.getFile().getOriginalFilename();
			
			String[] b =  filePath.split("[.]");

			// save
			outputStream = new FileOutputStream(new File(filePath));
			outputStream.write(aptTransactionPrice.getFile().getFileItem().get());
		
			// load
			FileInputStream file = new FileInputStream(new File(filePath));

			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			System.out.println(workbook.getSheetName(0));
			Iterator<Row> rowIterator = sheet.iterator();
			
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (row.getRowNum() == 0) {
					
				} else {
					String primaryNum = "201604"+workbook.getSheetName(0)+row.getRowNum();
					String[] aptPrice = row.getCell(5).getStringCellValue().split("[,]");
					aptTransactionPrice1= new AptTransactionPrice(primaryNum,
																			 row.getCell(0).getStringCellValue(), 
																			 row.getCell(1).getStringCellValue(), 
																			 row.getCell(2).getStringCellValue(), 
																			 Float.parseFloat(row.getCell(3).getStringCellValue()), 
																			 row.getCell(4).getStringCellValue(),
																			 Integer.parseInt( aptPrice[0]+aptPrice[1]), 
																			 Integer.parseInt(row.getCell(6).getStringCellValue()),
																			 Integer.parseInt( row.getCell(7).getStringCellValue()),
																			 row.getCell(8).getStringCellValue()
																			 );
	
					memberService.updateRealTransactionPriceService(aptTransactionPrice1);
				}
			}
			
			file.close();


			
			return "redirect:/main";
		}
		return "main";
	}
	
	
	@RequestMapping(value="/sendMail",method = RequestMethod.GET)
	public void sendMail(Model model) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/Spring-Mail.xml");
		MailMail mm = (MailMail) context.getBean("MailMail");
		mm.sendMail("aptmanager.kost111@gmail.com", "dongjoo1029@naver.com", "Testing123", 
	    		   "Testing only \n\n Hello Spring Email Sender");
	}
	@RequestMapping(value="/mypageTest",method = RequestMethod.GET)
	public void mypageTest(Model model) {
	
	}
	
	@RequestMapping(value="/certification",method = RequestMethod.GET)
	public void certificationGet(Model model) {
	
	}


	 @ResponseBody
	@RequestMapping(value = "/certification", method = RequestMethod.POST,produces ="application/json")
		public HashMap<String, String>certificationPost(@RequestParam("email") String email,@RequestParam("domain") String domain) {
		 HashMap<String, String> map = new HashMap<String, String>();
		 String Email = email+"@"+domain;
			ApplicationContext context = new ClassPathXmlApplicationContext("classpath:/Spring-Mail.xml");
			MailMail mm = (MailMail) context.getBean("MailMail");
			
		
			int Num1[] = new int[8];
			int Num2[] = new int[8];
			for(int i=0; i<Num1.length; i++){
				Num1[i]  = 97 + (int)(Math.random()*26);
				Num2[i]  = 1 + (int)(Math.random()*9);
			}
			String key = ""+((char)Num1[0])+((char)Num1[1])+((char)Num1[2])+((char)Num1[3])+
									Num2[0]+Num2[1]+Num2[2]+Num2[3]+
									((char)Num1[4])+((char)Num1[5])+((char)Num1[6])+((char)Num1[7])+
									Num2[4]+Num2[5]+Num2[6]+Num2[7];
			
			String text = "AptManager 회원 인증을 위한 메일입니다 \n\n 아래 key값을 복사하여 입력해주세요.\n\n\t\t"+key+"\n\n\t감사합니다.";
			mm.sendMail("aptmanager.kost111@gmail.com", Email, "AptManager 회원 인증",  text);
			map.put("key", key);
			return map;
	}


	
}
