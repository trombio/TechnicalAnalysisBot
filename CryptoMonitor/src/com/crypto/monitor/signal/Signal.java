package com.crypto.monitor.signal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.crypto.jmes.bean.TrendIndicator;
import com.crypto.jmes.bean.VolumeAnalysis;

/**
 * Esta clase contiene el resumen del analisis de todos los indicadores
 * @author Jean Michel
 *
 */
public class Signal {
	
	private String symbol;
	
	private VolumeAnalysis volumeAnalysis;
	private List<TrendIndicator> indicators;
	
	public Signal(){
		indicators = new ArrayList<TrendIndicator>();
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public List<TrendIndicator> getTrendIndicators() {
		return indicators;
	}

	public void setIndicators(List<TrendIndicator> indicators) {
		this.indicators = indicators;
	}
	
	public void addTrendIndicator(TrendIndicator trend) {
		indicators.add(trend);	
	}

	public VolumeAnalysis getVolumeAnalysis() {
		return volumeAnalysis;
	}

	public void setVolumeAnalysis(VolumeAnalysis volumeAnalysis) {
		this.volumeAnalysis = volumeAnalysis;
	}
	
	public List<TrendIndicator> getBullishIndicatorsList(){
		//List<TrendIndicator> b = indicators.stream().filter(i -> i.isBullish() == true).collect(Collectors.toList());
		List<TrendIndicator> b = new ArrayList<>();
		for(TrendIndicator t : indicators) {
			if(t.isBullish())
				b.add(t);
		}
		return b;
	}
	
	public List<TrendIndicator> getBearishIndicatorsList(){
		//List<TrendIndicator> b = indicators.stream().filter(i -> i.isBearish() == true).collect(Collectors.toList()); 
		List<TrendIndicator> b = new ArrayList<>();
		for(TrendIndicator t : indicators) {
			if(t.isBearish())
				b.add(t);
		}
		return b;
	} 

	@Override
	public String toString() {
		return "Signal [symbol=" + symbol + ", volumeAnalysis=" + volumeAnalysis + ", indicators=" + indicators + "]";
	}
	
}
