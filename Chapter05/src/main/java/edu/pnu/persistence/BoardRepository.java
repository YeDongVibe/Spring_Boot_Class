package edu.pnu.persistence;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.pnu.domain.Board;


public interface BoardRepository extends JpaRepository<Board, Long> {

	List<Board> findByTitleContaining(String kw);

	List<Board> findByTitleLike(String kw);

	List<Board> findByTitleContainingAndCntGreaterThan(String kw, Long kw2);

	List<Board> findByCntBetweenOrderBySeqAsc(Long kw, Long kw2);
	
	List<Board> findByTitleContainingOrContentContainingOrderBySeqDesc(String kw, String kw2);
	
	@Query("Select b from Board b where b.title like %?1% order by b.seq DESC")
	List<Board> queryAnnotationTest1(String searchkeyword);
	
	@Query("Select b from Board b where b.title like %:searchKeyword% order by b.seq desc")
	List<Board> queryAnnotationTest2(String searchKeyword);
	
	@Query("Select b.seq, b.title, b.writer, b.createDate from Board b where b.title like %:searchKeyword% order by b.seq desc")
	List<Object []> queryAnnotationTest3(String searchKeyword);
	
	@Query(value = "select seq, title, writer, createdate from board where title like '%'||?sk||'%' order by seq desc", nativeQuery = true)
	List<Object []> queryAnnotationTest4(String sk);
	
	/**board table에 잇는 모든데이터를 가져오는 매서드**/
	@Query("select b from Board b order by b.seq asc")
	List<Board> queryAnnotationTest5(Pageable paging);
}
