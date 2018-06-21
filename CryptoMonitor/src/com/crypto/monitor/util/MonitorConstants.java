package com.crypto.monitor.util;

import java.util.HashMap;
import java.util.Map;

import com.crypto.jmes.ta.ExponentialMovingAverageIndicator;
import com.crypto.jmes.ta.TAIndicator;

public class MonitorConstants{

	public static final Map<String, TAIndicator> DEFAULT_INDICATORS = new HashMap<String, TAIndicator>();

	static{
		DEFAULT_INDICATORS.put("EMA 9", new ExponentialMovingAverageIndicator(9));
		DEFAULT_INDICATORS.put("EMA 20", new ExponentialMovingAverageIndicator(20));
		DEFAULT_INDICATORS.put("EMA 50", new ExponentialMovingAverageIndicator(50));
		DEFAULT_INDICATORS.put("EMA 100", new ExponentialMovingAverageIndicator(100));
		DEFAULT_INDICATORS.put("EMA 200", new ExponentialMovingAverageIndicator(200));
	}

}
