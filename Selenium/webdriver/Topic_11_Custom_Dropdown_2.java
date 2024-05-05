package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_11_Custom_Dropdown_2 {
    WebDriver driver;
    WebDriverWait explicitWait; // Wait tuong minh

    // khoi tao trinh duyet san
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_JQuery() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
        // parentCSS là HTML của nút xổ xuống
        // childrentCSS là HTML của từng phần tử chứa text khi được xổ ra

        selectItemInDropdown("span#speed-button", "ul#speed-menu div", "Faster");
        sleepInSecond(3);

        selectItemInDropdown("span#files-button", "ul#files-menu div", "ui.jQuery.js");
        sleepInSecond(3);

        selectItemInDropdown("span#number-button", "ul#number-menu div", "15");
        sleepInSecond(3);

        // Verify
        Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button>span.ui-selectmenu-text")).getText(), "Faster");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#files-button>span.ui-selectmenu-text")).getText(), "ui.jQuery.js");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button>span.ui-selectmenu-text")).getText(), "15");


    }

    @Test
    public void TC_02_React() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

        selectItemInDropdown("i.dropdown.icon", "div.item span.text", "Jenny Hess");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Jenny Hess");
        sleepInSecond(3);

        selectItemInDropdown("i.dropdown.icon", "div.item span.text", "Stevie Feliciano");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Stevie Feliciano");
        sleepInSecond(3);

        selectItemInDropdown("i.dropdown.icon", "div.item span.text", "Christian");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Christian");
        sleepInSecond(3);
    }

    @Test
    public void TC_03_VueJS() {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        selectItemInDropdown("div.btn-group>li.dropdown-toggle", "ul.dropdown-menu li", "First Option");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.btn-group>li.dropdown-toggle")).getText(), "First Option");
        sleepInSecond(3);

    }

    @Test
    public void TC_04_editable() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        selectItemInEditableDropdown("input.search", "div.item span", "Afghanistan");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.divider.text")).getText(), "Afghanistan");

    }

    @Test
    public void TC_05_NopCommerce() {
        driver.get("https://demo.nopcommerce.com/register");
        selectItemInDropdown("select[name='DateOfBirthDay']", "select[name='DateOfBirthDay']>option", "18");
        //option in a select
        Assert.assertTrue(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']>option[value='18']")).isSelected());

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

    public void selectItemInDropdown(String parentCss, String childItemCss, String itemTextExpected) {
        driver.findElement(By.cssSelector(parentCss)).click(); //"span#number-button"
        sleepInSecond(1);

        // vừa wait, vừa tìm element
        // Wait nay sau khi wait xong nó se co nhiem vu la tra ve 1 list element nay luon
        /*explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childItemCss)));//"ul#number-menu div"
        List<WebElement> allItems = driver.findElements(By.cssSelector(childItemCss));//"ul#number-menu div"*/

        List<WebElement> allItems = explicitWait.until(ExpectedConditions.
                presenceOfAllElementsLocatedBy(By.cssSelector(childItemCss)));//"ul#number-menu div"

        for (WebElement items : allItems) {
            String textItems = items.getText();
            if (textItems.trim().equals(itemTextExpected)) {
                items.click();
                break;
            }
        }
    }

    public void selectItemInEditableDropdown(String parentCss, String childItemCss, String itemTextExpected) {
        driver.findElement(By.cssSelector(parentCss)).clear(); //"span#number-button"
        driver.findElement(By.cssSelector(parentCss)).sendKeys(itemTextExpected); //"span#number-button"
        sleepInSecond(1);

        List<WebElement> allItems = explicitWait.until(ExpectedConditions.
                presenceOfAllElementsLocatedBy(By.cssSelector(childItemCss)));//"ul#number-menu div"

        for (WebElement items : allItems) {
            String textItems = items.getText();
            if (textItems.equals(itemTextExpected)) {
                items.click();
                break;
            }
        }
    }

}
