package testNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.time.Duration;

public class Topic_12_Parallel_method {
    WebDriver driver;
    @BeforeMethod
    public void beforeMethod() {
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
    @Test
    public void TC_03_() {
        System.out.println("Hello Tung!");

    }

    @Test
    public void TC_04_() {
        System.out.println("Hello Phi!");

    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

}
