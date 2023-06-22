package edu.pnu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
	
	@GetMapping("/home")
	public String home() {
		//web-inf - board - home.jsp
		//return "home"이 .jsp앞으로 붙어서 연결된다.
		return "home";
	}
	//web-inf - board - home1.jsp
	@GetMapping("/home1")
	public void home1() {
		//void인 경우  getmapping의 url이 board - home1.jsp로 붙어서연결된다.
	}
	
	@GetMapping("/model")
	public String model(Model model) {
		
		
		model.addAttribute("data", "이예둥");
		
		return "model";
	}
}
