package com.crypto.jmes.ta;

import java.math.BigDecimal;

import org.ta4j.core.Indicator;
import org.ta4j.core.Rule;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.trading.rules.CrossedUpIndicatorRule;
import org.ta4j.core.trading.rules.OverIndicatorRule;

import com.crypto.jmes.bean.TrendIndicator;

public class ExponentialMovingAverageIndicator implements TAIndicator{
	
	private int timeFrame;
	
	public ExponentialMovingAverageIndicator(int timeFrame) {
		this.timeFrame = timeFrame;
	}

	@Override
	public String getName() {
		return "EMA " + timeFrame;
	}

	@Override
	public TrendIndicator analyzeTrend(Indicator indicator) {
		TrendIndicator ti = new TrendIndicator();
		EMAIndicator ema = new EMAIndicator(indicator, timeFrame);
		int lastCandle = indicator.getTimeSeries().getBarCount() - 1;
		ti.setName("EMA " + timeFrame);
		ti.setValue(new BigDecimal(ema.getValue(lastCandle).doubleValue()));
	
		Rule over = new OverIndicatorRule(indicator, ema);
		Rule cross = new CrossedUpIndicatorRule(indicator, ema);
		
		boolean bullish = over.isSatisfied(lastCandle) || cross.isSatisfied(lastCandle);
		ti.setBullish(bullish);
		ti.setBearish(!bullish);
		return ti;
	}
	
	

}
