package com.crypto.monitor.util;

import java.util.Collections;
import java.util.List;

import com.crypto.monitor.signal.AverageVolumeComparator;
import com.crypto.monitor.signal.Signal;

public class SignalUtils {
	
	public static void orderByAverageVolume(List<Signal> signals) {
		Collections.sort(signals, new AverageVolumeComparator());
	}

}
