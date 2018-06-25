package com.crypto.jmes.dao;

import java.util.List;

import org.ta4j.core.Bar;

import com.crypto.jmes.util.Interval;

public interface ExchangeDAO {
	
	public List<Bar> getSymbolInfo(String symbol, Interval interval);
	
	public List<Bar> getSymbolInfo(String symbol, Interval interva, int since);
	
	public List<String> getAvailableSymbols();

}
