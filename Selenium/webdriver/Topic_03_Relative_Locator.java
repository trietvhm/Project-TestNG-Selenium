package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_03_Relative_Locator {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    // khoi tao trinh duyet san
    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_Relative() {
        driver.get("https://demo.nopcommerce.com/login?returnUrl=%2F");
        By loginButtonBy = By.cssSelector("button.login-button");
        WebElement loginButtonElement = driver.findElement(By.cssSelector("button.login-button"));

        By rememberMeCheckboxBy = By.id("RememberMe");

        By forgotPasswordText = By.className("forgot-password");
       // By forgotPasswordText = By.linkText("Forgot password?");
       // WebElement forgotPasswordText = driver.findElement(By.cssSelector("span.forgot-password"));

   //     By passwordTextBox = By.id("Password");
   //     WebElement passwordTextBox = driver.findElement(By.cssSelector(".password"));
   //     WebElement passwordTextBox = driver.findElement(By.cssSelector("input[class=password]"));
        WebElement passwordTextBox = driver.findElement(By.cssSelector("input.password"));

        // GUI (location//position) // giong test giao dien, vi tri cua no o dau so voi element khac
        WebElement rememberMeTextElement = driver
                .findElement(RelativeLocator.with(By.tagName("label"))
                        .below(passwordTextBox)
                        .above(loginButtonBy)
                        .toRightOf(rememberMeCheckboxBy)
                        .toLeftOf(forgotPasswordText));

        // gettech cua element ra
        System.out.printf(rememberMeTextElement.getText());

        List<WebElement> countElement = driver.findElements(RelativeLocator.with(By.cssSelector("a")));
        System.out.println(countElement.size());

    }

    @AfterClass
    public void afterClass() {
        //driver.quit();
    }

}
