package com.testautomation.UIAutomation.apppages.myntra;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Autowired;

import com.testautomation.UIAutomation.annotations.Page;
import com.testautomation.UIAutomation.apppages.BasePage;

@Page
public class MenSneakersPage extends BasePage {

	@Autowired
	private FilterOptions filterOptions;
	
	@FindBy(how = How.CSS, using = "ul.filter-summary-filterList>li>div.filter-summary-filter")
	private List<WebElement> appliedFilters_List;

	@Override
	public boolean at() {
		return wait.until(
				ExpectedConditions.titleIs(
						MyntraPageTitles.MEN_SNEAKERS_SHOES.getTitleName()));
	}
	
	public void selectDifferentBrands(String...brandNames) {
		for(String brandName : brandNames) {
			filterOptions.selectBrand(brandName);
		}
	}
	
	public void selectPriceRange(String priceRange) {
		filterOptions.selectPrice(priceRange);
	}
	
	public boolean areAllFiltersApplied(String...filterNames) {
		boolean areSelected = false;
		if(!appliedFilters_List.isEmpty()) {
			if(appliedFilters_List.size() == filterNames.length) {
				List<String> filters = Arrays.asList(filterNames);
				for(WebElement filter : appliedFilters_List) {
					System.out.println("Filter : "+filter.getText());
					if(filters.contains(filter.getText())) {
						areSelected = true;
					}else {
						areSelected = false;
						break;
					}
				}
			}
		}
		return areSelected;
	}

}
