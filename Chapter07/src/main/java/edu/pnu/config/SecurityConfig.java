//Security를 이용하기 위한 가장 기본(시작)
/*package edu.pnu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration //안에서 bean 객체를 등록하기 위해 선언
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		return http.build();
	}
}*/

package edu.pnu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // 안에서 bean 객체를 등록하기 위해 선언
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	BoardUserDetailsService boardUserDetailsService;
	
	//인스턴스 만들어 IOC 컨테이너에 등록함.
	@Bean
	public BCryptPasswordEncoder encoder() {
		//암호화하는 라이브러리 BCryptPasswordEncoder
 		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
		//아래의 코드와 같은것.
		//auth.requestMatchers("/").permitAll().requestMatchers("/member/**").authenticated();
		
//		security.authorizeHttpRequests(auth -> {
//			auth.requestMatchers("/").permitAll()
//				.requestMatchers("/member/**").authenticated()
//				.requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
//				.requestMatchers("/admin/**").hasRole("ADMIN");
//		});
		
		security.authorizeHttpRequests(auth -> {
			auth.requestMatchers("/member/**").authenticated()
				.requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN") //manager, admin만 허용
				.requestMatchers("/admin/**").hasRole("ADMIN") //admin만 허용
				.anyRequest().permitAll();
		});
		
		security.csrf(csrf -> csrf.disable()); //위조사이트 막기?해킹 막기?
		security.formLogin(form -> {
			form.loginPage("/login")
				.defaultSuccessUrl("/loginSuccess", true);
		});
		
		security.exceptionHandling(ex -> ex.accessDeniedPage("/accessDenied"));
		security.logout(log -> {
			log.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID") //쿠키 삭제
			.logoutSuccessUrl("/login");
			});
		
		security.userDetailsService(boardUserDetailsService);
		return security.build();
	}
	
	@Autowired
	public void authenticate(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().
		withUser("member").
		password("{noop}qwer"). //noop:암호화를 안한 pw를 사용하겠다는 의미. 즉 암호화를 하지않겠다.
		roles("MEMBER");
		
		auth.inMemoryAuthentication().
		withUser("manager").
		password("{noop}qwer").
		roles("MANAGER");
		
		auth.inMemoryAuthentication().
		withUser("admin").
		password("{noop}qwer").
		roles("ADMIN");
	}
}
