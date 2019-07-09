package com.cybertek.projectVyTrack;

import com.cybertek.OfficeHours.Library;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class CybertekPracticeLogIn {

    private WebDriver driver;

    @BeforeClass()
    public void beforeClass(){

    }

    @BeforeTest
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://practice.cybertekschool.com/login");

        String expectedTitle = "Practice";

        Assert.assertEquals(driver.getTitle(),expectedTitle);

    }

    @Test()
    public void positiveTest(){

        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("tomsmith");
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("SuperSecretPassword");
        driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();

        Library.sleep(3);
        String expectedMessage = "You logged into a secure area!\n×";
        String actualMessage = driver.findElement(By.id("flash-messages")).getText();

        Assert.assertEquals(actualMessage,expectedMessage);

    }
    @Test()
    public void negativeTest(){
        driver.findElement(By.xpath("//input[@type='text']")).sendKeys("aiperiazizova");
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("AiperiAzizova");
        driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
        String expectedMessage = "Your username is invalid!\n×";

        Library.sleep(3);
        String actualMessage = driver.findElement(By.id("flash")).getText();

        Assert.assertEquals(actualMessage,expectedMessage);

    }

    @AfterTest
    public void teardown(){
        driver.close();
    }

}
