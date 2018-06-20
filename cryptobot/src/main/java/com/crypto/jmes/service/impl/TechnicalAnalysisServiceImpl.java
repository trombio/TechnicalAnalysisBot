package com.crypto.jmes.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

import org.ta4j.core.Indicator;
import org.ta4j.core.Rule;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.SMAIndicator;
import org.ta4j.core.indicators.helpers.VolumeIndicator;
import org.ta4j.core.trading.rules.CrossedUpIndicatorRule;
import org.ta4j.core.trading.rules.OverIndicatorRule;

import com.crypto.jmes.bean.TrendIndicator;
import com.crypto.jmes.bean.VolumeAnalysis;
import com.crypto.jmes.service.TechnicalAnalysisService;

public class TechnicalAnalysisServiceImpl implements TechnicalAnalysisService{

	@Override
	public TrendIndicator analyseMATrend(Indicator indicator, int timeFrame){
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

	@Override
	public VolumeAnalysis analyseVolume(VolumeIndicator vol, int bars) {
		SMAIndicator sma = new SMAIndicator(vol, 20);
		VolumeAnalysis va = new VolumeAnalysis();
		int maxIndex = vol.getTimeSeries().getBarCount(); 
		
		va.setAvgVolume(sma.getValue(maxIndex - 1).doubleValue());
		va.setLastVolume(vol.getValue(maxIndex - 1).doubleValue());
		
		ArrayList<Double> aux = new ArrayList<Double>();
		for(int i = maxIndex - (bars + 1); i < maxIndex; i++){
			aux.add(vol.getValue(i).doubleValue());
		}
		va.setHighestVolume(Collections.max(aux));
		
		return va;
	}
	
	

}
