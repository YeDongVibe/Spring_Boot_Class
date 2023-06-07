package com.rubypaper.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.domain.BoardVO;

//Controller : return한 내용이........놓쳐 부렸다리......큰일났다리...

@RestController // return하는 내용을 전체 그대로 호출하는 브라우져로 날라감.dispatcherservlet이 넘겨줌
public class BoardController {
	public BoardController() {
		System.out.println("=".repeat(50));
		System.out.println("BoardController 생성되었습니다.");
		System.out.println("=".repeat(50));
	}

	@GetMapping("/hello")
	public String hello1(String name) {
		return "Get Hello : " + name;
	}

	@PostMapping("/hello")
	public String hello2(String name) {
		return "Post Hello : " + name;
	}

	@PutMapping("/hello")
	public String hello3(String name) {
		return "Put Hello : " + name;
	}

	@DeleteMapping("/hello")
	public String hello4(String name) {
		return "Delete Hello : " + name;
	}

	@GetMapping("/getBoard")
	public BoardVO getBoard() {
		BoardVO board = new BoardVO();
		board.setSeq(1);
		board.setTitle("테스트 제목 ... ");
		board.setWriter("테스터");
		board.setContent("테스터 내용입니다...");
		board.setCreateDate(new Date());
		board.setCnt(0);
		return board;
	}

	@GetMapping("/getBoard")
	public BoardVO getBoard1() { //생성자에다가 값을 집어넣는 방법
		BoardVO board = new BoardVO(1, "테스트 제목 ... ", "테스터", "테스터 내용입니다...", new Date(), 0);
		return board;
	}
}
