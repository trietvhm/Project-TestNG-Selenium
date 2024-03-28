package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;

public class Topic_22_Wait_08_Mix_implicit_Explicit {
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
    public void TC_01_Only_Implicit_Found() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("https://www.facebook.com/");

        // khi vao tim element thi no tim thay ngay, nen la khong can cho het 5s (timeout)
        driver.findElement(By.cssSelector("input#email"));

    }

    @Test
    public void TC_02_Only_Implicit_Not_Found() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("https://www.facebook.com/");

        // Khi vao tim element thi khong tim thay
        // Polling mỗi nửa giây tìm lại một lần
        // khi het timeout danh fail test case va throw exception: noSuchElementException
        driver.findElement(By.cssSelector("input#automation"));

    }

    @Test
    public void TC_03_Only_Explicit_Found() {
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(5));

        driver.get("https://www.facebook.com/");
        // khi vao tim element thi no tim thay ngay, nen la khong can cho het 5s (timeout)
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
    }

    @Test
    public void TC_04_Only_Explicit_Not_Found() {
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(5));

        driver.get("https://www.facebook.com/");
        // Khi vao tim element thi khong tim thay
        // Polling mỗi nửa giây tìm lại một lần
        // khi het timeout danh fail test case va throw exception: TimeoutException
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#automation")));
    }

    @Test
    public void TC_05_Only_Explicit_Not_Found_Param_By() {
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(5));
        driver.get("https://www.facebook.com/");
        // Khi vao tim element thi khong tim thay
        // Polling mỗi nửa giây tìm lại một lần
        // khi het timeout danh fail test case va throw exception: TimeoutException
        System.out.println("Start time: " + getDateTimeNow());

        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#automation")));
        } catch (Exception e) {
            System.out.println("End time: "+ getDateTimeNow());
            throw new RuntimeException(e);
        }
    }

    @Test
    public void TC_06_Only_Explicit_Not_Found_Param_WebElement() {
        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(5));

        driver.get("https://www.facebook.com/");
        // Khi vao tim element thi khong tim thay
        // Polling mỗi nửa giây tìm lại một lần
        // khi het timeout danh fail test case va throw exception: TimeoutException
        System.out.println("Start time: " + getDateTimeNow());
        try {
            explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#automation"))));
        } catch (Exception e) {
            System.out.println("End time: "+ getDateTimeNow());
            throw new RuntimeException(e);
        }
    }

    @Test
    public void TC_07_Mix_Explicit_Implicit() {
        driver.get("https://www.facebook.com/");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));

        explicitWait = new WebDriverWait(driver,Duration.ofSeconds(5));

       // driver.findElement(By.cssSelector("input#email"));
        System.out.println("Start time: " + getDateTimeNow());
        //
        try {
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#automation")));
        } catch (Exception e) {
            System.out.println("End time: "+ getDateTimeNow());
            throw new RuntimeException(e);
        }

    }

    @AfterClass
    public void afterClass() {
        //driver.quit();
    }

    public String getDateTimeNow(){
        Date date = new Date();
        return date.toString();
    }



}
