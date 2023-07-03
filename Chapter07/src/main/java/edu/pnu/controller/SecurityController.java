package edu.pnu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class SecurityController {
	@GetMapping("/")
	public String index() {
		System.out.println("index 요청이다");
		return "index";

	}

	@GetMapping("/member")
	public void forMember() {
		System.out.println("Member 요청이다");
	}

	@GetMapping("/manager")
	public void forManager() {
		System.out.println("Manager 요청이다");
	}

	@GetMapping("/admin")
	public void forAdmin() {
		System.out.println("Admin 요청이다");
	}
//	@GetMapping("/accessDenied")
//	public void accessDenied() {
//		
//	}
}
