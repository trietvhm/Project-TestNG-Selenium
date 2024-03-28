package webdriver;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.function.Function;

public class Topic_22_Wait_09_Fluent {
    WebDriverWait explicitWait;
    WebDriver driver;

    FluentWait<WebDriver> fluentDriver;
    FluentWait<WebElement> fluentElement;
    FluentWait<String> fluentString;

    // khoi tao trinh duyet san
    @BeforeClass
    //Selenium version  2.x/3.x/4.x/(4.6 tro xuong) bat buoc xai ham beforeclass. Hientai khong co van chay duoc
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();

        // Time - Default polling time :0.5s
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Time + Polling: 0.3s // tuong tu nhu fluentWait
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(300));

    }

    @Test
    public void TC_01_() {
        // Khoi tao
        fluentDriver = new FluentWait<>(driver);

        WebElement element = driver.findElement(By.cssSelector(""));

        fluentElement = new FluentWait<WebElement>(element);

        //fluentString = new FluentWait<String>("Hello World");

        // SETTING
        // Tong time
        fluentDriver.withTimeout(Duration.ofSeconds(10));

        // Polling Time
        fluentDriver.pollingEvery(Duration.ofMillis(300));

        // Ignore NoSuchElement Exception
        fluentDriver.ignoring(NoSuchElementException.class);

        // Ignore TimeoutException
        fluentDriver.ignoring(TimeoutException.class);

        //CONDITION
        fluentDriver.until(new Function<WebDriver, Object>() {
            @Override
            public Object apply(WebDriver webDriver) {
                return null;
            }
        });


        fluentDriver.until(new Function<WebDriver, String>() {
            @Override
            public String apply(WebDriver webDriver) {
                return webDriver.findElement(By.cssSelector("")).getText();
            }
        });

        fluentDriver.withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(TimeoutException.class, NoSuchElementException.class);
        fluentDriver.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElement(By.xpath("")).isDisplayed();
            }
        });


    }

    @Test
    public void TC_02_Fluent_Wait() {
        // Khoi tao
        fluentDriver = new FluentWait<WebDriver>(driver);

        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.findElement(By.cssSelector("div#start button")).click();

        fluentDriver.withTimeout(Duration.ofSeconds(6))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(TimeoutException.class, NoSuchElementException.class)
                .until(new Function<WebDriver, Boolean>() {
                    @Override
                    public Boolean apply(WebDriver webDriver) {
                        return webDriver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).isDisplayed();
                    }
                });

        String helloText = fluentDriver.until(new Function<WebDriver, String>() {
            @Override
            public String apply(WebDriver webDriver) {
                return driver.findElement(By.cssSelector("div#finish h4")).getText();
            }
        });

        Assert.assertEquals(helloText, "Hello World!");

    }

    @Test
    public void TC_03_Fluent_Wait_Ex2() {
        driver.get("https://automationfc.github.io/fluent-wait/");

        WebElement countDownTime = driver.findElement(By.cssSelector("div#javascript_countdown_time"));
        fluentElement = new FluentWait<WebElement>(countDownTime);

        fluentElement.withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        fluentElement.until(new Function<WebElement, Boolean>() {
            @Override
            public Boolean apply(WebElement webElement) {
                String text = webElement.getText();
                System.out.println(text);
                return text.endsWith("00");
            }
        });
    }




    @AfterClass
    public void afterClass() {
        //driver.quit();
    }


}
