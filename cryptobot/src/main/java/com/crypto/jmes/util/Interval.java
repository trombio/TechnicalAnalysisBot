package com.crypto.jmes.util;

public enum Interval {

	HOUR(1),
	FOUR_HOUR(4),
	SIX_HOUR(6),
	DAILY(1),
	WEEKLY(1);

	private int value;

	Interval(int value){
		this.value = value;
	}

	public int getValue(){
		return value;
	}

}