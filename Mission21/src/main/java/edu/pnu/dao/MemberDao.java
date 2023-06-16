package edu.pnu.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.pnu.domain.Member;

@Repository //MemberDao라는 객체도 자동으로 객체로 만들어서 container에 올라감
public class MemberDao {
	@Autowired //dataSource와 연결된 부분을 자동적으로 호출하여 새로 호출할 필요없이 받아와짐 / memberservice와 연결
	private DataSource dataSource;

	public Member getMember(Long id) {
		try {
			Statement stmt = dataSource.getConnection().createStatement();

			ResultSet rs = stmt.executeQuery(String.format("select * from Member where id=%d", id));
			rs.next();

			Member m = Member.builder()
					.id(rs.getLong("id"))
					.pass(rs.getString("pass"))
					.name(rs.getString("name"))
					.regidate(rs.getDate("regidate"))
					.build();

			rs.close();
			stmt.close();
			return m;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public List<Member> getMembers() {
		List<Member> list = new ArrayList<>();

		try {
			Statement stmt = dataSource.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(String.format("select * from Member"));

			while (rs.next()) {
				Member m = Member.builder()
						.id(rs.getLong("id"))
						.pass(rs.getString("pass"))
						.name(rs.getString("name"))
						.regidate(rs.getDate("regidate"))
						.build();
				list.add(m);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	public int insertMember(Member member) {
		int result = 0;
        PreparedStatement psmt = null;
		
		try {
			String sql = "INSERT INTO MEMBER (pass, name) values (?, ?)";
			
			psmt = dataSource.getConnection().prepareStatement(sql);
			psmt.setString(1, member.getPass());
			psmt.setString(2, member.getName());
			
			result = psmt.executeUpdate(); 
			
		} catch (Exception e) {
			System.out.println("삽입 예외 발생");
			e.printStackTrace();
		}finally {

			try {
	        	if(psmt != null) psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	
    }
        
        return result;
    }


	public int updateMember(Member member) {
		int result = 0;
		PreparedStatement psmt = null;
		try {
			String sql = "UPDATE MEMBER SET name=? where id=?";
			psmt = dataSource.getConnection().prepareStatement(sql);
			psmt.setString(1, member.getName());
			psmt.setLong(2, member.getId());

			result = psmt.executeUpdate();
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
		return result;
	}

	public int deleteMember(Long id) {
		int result = 0;
		PreparedStatement psmt = null;
		try {
			String sql = "DELETE FROM MEMBER WHERE id=?"; 
			psmt = dataSource.getConnection().prepareStatement(sql);
			psmt.setLong(1, id);

			result = psmt.executeUpdate();
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
		return result;
	}

}
