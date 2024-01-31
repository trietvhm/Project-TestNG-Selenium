package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_08_WebBrowser_Exercises {
    WebDriver driver;

    // khoi tao trinh duyet san
    @BeforeClass
    //Selenium version  2.x/3.x/4.x/(4.6 tro xuong) bat buoc xai ham beforeclass. Hientai khong co van chay duoc
    public void beforeClass() {

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // khi tim elelment ma da thay element roi thi khong cho den 30s nua
    }

    @Test
    public void TC_01_Page_Url() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        //driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        //Thread.sleep(3000);// cu gap la wait 3s, xong hay chưa xong thi van cu wait 3s, khong quan tam thoa man dk hay chua
        sleepInSecond(3); // co hanh vi chuyen trang thi cho, de load xong page
        driver.get("http://live.techpanda.org/index.php/customer/account/login/");
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.xpath("//div[@class='buttons-set']//a[@title='Create an Account']")).click();
        sleepInSecond(3);
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");
     }

    @Test
    public void TC_02_Page_Tile() {
          driver.get("http://live.techpanda.org/");
          driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']")).click();
          sleepInSecond(3);
          Assert.assertEquals(driver.getTitle(),"Customer Login");

          driver.findElement(By.xpath("//a[@class='button']//span[text()='Create an Account']")).click();
          sleepInSecond(3);
          Assert.assertEquals(driver.getTitle(),"Create New Customer Account");

    }

    @Test
    public void TC_03_Page_Navigation() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']")).click();
        sleepInSecond(3);
        driver.findElement(By.xpath("//a[@class='button']//span[text()='Create an Account']")).click();
        sleepInSecond(3);
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");
        driver.navigate().back();
        sleepInSecond(3);
        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");
        driver.navigate().forward();
        sleepInSecond(3);
        Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
    }
    @Test
    public void TC_04_Page_Source() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div[class='footer'] a[title='My Account']")).click();
        sleepInSecond(3);

        Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));

        driver.findElement(By.xpath("//a[@class='button']//span[text()='Create an Account']")).click();
        sleepInSecond(3);

        Assert.assertTrue(driver.getPageSource().contains("Create an Account"));


    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void sleepInSecond(long timeInSecond)  {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
