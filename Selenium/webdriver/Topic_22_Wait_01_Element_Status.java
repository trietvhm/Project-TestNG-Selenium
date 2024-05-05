package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_22_Wait_01_Element_Status {
    WebDriver driver;

    WebDriverWait explicitWait;

    By reconfirmEmail = (By.cssSelector("input[name='reg_email_confirmation__']"));

    // khoi tao trinh duyet san
    @BeforeClass
    //Selenium version  2.x/3.x/4.x/(4.6 tro xuong) bat buoc xai ham beforeclass. Hientai khong co van chay duoc
    public void beforeClass() {
        driver = new FirefoxDriver();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        driver.manage().window().maximize();

        driver.get("https://www.facebook.com/");

    }

    @Test
    public void TC_01_Visible_In_DOM() {
        driver.findElement(By.xpath("//a[text()='Create new account']")).click();
        sleepInSecond(3);

        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("meat100kg@gmail.com");
        sleepInSecond(3);

        // Tai dung thoi diem nay/ step nay thi Confirm Email Text Box đang visible/displayed
        // Co the apply WAIT visible

        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(reconfirmEmail));

        // kiem tra 1 element dang hien thi
        Assert.assertTrue(driver.findElement(reconfirmEmail).isDisplayed());

    }

    @Test
    public void TC_02_Invisible_IN_DOM() {
        driver.findElement(By.xpath("//a[text()='Create new account']")).click();
        sleepInSecond(3);

        // Dieu kien 2: Element khong xuat hien tren UI va van co trong cay HTML
        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();

        // Tai dung thoi diem nay/ step nay thi Confirm Email Text Box đang Invisible/displayed
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(reconfirmEmail));

        // kiem tra 1 element khong hien thi
        // Chay nhanh ---> ket qua step nay pass
        Assert.assertFalse(driver.findElement(reconfirmEmail).isDisplayed());

    }

    @Test
    public void TC_02_Invisible_NOT_IN_DOM() {
        driver.findElement(By.xpath("//a[text()='Create new account']")).click();
        sleepInSecond(3);

        // click vao icon close để đóng popup lại
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        sleepInSecond(3);

        // Đieu kien 3: Element khong xuat hien tren UI va cung khong co trong cây HTML
        // Tai dung thoi diem nay/ step nay thi Confirm Email Text Box đang INvisible/NOTdisplayed
        // Co the apply WAIT visible

        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(reconfirmEmail));

        // KIEM  tra 1 element dang hien thi
        // Chay lau/ket qua step nay Failed
        //Assert.assertFalse(driver.findElement(reconfirmEmail).isDisplayed());
    }
    @Test
    public void TC_03_Presence() {
        driver.findElement(By.xpath("//a[text()='Create new account']")).click();
        sleepInSecond(3);

        driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys("meat100kg@gmail.com");
        sleepInSecond(3);

        // Dieu kien 1: Element co xuat hien o tren UI va co trong cay HTML
        // Tai dung thoi diem nay/ step nay thi Confirm Email Text Box đang presence (co trong HTML)
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(reconfirmEmail));

        // Dieu kien 2: Element khong xuat hien tren UI va van co trong cay HTML
        driver.findElement(By.cssSelector("input[name='reg_email__']")).clear();
        sleepInSecond(3);
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(reconfirmEmail));

    }

    @Test
    public void TC_04_Staleness() {
        driver.findElement(By.xpath("//a[text()='Create new account']")).click();
        sleepInSecond(3);

        // Tai thoi diem nay, Element xuat hien va minh se findelement
        WebElement reconfirmMail = driver.findElement(reconfirmEmail);

        // click vao icon close để đóng popup lại
        driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
        sleepInSecond(3);

        // Dieu kien 3 Element KHÔNG hiển thị trên UI và KHÔNG có trong DOM (HTML)
        // Bắt buộc của hàm này phải là 1 element, chu no khong dung By
        // 1 element có mặt o truoc do va hiện tai bay gio no khong co nua
        // invisible WAIT đúng thời diem nay, con khong quan tam den thoi diem truoc do
        explicitWait.until(ExpectedConditions.stalenessOf(reconfirmMail));

    }

    @AfterClass
    public void afterClass() {
        //driver.quit();
    }

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
