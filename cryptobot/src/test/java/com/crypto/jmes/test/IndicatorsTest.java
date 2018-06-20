package com.crypto.jmes.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseTimeSeries;
import org.ta4j.core.Decimal;
import org.ta4j.core.Rule;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.SMAIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.indicators.helpers.VolumeIndicator;
import org.ta4j.core.trading.rules.CrossedUpIndicatorRule;
import org.ta4j.core.trading.rules.OverIndicatorRule;

import com.crypto.jmes.bean.TrendIndicator;
import com.crypto.jmes.bean.VolumeAnalysis;
import com.crypto.jmes.service.ExchangeService;
import com.crypto.jmes.service.TechnicalAnalysisService;
import com.crypto.jmes.service.impl.BinanceService;
import com.crypto.jmes.service.impl.TechnicalAnalysisServiceImpl;
import com.crypto.jmes.util.Interval;

public class IndicatorsTest {
	
	private static ExchangeService binanceService;
	private static TimeSeries series;
	private static TechnicalAnalysisService taService;
	
	@BeforeClass
	public static void prepare(){
		binanceService = new BinanceService();
		List<Bar> bars = binanceService.getCandles("BTCUSDT", Interval.FOUR_HOUR);
		series = new BaseTimeSeries(bars);
		taService = new TechnicalAnalysisServiceImpl();
	}
	
	@Test
	public void addIndicatorsAndShowTATest(){
		List<Bar> bars = series.getBarData();
		//se analiza el Indicador EMA 20
		ClosePriceIndicator closePrice = new ClosePriceIndicator(series);
		TrendIndicator ti = taService.analyseMATrend(closePrice, 50);
		TrendIndicator ti2 = taService.analyseMATrend(closePrice, 100);
	
		System.out.println("BTC Price close: " + bars.get(bars.size() - 1).getClosePrice());
		System.out.println(ti);
		System.out.println(ti2);
		
		
	}
	
	@Test
	public void volumeAnalysisTest(){
		VolumeIndicator vi = new VolumeIndicator(series);
		VolumeAnalysis va = taService.analyseVolume(vi, 20);
		
		System.out.println("Volumen promedio: " + va.getAvgVolume());
		System.out.println("Volumen de la ultima barra: " + va.getLastVolume());
		System.out.println("Volumen mas alto en 20 barras: " + va.getHighestVolume());
		
	}
	
	public void volumeAndTATest(){
		
	}
	
}
