package edu.pnu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@Service
public class MemberService {

	@Autowired
	private MemberRepository memberReop;
	

	
	public String save(Member member) {
		if(memberReop.findById(member.getUsername()).isPresent()) {
			return "같은 아이디 존재한다.";
		}
		memberReop.save(member);
		return "";
	}
}
