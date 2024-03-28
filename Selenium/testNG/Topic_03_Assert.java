package testNG;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_03_Assert {
    @Test
    public void test01(){
        String fullName = "Automation FC";
        Assert.assertEquals(fullName, "Automation CF","Actual fullname is right");

    }

    @Test
    public void test02(){
        System.out.println("Test 02");
    }

}
