package testNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_04_alwaysRun {
    WebDriver driver;

    // khoi tao trinh duyet san
    @BeforeClass(alwaysRun = true)
    //Selenium version  2.x/3.x/4.x/(4.6 tro xuong) bat buoc xai ham beforeclass. Hientai khong co van chay duoc
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();

        // no bi fail o BeforeClass thi cac test case/after se bi skip
        Assert.assertTrue(false);


    }

    @Test
    public void TC_01_() {
        System.out.println("Hello world!");

    }

    @Test
    public void TC_02_() {
        System.out.println("Hello sis!");

    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }

}
