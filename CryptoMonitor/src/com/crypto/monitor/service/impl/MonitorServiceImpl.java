package com.crypto.monitor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseTimeSeries;
import org.ta4j.core.TimeSeries;

import com.crypto.jmes.service.TechnicalAnalysisService;
import com.crypto.jmes.service.impl.BinanceService;
import com.crypto.jmes.service.impl.TechnicalAnalysisServiceImpl;
import com.crypto.jmes.util.Interval;
import com.crypto.monitor.bean.Signal;
import com.crypto.monitor.service.MonitorService;

@Service("monitorService")
public class MonitorServiceImpl implements MonitorService{
	
	private BinanceService binanceService;
	private TechnicalAnalysisService taService;
	
	public MonitorServiceImpl() {
		binanceService = new BinanceService();
		taService = new TechnicalAnalysisServiceImpl();
	}

	@Override
	public List<Signal> getCryptoSignals(String Pair, Interval interval) {
		List<String> symbols = binanceService.getAllAvailableSymbols();
		for(String symbol : symbols) {
			List<Bar> bars = binanceService.getCandles(symbol, interval);
			TimeSeries ts = new BaseTimeSeries(bars);
			
			
		}
		
		return null;
	}
	
	

}
