package com.crypto.jmes.test;

import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.util.Date;
import java.util.List;

import org.joda.time.Hours;
import org.junit.Before;
import org.junit.Test;
import org.ta4j.core.Bar;

import com.crypto.jmes.dao.impl.BinanceDAO;
import com.crypto.jmes.util.Interval;
import com.crypto.jmes.util.SerializeUtil;

public class ExchangeProxyTest {
	
	private BinanceDAO binanceDao;
	private SerializeUtil utils;
	private String symbol;
	private Interval interval;
	
	@Before
	public void prepare() {
		binanceDao = new BinanceDAO();
		utils = new SerializeUtil("binance");
		symbol = "BTCUSDT";
		interval = Interval.FOUR_HOUR;
	}
	
	@Test
	public void refreshFilesTest() {
		List<Bar> bars = null;
		System.out.println(bars);
		if(utils.existsFile(symbol, interval.name())){
			bars = utils.retrieveHistoryData(symbol, interval);
			Bar b = bars.get(bars.size() - 1);
			ZonedDateTime zdt = b.getEndTime();
			ZoneId zone = zdt.getZone();
			ZonedDateTime zdt2 = ZonedDateTime.now(zone);
			
			Hours h = Hours.hoursBetween(new org.joda.time.Instant(zdt.toInstant().toEpochMilli()), 
					new org.joda.time.Instant(zdt2.toInstant().toEpochMilli()));
			long hours = h.getHours();
			int periods = (int)hours / 4;
			System.out.println("Hours: =" + hours + " periods = " + periods);
			
		} else {
			bars = binanceDao.getSymbolInfo(symbol, interval);
			utils.saveHistory(bars, symbol, interval);
		}
	}
	

}
