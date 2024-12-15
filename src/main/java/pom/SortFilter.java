package pom;

import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import utility.Parameterization;

public class SortFilter extends CommonMethods{
	
	@FindBy(xpath = "//select[@id='sortByFilter']") private WebElement sortFilterSelectBox;
	@FindBy(xpath = "//select[@id='sortByFilter']//option")private List<WebElement> sortOptionList;
	@FindBy(xpath = "//span[@class='offer-price']")private List<WebElement> productPriceList;
	@FindBy(xpath = "//div[@class='item_title']") private List <WebElement> productsNameList;
	
	public SortFilter(WebDriver driver)
	{
		PageFactory.initElements(driver, this);	
	}
	
	public void clickOnSortFilter() {
		sortFilterSelectBox.click();
	}
	
	public void getSelectedOptionFromList(int row) throws EncryptedDocumentException, IOException {
		Select option = new Select(sortFilterSelectBox);
		option.selectByVisibleText(Parameterization.getTestDataFromSheet("SortOptionList", row, 0));
	}
	
	public double[] getProductsPriceList() {
		double[] priceList = new double[productPriceList.size()];
			
		for(int i=0;i<productPriceList.size();i++) {
			String[] price= productPriceList.get(i).getText().split(" ");
			priceList[i] = Double.parseDouble(removeCommaFromString(price[0]));
		}
		
		return priceList;
	}
	
	public String[] getProductsNameList() {
		String[] nameList = new String[productsNameList.size()];
		
		for(int i=0;i<productsNameList.size();i++) {
			nameList[i] =productsNameList.get(i).getText();
		}
		
		return nameList;	
	}
}
