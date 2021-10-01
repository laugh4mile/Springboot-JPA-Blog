package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyController {
	
	@Autowired // Spring이 DummyController를 메모리에 띄울 때, UserRepository도 같이 띄운다!  // 이것이 DI : 의존성 주입이다!!!
	private UserRepository userRepository; // UserRepository 타입으로 Spring이 관리하는 객체가 있다면 변수에 넣어달란 의미

	// http://localhost:8000/blog/dummy/users
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	// 한 페이지당 2건에 데이터를 리턴받아보자
	//  http://localhost:8000/blog/dummy/user?page=0 // <- 이런식으로 특정 페이지를 받아올 수 있다!
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Direction.DESC) Pageable pageable){
//		List<User> users = userRepository.findAll(pageable).getContent(); // getContent()를 쓰면 pageable 객체 빼고 내용만 가져온다.
		Page<User> pagingUser = userRepository.findAll(pageable); 
		List<User> users = pagingUser.getContent(); // 보통 이런식으로 나눈다. 이렇게하면 Page클래스에서 제공하는 함수들을 유용하게 쓸 수 있다.
		return users;
	}
	
	// {id} 주소로 파라미터를 전달 받을 수 있음
	// http://localhost:8000/blog/dummy/user/5
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		/* 
		 * /dummy/user/4 를 찾으면 찾을 수 없다 (현재 DB에 1~3까지 밖에 없다)
		 * DB에서 못 찾아오니까 User 가 null이 될것이다.
		 * Optional로 User객체를 감싸서 가져오면 null인지 아닌지 판단해서 return할 수 있다. 
		*/
		
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() { // Supplier는 인터페이스이므로 get() 함수를 오버라이딩 해줘야함
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id :"+id); //IllegalArgumentException는 메시지를 담을 수 있음.
				// 이제 잘못된 인수가 들어올 경우. 해당 메시지를 리턴한다.
			}
		});
		// 람다식을 쓰면 더 짧게 쓸 Supplier 타입을 안써도 된다. 하지만 그냥 위에 방식으로 쓰겠다.
//		User user = userRepository.findById(id).orElseThrow(()->{
//			return new IllegalArgumentException("해당 유저는 없습니다. id :"+id);
//		});
		
		/* user 객체는 자바 오브젝트이다.
		 * 근데 요청은 get이기에 웹 브라우저에서 했다.
		 * 따라서 user 객체를 웹 브라우저가 이해할 수 있게 json으로 변환을 해야한다.
		 * 근데 스프링부트는 MessageConverter라는 애가 있다.
		 * 만약 이와같이 자바 오브젝트를 리턴하게되면 MessageConverter가 작동으로 작동되어 Jackson 라이브러리를 호출한다.
		 * user 오브젝트를 json으로 변환해서 브라우저한테 던진다.
		*/
		return user;
	}
	
	
	// http://localhost:8000/blog/dummy/join
	// http 의 body에 username, password, email 데이터를 가지고 요청
	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println("id : "+user.getId());
		System.out.println("username : "+user.getUsername());
		System.out.println("password : "+user.getPassword());
		System.out.println("email : "+user.getEmail());
		System.out.println("role : "+user.getRole());
		System.out.println("createDate : "+user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user); // save = insert
		return "회원가입이 완료되었습니다.";
	}
}
