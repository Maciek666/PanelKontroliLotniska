package com.example.demo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class AirplaneSeleniumTest {
    private WebDriver wd;
    private String url;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\JavaJars\\chromedriver_win32_74\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--whitelist-ip *");
        chromeOptions.addArguments("--proxy-server='direct://'");
        chromeOptions.addArguments("--proxy-bypass-list=*");
        wd = new ChromeDriver();
        url = "http://localhost:8080/";
    }

    @Test
    public void addAirplaneAndDelete() throws InterruptedException {
        wd.get(url);
        Thread.sleep(1000);
        wd.findElement(By.partialLinkText("Panel kontroli samolotów")).click();
        Thread.sleep(1000);
        wd.findElement(By.xpath("//button[@form='update']")).click();
        Thread.sleep(1000);
        WebElement model = wd.findElement(By.xpath("//input[@name='plane']"));
        WebElement vin = wd.findElement(By.xpath("//input[@name='series']"));

        model.clear();
        model.sendKeys("testowy");
        vin.clear();
        vin.sendKeys("vintestowy123456");
        Thread.sleep(3000);
        wd.findElement(By.xpath("//button[@id='dodaj']")).click();
        Thread.sleep(3000);
        Assert.assertTrue(wd.getPageSource().contains("testowy") &&
                wd.getPageSource().contains("vintestowy123"));
        wd.findElement(By.xpath("//button[@form='vintestowy123456']")).click();
        Thread.sleep(5000);
        Assert.assertFalse(wd.getPageSource().contains("testowy") &&
                wd.getPageSource().contains("vintestowy123"));
    }

    @After
    public void quit() {
        wd.quit();
    }
}
