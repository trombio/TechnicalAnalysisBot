package com.crypto.jmes.test;

import java.util.List;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseTimeSeries;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;

import com.crypto.jmes.bean.TrendIndicator;
import com.crypto.jmes.service.ExchangeService;
import com.crypto.jmes.service.TechnicalAnalysisService;
import com.crypto.jmes.service.impl.BinanceService;
import com.crypto.jmes.service.impl.TechnicalAnalysisServiceImpl;
import com.crypto.jmes.util.Interval;

public class AllSymbolsTest {
	
	private ExchangeService service;
	private TechnicalAnalysisService taService;
	
	@Before
	public void prepare(){
		service = new BinanceService();
		taService = new TechnicalAnalysisServiceImpl();
	}
	
	@Test
	public void analyseAllSymbolsTest(){
		List<String> symbols = service.getAllAvailableSymbols();
		
		for(String symbol : symbols){
			if(symbol.contains("BTC")) {
				List<Bar> bars = service.getCandles(symbol, Interval.FOUR_HOUR);
				TimeSeries ts = new BaseTimeSeries(bars);
				
				ClosePriceIndicator closePrice = new ClosePriceIndicator(ts);
				TrendIndicator ti2 = taService.analyseMATrend(closePrice, 50);
				TrendIndicator ti1 = taService.analyseMATrend(closePrice, 20);
				TrendIndicator ti3 = taService.analyseMATrend(closePrice, 200);
				
				System.out.println("Symbol: " +  symbol);
				System.out.println("->" + ti1);
				System.out.println("->" + ti2);
				System.out.println("->" + ti3);
				System.out.println();
			}
		}
	}

}
