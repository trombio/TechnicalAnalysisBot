package com.crypto.monitor.util;

import java.util.HashMap;
import java.util.Map;

import com.crypto.jmes.ta.ExponentialMovingAverageIndicator;
import com.crypto.jmes.ta.TAIndicator;
import com.crypto.jmes.util.Interval;

public class MonitorConstants{

	public static final Map<String, TAIndicator> DEFAULT_INDICATORS = new HashMap<String, TAIndicator>();
	
	public static final Map<String, Interval> DEFAULT_INTERVALS = new HashMap<String, Interval>();

	static{
		DEFAULT_INDICATORS.put("EMA 9", new ExponentialMovingAverageIndicator(9));
		DEFAULT_INDICATORS.put("EMA 20", new ExponentialMovingAverageIndicator(20));
		DEFAULT_INDICATORS.put("EMA 50", new ExponentialMovingAverageIndicator(50));
		DEFAULT_INDICATORS.put("EMA 100", new ExponentialMovingAverageIndicator(100));
		DEFAULT_INDICATORS.put("EMA 200", new ExponentialMovingAverageIndicator(200));
		
		DEFAULT_INTERVALS.put("4 Horas", Interval.FOUR_HOUR);
		DEFAULT_INTERVALS.put("1 Dia", Interval.DAILY);
		DEFAULT_INTERVALS.put("1 Semana", Interval.WEEKLY);
	}

}
