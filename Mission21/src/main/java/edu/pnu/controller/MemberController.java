package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Member;
import edu.pnu.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor //4번 방법 : lombok annotation을 이용한 방법
public class MemberController {
	//1번 방법 : field에 @Autowired를 설정한 방법
	//@Autowired //memberservice를 memberCDontroller와 자동으로 연결
	//private MemberService memberService;
	
	//4번 방법 : final이 붙어있는 객체를 자동적으로 container에 저장하여 연결함
	private final MemberService memberService;
	
	//2번 방법 : 생성자에 집어넣어주는 방법
//	@Autowired
//	public MemberController(MemberService memberService) {
//		this.memberService = memberService;
//	}
	
	//3번 방법 : setter를 이용한 방법
//	@Autowired
//	private void SetMemberService(MemberService memberService) {
//		this.memberService = memberService;
//	}
	
	
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
