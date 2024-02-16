package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Topic_14_Actions {
    WebDriver driver;
    Actions actions;

    JavascriptExecutor javascriptExecutor;

    // khoi tao trinh duyet san
    @BeforeClass
    //Selenium version  2.x/3.x/4.x/(4.6 tro xuong) bat buoc xai ham beforeclass. Hientai khong co van chay duoc
    public void beforeClass() {
        driver = new FirefoxDriver();

        //no dang gia lap lai hanh vi cua Mouse/keyboard/pen nen khi no dang chay minh khong su dung
        // cac thiet bi nay vi no se bi conflict
        actions = new Actions(driver);// ko gan driver se bi NULL

        javascriptExecutor = (JavascriptExecutor) driver;
        //javascriptExecutor la 1 interface, ep kieu

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_Hover_Tooltip() {
        driver.get("https://automationfc.github.io/jquery-tooltip/");

        WebElement ageTextBox = driver.findElement(By.cssSelector("input#age"));

        actions.moveToElement(ageTextBox).perform();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(),
                "We ask for your age only for statistical purposes.");
    }

    @Test
    public void TC_02_Hover_Menu_Login() {
        driver.get("https://www.myntra.com/");

        WebElement menu = driver.findElement(By.xpath("//a[text()='Kids' and @class='desktop-main']"));
        actions.moveToElement(menu).perform();

        /*driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();
        sleepInSecond(3);*/

        actions.click(driver.findElement(By.xpath("//a[text()='Home & Bath' and @class='desktop-categoryName']"))).perform();
        sleepInSecond(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("h1.title-title")).getText(), "Kids Home Bath");
        // Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Kids Home Bath']")).isDisplayed());
    }

    @Test
    public void TC_03_Hover_Menu_Fahasa() {
        driver.get("https://www.fahasa.com/");

        WebElement menuIcon = driver.findElement(By.cssSelector("span.icon_menu"));
        actions.moveToElement(menuIcon).perform();

        //WebElement sachTrongNuoc= driver.findElement(By.xpath("//li[@class='parent dropdown aligned-left active']//span[text()='Sách Trong Nước' and @class='menu-title']"));
        WebElement sachTrongNuoc = driver.findElement(By.xpath("//a[@title='Bách Hóa Online - Lưu Niệm']"));
        actions.moveToElement(sachTrongNuoc).perform();

        driver.findElement(By.xpath("//div[contains(@class,'fhs_menu_content')]//a[text()='Thiết Bị Số - Phụ Kiện Số']")).click();

        //  Assert.assertEquals(driver.findElement(By.cssSelector("ol.breadcrumb strong")).getText(),"THIẾT BỊ SỐ - PHỤ KIỆN SỐ");

        Assert.assertTrue(driver.findElement(By.xpath("//ol[@class='breadcrumb']//strong[text()='Thiết Bị Số - Phụ Kiện Số']")).isDisplayed());

    }

    @Test
    public void TC_04_Click_Hold() {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> allNumbers = driver.findElements(By.cssSelector("li.ui-state-default"));
        Assert.assertEquals(allNumbers.size(), 20);

        // chon theo block ngang/doc tu 1 -> 15 theo hang/cot
        actions.clickAndHold(allNumbers.get(0)) // click len so 1 va giu chuot
                .pause(2000)// Dung lai 2s
                .moveToElement(allNumbers.get(14)) // Di chuot trai den so 15
                .pause(2000)
                .release() // nha chuot trai ra
                .perform(); // Execute tat ca cac action tren

        sleepInSecond(3);

        //Tong cac so da chon
        List<WebElement> allNumbersSelected = driver.findElements(By.cssSelector("li.ui-state-default.ui-selected"));
        Assert.assertEquals(allNumbersSelected.size(), 12);

        List<String> allNumberExpected = new ArrayList<String>();
        allNumberExpected.add("1");
        allNumberExpected.add("2");
        allNumberExpected.add("3");

        allNumberExpected.add("5");
        allNumberExpected.add("6");
        allNumberExpected.add("7");

        allNumberExpected.add("9");
        allNumberExpected.add("10");
        allNumberExpected.add("11");
        allNumberExpected.add("13");
        allNumberExpected.add("14");
        allNumberExpected.add("15");


        List<String> allNumberActual = new ArrayList<String>();

        for (WebElement element : allNumbersSelected) {
            allNumberActual.add(element.getText());
            // Assert.assertEquals(element.getCssValue("background"),"#F39814");
        }
        Assert.assertEquals(allNumberExpected, allNumberActual);

//        //C2
//        String[] allNumberTextExpectedArray = {"1","2","3","5","6","7","9","10","11","13","14","15"};
//
//        //convert tu array qua
//        List<String> allNumberTextExpected = Arrays.asList(allNumberTextExpectedArray);
//        Assert.assertEquals(allNumberTextExpected,allNumberActual);
    }

    @Test
    public void TC_05_Click_Hold() {
        driver.get("https://automationfc.github.io/jquery-selectable/");

        List<WebElement> allNumbers = driver.findElements(By.cssSelector("li.ui-state-default"));
        Assert.assertEquals(allNumbers.size(), 20);

        // chon 1 -> 12 theo Đủ hang/cot
        actions.clickAndHold(allNumbers.get(0))
                .moveToElement(allNumbers.get(11))
                .release()
                .perform();

        // chon 13,14,15
        actions.keyDown(Keys.CONTROL).perform();//nhan phim ctrl xuong (chưa nhả ra)
        actions.click(allNumbers.get(12))
                .click(allNumbers.get(13))
                .click(allNumbers.get(14))
                .keyUp(Keys.CONTROL)//nha nut control ra
                .perform();

      /*  actions.clickAndHold(allNumbers.get(12))
                .moveToElement(allNumbers.get(14))
                .release()
                .keyUp(Keys.CONTROL)
                .perform();*/

    }

    @Test
    public void TC_06_DoubleClick() {
        driver.get("https://automationfc.github.io/basic-form/index.html");


        WebElement buttonDoubleClick = driver.findElement(By.xpath("//button[text()='Double click me']"));

        //Can scroll toi element rồi mới double click, danh rieng cho firefox moi scroll
        if (driver.toString().contains("firefox")) {
            //scrollIntoView(true): keo mep tren cua element len phia tren cung cua viewport
            //scrollIntoView(fail): keo mep duoi cua element xuong phia duoi cung cua viewport
            javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", buttonDoubleClick);
            sleepInSecond(3);
        } else {
            actions.scrollToElement(buttonDoubleClick).perform();
            sleepInSecond(3);
        }

        actions.doubleClick(buttonDoubleClick).perform();
        sleepInSecond(3);

        Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
    }

    @Test
    public void TC_07_rightClick() {
        driver.get("https://swisnl.github.io/jQuery-contextMenu/demo.html");

        // chua click chuot phai len - cac element chua duoc visible
        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());

        WebElement buttonElement = driver.findElement(By.xpath("//span[text()='right click me']"));
        //WebElement buttonElement = driver.findElement(By.cssSelector("span.context-menu-one"));
        actions.contextClick(buttonElement).perform();
        sleepInSecond(2);

        // moi click chuot phai len - cac element se duoc visible
        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());

        //hover quit
        actions.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
        sleepInSecond(2);

        // kiem tra xem quit da duoc hover thanh cong
        Assert.assertTrue(driver.findElement(
                        By.cssSelector("li.context-menu-icon-quit.context-menu-hover.context-menu-visible"))
                .isDisplayed());

        // click len quit
        actions.click(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
        sleepInSecond(2);

        // accept alert
        driver.switchTo().alert().accept();
        sleepInSecond(2);

        //kiem tra paste khong con hien thi
        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());

    }

    @Test
    public void TC_08_DragDropHtml4() {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");
        WebElement smallCircle = driver.findElement(By.cssSelector("div#draggable"));
        WebElement bigCircle = driver.findElement(By.cssSelector("div#droptarget"));

        //   actions.dragAndDrop(smallCircle,bigCircle).perform();
        actions.clickAndHold(smallCircle).moveToElement(bigCircle).release().perform();
        sleepInSecond(2);

        Assert.assertEquals(bigCircle.getText(), "You did great!");

        Assert.assertEquals(Color.fromString(bigCircle.getCssValue("background-color")).asHex().toLowerCase(), "#03a9f4");
    }

    @Test
    public void TC_09_DragDropHtml5_Css() throws IOException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");

        WebElement columnA = driver.findElement(By.cssSelector("div#column-a"));
        WebElement columnB = driver.findElement(By.cssSelector("div#column-b"));

        //actions.dragAndDrop(columnA, columnB).perform(); // khong chay duoc vi chi co drag chu ko drop duoc
       // actions.clickAndHold(columnA).moveToElement(columnB).release().perform();// khong chay duoc vi chi co drag chu ko drop duoc

        String projectPath= System.getProperty("user.dir"); // lay duong dan cua project

        String dragAndDropFilePath = projectPath + "/dragAndDrop/drag_and_drop_helper.js"; //+ ten folder

        String jsContentFile = getContentFile(dragAndDropFilePath);

        jsContentFile = jsContentFile + "$('div#column-a').simulateDragDrop({ dropTarget: 'div#column-b'});";
        javascriptExecutor.executeScript(jsContentFile);

        //case nay site nao co jquyri thi chay duoc

    }


    @Test
    public void TC_10_DragDropHtml5_Xpath() { // jquyry ko lam viec voi xpath
        driver.get("https://swisnl.github.io/jQuery-contextMenu/demo.html");

    }

    public String getContentFile(String filePath) throws IOException {
        Charset cs = Charset.forName("UTF-8");
        FileInputStream stream = new FileInputStream(filePath);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } finally {
            stream.close();
        }
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

}
