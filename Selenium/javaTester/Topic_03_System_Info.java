package javaTester;

import org.openqa.selenium.*;

import java.io.File;

public class Topic_03_System_Info {
    public static void main(String[] args) {
        // Lay ra đường dẫn tương đối tại thư mục hiện tại
        //String projectPath = System.getProperty("user.dir");
        //String fileName = "danang.jpg";

        String osName = System.getProperty("os.name");
        System.out.println(osName);
        Keys keys;

        if (osName.startsWith("Windows")) {
            keys = Keys.CONTROL;
        } else {
            keys = Keys.COMMAND;
        }

        Keys cmdCtrl = Platform.getCurrent().is(Platform.WINDOWS) ? Keys.CONTROL : Keys.COMMAND;

        // C1 trong nhạc nào cũng nhảy

        String projectPath = System.getProperty("user.dir");

       /* String character = Platform.getCurrent().is(Platform.WINDOWS) ? "\\" : "/";
        String hcmName = "saigon.jpg";
        String dnName = "danang.jpg";
        String hnName = "hanoi.jpg";

        String hcmFilePath = projectPath  +"uploadFiles" + character + hcmName;
        String dnFilePath = projectPath + character +"uploadFiles" + character + dnName;
        String hnFilePath = projectPath + character + "uploadFiles" + character + hnName;

        System.out.println(hcmFilePath);
        System.out.println(dnFilePath);
        System.out.println(hnFilePath);*/

        // Dau phan cach tieng anh la:  Seperator
        // tu dong detect ra cai he dieu hanh de no ra dung ki tu la sang trai hay sang phai
        // Việc sử dụng File.separator luôn được khuyến khích dùng bởi vì nó sẽ kiểm tra hệ điều hành của bạn
        // và tự động hiển thị chính xác dấu phân cách file
        // String character = File.separator;

        String hcmName = "saigon.jpg";
        String dnName = "danang.jpg";
        String hnName = "hanoi.jpg";

        String hcmFilePath = projectPath + File.separator + "uploadFiles" + File.separator + hcmName;
        String dnFilePath = projectPath + File.separator + "uploadFiles" + File.separator + dnName;
        String hnFilePath = projectPath + File.separator + "uploadFiles" + File.separator + hnName;

        System.out.println(hcmFilePath);
        System.out.println(dnFilePath);
        System.out.println(hnFilePath);


    }
}
