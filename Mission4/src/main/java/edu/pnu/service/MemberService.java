package edu.pnu.service;

import java.util.List;
import java.util.Map;

import edu.pnu.dao.MemberH2Impl;
import edu.pnu.dao.MemberInterface;
import edu.pnu.dao.MemberLogDao;
import edu.pnu.domain.MemberVO;

public class MemberService {
	private MemberInterface memberDao;
	private MemberLogDao logDao;
	
	public MemberService() {
		memberDao = new MemberH2Impl();
		logDao = new MemberLogDao();
		//memberDao = new MemberDaoListImpl();

	}
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
