package edu.pnu.controller;

//import java.awt.print.Pageable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@RestController
public class BoardController {

	@Autowired
	BoardRepository boardReop;
	
	@GetMapping("/getBoardList")
	public List<Board> getBoardList() {
		return boardReop.findAll();
	}
	
	@PostMapping("/insertBoardList")
	public Board insertBoardList(Board board) {
		if(board.getCreateDate() == null) {
			board.setCreateDate(new Date());
		}
		return boardReop.save(board);
				
	}
	
	@PostMapping("/insertJsonBoardList") //json으로 넣기
	public Board insertJsonBoardList(@RequestBody Board board) {
		if(board.getCreateDate() == null) {
			board.setCreateDate(new Date());
		}
		return boardReop.save(board);
				
	}
	
	@PutMapping("/updateBoardList")
	public Board updateBoardList(Board board) {
		return boardReop.save(board);
	}
	
	@DeleteMapping("/deleteBoardList")
	public String deleteBoardList(Long id) {
		boardReop.deleteById(id);
		return String.format("seq가 %d인 데이터가 삭제되었습니다.", id);
	}
	
	@GetMapping("/selectBoardList/{id}")
	public Board selectBoardList(Long id) {
		return boardReop.findById(id).get();
	}
	
	@GetMapping("/findByTitleContaining")
	public List<Board> findByTitleContaining(String kw){
		return boardReop.findByTitleContaining(kw);
	}
	
	@GetMapping("/findByTitleLike")
	public List<Board> findByTitleLike(String kw){
//		Pageable paging = PageRequest.of(0, 5);
		return boardReop.findByTitleLike("%" + kw + "%");
	}
	
	@GetMapping("/findByTitleContainingAndCntGreaterThan")
	public List<Board> findByTitleContainingAndCntGreaterThan(String kw, Long kw2){
		return boardReop.findByTitleContainingAndCntGreaterThan(kw, kw2);
	}
	
	@GetMapping("/findByCntBetweenOrderBySeqAsc")
	public List<Board> findByCntBetweenOrderBySeqAsc(Long kw, Long kw2) {
		return boardReop.findByCntBetweenOrderBySeqAsc(kw, kw2);
	}
	
	@GetMapping("/findByTitleContainingOrContentContainingOrderBySeqDesc")
	public List<Board> findByTitleContainingOrContentContainingOrderBySeqDesc(String kw, String kw2){
		return boardReop.findByTitleContainingOrContentContainingOrderBySeqDesc(kw, kw2);
	}
}
