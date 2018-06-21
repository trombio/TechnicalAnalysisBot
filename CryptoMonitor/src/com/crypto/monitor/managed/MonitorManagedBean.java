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
import com.crypto.jmes.util.Interval;
import com.crypto.monitor.service.MonitorService;
import com.crypto.monitor.signal.Signal;
import com.crypto.monitor.util.MonitorConstants;

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

	private List<String> checkIndicators;
	private List<String> selectedIndicators;

	@ManagedProperty(value="#{monitorService}")
	private MonitorService monitorService;

	@PostConstruct
	public void init() {
		//Se hace para tener seleccionados todos por default
		checkIndicators = new ArrayList<>(MonitorConstants.DEFAULT_INDICATORS.keySet());
		selectedIndicators = new ArrayList<>(MonitorConstants.DEFAULT_INDICATORS.keySet());
	}

	public void execute() {
		List<TAIndicator> indicators = new ArrayList<>();
		for(String s : selectedIndicators){
			indicators.add(MonitorConstants.DEFAULT_INDICATORS.get(s));
		}
		TAIndicator[] array = new TAIndicator[selectedIndicators.size()];
		array = indicators.toArray(array);

		signals = monitorService.getCryptoSignals(pair, Interval.FOUR_HOUR,
				array);

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
