package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	
	@GetMapping({"","/"}) // 이런식으로 여러 주소를 하나에 매핑할 수도 있다.
	public String index() {
		return "index";
		// prefix: /WEB-INF/views/
		// suffix: .jsp
		// 즉, /WEB-INF/views/index.jsp 를 리턴함
	}
}
