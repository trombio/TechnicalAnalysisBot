package com.crypto.jmes.dao.impl;

import java.util.List;

import org.ta4j.core.Bar;

import com.crypto.jmes.dao.ExchangeDAO;
import com.crypto.jmes.util.Interval;
import com.crypto.jmes.util.SerializeUtil;

/**
 * Este proxy se diseño debido a que no es necesario traer siempre el historial completo
 * de los candles de Binance, esta información se guardará en un archivo y este DAO 
 * se encargara de administrar los recursos de las consultas.
 * @author Jean Michel
 *
 */
public class BinanceProxy implements ExchangeDAO{
	
	private BinanceDAO binanceDao;
	private SerializeUtil utils;
	

	public BinanceProxy(BinanceDAO dao){
		this.binanceDao = dao;
		utils = new SerializeUtil("binance");
	}
	
	@Override
	public List<Bar> getSymbolInfo(String symbol, Interval interval) {
		List<Bar> bars = null; 
		if(utils.existsFile(symbol, interval.name())){
			bars = utils.retrieveHistoryData(symbol, interval);
		} else {
			bars = binanceDao.getSymbolInfo(symbol, interval);
			utils.saveHistory(bars, symbol, interval);
		}
		return bars;
	}

	@Override
	public List<String> getAvailableSymbols() {
		return binanceDao.getAvailableSymbols();
	}

}
