package com.crypto.monitor.signal;

import java.util.Comparator;

import com.crypto.jmes.bean.VolumeAnalysis;

public class AverageVolumeComparator implements Comparator<Signal>{

	@Override
	public int compare(Signal s0, Signal s1) {
		VolumeAnalysis v0 = s0.getVolumeAnalysis();
		VolumeAnalysis v1 = s1.getVolumeAnalysis();
		if(v0 != null) {
			if(v1 != null) {
				if(v0.getAvgVolume() > v1.getAvgVolume())
					return 1;
				else if(v0.getAvgVolume() < v1.getAvgVolume())
					return -1;
				else 
					return 0;
			} else {
				return 1;
			}
		}
		return 0;
	}

}
