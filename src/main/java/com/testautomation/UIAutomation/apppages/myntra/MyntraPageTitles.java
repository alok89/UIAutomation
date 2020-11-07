package com.testautomation.UIAutomation.apppages.myntra;

import org.springframework.context.annotation.Lazy;

@Lazy
public enum MyntraPageTitles {

	LANDING_PAGE("Online Shopping for Women, Men, Kids Fashion & Lifestyle - Myntra"),
	LOGIN_PAGE("Myntra"),
	MEN_SNEAKERS_SHOES("Men Sneaker Shoes - Buy Fancy Sneakers Online for Men in India | Myntra");
	
	private String titleName;
	
	private MyntraPageTitles(String titleName) {
		this.titleName = titleName;
	}
	
	public String getTitleName() {
		return titleName;
	}
}
