package demo.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class HomePage {

    public static void logIn (WebDriver driver) {

        WebElement userName = driver.findElement(By.name("userName"));
        userName.sendKeys("tutorial");

        WebElement passWord = driver.findElement(By.name("password"));
        passWord.sendKeys("tutorial");

        driver.findElement(By.name("login")).click();


    }

}
