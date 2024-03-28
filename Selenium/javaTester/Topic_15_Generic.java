package javaTester;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Topic_15_Generic {
    WebDriver driver;
    FluentWait<WebDriver> fluentDriver;

    FluentWait<WebElement> fluentElement;

    FluentWait<String> fluentString;


    public static void main(String[] args) {
        // List chi chua kieu du lieu String
        // Rang buoc no voi 1 kieu du lieu
        // E T V K L dai dien cho 1 type ma minh truyen vao, minh thay minh can design kieu gi thi minh truyen kieu do
        // dynamic
        // kieu nao cung nhay,
        List<String> student = new ArrayList<>();
        student.add("Tuan");
        student.add("Hai");
        student.add("Tung");
        student.add("Nam");

        // Non-Generic - co nhieu kieu du lieu khong hop le
        List addresses = new ArrayList<>();
        addresses.add("123 Main 1st"); //String
        addresses.add(15); //integer
        addresses.add(15.5); //float
        addresses.add(true);


    }

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
