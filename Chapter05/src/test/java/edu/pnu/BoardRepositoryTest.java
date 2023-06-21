package edu.pnu;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import edu.pnu.domain.Board;
import edu.pnu.persistence.BoardRepository;

@SpringBootTest
class BoardRepositoryTest {
	@Autowired
	private BoardRepository boardRepo;

	//@Test
	void contextLoads() { //여러 데이터 등록
//		Random rd = new Random(100)
		for (int i = 1; i <= 100; i++) {
			boardRepo.save(Board.builder()
					.title("첫 번째 게시글" + i)
					.writer("테스터" + i)
					.content("잘 등록되나요?" + i)
					.createDate(new Date())
					.cnt(0L)
					.build());
		}
	}

//	@Test
//	void contextLoads() { //단일 데이터 등록
//		Board board = new Board();
//
//		board.setTitle("첫 번째 게시글");
//		board.setWriter("테스터");
//		board.setContent("잘 등록되나요?");
//		board.setCreateDate(new Date());
//		board.setCnt(0L);
//		boardRepo.save(board);
//	}
	
	
	//@Test
	public void testGetBoard() {

		// 아래의 한줄 코드의 원래 코드
//		Optional<Board> opt = boardRepo.findById(1L);
//		Board board = opt.get();

		Board board = boardRepo.findById(1L).get();
		System.out.println(board);
	}

	//@Test
	public void testUpdateBoard() { //update test
		{
			Board board = boardRepo.findById(1L).get();
			System.out.println("수정 전");
			System.out.println(board);

			board.setTitle("제목 수정");
			boardRepo.save(board);
		}
		{
			Board board = boardRepo.findById(1L).get();
			System.out.println("수정 후");
			System.out.println(board);
		}
	}
	
	//@Test
	public void testDeleteBoard() { //삭제 test
		boardRepo.deleteById(2L);
	}
	//@Test
	public void testFindAll() { //모든 데이터 조회
		List<Board> list = boardRepo.findAll();
		System.out.println("모든 데이터를 출력합니다.");
		for(Board b : list) {
			System.out.println(b);
		}
		
	}
	
	//@Test //Query Test
	public void testQueryAnnotaionTest1() {
		//select b from Board b where b.title like '%title1%' order by b.seq desc;
		List<Board> list = boardRepo.queryAnnotationTest1("title1");
		for(Board b : list) {
			System.out.println(b);
		}
	}
	
	//@Test
	public void testQueryAnnotaionTest2() {
		List<Board> list = boardRepo.queryAnnotationTest2("title2");
		for(Board b : list) {
			System.out.println(b);
		}
	}
	
	@Test
	public void testQueryAnnotaionTest3() {
		List<Object []> list = boardRepo.queryAnnotationTest3("title3");
		for(Object [] b : list) {
			System.out.println(Arrays.toString(b));
		}
	}
	
	@Test
	public void testQueryAnnotaionTest4() {
		List<Object []> list = boardRepo.queryAnnotationTest3("title4");
		for(Object [] b : list) {
			System.out.println(Arrays.toString(b));
		}
	}
	
	@Test
	public void testQueryAnnotaionTest5() {
		//3개씩 0페이지를 가져옴
		//Pageable paging = PageRequest.of(0, 3, Sort.Direction.DESC, "cnt");
		Pageable paging = PageRequest.of(0, 3);
		List<Board> list = boardRepo.queryAnnotationTest5(paging);
		for(Board b : list) {
			System.out.println("---> " + b);
		}
	}
	
}
