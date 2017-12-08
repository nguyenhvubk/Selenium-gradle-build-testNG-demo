package demo.pageObject;

import demo.TicketInfor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class HomePage extends ParentPage {

    WebElement userName = driver.findElement(By.name("userName"));
    WebElement passWord = driver.findElement(By.name("password"));

    public HomePage(WebDriver driver){
        super(driver);
    }

    public void validLogIn (TicketInfor data) {

        this.userName.sendKeys(data.getUserName());

        this.passWord.sendKeys(data.getPassWord());

        driver.findElement(By.name("login")).click();


    }

}
