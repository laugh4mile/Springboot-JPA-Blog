package com.cos.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration // 빈 등록 : 스프링 컨테이너에서 객체 관리를 할 수 있게하는 것 (IoC 관리)
@EnableWebSecurity // 필터를 거는것. 시큐리티 필터 추가. = 스프링 시큐리티가 활성화가 되어있는데 어떤 설정을 해당 파일에서 하겠다!!!
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근을 하면 권한 및 인증을 미리 체크하겠다는 뜻!
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean // IoC 가 된다.
	public BCryptPasswordEncoder encodePWD() {
//		String encPassword = new BCryptPasswordEncoder().encode("1234");
		return new BCryptPasswordEncoder(); // 이제 요녀석은 스프링이 관리하여 필요할때마다 DI해서 쓸 수 있다.
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception { 
		http
			.csrf().disable() // csrf 토큰 비활성화 (테스트시 걸어두는게 좋음)
			.authorizeRequests() // request가 들어오면
				.antMatchers("/","/auth/**","/js/**", "/css/**", "/image/**") // auth/ 경로를 통해 들어오면
				.permitAll() //누구나 들어올 수 있다.
				.anyRequest() // 이게 아닌 모든 요청은
				.authenticated() // 인증을 해야한다.
			.and() // auth가 아닌 요청은
				.formLogin() // 로그인폼으로 가야한다.
				.loginPage("/auth/loginForm") // 로그인페이지의 주소는 /auth/loginForm 이다.
				.loginProcessingUrl("/auth/loginProc") // 스프링 시큐리티가 해당 주소로 요청이오는 로그인을 가로채서 대신 로그인 해준다.
				.defaultSuccessUrl("/"); // 정상적으로 요청이 완료될 경우 해당 주소로 이동.
				
	}
}
