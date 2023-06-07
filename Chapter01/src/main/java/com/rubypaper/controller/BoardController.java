package com.rubypaper.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.domain.BoardVO;

//Controller : return한 내용이 view이름이 브라우져로 날라감. public 뒤에 @ResponseBody를 넣어주면 RestController처럼 넘어감

@RestController // return하는 내용을 전체 그대로 호출하는 브라우져로 날라감.(임의로 만드는 객체는 무조건 json파일로 넘어감)dispatcherservlet이 넘겨줌/ 
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

	@GetMapping("/getBoard1")
	public BoardVO getBoard1() { // 생성자에다가 값을 집어넣는 방법
		BoardVO board = new BoardVO(1, "테스트 제목 ... ", "테스터", "테스터 내용입니다...", new Date(), 0);
		return board;
	}

	@GetMapping("/getBoard2")
	public BoardVO getBoard2() { // builder를 이용하여 값을 집어넣는 방법<chain coding>
		return BoardVO.builder()// ~~.builder는 static 매서드라는 의미
				.seq(1).title("테스트 제목 ... ").writer("테스터").content("테스터 내용입니다...").createDate(new Date()).cnt(0)
				.build();
	}

	@GetMapping("/getBoardList")
	public List<BoardVO> getBoardList() { // List에 추가하여 값 넣는 방법
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		for (int i = 1; i < 11; i++) {
			BoardVO board = new BoardVO();
			board.setSeq(i);
			board.setTitle("테스트 제목" + i + " ... ");
			board.setWriter("테스터" + i);
			board.setContent("테스터" + i + "내용입니다...");
			board.setCreateDate(new Date());
			board.setCnt(0);
			boardList.add(board);
		}
		return boardList;
	}

	@GetMapping("/getBoardList2")
	public List<BoardVO> getBoardList1() { // List에 추가하여 값 넣는 방법
		List<BoardVO> boardList1 = new ArrayList<BoardVO>();
		for (int i = 1; i < 11; i++) { // builder를 이용하여 값을 집어넣는 방법<chain coding>
			boardList1.add(BoardVO.builder()// ~~.builder는 static 매서드라는 의미
					.seq(i).title("테스트 제목" + i + "... ").writer("테스터"+ i).content("테스터" + i +" 내용입니다...").createDate(new Date()).cnt(0)
					.build());
		}
		return boardList1;
	}
	
	@GetMapping("/board") //Controller로 동작을 해야함
	public @ResponseBody BoardVO board(@RequestBody BoardVO b) { //파라미터로 BoardVO로 받음
		System.out.println("Board : " + b);
		return b; //들어온 데이터를 return함.
	}
}
