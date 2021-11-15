package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.service.BoardService;

@Controller // @RestController와 다르게 리턴 시 viewResolver가 작동한다.
public class BoardController {
	
	/* 컨트롤러에서 세션에 접근하는 방법!
	 *파라미터에 @AuthenticationPrincipal PrincipalDetail principal
	 * */
	@Autowired
	private BoardService boardService;
	
	@GetMapping({"","/"}) // 이런식으로 여러 주소를 하나에 매핑할 수도 있다.
	public String index(Model model) { // Spring 에서는 데이터를 가져올때 Model이 필요하다.
		model.addAttribute("boards", boardService.글목록()); // index 라는 페이지로 boards 가 날라간다!. request 정보라고 생각하자.
		return "index"; // viewResolver가 작동
	}

	// User 권한이 필요
	@GetMapping({"/board/saveForm"}) 
	public String saveForm() { 
		return "board/saveForm";
	}
}
