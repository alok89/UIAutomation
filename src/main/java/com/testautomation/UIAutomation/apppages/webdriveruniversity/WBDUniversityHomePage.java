package com.testautomation.UIAutomation.apppages.webdriveruniversity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.util.concurrent.Uninterruptibles;
import com.testautomation.UIAutomation.annotations.Page;
import com.testautomation.UIAutomation.apppages.BasePage;
import com.testautomation.UIAutomation.utils.SwitchWindowService;

@Page
public class WBDUniversityHomePage extends BasePage {
	
	@Autowired
	private ToDoListPage toDoListPage;
	
	@Autowired
	private IFramePage iFramePage;
	
	@Autowired
	private ContactUsPage contactUsPage;
	
	@Autowired
	private SwitchWindowService switchWindowService;
	
	/*
	 * @Value("${webdriveruniversity.contactUsPage_Title}") private String
	 * contactUsPage_Title;
	 * 
	 * @Value("${webdriveruniversity.toDoListPage_Title}") private String
	 * toDoListPage_Title;
	 * 
	 * @Value("${webdriveruniversity.iFramePage_Title}") private String
	 * iFramePage_Title;
	 */
	
	@FindBy(how = How.CSS, using = ".container .row a[id]")
	private List<WebElement> section_List;
	
	@FindBy(how = How.CSS, using = ".container .row a[id] .section-title>h1")
	private List<WebElement> sectionTitle_List;
	
	@FindBy(how = How.ID, using = "to-do-list")
	private WebElement toDoList_Link;
	
	@FindBy(how = How.ID, using = "iframe")
	private WebElement iFrame_Link;
	
	@FindBy(how = How.ID, using = "contact-us")
	private WebElement contactUs_Link;

	@Override
	public boolean at() {
		return ((wait.until(ExpectedConditions.titleIs(
									WebDriverUniversityPageTitles.HOME_PAGE.getPageTitle()))) && 
														(section_List.size() >= 17));
	}
	
	public List<String> getAllSectionsLabel() {
		List<String> labels = new ArrayList<>();
		sectionTitle_List.stream()
						.forEach(element -> labels.add(element.getText()));
		return labels;
	}
	
	public ToDoListPage goToDoListPage() {
		Uninterruptibles.sleepUninterruptibly(1500, TimeUnit.MILLISECONDS);
		toDoList_Link.click();
		switchWindowService.switchToWindowByTitle(WebDriverUniversityPageTitles.TODOLIST_PAGE.getPageTitle());
		return toDoListPage;
	}
	
	public IFramePage goToIFramesPage() {
		Uninterruptibles.sleepUninterruptibly(1500, TimeUnit.MILLISECONDS);
		iFrame_Link.click();
		switchWindowService.switchToWindowByTitle(WebDriverUniversityPageTitles.IFRAME_PAGE.getPageTitle());
		return iFramePage;
	}
	
	public ContactUsPage goToContactUsPage() {
		contactUs_Link.click();
		Uninterruptibles.sleepUninterruptibly(1500, TimeUnit.MILLISECONDS);
		switchWindowService.switchToWindowByTitle(WebDriverUniversityPageTitles.CONTACTUS_PAGE.getPageTitle());
		return contactUsPage;
	}

}
