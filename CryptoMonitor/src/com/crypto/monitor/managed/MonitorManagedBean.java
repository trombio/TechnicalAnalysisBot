package com.crypto.monitor.managed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.springframework.stereotype.Component;

import com.crypto.jmes.ta.ExponentialMovingAverageIndicator;
import com.crypto.jmes.ta.TAIndicator;
import com.crypto.monitor.service.MonitorService;
import com.crypto.monitor.signal.Signal;

@Component
@ManagedBean(name="monitorBinance")
@SessionScoped
public class MonitorManagedBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pair;
	private List<Signal> signals;
	private Map<String, TAIndicator> indicators;
	
	private List<String> checkIndicators;
	private List<String> selectedIndicators;
	
	@ManagedProperty(value="#{monitorService}")
	private MonitorService monitorService;
	
	@PostConstruct
	public void init() {
		//Definimos cuales indicadores mostraremos en pantalla
		indicators = new HashMap<String, TAIndicator>();
		indicators.put("EMA 9", new ExponentialMovingAverageIndicator(9));
		indicators.put("EMA 20", new ExponentialMovingAverageIndicator(20));
		indicators.put("EMA 50", new ExponentialMovingAverageIndicator(50));
		indicators.put("EMA 100", new ExponentialMovingAverageIndicator(100));
		indicators.put("EMA 200", new ExponentialMovingAverageIndicator(200));
		
		checkIndicators = new ArrayList<>(indicators.keySet());
		selectedIndicators = new ArrayList<>(indicators.keySet());
	}
	
	public void execute() {
		
	}

	public MonitorService getMonitorService() {
		return monitorService;
	}

	public void setMonitorService(MonitorService monitorService) {
		this.monitorService = monitorService;
	}

	public String getPair() {
		return pair;
	}

	public void setPair(String pair) {
		this.pair = pair;
	}

	public List<Signal> getSignals() {
		return signals;
	}

	public void setSignals(List<Signal> signals) {
		this.signals = signals;
	}

	public List<String> getCheckIndicators() {
		return checkIndicators;
	}

	public void setCheckIndicators(List<String> checkIndicators) {
		this.checkIndicators = checkIndicators;
	}

	public List<String> getSelectedIndicators() {
		return selectedIndicators;
	}

	public void setSelectedIndicators(List<String> selectedIndicators) {
		this.selectedIndicators = selectedIndicators;
	}

}