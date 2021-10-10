package com.cos.blog.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

// @Service 어노테이션이 붙으면 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IoC를 해준다는 의미!(메모리에 띄워준다!)
@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
//	@PostMapping
	@Transactional
	public void 회원가입(User user) {
		userRepository.save(user);
	}
}
