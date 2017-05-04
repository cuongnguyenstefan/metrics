package com.flexreceipts.metrics.util;

import java.util.Comparator;

/**
 * Comparator for Float, is used to reverse the comparasion
 * result is to reverse the sorting order and create a
 * max heap
 * 
 * @author Stefan
 *
 */
public class FLoatReverseComparator implements Comparator<Float> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Float o1, Float o2) {
		return (int) ((int) o2.floatValue()-o1.floatValue());
	}

}
