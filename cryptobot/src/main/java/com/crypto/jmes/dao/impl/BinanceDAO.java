package com.crypto.jmes.dao.impl;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;

import com.crypto.jmes.dao.ExchangeDAO;
import com.crypto.jmes.util.Interval;
import com.webcerebrium.binance.api.BinanceApi;
import com.webcerebrium.binance.api.BinanceApiException;
import com.webcerebrium.binance.datatype.BinanceCandlestick;
import com.webcerebrium.binance.datatype.BinanceInterval;
import com.webcerebrium.binance.datatype.BinanceSymbol;

public class BinanceDAO implements ExchangeDAO{
	
	private BinanceApi api;
	private Logger log = Logger.getLogger(getClass());
	
	public BinanceDAO(){
		api = new BinanceApi();
	}

	@Override
	public List<Bar> getSymbolInfo(String symbol, Interval interval) {
		return this.getSymbolBars(symbol, interval, 0);
	}
	
	@Override
	public List<Bar> getSymbolInfo(String symbol, Interval interval, int since) {
		return this.getSymbolBars(symbol, interval, since);
	}
	
	private List<Bar> getSymbolBars(String symbol, Interval interval, int since){
		List<Bar> bars = new ArrayList<Bar>();
		try {
			BinanceSymbol bs = new BinanceSymbol(symbol);
			BinanceInterval bi = getBinanceInterval(interval);
			
			//candles information retrieved
			List<BinanceCandlestick> candles = null;
			if(since > 0) {
				candles = api.klines(bs, bi, since, null);
			} else {
				candles = api.klines(bs, bi);
			}
			for(BinanceCandlestick b : candles){
				Date d = new Date(b.getCloseTime());
				ZonedDateTime z = ZonedDateTime.ofInstant(d.toInstant(), ZoneId.systemDefault());
				BaseBar bb = new BaseBar(z, 
						b.getOpen().doubleValue(), 
						b.getHigh().doubleValue(), 
						b.getLow().doubleValue(), 
						b.getClose().doubleValue(), 
						b.getVolume().doubleValue());
				
				bars.add(bb);
			}
		} catch (BinanceApiException e) {
			log.error("Error: ", e);
		}
		
		return bars;
	}
	
	@Override
	public List<String> getAvailableSymbols() {
		List<String> symbols = null;
		try {
			symbols = new ArrayList<String>(api.allBookTickersMap().keySet());
		} catch (BinanceApiException e) {
			log.error("Error: ", e);
		}
		return symbols;
	}
	
	private BinanceInterval getBinanceInterval(Interval interval){
		
		switch(interval){
		case FOUR_HOUR:
			return BinanceInterval.FOUR_HOURS;
		case HOUR:
			return BinanceInterval.ONE_HOUR;
		default:
			return BinanceInterval.ONE_DAY;
		}
				
	}

	
	

}
