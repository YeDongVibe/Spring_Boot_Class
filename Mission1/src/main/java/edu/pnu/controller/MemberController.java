package edu.pnu.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;

//@Slf4j
@RestController
public class MemberController {

	private MemberService memberService;
	
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	
	public MemberController() {
		memberService = new MemberService();
	}
	
	@GetMapping("/member")
	public List<MemberVO> getMembers() {
		log.info("getMembers()");
		System.out.println("getMembers : ");
		return memberService.getMembers();
	}

	//localhost:8080/member/1
	@GetMapping("/member/{id}")//전형적인 restAPI에서 데이터를 가져올때 쓰는 방식 : /id쓰면 된다
	public MemberVO getMember(@PathVariable Integer id) {
		log.info("getMember()");
		return memberService.getMember(id);
	}
	
	//localhost:8080/member?id=1
//	@GetMapping("/member")//member를 호출하여 ?id=얼마 이렇게 파라미터로 불러와 호출
//	public MemberVO getMember(Integer id) { //?id = 얼마라는 값이 id로 들어가 호출
//		log.info("getMember()");
//		return memberService.getMember(id);
//	}
	
	@PostMapping("/member")
	public MemberVO addMember(MemberVO member) {
		log.info("addMember()");
		return memberService.addMember(member);
	}

	@PutMapping("/member")
	public MemberVO updateMember(MemberVO member) {
		log.info("updateMember()");
		return memberService.updateMember(member);
	}
	
	@DeleteMapping("/member/{id}")
	public MemberVO deleteMember(@PathVariable Integer id) {
		log.info("deleteMember()");
		return memberService.deleteMember(id);
	}
}
