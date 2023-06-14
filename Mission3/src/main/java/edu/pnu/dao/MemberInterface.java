package edu.pnu.dao;

import java.util.List;

import edu.pnu.domain.MemberVO;

public interface MemberInterface {
	List<MemberVO> getMembers();
	
	MemberVO getMember (Integer id);
	
	int addMember (MemberVO member);
	
	int updateMember (MemberVO member);
	
	int deleteMember(Integer id);
	

}
