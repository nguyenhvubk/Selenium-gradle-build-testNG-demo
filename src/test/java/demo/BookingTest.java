package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class BookingTest {

    public WebDriver driver;

    @Test
    public void TicketInforCheck() throws Exception {

        TicketInfor result = new TicketInfor();
        Booking t = new  Booking();
        TicketInfor expect = t.booking(driver);

        //Collect flight information
        String outFlightText = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/table/tbody/tr[5]/td/table/tbody/tr[3]/td/font")).getText();
        String[] outFlightPart = outFlightText.split("\n");
        result.setOutFlightPort(outFlightPart[0]);
        result.setOutFlightName(outFlightPart[1]);
        result.setOutFlightClass(outFlightPart[2]);
        result.setOutFlightPrice(outFlightPart[3]);

        String inFlightText = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/table/tbody/tr[5]/td/table/tbody/tr[5]/td/font")).getText();
        String[] inFlightPart = inFlightText.split("\n");
        result.setInFlightPort(inFlightPart[0]);
        result.setInFlightName(inFlightPart[1]);
        result.setInFlightClass(inFlightPart[2]);
        result.setInFlightPrice(inFlightPart[3]);

        result.setPassCount(driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/table/tbody/tr[5]/td/table/tbody/tr[7]/td/font")).getText());
        result.setBillAddress(driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/table/tbody/tr[5]/td/table/tbody/tr[9]/td/p/font[1]")).getText());
        result.setDelAddress(driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/table/tbody/tr[5]/td/table/tbody/tr[11]/td/p/font[1]")).getText());

        String tax = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/table/tbody/tr[5]/td/table/tbody/tr[12]/td/table/tbody/tr[1]/td[2]/font/font/font/b/font")).getText();
        String totalPrice = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/table/tbody/tr[5]/td/table/tbody/tr[12]/td/table/tbody/tr[2]/td[2]/font/b/font/font/b/font")).getText();

        result.setTax(tax);
        result.setTotalPrice(totalPrice);

        System.out.printf(expect.toString());

        Assert.assertEquals(result.toString(), expect.toString());

    }

    @BeforeMethod
    public void beforeMethod() throws Exception {

        driver = new ChromeDriver();
        driver.get("http://newtours.demoaut.com");

    }

    @AfterMethod
    public void afterMethod() throws Exception {
    }



}