package com.crypto.monitor.service;

import java.util.List;

import com.crypto.jmes.ta.TAIndicator;
import com.crypto.jmes.util.Interval;
import com.crypto.monitor.signal.Signal;

public interface MonitorService {
	
	public List<Signal> getCryptoSignals(String Pair, Interval interval, TAIndicator... indicators);
}
