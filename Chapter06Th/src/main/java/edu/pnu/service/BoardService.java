package edu.pnu.service;

import java.util.List;

import edu.pnu.domain.Board;

public interface BoardService {

	// repository에서 findAll을 통해 객체를 리턴해 가져옴
	List<Board> getBoardList();

	void insertBoard(Board board);

	Board getBoard(Board board);

	void updateBoard(Board board);

	void deleteBoard(Board board);


}