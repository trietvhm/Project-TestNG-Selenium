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

public class Topic_22_Wait_07_Explicit_03 {
    WebDriver driver;

    WebDriverWait explicitWait;

    // khoi tao trinh duyet san
    @BeforeClass
    //Selenium version  2.x/3.x/4.x/(4.6 tro xuong) bat buoc xai ham beforeclass. Hientai khong co van chay duoc
    public void beforeClass() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_AJAXLOADING() {
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText(),
                "No Selected Dates to display.");

        driver.findElement(By.xpath("//a[text()='17']")).click();

        // Wait cho LOADING ICON bien mat trong vong X giây
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//div[contains(@id,'RadCalendar1')]/div[@class='raDiv']")));

        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText(),
                "Saturday, February 17, 2024");

    }

    @Test
    public void TC_02_GoFiles() {
        driver.get("https://gofile.io/welcome");

        //Wait icon loading disssapear
        // Tra ve kieu boolean
        /*explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector("div#mainContent div.spinner-border")));*/

        // ===> AssertTrue la mat đi roi
        // Wait + verify spiner biến mất
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector("div#mainContent div.spinner-border"))));

        // vi elementToBeClickable tra ve 1 element, nen ta co the click luon
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.ajaxLink>button"))).click();
        //driver.findElement(By.cssSelector("a.ajaxLink>button")).click();

        // ===> AssertTrue la mat đi roi
        // Wait + verify spiner biến mất
        /*Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector("div#mainContent div.spinner-border"))));*/

        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(
                driver.findElements(By.cssSelector("div.spinner-border")))));

        //explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#filesUpload button.btn-primary"))).click();

        // chưa hoc  hic
        //driver.findElement(By.cssSelector("input[type='file']")).sendKeys("");

    }

    @AfterClass
    public void afterClass() {
        // driver.quit();
    }


}
