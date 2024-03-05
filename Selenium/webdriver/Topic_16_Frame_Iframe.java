package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_16_Frame_Iframe {
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
    public void TC_01_Form_Site() {
        //trang A
        driver.get("https://www.formsite.com/templates/education/campus-safety-survey/");
        driver.findElement(By.cssSelector("div#imageTemplateContainer>img")).click();
        sleepInSecond(5);

        //Iframe element
        WebElement formIframe = driver.findElement(By.cssSelector("div#formTemplateContainer>iframe"));
        // check Iframe displayed
        Assert.assertTrue(formIframe.isDisplayed());

        //Switch vao Frame/Iframe truoc khi thao tac voi element ben trong
        // co 3 cach
        // C1 dung index // ko chinh xac cho lắm // co the bị thay đổi
        //  driver.switchTo().frame(0);

        // C2: name or id // co truong hop ko có ID or NAME
        // driver.switchTo().frame("frame-one85593366");

        // C3: dung element //nen dung
        driver.switchTo().frame(formIframe);

        // khong tim thay element (element nam trong iframe) đang tim o trang A
        new Select(driver.findElement(By.cssSelector("select#RESULT_RadioButton-2"))).selectByVisibleText("Sophomore");

        // Swtich ra lai trang A
        driver.switchTo().defaultContent();

        // Thao tac voi 1 element ben ngoai Iframe (Trang A)
        driver.findElement(By.cssSelector("nav.header--desktop-floater a.menu-item-login")).click();
        sleepInSecond(3);

    }

    @Test
    public void TC_02_KynaEnlish() {
        //trang A
        driver.get("https://skills.kynaenglish.vn/");

        //Iframe element
        WebElement formIframe = driver.findElement(By.cssSelector("div.face-content>iframe"));
        // check Iframe displayed
        Assert.assertTrue(formIframe.isDisplayed());

        //Switch vao Frame/Iframe truoc khi thao tac voi element ben trong
        // C3: dung element //nen dung
        driver.switchTo().frame(formIframe);

        // verify followers number
        Assert.assertEquals(driver.findElement(
                By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText()
                ,"176K followers");

        // Swtich ra lai trang A
        driver.switchTo().defaultContent();
        // Switch vao Iframe Wechat
        driver.switchTo().frame("cs_chat_iframe");


        //driver.findElement(By.cssSelector("div.meshim_widget_components_chatButton_Button")).click();
        driver.findElement(By.cssSelector("div.button_bar")).click();
        sleepInSecond(3);

        driver.findElement(By.cssSelector("input.input_name")).sendKeys("John wick");

        //Swtich ve trang Default/parent chua iframe
        driver.switchTo().defaultContent();

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
