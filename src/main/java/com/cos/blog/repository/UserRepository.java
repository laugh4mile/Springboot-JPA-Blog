package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

// DAO
// 자동으로 bean 등록이 된다.
// @Repository // 생략 가능
public interface UserRepository extends JpaRepository<User, Integer> { // User 테이블이 관리하는 레포지토리이며 User 테이블의 PK는 Int 형이다. 라는 뜻
	
}
