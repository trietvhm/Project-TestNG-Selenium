package webdriver;

import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;


import java.time.Duration;
import java.util.List;

public class Topic_12_Checkbox_Radio {
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
    public void TC_01_Default_Telerik() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

        By dualZoneCheckBox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
        By sideAirBags = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input");

        // Chon 2 check box
        // click vao check box
        // Co 2 case ton tai

        // Case 1: Neu nhu app nay mo ra ma checkBox da duoc chon thi sao
        checkToElement(sideAirBags);
        // Case 2: Neu nhu app nay mo ra ma checkBox chua duoc chon
        // ==> phai kiem tra
        checkToElement(dualZoneCheckBox);

        // Verify checkBox da được chon thanh cong
        Assert.assertTrue(driver.findElement(sideAirBags).isSelected());
        Assert.assertTrue(driver.findElement(dualZoneCheckBox).isSelected());

        // Bo chon 2 checkBox chi can viet nguoc lai
        uncheckToElement(sideAirBags);
        uncheckToElement(dualZoneCheckBox);

        // Verify checkBox da được BO chon thanh cong
        Assert.assertFalse(driver.findElement(sideAirBags).isSelected());
        Assert.assertFalse(driver.findElement(dualZoneCheckBox).isSelected());

    }

    @Test
    public void TC_02_Default_Telerik_Radio() { // phai hieu hanh vi tung loai element
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");

        By peotroRadio = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
        By diezelRadio = By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::input");

        // click chon 1 trong 2
        checkToElementPlus(peotroRadio);

        // Verify
        Assert.assertTrue(driver.findElement(peotroRadio).isSelected());
        Assert.assertFalse(driver.findElement(diezelRadio).isSelected());

        checkToElementPlus(diezelRadio);

        Assert.assertFalse(driver.findElement(peotroRadio).isSelected());
        Assert.assertTrue(driver.findElement(diezelRadio).isSelected());


    }

    @Test
    public void TC_03_Select_All_Checkbox() {
        driver.get("https://automationfc.github.io/multiple-fields/");

        List<WebElement> allCheckBoxes = driver.findElements(By.cssSelector("div.form-single-column input[type='checkbox']"));

        // Chon het tat ca cac element trong man hinh do
        for (WebElement checkbox : allCheckBoxes) {
            if (!checkbox.isSelected()) {
                checkbox.click();
                sleepInSecond(1);
            }
        }
        // verify hết tất ca cac checkBox
        for (WebElement checkbox : allCheckBoxes) {
            Assert.assertTrue(checkbox.isSelected());
        }
        driver.manage().deleteAllCookies(); // xoa het trang thai cua page hien tai di
        driver.navigate().refresh();

        allCheckBoxes = driver.findElements(By.cssSelector("div.form-single-column input[type='checkbox']"));


        // Chon 1 chekBox/Radio nao do trong tat ca cac checkBox/Radio
        for (WebElement checkbox : allCheckBoxes) {
            if (checkbox.getAttribute("value").equals("Heart Attack") && !checkbox.isSelected()) {
                checkbox.click();
            }
        }

        //verify hết tất ca cac checkbox
        for (WebElement checkbox : allCheckBoxes) {
            if (checkbox.getAttribute("value").equals("Heart Attack")) {
                Assert.assertTrue(checkbox.isSelected());// kiem tra checkbox da duoc chon
            } else Assert.assertFalse(checkbox.isSelected());
        }
    }

    @Test
    public void TC_04_Custom_Google_Doc() {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");

        By canThoRadio = By.xpath("//div[@aria-label='Cần Thơ']");
        By quangNamCheckbox = By.xpath("//div[@aria-label='Quảng Nam']");
        By quangBinhCheckbox = By.xpath("//div[@aria-label='Quảng Bình']");

        // verify radio is not selected
        Assert.assertEquals(driver.findElement(canThoRadio).getAttribute("aria-checked"),"false");
        Assert.assertEquals(driver.findElement(quangNamCheckbox).getAttribute("aria-checked"),"false");
        Assert.assertEquals(driver.findElement(quangBinhCheckbox).getAttribute("aria-checked"),"false");
        //Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ'] and @aria-checked='false'")).isDisplayed());

        driver.findElement(canThoRadio).click();
        driver.findElement(quangNamCheckbox).click();
        driver.findElement(quangBinhCheckbox).click();
        sleepInSecond(3);

        // verify radio is selected
        Assert.assertEquals(driver.findElement(canThoRadio).getAttribute("aria-checked"),"true");
        Assert.assertEquals(driver.findElement(quangNamCheckbox).getAttribute("aria-checked"),"true");
        Assert.assertEquals(driver.findElement(quangBinhCheckbox).getAttribute("aria-checked"),"true");


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

    public void checkToElement(By byxpath) {
        if (!driver.findElement(byxpath).isSelected()) { // neu element chua duoc chon thi moi click
            driver.findElement(byxpath).click();
            sleepInSecond(5);
        }
    }

    public void checkToElementPlus(By byxpath) {
        if (!driver.findElement(byxpath).isSelected()) { // neu element chua duoc chon thi moi click
            WebElement element = driver.findElement(byxpath);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", element);
            js.executeScript("arguments[0].click();", element);

        }
    }

    public void uncheckToElement(By byxpath) {
        if (driver.findElement(byxpath).isSelected()) { // neu element dang duoc chon thi minh click se thanh bo chon
            driver.findElement(byxpath).click();
            sleepInSecond(3);
        }
    }
   /* public void getWebDriver(){
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index#");
    }*/
   /* WebElement element = driver.findElement(byxpath);
    Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
    sleepInSecond(2);*/


}
