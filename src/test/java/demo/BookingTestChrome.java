package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BookingTestChrome {

    public WebDriver driver;
    public Properties prop = new Properties();
    public InputStream input = null;
    public TicketInfor inputData = new TicketInfor();

    @Test
    public void TicketInforCheck() {

        TicketInfor result = new TicketInfor();
        Booking t = new  Booking();
        TicketInfor expect = t.booking(driver, inputData);

        //Collect flight result information
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
        result.setBillAdd(driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/table/tbody/tr[5]/td/table/tbody/tr[9]/td/p/font[1]")).getText());
        result.setDelAdd(driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/table/tbody/tr[5]/td/table/tbody/tr[11]/td/p/font[1]")).getText());

        String tax = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/table/tbody/tr[5]/td/table/tbody/tr[12]/td/table/tbody/tr[1]/td[2]/font/font/font/b/font")).getText();
        String totalPrice = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/table/tbody/tr[5]/td/table/tbody/tr[12]/td/table/tbody/tr[2]/td[2]/font/b/font/font/b/font")).getText();

        result.setTax(tax);
        result.setTotalPrice(totalPrice);

        System.out.printf(result.toString());

        Assert.assertEquals(result.toString(), expect.toString());

    }

    @BeforeMethod
    public void beforeMethod() throws Exception {

        driver = new ChromeDriver();
        driver.get("http://newtours.demoaut.com");

        try {

            input = new FileInputStream("./TestData/config.properties");

            // load a properties file
            prop.load(input);

            inputData.setUserName(prop.getProperty("userName"));
            inputData.setPassWord(prop.getProperty("passWord"));

            inputData.setFlightType(prop.getProperty("flightType"));
            inputData.setPassCountSelect(prop.getProperty("passCount"));
            inputData.setOutFlightPortSelect(prop.getProperty("outFlightPort"));
            inputData.setOutFlightMonth(prop.getProperty("outFlightMonth"));
            inputData.setOutFlightDate(prop.getProperty("outFlightDate"));
            inputData.setInFlightPortSelect(prop.getProperty("inFlightPort"));
            inputData.setInFlightMonth(prop.getProperty("inFlightMonth"));
            inputData.setInFlightDate(prop.getProperty("inFlightDate"));
            inputData.setFlightClassSelect(prop.getProperty("flightClass"));
            inputData.setAirline(prop.getProperty("airline"));

            inputData.setOutFlightSelect(prop.getProperty("outFlightSelect"));
            inputData.setInFlightSelect(prop.getProperty("inFlightSelect"));

            inputData.setFirstName(prop.getProperty("firstName"));
            inputData.setLastName(prop.getProperty("lastName"));
            inputData.setCreditNum(prop.getProperty("creditNum"));

            inputData.setBillAddress(prop.getProperty("billAddress"));
            inputData.setBillCity(prop.getProperty("billCity"));
            inputData.setBillState(prop.getProperty("billState"));
            inputData.setBillPostal(prop.getProperty("billPostal"));

            inputData.setDelAddress(prop.getProperty("delAddress"));
            inputData.setDelCity(prop.getProperty("delCity"));
            inputData.setDelState(prop.getProperty("delState"));
            inputData.setDelPostal(prop.getProperty("delPostal"));

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @AfterMethod
    public void afterMethod() throws Exception {
    }

}