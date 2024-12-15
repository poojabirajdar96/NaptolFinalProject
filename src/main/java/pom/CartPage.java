package pom;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends CommonMethods {

	@FindBy(xpath = "//ul[@id='cartData']//h2")private WebElement productName;
	@FindBy(xpath = "//span[@class='font-bold-imp']")private WebElement productCount;
	@FindBy(xpath = "(//ul[@id='cartData'])//li//div//h2//a")private List<WebElement> cartProductsList;
	@FindBy(xpath = "//p[@class='chintu']//a")private List<WebElement> removeProductBtn;
	@FindBy(xpath = "//li[@class='head_qty']//input")private List<WebElement> productQuantity;
	@FindBy(xpath = "//ul[@id='cartData']//li[@class='head_UPrice']")private List<WebElement> productUnitPrice;
	@FindBy(xpath = "//ul[@id='cartData']//li[@class='head_ship']")private List<WebElement> productShippingCharge;
	@FindBy(xpath = "//ul[@id='cartData']//li[@class='head_Amount']")private List<WebElement> productTotalPrice;
	@FindBy(xpath = "//span[@id='cvDiscount']")private WebElement giftVoucherDiscount;
	@FindBy(xpath = "//span[@id='totalPayableAmount']")private WebElement totalPayableCartAmount;

	@FindBy(xpath = "//div[@id='ShoppingCartBox']//button")private WebElement closeBtn;
	
	WebDriver driver;
	
	public CartPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	public String getCartProductname()
	{
		return productName.getText();
	}
	
	
	public int getCartProductCount() 
	{
		String[] count = productCount.getText().split(" ");
		return Integer.parseInt(removeBracesFromString(count[0]));
	}
	
	public int getCartProductList()
	{	
		return cartProductsList.size();
	}
	
	public String[] getCartProductNamesList()
	{
		String[] productName=new String[cartProductsList.size()];
		for(int i=0;i<cartProductsList.size();i++) {
			productName[i] = cartProductsList.get(i).getText();
		}
		return productName;
	}
	
	public String clickOnRemoveBtn(int index)	
	{			
		 //CartProductsList=driver.findElements(By.xpath("(//ul[@id='cartData'])//li//div//h2//a"));
		 String removedProductName = cartProductsList.get(index).getText();
		 removeProductBtn.get(index).click();	
		 return removedProductName;
	}
	
	public void clickOnCloseBtn() {
		closeBtn.click();
	}
	
	public void enterQuantity(int index) throws InterruptedException {
		Thread.sleep(3000);
		productQuantity.get(index).sendKeys("2");
		Actions act = new Actions(driver);
		act.sendKeys(Keys.ARROW_RIGHT);
		act.sendKeys(Keys.BACK_SPACE);
		act.sendKeys(Keys.NUMPAD2);
		act.build().perform();	
	 }
	
	public int getProductQuantity(int index) {
		return Integer.parseInt(productQuantity.get(index).getAttribute("value"));
	}
	
	
	public double getProductUnitPrice(int index) {
		
		String unitPriceText=productUnitPrice.get(index).getText();
		String unitPriceSpan=productUnitPrice.get(index).findElement(By.xpath(".//span")).getText();
		unitPriceText=unitPriceText.replace(unitPriceSpan, "");
		
		String [] unitPrice =  unitPriceText.split(" ");
		return Double.parseDouble(removeCommaFromString(unitPrice[0]));
	}
	
	public double getProductShippingCharge(int index) {

		String shippingPriceText=productShippingCharge.get(index).getText();
		String shippingPriceSpan=productShippingCharge.get(index).findElement(By.xpath(".//span")).getText();
		shippingPriceText=shippingPriceText.replace(shippingPriceSpan, "");
		
		String [] shippingPrice =  shippingPriceText.split(" ");
		return Double.parseDouble(removeCommaFromString(shippingPrice[0]));
	}
	
	public double getProductTotalAmount(int index) {

		String totalAmountText=productTotalPrice.get(index).getText();
		String [] productTotalAmount= totalAmountText.split(" ");
		return Double.parseDouble(removeCommaFromString(productTotalAmount[0]));
	}
	
	public double[] getAllProductTotalPriceList() {
		double[] allProductTotalAmount = new double[productTotalPrice.size()];

		for(int i=0;i<productTotalPrice.size();i++) {
			String totalAmountText=productTotalPrice.get(i).getText();
			String [] productTotalAmount= totalAmountText.split(" ");
			allProductTotalAmount[i] = Double.parseDouble(removeCommaFromString(productTotalAmount[0]));
		}
		return allProductTotalAmount;
	}
	
	public double getGiftVoucherDiscount() {
		String giftVoucherDiscountText=giftVoucherDiscount.getText();
		String [] giftVoucherDiscount= giftVoucherDiscountText.split(" ");
		return Double.parseDouble(removeCommaFromString(giftVoucherDiscount[0]));
	}
	
	public double getTotalPayableCartAmount() {
		String totalPayableCartAmountText=totalPayableCartAmount.getText();
		String [] totalPayableCartAmount= totalPayableCartAmountText.split(" ");
		return Double.parseDouble(removeCommaFromString(totalPayableCartAmount[0]));
	}
}
