package edu.pnu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberReop;
	

	
	public void save(Member member) {
		
		memberReop.save(member);
	}
}
