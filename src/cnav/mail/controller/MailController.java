package cnav.mail.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cnav.mail.dto.MailDTO;
import cnav.mail.service.MailServiceImpl;


@Controller
@RequestMapping("/mail/*")
public class MailController {
	
	//contro - service - dao - sql
	
	@Autowired
	private MailServiceImpl MailService = null;
	
	// 받은 편지함
	@RequestMapping("recMailList.cnav")
	public String recMailList(String pageNum, String sel, String search, Model model) throws SQLException{
		System.out.println("받은mail list");
		String id = "test";
		
		
		Map<String, Object> result = null;
		if(sel == null || search == null) {
			result = MailService.recMailList(pageNum, id); // select * from mail where id=""; 			
		}else {
			result = MailService.recMailSearch(pageNum, sel, search, id);
		}
		
		model.addAttribute("pageSize", result.get("pageSize"));
		model.addAttribute("pageNum", result.get("pageNum"));
		model.addAttribute("currentPage", result.get("currentPage"));
		model.addAttribute("startRow", result.get("startRow"));
		model.addAttribute("endRow", result.get("endRow"));
		model.addAttribute("recMailList", result.get("recMailList"));
		model.addAttribute("count", result.get("count"));
		model.addAttribute("number", result.get("number"));
		model.addAttribute("sel", sel);
		model.addAttribute("search", search);
		
		System.out.println("sel" + sel);
		System.out.println("list" + result);
		
		
		
		return "mail/recMailList";
	}
	
	// 보낸 편지함
	@RequestMapping("sendMailList.cnav")
	public String sendMailList(String pageNum, String sel, String search, Model model) throws SQLException{
		System.out.println("보낸 mail list");
		String id = "mero";
		
		
		Map<String, Object> result = null;
		if(sel == null || search == null) {
			result = MailService.sendMailList(pageNum, id); // select * from mail where id=""; 			
		}else {
			result = MailService.sendMailSearch(pageNum, sel, search, id);
		}
		
		model.addAttribute("pageSize", result.get("pageSize"));
		model.addAttribute("pageNum", result.get("pageNum"));
		model.addAttribute("currentPage", result.get("currentPage"));
		model.addAttribute("startRow", result.get("startRow"));
		model.addAttribute("endRow", result.get("endRow"));
		model.addAttribute("sendMailList", result.get("sendMailList"));
		model.addAttribute("count", result.get("count"));
		model.addAttribute("number", result.get("number"));
		model.addAttribute("sel", sel);
		model.addAttribute("search", search);
		
		System.out.println("list" + result);
		
		
		
		return "mail/sendMailList";
	}
	
	// 메일 보내기
	@RequestMapping("writeMailForm.cnav")
	public String writeMailForm() {
		
		return "mail/writeMailForm";
	}
	
	// 메일 보내기 처리
	@RequestMapping("writeMailPro.cnav")
	public String writeMailPro(MailDTO dto, Model model) throws SQLException {
		dto.setUserId("test");
		int result = MailService.insertMail(dto);
		
		
		model.addAttribute("result", result);
		
		return "mail/writeMailPro";
	}
	
	// 편지함 선택해서 게시물 삭제
	@RequestMapping("/deleteMailForm")
	public String ajaxTest(HttpServletRequest request) throws SQLException {
		
		String[] ajaxMsg = request.getParameterValues("valueArr");
		int size = ajaxMsg.length;
		System.out.println("size" + size);
		for(int i = 0; i<size; i++) {
			MailService.delete(ajaxMsg[i]);
		}
		return "redirect:/mail/recMailList.cnav";
	}
	
	// 편지 내용에서 삭제
	@RequestMapping("/deleteForm")
	public String delete(@RequestParam("num")int num) throws SQLException {
		MailService.deleteMail(num);
		
		return "redirect:/mail/recMailList.cnav";
	}
	
	// 받은 편지 보기
	@RequestMapping("mail.cnav")
	public String recMail(@ModelAttribute("pageNum") String pageNum, Integer num, Model model) throws SQLException{
		String id = "mero";
		MailDTO mail = MailService.getMail(num);
		model.addAttribute("mail", mail);
		model.addAttribute("id", id);
		
		return "mail/mail";
	}
	
	
	
	
	
}