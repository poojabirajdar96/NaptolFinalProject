package pojo;

import java.time.Duration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Browser {

	public static WebDriver driver;
	public static WebDriver openBrowser(String browserName)
	{
		if(browserName.equals("Chrome")) {
		   WebDriverManager.chromedriver().setup();
		   driver = new ChromeDriver();
		}
		else if(browserName.equals("Edge")) {
		   WebDriverManager.edgedriver().setup();
		   driver= new EdgeDriver();
		}
		else if(browserName.equals("Firefox")) {
		   WebDriverManager.firefoxdriver().setup();
		   driver= new FirefoxDriver();
		}
		
		driver.navigate().to("https://www.naaptol.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
		return driver;
	
	}
}
