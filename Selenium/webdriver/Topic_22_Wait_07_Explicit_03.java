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

import java.io.File;
import java.time.Duration;

public class Topic_22_Wait_07_Explicit_03 {
    WebDriver driver;

    WebDriverWait explicitWait;
    String projectPath = System.getProperty("user.dir");
    String hcmName = "saigon.jpg";
    String dnName = "danang.jpg";
    String hnName = "hanoi.jpg";

    String hcmFilePath = projectPath + File.separator + "uploadFiles" + File.separator + hcmName;
    String dnFilePath = projectPath + File.separator + "uploadFiles" + File.separator + dnName;
    String hnFilePath = projectPath + File.separator + "uploadFiles" + File.separator + hnName;

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

        driver.findElement(By.xpath("//a[text()='5']")).click();

        // Wait cho LOADING ICON bien mat trong vong X giây 
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//div[contains(@id,'RadCalendar1')]/div[@class='raDiv']")));

        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText(),
                "Tuesday, March 5, 2024");

    }

    @Test
    public void TC_02_GoFiles() {
        driver.get("https://gofile.io/welcome");

        // Wait icon loading disssapear
        // Tra ve kieu boolean
        /*explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.cssSelector("div#mainContent div.spinner-border")));*/

        // mong doi la mat di roi ===> AssertTrue la mat đi roi
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

        // tra ve boolean nen co the upload file duoc
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(
                driver.findElements(By.cssSelector("div.spinner-border")))));

        //explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#filesUpload button.btn-primary"))).click();

        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(hcmFilePath + "\n" + dnFilePath + "\n" + hnFilePath);

        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(
                driver.findElements(By.cssSelector("div.spinner-border")))));

        // Wait những thanh progress bar bien mat
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(
                driver.findElements(By.cssSelector("div.progress"))));

        // Wait cho no ready trước da roi moi click
        explicitWait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("div.mainUploadSuccessLink a.ajaxLink"))).click();

        // Wait + Verify button play co tai tung hinh duoc upload
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[text()='"+ hcmName +"']//ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Download']"))).isDisplayed());

        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[text()='"+ dnName +"']//ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Download']"))).isDisplayed());

        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[text()='"+ hnName +"']//ancestor::div[contains(@class,'text-md-start')]/following-sibling::div//span[text()='Download']"))).isDisplayed());

    }

    @AfterClass
    public void afterClass() {
        // driver.quit();
    }


}
