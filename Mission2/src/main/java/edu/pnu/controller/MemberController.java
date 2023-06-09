package edu.pnu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Member;
import edu.pnu.service.MemberService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MemberController {
	
	private MemberService memberService;
	
	public MemberController() {
		System.out.println("sysout : MemberController가 생성되었습니다.");
		log.info("log : MemberController가 생성되었습니다.");
		
		memberService = new MemberService();
	}
	
	//localhost:8080/member/1
	@GetMapping("/member/{id}") //id는 pass Value
	public Member getMember(@PathVariable Long id) {
		return memberService.getMember(id);
	}
	
	@GetMapping("/member")
	public List<Member> getMembers() { // 모든 맴버 데이터를 리턴
		return memberService.getMembers();
	}
	@PostMapping("/member")
	public int insertMember (Member member) { // 새로운 맴버 입력
		return memberService.insertMember(member);
	}
	@PutMapping("/member")
	public int updateMember (Member member) { // 맴버 이름, 암호 수정
		return memberService.updateMember(member);
	}	
	@DeleteMapping("/member/{id}")
	public int deleteMember (@PathVariable Long id) { // 맴버 삭제
		return memberService.deleteMember(id);
	}
}
