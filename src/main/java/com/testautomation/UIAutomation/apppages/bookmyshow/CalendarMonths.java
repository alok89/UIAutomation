package com.testautomation.UIAutomation.apppages.bookmyshow;

import com.testautomation.UIAutomation.annotations.LazyComponent;

@LazyComponent
public enum CalendarMonths {

	JAN (1),
	FEB (2),
	MAR (3),
	APR (4),
	MAY (5),
	JUN (6),
	JUL (7),
	AUG (8),
	SEP (9),
	OCT (10),
	NOV (11),
	DEC (12);
	
	private int monthNo;
	
	private CalendarMonths(int monthNo) {
		this.monthNo = monthNo;
	}
	
	public int getMonthNo() {
		return monthNo;
	}
}
