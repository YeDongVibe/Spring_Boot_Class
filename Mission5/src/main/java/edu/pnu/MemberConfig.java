package edu.pnu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import edu.pnu.service.MemberService;

@Configuration
public class MemberConfig {

	@Bean
	public MemberService memberService() {
		return new MemberService();
	}
	


}
