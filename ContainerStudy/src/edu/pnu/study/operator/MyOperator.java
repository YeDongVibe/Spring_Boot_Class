package edu.pnu.study.operator;

import java.util.Calendar;

public abstract class MyOperator {

	private String cmd;
	private Calendar createdTime;
	private Calendar lastUsedTime;
	
	public MyOperator(String cmd) {
		this.cmd = cmd;
		createdTime = lastUsedTime = Calendar.getInstance();
	}
	
	public abstract int operate(int a, int b);

	public String getCmd() {
		return cmd;
	}

	public Calendar getCreatedTime() {
		return createdTime;
	}

	public Calendar getLastUsedTime() {
		return lastUsedTime;
	}

	public void setLastUsedTime(Calendar lastUsedTime) {
		this.lastUsedTime = lastUsedTime;
	}
}

class MyOperatorForAdd extends MyOperator {

	public MyOperatorForAdd() {
		super("+");
	}
	
	@Override
	public int operate(int a, int b) {
		return a + b;
	}
}

class MyOperatorForSub extends MyOperator {

	public MyOperatorForSub() {
		super("-");
	}	
	
	@Override
	public int operate(int a, int b) {
		return a - b;
	}
}

class MyOperatorForMul extends MyOperator {

	public MyOperatorForMul() {
		super("*");
	}		
	
	@Override
	public int operate(int a, int b) {
		return a * b;
	}
}

class MyOperatorForDiv extends MyOperator {

	public MyOperatorForDiv() {
		super("/");
	}
	
	@Override
	public int operate(int a, int b) {
		return a / b;
	}
}