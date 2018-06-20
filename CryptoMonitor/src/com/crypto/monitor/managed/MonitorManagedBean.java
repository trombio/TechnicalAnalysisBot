package com.crypto.monitor.managed;

import java.util.List;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.springframework.stereotype.Component;

import com.crypto.monitor.bean.Signal;
import com.crypto.monitor.service.MonitorService;

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
	
	@ManagedProperty(value="#{monitorService}")
	private MonitorService monitorService;
	
	public void execute() {
		signals = monitorService.getCryptoSignals(pair);
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
	
	
	
}