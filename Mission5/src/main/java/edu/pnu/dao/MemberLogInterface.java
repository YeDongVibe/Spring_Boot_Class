package edu.pnu.dao;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


public interface MemberLogInterface {
	int addLog (Map<String, Object> maps);
}
