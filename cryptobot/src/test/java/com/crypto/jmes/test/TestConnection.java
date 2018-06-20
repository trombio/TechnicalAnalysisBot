package com.crypto.jmes.test;

import org.junit.Before;
import org.junit.Test; 
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import org.ta4j.core.BaseTimeSeries;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.CachedIndicator;
import org.ta4j.core.indicators.RSIIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;

import java.time.Clock;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.crypto.jmes.service.ExchangeService;
import com.crypto.jmes.service.impl.BinanceService;
import com.crypto.jmes.util.Interval;
import com.webcerebrium.binance.api.BinanceApi;
import com.webcerebrium.binance.api.BinanceApiException;
import com.webcerebrium.binance.datatype.BinanceCandlestick;
import com.webcerebrium.binance.datatype.BinanceInterval;
import com.webcerebrium.binance.datatype.BinanceSymbol;

public class TestConnection {
	
	private ExchangeService binanceService;
	
	@Before
	public void prepare(){
		binanceService = new BinanceService();
	}
	
	//@Test
	public void testBinanceConnecion() throws BinanceApiException{
		BinanceApi api = new BinanceApi();
		BinanceSymbol symbol = new BinanceSymbol("BTCUSDT");
		List<BinanceCandlestick> candles = api.klines(symbol, BinanceInterval.FOUR_HOURS, 5, null);
		System.out.println("Elementos: " + candles.size());
		System.out.println("4H Candles: ");
		for(BinanceCandlestick c : candles){
			System.out.println(c);
		}
	}
	
	//@Test
	public void testSeries() throws BinanceApiException{
		List<Bar> bars = binanceService.getCandles("BTCUSDT", Interval.FOUR_HOUR);
		TimeSeries series = new BaseTimeSeries(bars);
		ClosePriceIndicator cpi = new ClosePriceIndicator(series);
		RSIIndicator rsi = new RSIIndicator(cpi, 14);
		
		for(int i = 0; i < bars.size(); i++){
			System.out.println("Candle: open = " + bars.get(i).getOpenPrice() + 
					", close = " + bars.get(i).getClosePrice() + 
					" Valor RSI: " + rsi.getValue(i));
		}
		
	}
	
	@Test
	public void getAllSymbols() throws BinanceApiException{
		System.out.println((new BinanceApi()).allBookTickers());
	}
	
	

}
