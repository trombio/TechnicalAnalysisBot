package com.crypto.monitor.signal;

import java.util.ArrayList;
import java.util.Collections;

import org.ta4j.core.Indicator;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.SMAIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.indicators.helpers.VolumeIndicator;

import com.crypto.jmes.bean.VolumeAnalysis;
import com.crypto.jmes.ta.TAIndicator;

public class SignalBuilder {

	private Signal signal;

	public SignalBuilder(String simbol) {
		signal = new Signal();
		signal.setSymbol(simbol);
	}


	public void addTrendIndicator(TAIndicator ta, TimeSeries series){
		ClosePriceIndicator closePrice = new ClosePriceIndicator(series);
		addTrendIndicator(ta, closePrice);
	}

	public void addTrendIndicator(TAIndicator ta, Indicator dataOrigin){
		signal.addTrendIndicator(ta.analyzeTrend(dataOrigin));
	}

	public void setVolumeIndicator(TimeSeries series){
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
		return signal;
	}

}
