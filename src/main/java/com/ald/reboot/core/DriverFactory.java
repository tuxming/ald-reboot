package com.ald.reboot.core;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverFactory {
	private static Logger log = Logger.getLogger(DriverFactory.class);
	public static WebDriver firefoxDriver(){
		
		WebDriver driver = null;
		try {
			driver = new FirefoxDriver();
		} catch (WebDriverException e) {
			log.error(e.getMessage());
			try {
				DesiredCapabilities capability=DesiredCapabilities.firefox();
				//System.setProperty("webdriver.firefox.bin", "D:\\app\\Mozilla Firefox\\firefox.exe"); 
				capability.setCapability("firefox_binary", Config.get("firefox_binary"));
				driver = new FirefoxDriver(capability);
			} catch (Exception e2) {
				log.error(e.getMessage(), e);
			}
		}
		
		return driver;
	}
	
}
