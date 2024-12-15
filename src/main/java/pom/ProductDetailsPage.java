package pom;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage extends CommonMethods{

	@FindBy(xpath = "//div[@id='square_Details']//h1") private WebElement productName;
	@FindBy(xpath = "//span[@class='offer-price']") private List<WebElement> productsPriceList;
	
	@FindBy(xpath = "//ul[@class='sizeBox clearfix']//li") private List<WebElement> productColorList;
	
	@FindBy(xpath = "//a[@id='cart-panel-button-0']") private WebElement buyButton;
	
	public ProductDetailsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);	
	}
	
	public String getProductName()
	{
		return productName.getText();
	}
		
	public double getProductPrice(int index)
	{
		String [] a = productsPriceList.get(index).getText().split(" ");
		return Double.parseDouble(removeCommaFromString(a[0]));	
	}
	
	public void selectProductColor(int index)
	{				
		productColorList.get(index).click();	
	}
	
	public int getProductColorList()
	{
		return productColorList.size();
	}
			
	public void clickOnClickHereToBuyButton()
	{
		buyButton.click();	
	}
}
