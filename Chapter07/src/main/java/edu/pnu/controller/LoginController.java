package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.pnu.config.MemberService;
import edu.pnu.domain.Member;

@Controller
public class LoginController {
	@Autowired
	private MemberService memberservice;

	@Autowired
	BCryptPasswordEncoder encoder;

	@GetMapping("/join")
	public void join() {

	}

	@PostMapping("/join")
	public String joinProc(Member member, Model model) {
		if (member.getPassword() != null && !member.getPassword().isEmpty() && member.getUsername() != null
				&& !member.getUsername().isEmpty()) {

			member.setRole("ROLE_MEMBER");
			member.setEnabled(true);
			member.setPassword(encoder.encode(member.getPassword()));
			String msg = memberservice.save(member);
			if (msg.isEmpty()) {
				return "redirect:/login";
			}
			model.addAttribute("errorMsg", msg);
		} else {
			model.addAttribute("errorMsg", "부적절한 아이디랑 암호다. 달아라");
		}
		return "joinerr";
	}

	@GetMapping("/login")
	public void login() {

	}

	@GetMapping("/loginSuccess")
	public void loginSuccess() {

	}

	@GetMapping("/accessDenied")
	public void accessDenied() {

	}

	// 로그인 세션 정보 확인용 URL
	@GetMapping("/auth")
	public @ResponseBody String auth(@AuthenticationPrincipal User user) {
		return user.toString();
	}

//	@GetMapping("/test")
//	public void test(Model model) {
//		model.addAttribute("errorMsg", "이것은 에러다.");
//	}

}
