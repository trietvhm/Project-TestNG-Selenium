package javaTester;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_00_Template {
    WebDriver driver;
    JavascriptExecutor js;

    Actions actions;

    // khoi tao trinh duyet san
    @BeforeClass
    //Selenium version  2.x/3.x/4.x/(4.6 tro xuong) bat buoc xai ham beforeclass. Hientai khong co van chay duoc
    public void beforeClass() {
        driver = new FirefoxDriver();
        js = (JavascriptExecutor) driver;
        actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_CHECK() {
        driver.get("https://omayo.blogspot.com/");
        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.cssSelector("input#confirm")));
        //actions.click(driver.findElement(By.cssSelector("input#hbutton"))).perform();
        // actions.click(driver.findElement(By.cssSelector("input#confirm"))).perform();

        //js.executeScript("arguments[0].click();",driver.findElement(By.cssSelector("input#hbutton")));
        //driver.findElement(By.cssSelector("input#hbutton")).click();

    }

    @Test
    public void TC_02_() {

    }

    @AfterClass
    public void afterClass() {
        // driver.quit();
    }

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
