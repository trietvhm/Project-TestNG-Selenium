package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class Topic_09_Default_Dropdown {
    WebDriver driver;
    String firstName = "Triet", lastName = "Vu",
            emailAddress = getEmailAddress(),
            companyName = "Bosch",
            password = "123456789";

    String Day="15", Month="November", Year="1999";
    // khoi tao trinh duyet san
    @BeforeClass
    //Selenium version  2.x/3.x/4.x/(4.6 tro xuong) bat buoc xai ham beforeclass. Hientai khong co van chay duoc
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_() {
        driver.get("https://demo.nopcommerce.com/");

        // driver.findElement(By.cssSelector("div.header-links a.ico-register")).click();
        driver.findElement(By.cssSelector("a.ico-register")).click();
        sleepInSecond(3);

        driver.findElement(By.cssSelector("span.male input#gender-male")).click();
        sleepInSecond(3);

        driver.findElement(By.cssSelector("input#FirstName")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#LastName")).sendKeys(lastName);

        //Day dropdown
        // chon ngay
        //new Select(driver.findElement(By.name("DateOfBirthDay"))).selectByVisibleText("15");
        Select day = new Select(driver.findElement(By.name("DateOfBirthDay")));
        day.selectByVisibleText("15");

        // verify dropdown nay la single (không phai multiple)
        // Neu no la Multiple no se tra ve True
        // Neu no la Single no se tra ve False
        Assert.assertFalse(day.isMultiple());

        //verify so luong trong dropdown nay la 32 item
        //Select day = new Select(driver.findElement(By.name("DateOfBirthDay")));
        List<WebElement> dayOptions = day.getOptions();
        Assert.assertEquals(dayOptions.size(),32);


        //Assert.assertEquals(day.getOptions().size(),32);

        Select month = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        month.selectByVisibleText("November");

        Select year = new Select(driver.findElement(By.name("DateOfBirthYear")));
        year.selectByVisibleText("1999");

        driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys(password);

        driver.findElement(By.cssSelector("button#register-button")).click();
        sleepInSecond(3);

        //Verify message register success
        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(),"Your registration completed");
        driver.findElement(By.cssSelector("a.register-continue-button")).click();
        sleepInSecond(3);
        
    }

    @Test
    public void TC_02_Login() {
        driver.get("https://demo.nopcommerce.com/");
        driver.findElement(By.cssSelector("a.ico-login")).click();

        driver.findElement(By.id("Email")).sendKeys(emailAddress);
        driver.findElement(By.id("Password")).sendKeys(password);
        driver.findElement(By.cssSelector("button.login-button")).click();
        sleepInSecond(3);

        // verify
        driver.findElement(By.cssSelector("a.ico-account")).click();
        sleepInSecond(3);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#FirstName")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#LastName")).getAttribute("value"), lastName);

        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthDay")))
                .getFirstSelectedOption().getText(),Day);
        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthMonth"))).getFirstSelectedOption().getText(),Month);
        Assert.assertEquals(new Select(driver.findElement(By.name("DateOfBirthYear"))).getFirstSelectedOption().getText(),Year);

        Assert.assertEquals(driver.findElement(By.cssSelector("input#Email"))
                .getAttribute("value"), emailAddress);

        Assert.assertEquals(driver.findElement(By.cssSelector("input#Company")).getAttribute("value"), companyName);

    }

    @AfterClass
    public void afterClass() {
        //driver.quit();
    }

    public String getEmailAddress() {
        Random rand = new Random();
        return "trietvu" + rand.nextInt(99999) + "@gmail.net";
    }

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
