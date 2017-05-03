package com.flexreceipts.metrics;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import com.flexreceipts.metrics.util.FLoatReverseComparator;

public class Test {

	List<Float> floats = new ArrayList<Float>();

	PriorityQueue<Float> minHeap = new PriorityQueue<Float>();
	PriorityQueue<Float> maxHeap = new PriorityQueue<Float>(new FLoatReverseComparator());
	int balance = 0;

	float currentMedian = 0;

	float avr = 0;

	public static void main(String[] args) {
		Test t = new Test();
		System.out.println(t.addNumber(1));
		System.out.println(t.addNumber(2));
		System.out.println(t.addNumber(2));
		System.out.println(t.addNumber(2));
		System.out.println(t.addNumber(2));
		System.out.println(t.addNumber(2));
		System.out.println(t.addNumber(8));
		System.out.println(t.addNumber(9));
	}

	String addNumber(float a) {
		System.out.print("adding " + a + ": ");
		add(a);
		avr = avr + (a - avr) / floats.size();
		return avr + " " + currentMedian;
	}

	void add(float newval) {
		floats.add(newval);
		switch (balance) {
		case 0: // the two heaps have the same number of elements
			if (newval < currentMedian) { // "<=" is just as good as "<"
				maxHeap.add(newval);
				currentMedian = maxHeap.peek();
				balance = -1;
			} else {
				minHeap.add(newval);
				currentMedian = minHeap.peek();
				balance = +1;
			}
			return;
		case +1: // BigElements has an extra element
			if (newval <= currentMedian) { // "<=" is better than "<"
				// this Insert brings us back into balance
				maxHeap.add(newval);
			} else {
				minHeap.add(newval);
				// BigElements now has two extra elements; must rebalance
				maxHeap.add(minHeap.poll());
			}
			balance = 0; // we’re always balanced at this point
			return; // note that CurrentMedian doesn’t change
		case -1: // SmallElements has an extra element
			if (newval >= currentMedian) { // "<=" is better than "<"
				// this Insert brings us back into balance
				minHeap.add(newval);
			} else {
				maxHeap.add(newval);
				// BigElements now has two extra elements; must rebalance
				minHeap.add(maxHeap.poll());
			}
			balance = 0; // we’re always balanced at this point
			return;
		}
	}
}
