package com.crypto.jmes.ta;

import org.ta4j.core.Indicator;

import com.crypto.jmes.bean.TrendIndicator;

public interface TAIndicator {
	
	public String getName();
	
	public TrendIndicator analyzeTrend(Indicator indicator);
	
	

}
