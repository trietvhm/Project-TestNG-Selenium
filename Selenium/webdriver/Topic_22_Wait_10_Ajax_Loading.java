package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_22_Wait_10_Ajax_Loading {
    WebDriver driver;

    WebDriverWait explicitWait;

    // khoi tao trinh duyet san
    @BeforeClass
    //Selenium version  2.x/3.x/4.x/(4.6 tro xuong) bat buoc xai ham beforeclass. Hientai khong co van chay duoc
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(30));

    }

    @Test
    public void TC_01_AJAXLOADING() {
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText(),
                "No Selected Dates to display.");

        driver.findElement(By.xpath("//a[text()='5']")).click();

        // Wait cho LOADING ICON bien mat trong vong X giây
        /* explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//div[contains(@id,'RadCalendar1')]/div[@class='raDiv']")));*/

        // Wait cho page được ready
        // Test Manual trong quá trình load vẫn true
        Assert.assertTrue(isPageLoadedSuccess());

        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText(),
                "Tuesday, March 5, 2024");

    }

    @Test
    public void TC_02_() {

    }

    public boolean isPageLoadedSuccess() {
        WebDriverWait explicitWait = new WebDriverWait(driver,Duration.ofSeconds(10));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

        // Dieu kien 1
        // hoan toan co the khoi tao ExpectedCondition, no giong mot annonymous function
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
            }
        };

        // Dieu kien 2
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };
        // ket hop 2 dk với nhau, 2 thg này trả về kiểu boolean
        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    @AfterClass
    public void afterClass() {
        //driver.quit();
    }


}
