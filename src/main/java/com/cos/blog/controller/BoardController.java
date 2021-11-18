package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cos.blog.service.BoardService;

@Controller // @RestController와 다르게 리턴 시 viewResolver가 작동한다.
public class BoardController {
	
	/* 컨트롤러에서 세션에 접근하는 방법!
	 *파라미터에 @AuthenticationPrincipal PrincipalDetail principal
	 * */
	@Autowired
	private BoardService boardService;
	
	@GetMapping({"","/"}) // 이런식으로 여러 주소를 하나에 매핑할 수도 있다.
	public String index(Model model, @PageableDefault(size = 3, sort = "id", direction = Direction.DESC) Pageable pageable) { // Spring 에서는 데이터를 가져올때 Model이 필요하다.
		model.addAttribute("boards", boardService.글목록(pageable)); // index 라는 페이지로 boards 가 날라간다!. request 정보라고 생각하자. + pageable을 넘겨주면 페이징을 할 수 있다.
		return "index"; // viewResolver가 작동
	}
	
	@GetMapping("/board/{id}")
	public String findById(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.글상세보기(id));
		return "board/detail";
	}

	// User 권한이 필요
	@GetMapping({"/board/saveForm"}) 
	public String saveForm() { 
		return "board/saveForm";
	}
}
