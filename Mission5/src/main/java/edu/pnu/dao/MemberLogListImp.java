package edu.pnu.dao;

import java.io.File;
import java.io.FileWriter;
import java.util.Map;

public class MemberLogListImp implements MemberLogInterface {

	@Override
	public int addLog(Map<String, Object> maps) {
		return 0;
//		try {
//			File file = new File("log.txt");
//			FileWriter fw = new FileWriter(file, true);
//			fw.write(maps.put("method", "get"), maps.put("sqlstring", sql), maps.put(null, fw));
//			fw.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

}
