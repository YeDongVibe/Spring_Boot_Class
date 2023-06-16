package edu.pnu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.service.TestService1;
import edu.pnu.service.TestService2;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TestController {
	
	//필드에 Autowired를 적용하는 방법
//	@Autowired
//	private TestService1 testService1;
//	
//	@Autowired
//	private TestService2 testService2;
//	
//	public TestController() {
//		System.out.println("TestController");
//	}
	
	//생성자에다가 넣어주는 방법
//	private TestService1 test1;
//	private TestService2 test2;
//	
//	public TestController(TestService1 test1, TestService2 test2) {
//		this.test1 = test1;
//		this.test2 = test2;
//		
//		System.out.println("TestController");
//	}
	
	//lomebok을 이용한 방법(@RestController)
	private final TestService1 test1;
	private final TestService2 test2;
	
	@GetMapping({"/", "/home"})
	public String home() {
		return "home";
	}
	@GetMapping("/test")
	public String test() {
		return test1.test();
	}
}
