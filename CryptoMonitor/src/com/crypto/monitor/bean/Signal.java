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
	private String highestVolume;
	private String averageVolume;
	
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

	public String getHighestVolume() {
		return highestVolume;
	}

	public void setHighestVolume(String highestVolume) {
		this.highestVolume = highestVolume;
	}

	public String getAverageVolume() {
		return averageVolume;
	}

	public void setAverageVolume(String averageVolume) {
		this.averageVolume = averageVolume;
	}

	public List<TrendIndicator> getIndicators() {
		return indicators;
	}

	public void setIndicators(List<TrendIndicator> indicators) {
		this.indicators = indicators;
	}

}
