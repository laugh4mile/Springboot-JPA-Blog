package com.cos.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service // Bean 등록
public class PrincipalDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	/*
	 * 스프링이 로그인 요청을 가로챌 때, username, password 변수 2개를 가로챈다
	 * password 부분 처리는 알아서 한다.
	 * 우리가 해줄것은 해당 username이 DB에 존재하는지만 확인해주면 된다.
	 * 그 확인을 아래의 함수가 해준다.
	 * */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userRepository.findByUsername(username)
				.orElseThrow(()->{
					return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. : "+username);
				});
		return new PrincipalDetail(principal);
		/*
		 * 이때 시큐리티의 세션에 유저 정보가 저장이된다.
		 * 여태까지 이걸위해 그많은 작업을 했던 것이다.
		 * 타입은 반드시 UserDetails 여야 한다.
		 * 이 작업을 하지 않으면 맨처음 SpringSecurity를 적용했던 것처럼 id:user, password:콘솔의 그 해쉬 가 된다.
		 * */
	}

}
