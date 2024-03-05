package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_22_Wait_02_Find_Element {
    WebDriver driver;
    WebDriverWait explicitWait;

    FluentWait<WebDriver> fluentWait;

    // khoi tao trinh duyet san
    @BeforeClass
    //Selenium version  2.x/3.x/4.x/(4.6 tro xuong) bat buoc xai ham beforeclass. Hientai khong co van chay duoc
    public void beforeClass() {
        driver = new FirefoxDriver();

        //implicit wait
        // Set implicit ---> seleium Version 4.x tro len
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Timeout duoc Set la 10s

        // Set implicit ---> seleium Version 3.x tro xuong
        // tuong tu nhu vay neu minh dung voi Explicit or FluentWAIT
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // version 4
        //explicitWait = new WebDriverWait(driver,Duration.ofSeconds(10));

        //version 3
        //explicitWait = new WebDriverWait(driver,10);

        //fluentWAIT ver 4
        //fluentWait = new FluentWait(driver);
        // fluentWait.withTimeout(Duration.ofSeconds(10))
        //                 .pollingEvery(Duration.ofMillis(500));

        driver.get("https://www.facebook.com/");
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_FindElement() {
        // case 1: Element duoc tim thay chi co 1
        // Se khong can cho het Timeout
        // Tim thay se tra ve 1 WebElement
        // Qua step tiep theo
        System.out.println("Start step: "+ getDateTimeNow());
        driver.findElement(By.cssSelector("input#email"));
        System.out.println("End step: " + getDateTimeNow());

        // case 2: Element duoc tim thay nhung co nhieu hon 1
        // Se khong can cho het Timeout
        // Tim thay se tra ve 1 WebElement - Element tìm thấy đâu tiên - du cho co tim thay ca tram hay ngan node
        // Qua step tiep theo, khong can cho het thoi gian la 10s
        driver.findElement(By.cssSelector("input[type='text'],[type='password']")).sendKeys("Dam@gmail.com");

        // case 3: Element khong duoc tim thay
        // Cho het Timeout là 10s
        // Trong thoi gian 10s này cứ mỗi nua giay là se tim lại 1 lần (POLLING)
        // + Neu tim lai ma thay (MINH CLICK) thi NGAY LAP TUC cung tra ve element roi qua step tiep theo ===> PASS
        // + Neu tim lai ma het TIMEOUT van khong thay thi danh FAIL test case va thow EXCEPTION
        // Cac step con lai khong chay nua
        driver.findElement(By.cssSelector("input#not-Found"));
    }

    @Test
    public void TC_02_FindElements() {
        List<WebElement> elementList;
        // case 1: Element duoc tim thay chi co 1
        // Khong can cho het TimeOut 10s
        // Tra ve 1 list Element chua dung 1 element
        System.out.println("Start step: "+ getDateTimeNow());
        elementList = driver.findElements(By.cssSelector("input#email"));
        System.out.println("Element Size: " + elementList.size());
        System.out.println("End step: " + getDateTimeNow());

        // case 2: Element duoc tim thay nhung co nhieu hon 1
        // Khong can cho het TimeOut 10s
        // Tra ve 1 list Element chua nhieu element
        System.out.println("Start step: "+ getDateTimeNow());
        elementList = driver.findElements(By.cssSelector("input[type='text'],[type='password']"));
        System.out.println("Element Size: " + elementList.size());
        System.out.println("End step: " + getDateTimeNow());

        // case 3: Element khong duoc tim thay
        // Cho het Timeout là 10s
        // Trong thoi gian 10s này cứ mỗi nua giay là se tim lại 1 lần (POLLING)
        // + Neu tim lai ma thay (MINH CLICK) thi NGAY LAP TUC cung tra ve List chứa element roi qua step tiep theo ===> PASS
        // + Neu tim lai ma het TIMEOUT van khong thay, thi TRA VE 1 LIST RONG (EMPTY) VA KHONG DANH FAIL TEST CASE
        // Qua step tiep theo
        System.out.println("Start step: "+ getDateTimeNow());
        elementList = driver.findElements(By.cssSelector("input#not-Found"));
        System.out.println("Element Size: " + elementList.size());
        System.out.println("End step: " + getDateTimeNow());
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
