package com.crypto.jmes.bean;

import java.math.BigDecimal;

public class TrendIndicator {
	
	private String name;
	private BigDecimal value;
	
	boolean bullish;
	boolean bearish;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public boolean isBullish() {
		return bullish;
	}
	public void setBullish(boolean bullish) {
		this.bullish = bullish;
	}
	public boolean isBearish() {
		return bearish;
	}
	public void setBearish(boolean bearish) {
		this.bearish = bearish;
	}
	
	public String toString(){
		String result = "Name: " + name + ", Value: " + value + ", ";
		result += this.isBullish() ? "bullish" : "";
		result += this.isBearish() ? "bearish" : "";
		return result;
	}
}
