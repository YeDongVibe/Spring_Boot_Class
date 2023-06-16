package edu.pnu;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.pnu.domain.MemberVO;
import edu.pnu.service.MemberService;

@SpringBootTest
class Mission2ApplicationTests {

	@Autowired
	MemberService memberService;

	@Test
	@Order(1)
	void contextLoads() {

		List<MemberVO> list = memberService.getMembers();
		for (MemberVO m : list) {
			System.out.println(m);
		}
		System.out.println("=".repeat(50));
	}

	@Test
	@Order(2)
	void contextLoads1() {
		System.out.println();
		System.out.println(memberService.addMember(new MemberVO(99, "123456", "이름", new Date())));
		System.out.println("=".repeat(50));
	}
	@Test
	@Order(3)
	void contextLoads2() {
		MemberVO m = memberService.getMember(1);
		m.setName("아이오");
		System.out.println();
		System.out.println(memberService.updateMember(m));
	}

	@Test
	@Order(4)
	void contextLoads3() {
		System.out.println();
		System.out.println(memberService.deleteMember(8));
	}


}
