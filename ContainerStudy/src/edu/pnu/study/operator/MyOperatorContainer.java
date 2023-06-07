package edu.pnu.study.operator;

import java.util.ArrayList;
import java.util.Calendar;

public class MyOperatorContainer {

	private ArrayList<MyOperator> container = new ArrayList<>();
	
	public MyOperator getOperator(String cmd) {
		
		// 컨테이너에서 오퍼레이터 객체를 검색한다.
		for (MyOperator mo : container) {
			if (mo.getCmd().equals(cmd)) {
				// 객체가 있으면 현재 시간을 마지막 사용 시간으로 설정하고 리턴
				mo.setLastUsedTime(Calendar.getInstance());
				return mo;
			}
		}
		
		// 요청된 오퍼레이터 객체가 없으면 새로 만들어서 컨테이너에 추가하고 리턴한다.
		MyOperator mo;
		switch(cmd) {
			case "+": mo = new MyOperatorForAdd();	break;
			case "-": mo = new MyOperatorForSub();	break;
			case "*": mo = new MyOperatorForMul();	break;
			case "/": mo = new MyOperatorForDiv();	break;
			default : mo = null;					break;
		}
		if (mo != null)	container.add(mo);
		return mo;
	}
	
	public int doOperate(String cmd, int f, int s) {

		// cmd에 해당하는 오페레이터 객체를 가져온다.
		MyOperator mo = getOperator(cmd);
		if (mo == null) {
			System.out.println("잘못된 명령입니다. : [" + cmd + "]");
			return 0;
		}		
		
		System.out.println("-".repeat(10));
		System.out.println("컨테이너 객체 개수 : " + container.size());
		System.out.println("-".repeat(10));
		
		return mo.operate(f,  s);
	}
	
	// 생존 기준 시간인 millisecond를 넘겨서 사용되지 않은 객체는 제거한다.
	public void releaseOldObject(int millisecond) {
		Calendar current = Calendar.getInstance();

		int size = container.size();
		
		// 컨테이너에서 오퍼레이터 객체들 중 second 시간만큼 사용된 적이 없는 객체를 검색한다.
		for (int i = size - 1 ; 0 <= i ; i--) {

			MyOperator mo = container.get(i);
			
			// 현재 시간과 객체가 마지막으로 사용된 시간을 비교한다.
			long diff = current.getTimeInMillis() - mo.getLastUsedTime().getTimeInMillis();
			
			// 일정 시간(millisecond) 이상 사용되지 않은 객체는 제거한다.
			if (millisecond < diff) {
				container.remove(i);
				mo = null;
			}
		}
		
		// 컨테이너 객체 개수가 변하면 화면에 출력한다.
		int size1 = container.size();
		if (size != size1) {
			System.out.println("Container Size is changed![" + size + " ==> " + size1 + "]");
		}
	}
}
