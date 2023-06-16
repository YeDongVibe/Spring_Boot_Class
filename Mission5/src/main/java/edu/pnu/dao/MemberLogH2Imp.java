package edu.pnu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository //MemberDao라는 객체도 자동으로 객체로 만들어서 container에 올라감
public class MemberLogH2Imp  implements MemberLogInterface{
	@Autowired //dataSource와 연결된 부분을 자동적으로 호출하여 새로 호출할 필요없이 받아와짐 / memberservice와 연결
	private DataSource dataSource;

	public int addLog(Map<String, Object> maps) {
		int re = 0;
		PreparedStatement psmt = null;
		try (Connection con = dataSource.getConnection()){
			String sql = "INSERT INTO dblog (method, sqlstring, success) values (?, ?, ?)";

			psmt = con.prepareStatement(sql);
			psmt.setObject(1, maps.get("method"));
			psmt.setObject(2, maps.get("sqlstring"));
			psmt.setObject(3,  maps.get("success"));

			re = psmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("추가 예외 발생");
			e.printStackTrace();
		} 
//		finally {
//
//			try {
//				if (psmt != null)
//					psmt.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//
//		}

		return re;
		
	}
}
