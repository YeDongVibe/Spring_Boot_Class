package edu.pnu.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardRepository boardRepo;

	// repository에서 findAll을 통해 객체를 리턴해 가져옴
	@Override
	public List<Board> getBoardList() {
		return (List<Board>) boardRepo.findAll();
	}

	@Override
	public void insertBoard(Board board) {
		board.setCreateDate(new Date());
		board.setCnt(0L);
		boardRepo.save(board);
	}

	@Override
	public Board getBoard(Board board) {

		Board b = boardRepo.findById(board.getSeq()).get();
		if (b.getCnt() == null) {
			b.setCnt(1L);
		} else {
			b.setCnt(b.getCnt() + 1L);
		}
		return boardRepo.save(b);
	}

	@Override
	public void updateBoard(Board board) {
		Board findBoard = boardRepo.findById(board.getSeq()).get();

		findBoard.setTitle(board.getTitle());
		findBoard.setContent(board.getContent());
		boardRepo.save(findBoard);
	}

	@Override
	public void deleteBoard(Board board) {
		boardRepo.deleteById(board.getSeq());
	}
}
