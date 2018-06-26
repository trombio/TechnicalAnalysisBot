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
	private List<TrendIndicator> bullishIndicators;
	private List<TrendIndicator> bearishIndicators;
	
	public Signal(){
		bullishIndicators = new ArrayList<TrendIndicator>();
		bearishIndicators = new ArrayList<TrendIndicator>();
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public List<TrendIndicator> getTrendIndicators() {
		List<TrendIndicator> indicators = new ArrayList<TrendIndicator>();
		indicators.addAll(bearishIndicators);
		indicators.addAll(bullishIndicators);
		return indicators;
	}

	public void setIndicators(List<TrendIndicator> indicators) {
		for(TrendIndicator t : indicators) {
			addTrendIndicator(t);
		}
	}
	
	public void addTrendIndicator(TrendIndicator trend) {
		if(trend.isBearish()) {
			bearishIndicators.add(trend);
		}
		if(trend.isBullish()) {
			bullishIndicators.add(trend);
		}
	}

	public VolumeAnalysis getVolumeAnalysis() {
		return volumeAnalysis;
	}

	public void setVolumeAnalysis(VolumeAnalysis volumeAnalysis) {
		this.volumeAnalysis = volumeAnalysis;
	}
	
	public List<TrendIndicator> getBullishIndicators() {
		return bullishIndicators;
	}

	public List<TrendIndicator> getBearishIndicators() {
		return bearishIndicators;
	}

	@Override
	public String toString() {
		return "Signal [symbol=" + symbol + ", volumeAnalysis=" + volumeAnalysis + ", bullishIndicators="
				+ bullishIndicators + ", bearishIndicators=" + bearishIndicators + "]";
	}
}
