package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@Controller
//사용자가 요청 -> 응답(HTML 파일)

// 사용자의 요청 -> 응답(data) 
@RestController
public class HttpController {
	private static final String TAG = "HttpController : ";
	//인터넷 브라우저 요청은 무조건 get 요청밖에 할 수 없다.
	//http://localhost:8080/http/get (select)
	// 데이터를 get방식으로 요청하는 방법은 쿼리 스트링밖에 없다.
		// id=1&username=ssar&password=1234&email=ssar@nate.com 
	@GetMapping("/http/lombok") 
	public String lombokTest() {
		Member m = Member.builder().username("ssar").password("1234").email("ssar@nate.com").build();
		System.out.println(TAG+"getter : "+ m.getUsername());
		m.setUsername("cos");
		System.out.println(TAG+"setter : "+ m.getUsername());
		return "lombok test 완료";
	}
	
	@GetMapping("/http/get") 
	public String getTest(Member m) {
		return "get 요청 : "+m.getId()+", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
	}
	 
	//http://localhost:8080/http/post (insert)
	@PostMapping("/http/post")
	public String postTest(@RequestBody  Member m) {
		return "post 요청 : "+m.getId()+", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail();
	}
	
	//http://localhost:8080/http/put (put)
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청 : "+m.getId()+", "+m.getUsername()+", "+m.getPassword()+", "+m.getEmail() ;
	}
	
	//http://localhost:8080/http/delete (delete)
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
	
}
