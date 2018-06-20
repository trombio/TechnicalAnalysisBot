package com.crypto.jmes.service;

import java.util.Collection;

import org.ta4j.core.Indicator;
import org.ta4j.core.indicators.helpers.VolumeIndicator;

import com.crypto.jmes.bean.TrendIndicator;
import com.crypto.jmes.bean.VolumeAnalysis;

public interface TechnicalAnalysisService {
	
	public TrendIndicator analyseMATrend(Indicator indicator, int timeFrame);
	
	public VolumeAnalysis analyseVolume(VolumeIndicator vol, int bars);

}
