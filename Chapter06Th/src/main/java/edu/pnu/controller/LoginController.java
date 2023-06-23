package edu.pnu.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import edu.pnu.domain.Member;
import edu.pnu.exception.UsernameNotFoundException;
import edu.pnu.service.MemberService;

@SessionAttributes("member")
@Controller
public class LoginController {
	
	@Autowired
	private MemberService memberServiece;
	
	@GetMapping("/login")
	public void loginView() {
		
	}
	@PostMapping("/login")
	public String login(Member member, Model model) {
		Member findMember = memberServiece.getMember(member);
		
		if(findMember != null && findMember.getPassword().equals(member.getPassword())) {
			model.addAttribute("member", findMember); //session에 member라는 이름으로 findMember를 통해 검색되어 저장됨
			return "redirect:getBoardList";
		} else {
//			return "/resources/templates/errors/loginError.html";
			throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
		}
	}
	
	@GetMapping("/logout")
	public String logout(SessionStatus status) {
		status.setComplete();
		return "redirect:index.html";
	}
}
