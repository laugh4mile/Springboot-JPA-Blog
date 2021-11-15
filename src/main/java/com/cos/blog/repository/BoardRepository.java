package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.Board;

// DAO (Database Access Object)
// 자동으로 Bean 등록이 된다. Bean으로 등록 되었냐 = Spring IoC에서 객체를 가지고 있나 -> DI 하기 위한 조건임 
// @Repository // 따라서 이 어노테이션을 생략 할 수 있다
public interface BoardRepository extends JpaRepository<Board, Integer> { // User 테이블이 관리하는 레포지토리이며 User 테이블의 PK는 Int 형이다. 라는 뜻
	
}
