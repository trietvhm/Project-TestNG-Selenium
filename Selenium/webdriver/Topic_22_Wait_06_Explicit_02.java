package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_22_Wait_06_Explicit_02 {
    WebDriver driver;
    WebDriverWait explicitWait;

    // khoi tao trinh duyet san
    @BeforeClass
    //Selenium version  2.x/3.x/4.x/(4.6 tro xuong) bat buoc xai ham beforeclass. Hientai khong co van chay duoc
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Equal_5_Second() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.get(("https://automationfc.github.io/dynamic-loading/"));
        driver.findElement(By.cssSelector("div#start>button")).click();

        // Wait cho LOADING ICON bien mat trong vong X giây
        //explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));

        // WAIT cho HELLO world text xuất hiện
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }

    @Test
    public void TC_02_Less_Than_5_Second() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(3));

        driver.get(("https://automationfc.github.io/dynamic-loading/"));
        driver.findElement(By.cssSelector("div#start>button")).click();

        // Wait cho LOADING ICON bien mat trong vong X giây
        //explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));

        // WAIT cho HELLO world text xuất hiện
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }

    @Test
    public void TC_03_More_Than_5_Second() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get(("https://automationfc.github.io/dynamic-loading/"));
        driver.findElement(By.cssSelector("div#start>button")).click();

        // Wait cho LOADING ICON bien mat trong vong X giây
        //explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
