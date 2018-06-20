package com.crypto.jmes.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseTimeSeries;
import org.ta4j.core.Rule;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.SMAIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.trading.rules.CrossedDownIndicatorRule;
import org.ta4j.core.trading.rules.CrossedUpIndicatorRule;
import org.ta4j.core.trading.rules.OverIndicatorRule;

import com.crypto.jmes.service.ExchangeService;
import com.crypto.jmes.service.impl.BinanceService;
import com.crypto.jmes.util.Interval;

public class TestTA {
	
	private ExchangeService binanceService;
	
	@Before
	public void init(){
		binanceService = new BinanceService();
	}
	
	@Test
	public void cross50MAStrategy(){
		List<Bar> bars = binanceService.getCandles("BTCUSDT", Interval.FOUR_HOUR);
		TimeSeries series = new BaseTimeSeries(bars);
		
		ClosePriceIndicator closePrice = new ClosePriceIndicator(series);
		EMAIndicator ema50 = new EMAIndicator(closePrice, 50);
		System.out.println("EMA50 valor hoy: " + ema50.getValue(bars.size() - 1));
		
		Rule over = new OverIndicatorRule(closePrice, ema50);
		Rule cross = new CrossedUpIndicatorRule(closePrice, ema50);
		
		for(int i = bars.size() - 15;i < bars.size() ; i++){
			System.out.println("Over Se satisface en " + bars.get(i).getClosePrice() + "? " + over.isSatisfied(i));
			System.out.println("Cross Se satisface en " + bars.get(i).getClosePrice() + "? " + cross.isSatisfied(i));
			System.out.println("");
		}
		
		
	}

}
