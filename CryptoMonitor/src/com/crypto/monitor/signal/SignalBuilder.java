package com.crypto.monitor.signal;

import org.ta4j.core.Indicator;
import com.crypto.jmes.ta.TAIndicator;

public class SignalBuilder {

	private Signal signal;

	public SignalBuilder(String simbol) {
		signal = new Signal();
		signal.setSymbol(simbol);
	}

	public void addTrendIndicator(TAIndicator ta, Indicator dataOrigin){
		signal.addIndicator(ta.analyzeTrend(dataOrigin));
	}


	public Signal build(){
		return signal;
	}

}
