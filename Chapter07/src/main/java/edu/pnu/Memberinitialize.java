package edu.pnu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

//@Component
//member에 데이터 넣기 위해 만든것.
public class Memberinitialize implements ApplicationRunner {

	@Autowired
	MemberRepository memRepo;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		memRepo.save(Member.builder()
				.username("member")
				.password(encoder.encode("qwer")) //암호화
				.role("ROLE_MEMBER") //ROLE_뭐시기 : boot에서 사용하는 규칙. 
				.enabled(true) //username이 사용가능하도록 설정
				.build());

		memRepo.save(Member.builder()
				.username("manager")
				.password(encoder.encode("qwer"))
				.role("ROLE_MANAGER")
				.enabled(true)
				.build());
		
		memRepo.save(Member.builder()
				.username("admin")
				.password(encoder.encode("qwer"))
				.role("ROLE_ADMIN")
				.enabled(true)
				.build());
	}

}
