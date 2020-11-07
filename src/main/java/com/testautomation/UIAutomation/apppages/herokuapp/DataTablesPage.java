package com.testautomation.UIAutomation.apppages.herokuapp;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Value;

import com.testautomation.UIAutomation.annotations.Page;
import com.testautomation.UIAutomation.apppages.BasePage;

@Page
public class DataTablesPage extends BasePage {

	@Value("Data Tables")
	private String text;

	@FindBy(how = How.TAG_NAME, using = "h3")
	private WebElement pageHeading_Text;

	@FindBy(how = How.ID, using = "table1")
	private WebElement table1;

	@FindBy(how = How.CSS, using = "#table1>thead>tr>th>span")
	private List<WebElement> table1Headers_List;

	@FindBy(how = How.CSS, using = "#table1>tbody>tr")
	private List<WebElement> table1Rows_List;

	@Override
	public boolean at() {
		return wait.until(ExpectedConditions.textToBePresentInElement(pageHeading_Text, text));
	}

	private List<String> getAllColumnNames() {
		List<String> headers_List = new ArrayList<>();
		for(WebElement header : table1Headers_List) {
			headers_List.add(header.getText());
		}
		return headers_List;
	}
	
	private Map<Integer, String> assignColumnsBasedOnSequence() {
		Map<Integer, String> columnSequences = new LinkedHashMap<>();
		int i = 1;
		List<String> columnNames = getAllColumnNames();
		for(String columnName : columnNames) {
			columnSequences.put(i++, columnName);
		}
		return columnSequences;
	}
	
	public List<String> getAllValuesFromAColumn(String columnName) {
		Map<Integer, String> columnSequences = assignColumnsBasedOnSequence();
		List<String> columnNames = new ArrayList<>();
		for(Map.Entry<Integer, String> map : columnSequences.entrySet()) {
			int seq = map.getKey();
			String name = map.getValue();
			if(name.equals(columnName)) {
				List<WebElement> elements = driver.findElements(By.cssSelector("#table1>tbody>tr>td:nth-child("+seq+")"));
				for(WebElement element : elements) {
					columnNames.add(element.getText());
				}
			}
		}
		return columnNames;
	}

	public List<String> getPersonDetails(String firstName) {
		Map<String, List<String>> personDetails = fillDetailsBasedOnFirstName();
		List<String> details = new ArrayList<>();
		for(Map.Entry<String, List<String>> map : personDetails.entrySet()) {
			if(firstName.equals(map.getKey())) {
				for(String value  : map.getValue()) {
					details.add(value);
				}
			}
		}
		return details;
	}
	
	private Map<String, List<String>> fillDetailsBasedOnFirstName() {
		Map<String, List<String>> personDetails = new LinkedHashMap<>();
		for(int j=1; j<=table1Rows_List.size(); j++) {
			List<WebElement> elements = driver.findElements(By.cssSelector("#table1>tbody>tr:nth-child("+j+")>td"));
			List<String> elementValues = new ArrayList<>();
			for(WebElement element : elements) {
				String elementText = element.getText();
				if(!elementText.equals("")){
					elementValues.add(elementText);
				}
			}
			personDetails.put(elementValues.get(1), elementValues);
		}
		return personDetails;
	}
	
	public Map<String, String> getPersonMappedDetails(String firstName) {
		Map<String, String> map = new LinkedHashMap<>();
		List<String> details = getPersonDetails(firstName);
		List<String> headers = getAllColumnNames();
		if(headers.size() == details.size()) {
			for(int i=0; i<headers.size(); i++) {
				map.put(headers.get(i), details.get(i));
			}
		}else {
			System.err.println("Columns & Person Details size are not equal");
		}
		return map;
	}

}
