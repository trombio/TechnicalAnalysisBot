package com.crypto.jmes.test;

import java.time.Period;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import org.joda.time.Hours;
import org.junit.Before;
import org.junit.Test;
import org.ta4j.core.Bar;

import com.crypto.jmes.dao.impl.BinanceDAO;
import com.crypto.jmes.util.Interval;
import com.crypto.jmes.util.IntervalUtil;
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
	public void refreshFiles4HourTest() {
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
			
		}
	}
	
	@Test
	public void refreshFilesDailyTest() {
		List<Bar> bars = null;
		System.out.println(bars);
		if(utils.existsFile(symbol, interval.name())){
			bars = utils.retrieveHistoryData(symbol, interval);
			Bar b = bars.get(bars.size() - 1);
			ZonedDateTime zdt = b.getEndTime();
			ZoneId zone = zdt.getZone();
			ZonedDateTime zdt2 = ZonedDateTime.now(zone);
			
			Period p = Period.between(zdt.toLocalDate(), zdt2.toLocalDate());
			
			int periods = p.getDays();
			System.out.println("Days: =" + periods);
		}
	}
	
	@Test
	public void refreshLocalBars() {
		List<Bar> bars = null;
		bars = utils.retrieveHistoryData(symbol, interval);
		Bar last = bars.get(bars.size() - 1);
		int periods = IntervalUtil.getPeriodsFromBarUntilNow(last, interval);
		List<Bar> remaining = null;
		remaining = binanceDao.getSymbolInfo(symbol, interval, periods);
		Bar first = remaining.get(0);
		System.out.println("La ultima:" + last);
		System.out.println("La primera:" + first);
	}

}
