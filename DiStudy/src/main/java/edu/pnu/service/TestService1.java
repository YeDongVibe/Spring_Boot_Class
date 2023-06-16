package edu.pnu.service;

import org.springframework.stereotype.Service;

@Service
public class TestService1 {
public TestService1() {
	System.out.println("TestService1");
}

public String test() {

	return "TestService1:test()";
}
}
