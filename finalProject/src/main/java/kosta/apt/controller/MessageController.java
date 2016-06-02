package kosta.apt.controller;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kosta.apt.domain.Message.Message;
import kosta.apt.domain.Message.MessageCriteria;
import kosta.apt.domain.Paging.Criteria;
import kosta.apt.domain.Paging.PageMaker;
import kosta.apt.service.MessageService;

@Controller
@RequestMapping()
public class MessageController {
	
	
	private MessageService messageService;
	
	@Autowired
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	
	@RequestMapping(value="/aptMessage", method = RequestMethod.GET)
	public void Msgview(Message message){
		System.out.println("메시지창이다.");
		
	}
	
	@RequestMapping(value="/aptMessage", method = RequestMethod.POST)
	public String SandMsg(Message message)throws Exception{
		System.out.println("성공적이야");
		
		message.setMg_messageNo(messageService.MsgNo());
		
		message.setM_memberNo("999");
		messageService.SandMessage(message);
		
		
		return "redirect:/readMessageList";
		//리다이렉트해서 메시지창 닫기 만들어라.
	}
	
	
/*	//메시지수신함
	@RequestMapping(value="/readMessageList", method=RequestMethod.GET)
	public void readMessageList(Model model,Message message)throws Exception{
		List<Message> list = messageService.readlistMsg(message);
		
			System.out.println(list.size());
		System.out.println("메세지함 성공이야");
		model.addAttribute("list", list);
	}*/
	
	
	//수신함
	@RequestMapping(value="/readMessageList", method=RequestMethod.GET)
	public void readMessageList(Model model,Message message,@ModelAttribute("cri") Criteria cri)throws Exception{
		
		
		List<Message> list = messageService.readlistMsg(message,cri);
		System.out.println(list.size());
		System.out.println("메세지함 성공이야");
		
		PageMaker pageMaker = new PageMaker();
		
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(messageService.listCountCri(cri));
		
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("list", list);
	
		
	}
	
	
	
	//메시지발송함
	@RequestMapping(value="/sendMessageList", method=RequestMethod.GET)
	public void sendMessageList(Model model,Message message,@ModelAttribute("cri") Criteria cri)throws Exception{
		List<Message> list = messageService.sendMessageList(message,cri);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(messageService.sendPage(cri));
		
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("list", list);
		
	}
	
	
	//메시지 잘보내지면 OK
	@RequestMapping(value="/aptMessageOkdelect", method=RequestMethod.GET)
	public void sendMsgOK()throws Exception{
		
	}
	
	
/*	//메세지 리스트출력 ==메시지수신함 ==이건 신버전
	@RequestMapping(value="readMessageList", method=RequestMethod.GET)
	public void MessageList(Model model, Message message)throws Exception{
		
		
		model.addAttribute("list", messageService.readlistMsg(message));
			//@@@나중에 where 세션ID값 = mg_to 의 값일치한것만 리스트에넣으면 ===>내가읽어야할 메세지만 출력가능
	}
	
	
	//==신버전 발송함
	@RequestMapping(value="sendMessageList", method=RequestMethod.GET)
	public void sendMessageList(Model model, Message message)throws Exception{
		
		model.addAttribute("list", messageService.sandlistMsg(message));
	}*/
	
	
	
/*	//리스트페이징처리 
	@RequestMapping(value="/MessageList", method = RequestMethod.GET)
	public void listMessage(Model model, Criteria cri)throws Exception{
		
		model.addAttribute("listMsg", messageService.listMsg(cri));
	}*/
	
}
