package edu.pnu.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor //4번 방법 : lombok annotation을 이용한 방법
public class MemberController {
	private final MemberService memberService;
	
//	public MemberController() {
//		memberService = new MemberService();
//	}
	
	@GetMapping("/members")
	public List<MemberVO> getMembers() {
		return memberService.getMembers();
	}
	
	@GetMapping("/member/{id}") //id는 pass Value
	public MemberVO getMember(@PathVariable int id) {
		return memberService.getMember(id);
	}
	
	@PostMapping("/member")
	public int addMember(MemberVO member) {
		return memberService.addMember(member);
	}
	@PutMapping("/member")
	public int updateMember(MemberVO member) {
		return memberService.updateMember(member);
	}
	@DeleteMapping("/member/{id}")
	public int deleteMember(@PathVariable Integer id) {
		return memberService.deleteMember(id);
	}

}
