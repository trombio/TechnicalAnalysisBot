package com.crypto.monitor.managed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.springframework.stereotype.Component;

import com.crypto.jmes.bean.TrendIndicator;
import com.crypto.jmes.bean.VolumeAnalysis;
import com.crypto.jmes.ta.ExponentialMovingAverageIndicator;
import com.crypto.jmes.ta.TAIndicator;
import com.crypto.jmes.util.Interval;
import com.crypto.monitor.service.MonitorService;
import com.crypto.monitor.signal.Signal;
import com.crypto.monitor.util.MonitorConstants;
import com.crypto.monitor.util.SignalUtils;

@Component
@ManagedBean(name="monitorBinance")
@SessionScoped
public class MonitorManagedBean implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String pair;
	private String interval;
	private List<Signal> signals;

	private List<String> checkIndicators;
	private List<String> selectedIndicators;
	private List<String> intervals;

	@ManagedProperty(value="#{binanceMonitorService}")
	private MonitorService monitorService;

	@PostConstruct
	public void init() {
		//Se hace para tener seleccionados todos por default
		checkIndicators = new ArrayList<>(MonitorConstants.DEFAULT_INDICATORS.keySet());
		selectedIndicators = new ArrayList<>(MonitorConstants.DEFAULT_INDICATORS.keySet());
		intervals = new ArrayList<>(MonitorConstants.DEFAULT_INTERVALS.keySet());
		
		/*Signal s = new Signal();
		s.setSymbol("BTCUSDT");
		VolumeAnalysis volumeAnalysis = new VolumeAnalysis();
		volumeAnalysis.setAvgVolume(50000);
		volumeAnalysis.setHighestVolume(60000);
		volumeAnalysis.setLastVolume(55000);
		s.setVolumeAnalysis(volumeAnalysis);
		
		TrendIndicator i = new TrendIndicator();
		i.setBearish(true);
		i.setName("EMA 20");
		i.setValue(new BigDecimal(6200.34));
		s.addTrendIndicator(i);
		
		TrendIndicator i2 = new TrendIndicator();
		i2.setBullish(true);
		i2.setName("EMA 50");
		i2.setValue(new BigDecimal(6500.34));
		s.addTrendIndicator(i2);
		
		signals = new ArrayList<>();
		signals.add(s);*/
	}

	public void execute() {
		List<TAIndicator> indicators = new ArrayList<>();
		for(String s : selectedIndicators){
			indicators.add(MonitorConstants.DEFAULT_INDICATORS.get(s));
		}
		TAIndicator[] array = new TAIndicator[selectedIndicators.size()];
		array = indicators.toArray(array);
		Interval i = MonitorConstants.DEFAULT_INTERVALS.get(interval);
		
		signals = monitorService.getCryptoSignals(pair, i, array);
		SignalUtils.orderByAverageVolume(signals);

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

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public List<String> getIntervals() {
		return intervals;
	}

	public void setIntervals(List<String> intervals) {
		this.intervals = intervals;
	}
	
}
