package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.pnu.domain.Board;
import edu.pnu.domain.Member;
import edu.pnu.service.BoardService;

@SessionAttributes("member") // session에다가 맴버라는 키이름의 저장소를 만들겠다는 의미
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

	@RequestMapping("/getBoardList")
	public String getBoardList(@ModelAttribute("member") Member member, Model model) {
		if (member.getId() == null) {
			return "redirect:login";
		}
		List<Board> boardList = boardService.getBoardList();
		model.addAttribute("boardList", boardList);

		return "getBoardList";
	}

	@GetMapping("/insertBoard")
	public String insertBoard(@ModelAttribute("member") Member member) {
		if (member.getId() == null) {
			return "redirect:login";
		}
		// "insertBoard"라는 view를 호출함
		return "insertBoard";
	}

	@PostMapping("/insertBoard")
	public String insertBoardPost(@ModelAttribute("member") Member member, Board board) {
		if (member.getId() == null) {
			return "redirect:login";
		}
		boardService.insertBoard(board);
		// redirect 미기재시 default값이 forward
		return "redirect:getBoardList";
	}

	@GetMapping("/getBoard")
	public String getBoard(@ModelAttribute("member") Member member, Long seq, Model model) {
		if (member.getId() == null) {
			return "redirect:login";
		}
		// "getBoard"라는 view를 호출함
		Board board = boardService.getBoard(Board.builder().seq(seq).build());
		model.addAttribute("board", board);

		return "getBoard";
	}

	@PostMapping("/updateBoard")
	public String updateBoard(@ModelAttribute("member") Member member, Board board) {
		if (member.getId() == null) {
			return "redirect:login";
		}
		boardService.updateBoard(board);
		return "redirect:getBoardList";
	}

	@GetMapping("/deleteBoard")
	public String deleteBoard(@ModelAttribute("member") Member member, Board board) {
		if (member.getId() == null) {
			return "redirect:login";
		}
		boardService.deleteBoard(board);
		return "redirect:getBoardList";
	}

	@ModelAttribute("member")
	public Member setMember() {
		return new Member();
	}

}
