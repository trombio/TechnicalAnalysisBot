package com.crypto.jmes.service.impl;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;

import com.crypto.jmes.dao.ExchangeDAO;
import com.crypto.jmes.dao.impl.BinanceDAO;
import com.crypto.jmes.dao.impl.BinanceProxy;
import com.crypto.jmes.service.ExchangeService;
import com.crypto.jmes.util.Interval;
import com.webcerebrium.binance.api.BinanceApi;
import com.webcerebrium.binance.api.BinanceApiException;
import com.webcerebrium.binance.datatype.BinanceCandlestick;
import com.webcerebrium.binance.datatype.BinanceInterval;
import com.webcerebrium.binance.datatype.BinanceSymbol;

public class BinanceService implements ExchangeService{
	
	private ExchangeDAO binanceDao;
	
	public BinanceService(){
		binanceDao = new BinanceProxy(new BinanceDAO());
	}
	
	public List<Bar> getCandles(String symbol, Interval interval) {
		return binanceDao.getSymbolInfo(symbol, interval);
	}
	
	public List<String> getAllAvailableSymbols() {
		return binanceDao.getAvailableSymbols();
	}

}
