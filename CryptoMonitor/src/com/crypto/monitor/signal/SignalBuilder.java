package com.crypto.monitor.signal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.ta4j.core.Indicator;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.SMAIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.indicators.helpers.VolumeIndicator;

import com.crypto.jmes.bean.VolumeAnalysis;
import com.crypto.jmes.ta.TAIndicator;

public class SignalBuilder {

	private Signal signal;
	private List<TAIndicator> taIndicators;
	private TimeSeries series;

	public SignalBuilder(String simbol, TimeSeries series) {
		signal = new Signal();
		signal.setSymbol(simbol);
		this.series = series;
		taIndicators = new ArrayList<TAIndicator>();
	}


	public void addTrendIndicator(TAIndicator ta){
		taIndicators.add(ta);
	}
	
	public void addVolumeIndicator(){
		VolumeIndicator vol = new VolumeIndicator(series);

		SMAIndicator sma = new SMAIndicator(vol, 20);
		VolumeAnalysis va = new VolumeAnalysis();
		int maxIndex = vol.getTimeSeries().getBarCount();

		va.setAvgVolume(sma.getValue(maxIndex - 1).doubleValue());
		va.setLastVolume(vol.getValue(maxIndex - 1).doubleValue());

		ArrayList<Double> aux = new ArrayList<Double>();
		for(int i = maxIndex - (series.getBarCount() + 1); i < maxIndex; i++){
			aux.add(vol.getValue(i).doubleValue());
		}
		va.setHighestVolume(Collections.max(aux));
		signal.setVolumeAnalysis(va);
	}


	public Signal build(){
		ClosePriceIndicator closePrice = new ClosePriceIndicator(series);
		for(TAIndicator ta : taIndicators) {
			signal.addTrendIndicator(ta.analyzeTrend(closePrice));
		}
		
		return signal;
	}

}
