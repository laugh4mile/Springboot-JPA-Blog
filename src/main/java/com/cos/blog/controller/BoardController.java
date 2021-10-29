package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	
	/* 컨트롤러에서 세션에 접근하는 방법!
	 *파라미터에 @AuthenticationPrincipal PrincipalDetail principal
	 * */
	// 
	//
	@GetMapping({"","/"}) // 이런식으로 여러 주소를 하나에 매핑할 수도 있다.
	public String index() { 
		return "index";
		// prefix: /WEB-INF/views/
		// suffix: .jsp
		// 즉, /WEB-INF/views/index.jsp 를 리턴함
	}

	// User 권한이 필요
	@GetMapping({"/board/saveForm"}) // 이런식으로 여러 주소를 하나에 매핑할 수도 있다.
	public String saveForm() { 
		return "board/saveForm";
		// prefix: /WEB-INF/views/
		// suffix: .jsp
		// 즉, /WEB-INF/views/index.jsp 를 리턴함
	}
}
