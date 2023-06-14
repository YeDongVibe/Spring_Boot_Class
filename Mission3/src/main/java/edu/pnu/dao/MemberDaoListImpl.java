package edu.pnu.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.pnu.domain.MemberVO;

public class MemberDaoListImpl implements MemberInterface{

	private List<MemberVO> list;
	
	public MemberDaoListImpl() {
		list = new ArrayList<>();
		for(int i = 1; i <= 5; i++) {
			list.add(new MemberVO(i, "1234", "이름"+i, new Date()));
		}
	}
	@Override
	public List<MemberVO> getMembers() {

		return list;
	}

	@Override
	public MemberVO getMember(Integer id) {
		for(MemberVO m : list) {
			if(m.getId() == id)
				return m;
		}
		return null;
	}

	@Override
	public int addMember(MemberVO member) {
		member.setId(list.size() + 1);
		member.setRegidate(new Date());
		list.add(member);
		return 1;
	}

	@Override
	public int updateMember(MemberVO member) {
		for(MemberVO m : list) {
			if(m.getId() == member.getId()) {
				m.setName(member.getName());
				m.setPass(member.getPass());
				return 1;
			}
		}
		return 0;
	}

	@Override
	public int deleteMember(Integer id) {
//		for (MemberVO m : list) {
//			if(m.getId() == id) {
//				list.remove(m);
//			}
//		}
		for(int i = 0; i <list.size();i++) {
			if(list.get(i).getId() == id) {
				list.remove(i);
				return 1;
			}
		}
		return 0;
	}

}
