package edu.pnu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import edu.pnu.domain.Board;
import edu.pnu.service.BoardService;

@Controller
public class BoardController {

//	@RequestMapping("/getBoardList")
//	public String getBoardList(Model model) {
//		List<Board> boardList = new ArrayList<Board>();
//		
//		for(int i = 1; i <= 10; i++) {
//			Board board = new Board();
//			board.setSeq((long)i);
//			board.setTitle("게시판 프로그램 테스트");
//			board.setWriter("도우너");
//			board.setContent("게시판 프로그램 테스트임돠");
//			board.setCreateDate(new Date());
//			board.setCnt(0L);
//			boardList.add(board);
//		}
//		model.addAttribute("boardList", boardList);
//		return "getBoardList";
//	}

//	 @RequestMapping("/getBoardList")
//	   public String GetBoardList(Model model) {
//	      List<Board> boardList = new ArrayList<>();
//	      
//	      for(int i = 1; i <= 10; i++) {
//	         boardList.add(Board.builder()
//	               .seq((long)i)
//	               .title("title" +i)
//	               .writer("writer" + i)
//	               .content("content" + i)
//	               .createDate(new Date())
//	               .cnt(0L).build());
//	      }
//	      model.addAttribute("boardList", boardList);
//	      return "getBoardList";
//	   }

	@Autowired
	private BoardService boardService;

	@GetMapping("/getBoardList")
	public String getBoardList(Model model) {

		model.addAttribute("boardList", boardService.getBoardList());

		return "getBoardList";
	}

	@GetMapping("/insertBoard")
	public String insertBoard() {
		//"insertBoard"라는 view를 호출함
		return "insertBoard";
	}

	@PostMapping("/insertBoard")
	public String insertBoardPost(Board board) {
		boardService.insertBoard(board);
		//redirect 미기재시 default값이 forward
		return "redirect:getBoardList";
	}
	
	@GetMapping("/getBoard")
	public String getBoard(Long seq, Model model) {
		//"getBoard"라는 view를 호출함
		Board board = boardService.getBoard(Board.builder().seq(seq).build());
		model.addAttribute("board", board);
		
		return "getBoard";
	}
	@PostMapping("/updateBoard")
	public String updateBoard(Board board) {
		boardService.updateBoard(board);
		return "redirect:getBoardList";
	}
	@GetMapping("/deleteBoard")
	public String deleteBoard(Board board) {
		boardService.deleteBoard(board);
		return "redirect:getBoardList";
	}

}
