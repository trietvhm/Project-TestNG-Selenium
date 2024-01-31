package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Topic_08_Element_Exercises {
    WebDriver driver;

    // khoi tao trinh duyet san
    @BeforeClass
    //Selenium version  2.x/3.x/4.x/(4.6 tro xuong) bat buoc xai ham beforeclass. Hientai khong co van chay duoc
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Displayed() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        if (driver.findElement(By.cssSelector("input#mail")).isDisplayed()) {
            driver.findElement(By.cssSelector("input#mail")).sendKeys("AutomationTesting@gmail.com");
            System.out.println("Email texbox is displayed");
        } else System.out.println("Email texbox is not displayed");

        if (driver.findElement(By.cssSelector("textarea#edu")).isDisplayed()) {
            driver.findElement(By.cssSelector("textarea#edu")).sendKeys("International University");
            System.out.println("Education texbox is displayed");
        } else System.out.println("Education texbox is not displayed");

        if (driver.findElement(By.cssSelector("input#under_18")).isDisplayed()) {
            driver.findElement(By.cssSelector("input#under_18")).click();
            System.out.println("Under 18 radio is displayed");
        } else System.out.println("Under 18 radio is not displayed");


        //   Assert.assertTrue(driver.findElement(By.cssSelector("textarea[name='user_edu']")).isDisplayed());


        if (driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed()) {
            System.out.print("Name: User5 is displayed");
        } else System.out.print("Name: User5 is not displayed");

    }

    @Test
    public void TC_02_Enabled() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        if (driver.findElement(By.cssSelector("input#mail")).isEnabled()) {
            System.out.println("Email textbox is enabled");
        } else System.out.println("Email textbox is not enabled");

        if (driver.findElement(By.cssSelector("input#over_18")).isEnabled()) {
            System.out.println("18 or older Radio is enabled");
        } else System.out.println("18 or older Radio is not enabled");

        if (driver.findElement(By.cssSelector("select[name='user_job1']")).isEnabled()) {
            System.out.println("Job Role 01 is enabled");
        } else System.out.println("Job Role 01 is not enabled");

        if (driver.findElement(By.cssSelector("#slider-1")).isEnabled()) {
            System.out.println("Slider 01 is enabled");
        } else System.out.println("Slider 01 is not enabled");

        System.out.println("---------------------------------------------------");

        if (driver.findElement(By.cssSelector("#disable_password")).isEnabled()) {
            System.out.println("Password is enabled");
        } else System.out.println("Password is disabled");

        if (driver.findElement(By.cssSelector("#radio-disabled")).isEnabled()) {
            System.out.println("\"Radio button\" is enabled");
        } else System.out.println("\"Radio button\" is disable");

        if (driver.findElement(By.cssSelector("#slider-2")).isEnabled()) {
            System.out.println("Slider 2 is enabled");
        } else System.out.println("Slider 2 is disable");

    }

    @Test
    public void TC_03_Selected() {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.cssSelector("#under_18")).click();
        driver.findElement(By.cssSelector("#java")).click();
        if (driver.findElement(By.cssSelector("#under_18")).isSelected()) {
            System.out.println("Under 18 Radio is selected");
        } else System.out.println("Under 18 Radio is not selected");

        if (driver.findElement(By.cssSelector("#java")).isSelected()) {
            System.out.println("Languages Java Radio is selected");
        } else System.out.println("Languages Java Radio is not selected");


    }

    @Test
    public void TC_04_MailChimp() {
        driver.get("https://login.mailchimp.com/signup/");
        driver.findElement((By.cssSelector("input#email"))).sendKeys("meat100kg@gmail.com");

        // case 7 - Empty case
        driver.findElement((By.cssSelector("input#new_password"))).clear();

        WebElement element = driver.findElement(By.cssSelector("button#create-account-enabled"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
        sleepInSecond(2);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.not-completed")).isDisplayed());

        // case 1 - Number
        driver.findElement((By.cssSelector("input#new_password"))).sendKeys("12345");
        sleepInSecond(2);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        // case 2 - Lowercase
        driver.findElement((By.cssSelector("input#new_password"))).clear();
        driver.findElement((By.cssSelector("input#new_password"))).sendKeys("auto");
        sleepInSecond(2);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        // case 3 - uppercase
        driver.findElement((By.cssSelector("input#new_password"))).clear();
        driver.findElement((By.cssSelector("input#new_password"))).sendKeys("AUTO");
        sleepInSecond(2);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        // case 4 - Special Chars
        driver.findElement((By.cssSelector("input#new_password"))).clear();
        driver.findElement((By.cssSelector("input#new_password"))).sendKeys("@#!$");
        sleepInSecond(2);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        // case 5 - Special Chars
        driver.findElement((By.cssSelector("input#new_password"))).clear();
        driver.findElement((By.cssSelector("input#new_password"))).sendKeys("12345678");
        sleepInSecond(2);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        // case 6 - Valid
        driver.findElement((By.cssSelector("input#new_password"))).clear();
        driver.findElement((By.cssSelector("input#new_password"))).sendKeys("Auto123@1");
        sleepInSecond(2);

        Assert.assertFalse(driver.findElement(By.cssSelector("li.lowercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.uppercase-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.number-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.special-char.completed")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li[class='8-char completed']")).isDisplayed());
        Assert.assertFalse(driver.findElement(By.cssSelector("li.username-check.completed")).isDisplayed());

        /*// case 7 - Empty case
        driver.findElement((By.cssSelector("input#new_password"))).clear();

        WebElement element = driver.findElement(By.cssSelector("button#create-account-enabled"));
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
        sleepInSecond(2);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.lowercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.uppercase-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.number-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.special-char.not-completed")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li[class='8-char not-completed']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.cssSelector("li.username-check.not-completed")).isDisplayed());*/
    }

    @AfterClass
    public void afterClass() {
        //driver.quit();
    }

    public void sleepInSecond(long timeInSecond)  {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
