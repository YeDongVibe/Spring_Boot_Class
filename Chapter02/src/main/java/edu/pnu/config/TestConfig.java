package edu.pnu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

	public TestConfig() {
		System.out.println("=".repeat(50));
		System.out.println("TestConfig가 생성되었습니다.");
		System.out.println("=".repeat(50));
	}
	@Bean //자바 객체(인스턴스) : spring(IOC) container라는 메모리에 자동으로 생성해서 올려줌
	public TestBean testBean() {
		return new TestBean();
	}
}

class TestBean {
	public TestBean() {
		System.out.println("=".repeat(50));
		System.out.println("TestBean가 생성되었습니다.");
		System.out.println("=".repeat(50));
	}
}
