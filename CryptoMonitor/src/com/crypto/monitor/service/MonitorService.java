package com.crypto.monitor.service;

import java.util.List;

import com.crypto.jmes.util.Interval;
import com.crypto.monitor.bean.Signal;

public interface MonitorService {
	
	public List<Signal> getCryptoSignals(String Pair, Interval interval);
}
