package com.dice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobSearch {
	public static void main(String[] args) {
		//setup chrome driver path
		WebDriverManager.chromedriver().setup();
		
		//invoke selenium webdriver
		WebDriver driver = new ChromeDriver();
		//full screen
		driver.manage().window().fullscreen();
		//set universal wait time incase web page is slow
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		String url = "https://www.dice.com/";
		driver.get(url);
		
		String actualTitle = driver.getTitle();
		String expectedTitle = "job search for technology professionals| Dice.com";
		
		if (actualTitle.equals(expectedTitle)) {
			System.out.println("step pass. Dice home page successfully loaded");
		}else {
			System.out.println("step fail. Dice home page did not load successfully");
		}
		
		String keyword ="java developer";
		driver.findElement(By.id("search-field-keyword")).clear();
		driver.findElement(By.id("search-field-keyword")).sendKeys(keyword);
		
		String location = "60601";
		driver.findElement(By.id("search-field-location")).clear();
		driver.findElement(By.id("search-field-location")).sendKeys(location);
		
		driver.findElement(By.id("findTechJobs")).click();
		
		String count = driver.findElement(By.id("posiCountId")).getText();
		System.out.println(count);
		//ensure count is more than 0
		int countResult = Integer.parseInt(count.replace(",", ""));
		if (countResult > 0) {
			System.out.println("step PASS: keyword:  "+keyword+"  search return  "+countResult+"  result in  "+ location);
		}else {
			System.out.println("step FAIL: keyword:  "+keyword+"  search return  "+countResult+"  result in  "+ location);
		}
		driver.close();
		
	}
}
