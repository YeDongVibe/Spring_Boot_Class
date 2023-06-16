package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.pnu.domain.MemberVO;

public class MemberH2Impl implements MemberInterface{
	private String driver = "org.h2.Driver";
	private String url = "jdbc:h2:tcp://localhost/~/mission2";
	private String username = "YeDongVibe";
	private String password = "1234";

	private Connection con;

	public MemberH2Impl() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
//	public List<MemberVO> getMembers() {
	public Map<String, Object> getMembers() {
		List<MemberVO> list = new ArrayList<>();

		Map<String, Object> maps = new HashMap<>();
		maps.put("method", "get");
		try {
			Statement st = con.createStatement();
			String sql = String.format("select * from Member");
			ResultSet rs = st.executeQuery(sql);
			maps.put("sqlstring", sql);
			
			
			while (rs.next()) {
				MemberVO mv = MemberVO.builder()
						.id(rs.getInt("id"))
						.pass(rs.getString("pass"))
						.name(rs.getString("name"))
						.regidate(rs.getDate("regidate"))
						.build();
				list.add(mv);
			}
			maps.put("result", list);
			maps.put("success", true);
//			return list;
		} catch (Exception e) {
			maps.put("success", false);
			e.printStackTrace();
		}
		
		return maps;
	}

	@Override
	public Map<String, Object> getMember(Integer id) {
	//public MemberVO getMember(Integer id) {
		
		Map<String, Object> maps = new HashMap<>();
		maps.put("method", "get");
		try {
			Statement st = con.createStatement();
			String sql = String.format("select * from Member where id=%d", id);
			ResultSet rs = st.executeQuery(sql);
			maps.put("sqlstring", sql);
			rs.next();

			MemberVO m = MemberVO.builder()
					.id(rs.getInt("id"))
					.pass(rs.getString("pass"))
					.name(rs.getString("name"))
					.regidate(rs.getDate("regidate"))
					.build();
			
			maps.put("result", m);
			maps.put("success", true);
			
			rs.close();
			st.close();
			//return m;

		} catch (Exception e) {
			maps.put("success", false);
			e.printStackTrace();
		}
		return maps;
	}

	@Override
	public Map<String, Object> addMember(MemberVO member) {
	//public int addMember(MemberVO member) {
		int re = 0;
		PreparedStatement psmt = null;
		
		Map<String, Object> maps = new HashMap<>();
		maps.put("method", "post");
		try {
			String sql = "INSERT INTO MEMBER (pass, name) values (?, ?)";

			psmt = con.prepareStatement(sql);
			maps.put("sqlstring", sql);
			psmt.setString(1, member.getPass());
			psmt.setString(2, member.getName());

			re = psmt.executeUpdate();
			maps.put("result", re);
			maps.put("success", true);
		} catch (Exception e) {
			maps.put("success", false);
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

		return maps;
	}

	@Override
	public Map<String, Object> updateMember(MemberVO member) {
	//public int updateMember(MemberVO member) {
		int re = 0;
		PreparedStatement psmt = null;
		
		Map<String, Object> maps = new HashMap<>();
		maps.put("method", "put");
		try {
			String sql = "UPDATE MEMBER SET name=?, pass=? where id=?";
			psmt = con.prepareStatement(sql);
			maps.put("sqlstring", sql);
			psmt.setString(1, member.getName());
			psmt.setString(2, member.getPass());
			psmt.setInt(3, member.getId());

			re = psmt.executeUpdate();
			maps.put("result", re);
			maps.put("success", true);
		} catch (Exception e) {
			maps.put("success", false);
			System.out.println("업데이트 예외 발생");
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return maps;
	}

	@Override
	public Map<String, Object> deleteMember(Integer id) {
	//public int deleteMember(Integer id) {
		int re = 0;
		PreparedStatement psmt = null;
		
		Map<String, Object> maps = new HashMap<>();
		maps.put("method", "delete");
		try {
			String sql = "DELETE FROM MEMBER WHERE id=?"; 
			psmt = con.prepareStatement(sql);
			maps.put("sqlstring", sql);
			psmt.setInt(1, id);

			re = psmt.executeUpdate();
			maps.put("result", re);
			maps.put("success", true);
		} catch (Exception e) {
			maps.put("success", false);
			System.out.println("삭제 예외 발생");
			e.printStackTrace();
		} finally {
			try {
				if (psmt != null)
					psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return maps;
	}


}
