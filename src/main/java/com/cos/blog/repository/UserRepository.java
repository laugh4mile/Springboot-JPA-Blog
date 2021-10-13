package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;

// DAO (Database Access Object)
// 자동으로 Bean 등록이 된다. Bean으로 등록 되었냐 = Spring IoC에서 객체를 가지고 있나 -> DI 하기 위한 조건임 
// @Repository // 따라서 이 어노테이션을 생략 할 수 있다
public interface UserRepository extends JpaRepository<User, Integer> { // User 테이블이 관리하는 레포지토리이며 User 테이블의 PK는 Int 형이다. 라는 뜻
	/* JpaRepository 는 정말 많은것을 가지고 있다.
	 * 예를들면
	 * findAll() 					// select * from User 와 같다
	 * findAll(Sort sort) 			// select * from User 인데 정렬해서 받아올 수 있다.
	 * findAll(Pageable pageable) 	// select * from User 인데 페이징해서 받을 수도있다
	 * 그외에 데이터의 insert와 update delete 같은 CRUD는 기본이고 그 외에 많은 기능 지원
	 * 
	*/
	
	// JPA Naming 전략1
	// SELECT * FROM user WHERE username=?1 AND password=?2; // ? = 파라미터1, ? = 파라미터2
	User findByUsernameAndPassword(String username, String password);
	
	// JPA Naming 전략2
//	@Query(value="SELECT * FROM user WHERE username=?1 AND password=?2", nativeQuery = true)
//	User login(String username, String password);
}
