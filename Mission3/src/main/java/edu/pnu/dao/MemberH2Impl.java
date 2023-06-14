package edu.pnu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
	public List<MemberVO> getMembers() {
		List<MemberVO> list = new ArrayList<>();

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(String.format("select * from Member"));

			while (rs.next()) {
				MemberVO mv = MemberVO.builder()
						.id(rs.getInt("id"))
						.pass(rs.getString("pass"))
						.name(rs.getString("name"))
						.regidate(rs.getDate("regidate"))
						.build();
				list.add(mv);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public MemberVO getMember(Integer id) {
		try {
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(String.format("select * from Member where id=%d", id));
			rs.next();

			MemberVO m = MemberVO.builder()
					.id(rs.getInt("id"))
					.pass(rs.getString("pass"))
					.name(rs.getString("name"))
					.regidate(rs.getDate("regidate"))
					.build();

			rs.close();
			st.close();
			return m;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int addMember(MemberVO member) {
		int re = 0;
		PreparedStatement psmt = null;
		try {
			String sql = "INSERT INTO MEMBER (pass, name) values (?, ?)";

			psmt = con.prepareStatement(sql);
			psmt.setString(1, member.getPass());
			psmt.setString(2, member.getName());

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

	@Override
	public int updateMember(MemberVO member) {
		int re = 0;
		PreparedStatement psmt = null;
		try {
			String sql = "UPDATE MEMBER SET name=?, pass=? where id=?";
			psmt = con.prepareStatement(sql);
			psmt.setString(1, member.getName());
			psmt.setString(2, member.getPass());
			psmt.setInt(3, member.getId());

			re = psmt.executeUpdate();
		} catch (Exception e) {
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
		return re;
	}

	@Override
	public int deleteMember(Integer id) {
		int re = 0;
		PreparedStatement psmt = null;
		try {
			String sql = "DELETE FROM MEMBER WHERE id=?"; 
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, id);

			re = psmt.executeUpdate();
		} catch (Exception e) {
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
		return re;
	}


}
