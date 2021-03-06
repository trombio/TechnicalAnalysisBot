package com.crypto.monitor.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ta4j.core.Bar;
import org.ta4j.core.Rule;

import com.crypto.jmes.bean.TrendIndicator;

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
	
	public void addIndicator(TrendIndicator indicator){
		indicators.add(indicator);
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

}
