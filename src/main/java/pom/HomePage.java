package pom;

import java.io.IOException;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.MouseActions;
import utility.Parameterization;

public class HomePage extends CommonMethods {

	@FindBy(xpath = "//input[@id='header_search_text']") private WebElement searchInputBox;
	@FindBy(xpath = "(//a[@href='javascript:autoSuggestion.headerSearch()'])[2]")private WebElement searchButton;
	@FindBy(xpath = "//span[@id='resultCountSpan']") private WebElement searchResultCount;
	
	@FindBy(xpath = "//div[@class='cate_head']")private WebElement shoppingCategoriesBtn; 
	@FindBy(xpath = "//div[@id='mainMenuContent']") private WebElement shoppingCategoriesDropdownList;
	
	@FindBy(xpath = "//div[@class='item_title']") private List <WebElement> productsNameList;
	@FindBy(xpath = "//span[@class='offer-price']") private List<WebElement> productsPriceList;
	
	@FindBy(xpath = "//a[@class='bt_compare icon chat quickFancyBox']") private List <WebElement> quickViewBtn;
	
	@FindBy(xpath = "//div[@id='productItem2']") private WebElement homePageProductDetailsDiv;
	
	@FindBy(xpath = "//section[@id='quickViewBox']") private WebElement quickViewProductDetailsPopUp;

	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void enterValidProductName() throws EncryptedDocumentException, IOException
	{
		searchInputBox.sendKeys(Parameterization.getTestDataFromSheet("TestData", 0, 0));		
	}
	
	public void enterInvalidProductName() throws EncryptedDocumentException, IOException
	{
		searchInputBox.sendKeys(Parameterization.getTestDataFromSheet("TestData", 1, 0));	
	}
	
	public void clickOnSearchButton()
	{
		searchButton.click();
	}
	
	public String getSearchResult()
	{
		return searchResultCount.getText();
	}
	
	public void mouseHoverOnShoppingCategories(WebDriver driver)
	{			
		MouseActions.mouseHoverAction(driver,shoppingCategoriesBtn);	
	}
	
	public boolean verifyShoppingcategoriesDropdownList()
	{
		return shoppingCategoriesDropdownList.isDisplayed(); 
	}
	
	public void moveToProduct(WebDriver driver, int index)
	{
		MouseActions.mouseHoverAction(driver,productsNameList.get(index));
	}
	
	public String getProductName(int index)
	{
		String productName = productsNameList.get(index).getText();
		return productName;
	}
	
	public String[] getProductsNameList() {
		String[] nameList = new String[productsNameList.size()];
		for(int i=0;i<productsNameList.size();i++) {
			nameList[i] =productsNameList.get(i).getText();
		}
		
		return nameList;
		
	}
	
	public double getProductPrice(int index)
	{
		String [] a = productsPriceList.get(index).getText().split(" ");
		return Double.parseDouble(removeCommaFromString(a[0]));	
	}
	
	public boolean clickOnQuickView(int index)
	{
		quickViewBtn.get(index).click();
		return quickViewProductDetailsPopUp.isDisplayed();	
	}
	
	public void clickOnProductDiv(WebDriver driver)
	{
		homePageProductDetailsDiv.click();
		launchChildBrowser(driver);	
	}
}
