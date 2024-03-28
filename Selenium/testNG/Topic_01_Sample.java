package testNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_01_Sample {
    WebDriver driver;
    @BeforeClass

    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_() {
        System.out.println("Hello world!");

    }

    @Test
    public void TC_02_() {
        System.out.println("Hello sis!");

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
