package edu.pnu.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Member;
import edu.pnu.persistence.MemberRepository;

@Service
public class BoardUserDetailsService implements UserDetailsService {

	@Autowired
	MemberRepository memRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//데이터 찾기
		Optional <Member> option = memRepo.findById(username);
		if(!option.isPresent()) {
			throw new UsernameNotFoundException("사용자가 없슴돠");

		}
		

		//DB에서 맴버 데이터 가져오기
		Member member = option.get();
		System.out.println(member);
		return new User(member.getUsername(), member.getPassword(), member.getAuthorities());
		
	}

}
