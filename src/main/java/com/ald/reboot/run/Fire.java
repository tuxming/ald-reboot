package com.ald.reboot.run;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ald.reboot.core.Config;
import com.ald.reboot.core.DriverFactory;


public class Fire {
	private static Logger log = Logger.getLogger(Fire.class);
	
	public static void main(String[] args) throws Exception {
		
		//System.setProperty("webdriver.chrome.driver","C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
		//WebDriver driver = new ChromeDriver();//DriverFactory.firefoxDriver();
		WebDriver driver = DriverFactory.firefoxDriver();
		
		
		if(driver==null)
			throw new Exception("driver init failure");
		
		driver.manage().timeouts().implicitlyWait(9999, TimeUnit.SECONDS); 
		
		driver.get("https://www.upwork.com/ab/account-security/login");
		
		WebElement loginName = driver.findElement(By.id("login_username"));
		WebElement loginPwd = driver.findElement(By.id("login_password"));
		WebElement submit = driver.findElement(By.xpath("//button[@type='submit']"));
		
		//loginName.sendKeys("554512169@qq.com");
		//loginPwd.sendKeys("ftd314159");
		Map<String, String> loginInfo = Config.getLoginData().get(0);
		for (String loginNameStr : loginInfo.keySet()) {
			loginName.sendKeys(loginNameStr);
			loginPwd.sendKeys(loginInfo.get(loginNameStr));
		}
		
		driver.get("https://www.upwork.com/tests");
		
		List<WebElement> tags = driver.findElements(By.xpath("//*[@id='skilltestslist']//td[1]/a"));
		for (WebElement a : tags) {
			String href = a.getAttribute("href");
			
			//save url?
			log.debug(href);
		}
		
		submit.click();
	}
}
