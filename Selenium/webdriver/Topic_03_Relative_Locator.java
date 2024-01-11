package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
        driver.get("https://www.nopcommerce.com/en/register");
        By loginButtonBy = By.cssSelector("button.login-button");
//        WebElement loginButtonElement = driver.findElement(By.cssSelector("button.login-button"));

      //  RelativeLocator.with(By.tagName("label"));
        // password text box
        By rememberMeCheckboxBy = By.id("RememberMe");

        //Dang tra ve relativeby thằng with
        /*  RelativeLocator.with(By.tagName("label"))
                .above(loginButtonBy)
                .toRightOf(passwordTextBox);  */
      //  RelativeLocator.with(By.tagName("label")).above(loginButtonElement);

        //muon define nó thành 1 element đề minh tim va thao tac lên element này
        WebElement forgotPassword = driver.findElement(RelativeLocator.with(By.tagName("label")));
                       /* .above(loginButtonBy)
                        .toRightOf(rememberMeCheckboxBy));*/

        // gettech cua element ra
 //       System.out.printf(forgotPassword.getText());

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}
