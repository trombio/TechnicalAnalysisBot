package com.crypto.monitor.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseTimeSeries;
import org.ta4j.core.TimeSeries;

import com.crypto.jmes.service.TechnicalAnalysisService;
import com.crypto.jmes.service.impl.BinanceService;
import com.crypto.jmes.ta.TAIndicator;
import com.crypto.jmes.util.Interval;
import com.crypto.monitor.service.MonitorService;
import com.crypto.monitor.signal.Signal;
import com.crypto.monitor.signal.SignalBuilder;

@Service("binanceMonitorService")
public class BinanceMonitorServiceImpl implements MonitorService{

	private BinanceService binanceService;
	private TechnicalAnalysisService taService;

	public BinanceMonitorServiceImpl() {
		binanceService = new BinanceService();
	}

	@Override
	public List<Signal> getCryptoSignals(String Pair, Interval interval, TAIndicator... indicators) {
		List<Signal> signals = new ArrayList<>();

		List<String> symbols = binanceService.getAllAvailableSymbols();
		for(String symbol : symbols) {
			List<Bar> bars = binanceService.getCandles(symbol, interval);
			TimeSeries series = new BaseTimeSeries(bars);
			SignalBuilder builder = new SignalBuilder(symbol);

			//Agregamos los trendIndicators
			for(TAIndicator tai : indicators){
				builder.addTrendIndicator(tai, series);
			}
			//Agregamos el analisis de volumen
			builder.setVolumeIndicator(series);

			signals.add(builder.build());
		}

		return signals;
	}


}
