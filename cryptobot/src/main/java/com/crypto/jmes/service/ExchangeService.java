package com.crypto.jmes.service;

import java.util.List;

import org.ta4j.core.Bar;

import com.crypto.jmes.util.Interval;

public interface ExchangeService {
	
	public List<Bar> getCandles(String symbol, Interval interval);
	
	public List<String> getAllAvailableSymbols();

}
