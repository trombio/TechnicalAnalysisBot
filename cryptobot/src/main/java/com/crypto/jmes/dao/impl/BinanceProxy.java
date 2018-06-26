package com.crypto.jmes.dao.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.ta4j.core.Bar;

import com.crypto.jmes.dao.ExchangeDAO;
import com.crypto.jmes.util.Interval;
import com.crypto.jmes.util.IntervalUtil;
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
	private List<String> symbols;
	private Date snapshot;

	public BinanceProxy(BinanceDAO dao){
		this.binanceDao = dao;
		utils = new SerializeUtil("binance");
	}
	
	@Override
	public List<Bar> getSymbolInfo(String symbol, Interval interval) {
		List<Bar> bars = null; 
		if(utils.existsFile(symbol, interval.name())){
			bars = utils.retrieveHistoryData(symbol, interval);
			if(bars != null) {
				Bar last = bars.get(bars.size() - 1);
				int periods = IntervalUtil.getPeriodsFromBarUntilNow(last, interval);
				if(periods > 0) {
					List<Bar> updateBars = binanceDao.getSymbolInfo(symbol, interval, periods);
					Bar first = updateBars.get(0);
					int diff = IntervalUtil.getPeriodsFromBars(first, last, interval);
					if(diff > interval.getValue()) {
						throw new IllegalStateException("Too much difference between periods: " 
							+ first.getEndTime() + " ," + last.getEndTime());
					}
					bars.addAll(updateBars);
					utils.saveHistory(bars, symbol, interval);
				} 
			}
		} else {
			bars = binanceDao.getSymbolInfo(symbol, interval);
			utils.saveHistory(bars, symbol, interval);
		}
		return bars;
	}
	
	@Override
	public List<String> getAvailableSymbols() {
		Calendar cal = Calendar.getInstance();
		int today = cal.get(Calendar.DAY_OF_YEAR);
		int snapshotDay = 0;
		if(this.snapshot == null) {
			snapshotDay = today;
		} else {
			Calendar c = Calendar.getInstance();
			c.setTime(snapshot);
			snapshotDay = c.get(Calendar.DAY_OF_YEAR);
		}
		//TODO implementar un serializable tambien
		if(symbols == null || today != snapshotDay) {
			symbols = binanceDao.getAvailableSymbols();
		}
		return symbols;
	}

	@Override
	public List<Bar> getSymbolInfo(String symbol, Interval interva, int since) {
		// TODO Auto-generated method stub
		return null;
	}

}
