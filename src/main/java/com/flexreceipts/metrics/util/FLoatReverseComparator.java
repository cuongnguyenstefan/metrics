package com.flexreceipts.metrics.util;

import java.util.Comparator;

public class FLoatReverseComparator implements Comparator<Float> {

	@Override
	public int compare(Float o1, Float o2) {
		return (int) ((int) o2.floatValue()-o1.floatValue());
	}

}
