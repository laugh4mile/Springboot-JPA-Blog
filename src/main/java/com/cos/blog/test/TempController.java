package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempController {
	
	// localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome() 호출 ");
		// 파일리턴 기본 경로 : src/main/resources/static
		// 리턴명 : /home.html
		return "/home.html";
	}
}
