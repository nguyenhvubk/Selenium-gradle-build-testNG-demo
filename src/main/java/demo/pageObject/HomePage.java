package demo.pageObject;

import demo.TicketInfor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class HomePage {

    public static void logIn (WebDriver driver, TicketInfor data) {

        WebElement userName = driver.findElement(By.name("userName"));
        userName.sendKeys(data.getUserName());

        WebElement passWord = driver.findElement(By.name("password"));
        passWord.sendKeys(data.getPassWord());

        driver.findElement(By.name("login")).click();


    }

}
