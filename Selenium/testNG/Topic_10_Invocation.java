package testNG;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Random;

public class Topic_10_Invocation {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test(invocationCount = 5) // 5 lần thi la 5 testcases
    public void TC_01_Register() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        sleepInSecond(3);

        // Dang ki 1 tai khoan truoc
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        sleepInSecond(3);

        String firsName = "triet", lastName = "vu", emailAddress= getEmailAddress(), password ="123456789";
        String fullName = firsName + " " + lastName;

        driver.findElement(By.cssSelector("input#firstname")).sendKeys(firsName);
        driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#email_address")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys(password);

        driver.findElement(By.xpath("//button[@title='Register']")).click();
        sleepInSecond(3);

        //verify register account
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),"Thank you for registering with Main Website Store.");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(),"Hello, " + fullName + "!");

        // locator khó
        String contactInfor  = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInfor.contains(fullName));
        Assert.assertTrue(contactInfor.contains(emailAddress));

        // Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText());

        //   driver.findElement().getText().contains(); contains trả ve boolean

        //logout    // locator khó
        driver.findElement(By.cssSelector("a.skip-account")).click();
        sleepInSecond(3);
        driver.findElement(By.cssSelector("li.last>a[title='Log Out']")).click();
        sleepInSecond(3);

        //login
        driver.findElement(By.cssSelector("div.footer a[title='My Account']")).click();
        sleepInSecond(3);

        driver.findElement(By.cssSelector("input#email")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#pass")).sendKeys(password);
        driver.findElement(By.cssSelector("button#send2")).click();
        sleepInSecond(3);


        //verify success login
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(),"Hello, " + fullName + "!");

        contactInfor  = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(contactInfor.contains(fullName));
        Assert.assertTrue(contactInfor.contains(emailAddress));


        //verify account information
        driver.findElement(By.xpath("//a[text()='Account Information']")).click();
        sleepInSecond(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("input#firstname")).getAttribute("value"), firsName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#lastname")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#email")).getAttribute("value"), emailAddress);

        //logout    // locator khó
        driver.findElement(By.cssSelector("a.skip-account")).click();
        sleepInSecond(3);
        driver.findElement(By.cssSelector("li.last>a[title='Log Out']")).click();
        sleepInSecond(3);

        System.out.println("Email address / Password: " + emailAddress + " - " + password);
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

    public String getEmailAddress() {
        Random rand = new Random();
        /*String emailAddress = "automation" + rand.nextInt(9999) + "@gmail.net";
        return emailAddress;*/
        return "automation" + rand.nextInt(9999) + "@gmail.net";
    }
}