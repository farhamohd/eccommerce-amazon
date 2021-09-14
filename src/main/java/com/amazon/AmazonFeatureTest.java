package com.amazon;


import org.junit.Assert;
import org.junit.runner.OrderWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ISelect;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AmazonFeatureTest {
    ChromeDriver driver;

    @BeforeClass
    public void invokeBrowser()
    {
        System.setProperty("webdriver.chrome.driver","/Users/mohaseenbagwan/Downloads/chromedriver");
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        driver.get("https://amazon.com");

    }

    @AfterClass
    public void closeBrowser()
    {
        //driver.quit();
    }

    @Test(priority = 0)
    public void verifyTitleOfThePage()
    {
        String expectedTitle="Amazon.com. Spend less. Smile more.";
        String actualTitle;
        actualTitle=driver.getTitle();
        Assert.assertEquals(actualTitle,expectedTitle);
    }
    @Test(priority = 100)
    public void searchProduct()
    {
        String productItem="Apple Watch";
        String category="Electronics";
        WebElement selDropdown=driver.findElement(By.id("searchDropdownBox"));
        Select selCategory=new Select(selDropdown);
        selCategory.selectByVisibleText(category);
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys(productItem);
        driver.findElement(By.xpath("//input[@value='Go']")).click();


    }
    @Test(priority = 200)
    public void getNthProduct()
    {
        int productItemNumber=4;
        String xpathExpression=String.format("//div[@data-component-type='s-search-result'][%d]",productItemNumber);
        WebElement nthProduct=driver.findElement(By.xpath(xpathExpression));
        String nthProductResult=nthProduct.getText();
        System.out.println(nthProductResult);
    }

}
