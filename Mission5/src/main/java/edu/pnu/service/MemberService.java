package edu.pnu.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.pnu.dao.MemberH2Impl;
import edu.pnu.dao.MemberInterface;
import edu.pnu.dao.MemberLogInterface;
import edu.pnu.domain.MemberVO;

//@Service
public class MemberService {
	@Autowired
	private MemberInterface memberDao;
	@Autowired
	private MemberLogInterface logDao;
	
//	public MemberService() {
////		memberDao = new MemberH2Impl();
////		logDao = new MemberLogDao();
//		//memberDao = new MemberDaoListImpl();
//
//	}
	public List<MemberVO> getMembers() {
//		return memberDao.getMembers();
		Map<String, Object> maps = memberDao.getMembers();
		logDao.addLog(maps);
		return (List<MemberVO>) maps.get("result");
	}
	public MemberVO getMember(Integer id) {
		Map<String, Object> maps = memberDao.getMember(id);
		logDao.addLog(maps);
		return (MemberVO) maps.get("result");
	}
	public int addMember(MemberVO member) {
		Map<String, Object> maps = memberDao.addMember(member);
		logDao.addLog(maps);
		return (int) maps.get("result");
	}
	public int updateMember(MemberVO member) {
		Map<String, Object> maps = memberDao.updateMember(member);
		logDao.addLog(maps);
		//return (int) maps.get("result");
		int ret =  (int) maps.get("result");
		return ret;
	}
	public int deleteMember(Integer id) {
		Map<String, Object> maps = memberDao.deleteMember(id);
		logDao.addLog(maps);
		return (int) maps.get("result");
	}

}
