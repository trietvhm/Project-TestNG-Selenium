package webdriver;

import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.Color;

import java.time.Duration;

public class Topic_12_Button_Radio_CheckBox {
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
    public void TC_01_Egov_Button() {
        driver.get("https://egov.danang.gov.vn/reg");
        // Verify button bi disable khi chua click vao check box
        // dung class vi name va id chung chung

        WebElement registerbutton = driver.findElement(By.cssSelector("input.egov-button"));
        // neu nhu nó enalbe thi ham nay tra ve true
        // neu no ko enalbe ham nay tra ve false ==? assertfalse thì dung
        Assert.assertFalse(registerbutton.isEnabled());

        driver.findElement(By.cssSelector("input#chinhSach")).click();
        sleepInSecond(5);

        // verify button da duoc enable sau khi click vao checkbox
        Assert.assertTrue(registerbutton.isEnabled());

        //Lay ra ma mau nen cua button
        String registerBackgroundRgb = registerbutton.getCssValue("background-color");
        System.out.println("Background color RGB: " + registerBackgroundRgb);

        // convert tu kieu String mã RGB sang kieu color
        Color loginButtonBackgroundHexa = Color.fromString(registerBackgroundRgb);

        // convert qua String ma hexa
        String registerBackgroundHexa = loginButtonBackgroundHexa.asHex();
        System.out.println("Background color Hexa:" + registerBackgroundHexa);

        Assert.assertEquals(registerBackgroundHexa.toLowerCase(), "#ef5a00");

    }

    @Test
    public void TC_02_Fahasa() {
        driver.get("https://www.fahasa.com/customer/account/create");
        //Step 1
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
        // step 3
        WebElement registerButton = driver.findElement(By.cssSelector("button.fhs-btn-login"));
        Assert.assertFalse(registerButton.isSelected());
        //step 4
        String registerButtonRgb = registerButton.getCssValue("background-color");
        System.out.println("Background color RGB: " + registerButtonRgb);

        //Step 5
        Color variable = Color.fromString(registerButtonRgb);
        String registerButonHexa = variable.asHex();
        System.out.println(registerButonHexa);
        System.out.println(registerButonHexa.toLowerCase());
        Assert.assertEquals(registerButonHexa.toLowerCase(), "#000000");

        /*String registerButonHexa = Color.fromString(registerButton.getCssValue("background-color")).asHex().toLowerCase();
        Assert.assertEquals(registerButonHexa, "#000000");*/

        // nhap Email/Pass
        driver.findElement(By.cssSelector("input#login_username")).sendKeys("dam@gmail.com");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456");
        sleepInSecond(3);

        //Verify enalbe button
        //driver.findElement(By.cssSelector("button.fhs-btn-login")).isSelected();
        Assert.assertTrue(driver.findElement(By.cssSelector("button.fhs-btn-login")).isEnabled());

        // verify color button
        String buttoncollor = driver.findElement(By.cssSelector("button.fhs-btn-login")).getCssValue("background");
        System.out.println(buttoncollor);

        // doi qua kieu color
        String butoncolorHexa = Color.fromString(buttoncollor).asHex().toLowerCase();
        Assert.assertEquals(butoncolorHexa,"#c92127");

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
