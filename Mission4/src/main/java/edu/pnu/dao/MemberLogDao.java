package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;




public class MemberLogDao {

	private String driver = "org.h2.Driver";
	private String url = "jdbc:h2:tcp://localhost/~/mission2";
	private String username = "YeDongVibe";
	private String password = "1234";

	private Connection con;

	public MemberLogDao() {
		try {
			Class.forName(driver);

			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int addLog(Map<String, Object> maps) {
		int re = 0;
		PreparedStatement psmt = null;
		try {
			String sql = "INSERT INTO dblog (method, sqlstring, success) values (?, ?, ?)";

			psmt = con.prepareStatement(sql);
			psmt.setObject(1, maps.get("method"));
			psmt.setObject(2, maps.get("sqlstring"));
			psmt.setObject(3,  maps.get("success"));

			re = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("추가 예외 발생");
			e.printStackTrace();
		} finally {

			try {
				if (psmt != null)
					psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return re;
		
	}




	
}
