package testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class Topic_009_Parameter {
    WebDriver driver;

    @Parameters({"browser"})
    @BeforeClass
    public void beforeClass(String browser) {
        driver = getDriver(browser);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Parameters({"environment"})
    @Test
    // chuc nang login
    public void TC_01_Login(@Optional("live") String environmentName) {
        driver.get(getEnvironmentURL(environmentName)+"/index.php/customer/account/login/");

        driver.findElement(By.xpath("//*[@id='email']")).sendKeys("selenium_11_01@gmail.com");
        driver.findElement(By.xpath("//*[@id='pass']")).sendKeys("111111");
        driver.findElement(By.xpath("//*[@id='send2']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains("selenium_11_01@gmail.com"));

        // Action nay cho tat ca cac user đều giống nhau
        // Mua san pham, add gio hàng - thanh toan - verify

        // logout ra
        driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
        driver.findElement(By.xpath("//a[text()='Log Out']")).click();
    }

   /* @DataProvider(name = "loginData")
    public Object[][] UserAndPasswordData() {
        return new Object[][]{
                {"selenium_11_01@gmail.com", "111111"}
                //{"selenium_11_02@gmail.com", "111111"},
                // {"selenium_11_03@gmail.com", "111111"}};
        };
    }*/

    private WebDriver getDriver (String browser){
        if (browser.equals("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equals("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equals("edge")) {
            driver = new EdgeDriver();
        } else {
            throw new RuntimeException("Browser name is not valid ");
        }
        return driver;
    }

    public String getEnvironmentURL(String environmentName) {
        String urlValue;
        if (environmentName.equals("dev")) {
            urlValue = "http://dev.techpanda.org";
        } else if (environmentName.equals("testing")) {
            urlValue = "http://testing.techpanda.org";
        } else if (environmentName.equals("staging")) {
            urlValue = "http://staing.techpanda.org";
        } else if (environmentName.equals("live")) {
            urlValue = "http://live.techpanda.org";
        } else {
            throw new RuntimeException("Environment name is not valid ");
        }
        return urlValue;
    }

    @AfterClass
    public void afterClass() {
        //driver.quit();
    }

}
