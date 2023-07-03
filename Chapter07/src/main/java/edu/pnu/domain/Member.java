package edu.pnu.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
	@Id
	private String username;
	private String password;
	private String role;
	private boolean enabled;
	
	// role이 여러개일때 ,를 기준으로 list로 만들어 return 하는 매서드
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		//아래의 한줄짜리 코드 구현
//		Collection<GrantedAuthority> list = new ArrayList<>();
//		list.add(new GrantedAuthority() {
//			
//			@Override
//			public String getAuthority() {
//				// TODO Auto-generated method stub
//				return role;
//			}
//		});
//		return list;
		
		return AuthorityUtils.createAuthorityList(role);
	}
}
