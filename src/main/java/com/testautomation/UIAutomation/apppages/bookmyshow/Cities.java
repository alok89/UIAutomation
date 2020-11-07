package com.testautomation.UIAutomation.apppages.bookmyshow;

import com.testautomation.UIAutomation.annotations.LazyComponent;

@LazyComponent
public enum Cities {
	
	MUMBAI("mumbai"),
	NCR("ncr"),
	BENGALURU("bang"),
	HYDERABAD("hyd"),
	AHMEDABAD("ahd"),
	CHANDIGARH("chd"),
	CHENNAI("chen"),
	PUNE("pune");
	
	private String name;
	
	private Cities(String name) {
		this.name = name;
	}
	
	public String getCityName() {
		return name;
	}

}
