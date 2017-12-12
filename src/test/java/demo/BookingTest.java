package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BookingTest {



    public WebDriver driver;
    public Properties prop = new Properties();
    public InputStream input = null;
    public TicketInfor inputData = new TicketInfor();

    private TicketInfor CollectFlightInfor () {

        TicketInfor out = new TicketInfor();

        String outFlightText = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/table/tbody/tr[5]/td/table/tbody/tr[3]/td/font")).getText();
        String[] outFlightPart = outFlightText.split("\n");
        out.setOutFlightPort(outFlightPart[0]);
        out.setOutFlightName(outFlightPart[1]);
        out.setOutFlightClass(outFlightPart[2]);
        out.setOutFlightPrice(outFlightPart[3]);

        String inFlightText = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/table/tbody/tr[5]/td/table/tbody/tr[5]/td/font")).getText();
        String[] inFlightPart = inFlightText.split("\n");
        out.setInFlightPort(inFlightPart[0]);
        out.setInFlightName(inFlightPart[1]);
        out.setInFlightClass(inFlightPart[2]);
        out.setInFlightPrice(inFlightPart[3]);

        out.setPassCount(driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/table/tbody/tr[5]/td/table/tbody/tr[7]/td/font")).getText());
        out.setBillAdd(driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/table/tbody/tr[5]/td/table/tbody/tr[9]/td/p/font[1]")).getText());
        out.setDelAdd(driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/table/tbody/tr[5]/td/table/tbody/tr[11]/td/p/font[1]")).getText());

        String tax = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/table/tbody/tr[5]/td/table/tbody/tr[12]/td/table/tbody/tr[1]/td[2]/font/font/font/b/font")).getText();
        String totalPrice = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/table/tbody/tr[5]/td/table/tbody/tr[12]/td/table/tbody/tr[2]/td[2]/font/b/font/font/b/font")).getText();

        out.setTax(tax);
        out.setTotalPrice(totalPrice);

        return out;
    }

    @Test
    public void TicketInforCheck() {

        TicketInfor result = new TicketInfor();
        Booking t = new  Booking();

        TicketInfor expect = t.booking(driver, inputData);

        //Collect flight
        result = CollectFlightInfor();

        System.out.printf(result.toString());

        Assert.assertEquals(result.toString(), expect.toString());

    }

    @BeforeMethod
    @Parameters({"browserName" , "osName"})
    public void beforeMethod(String browserName, String OsName) throws Exception {

//        driver = new ChromeDriver();

        // Init Webdriver based on browser and go to test page
        if (OsName.equalsIgnoreCase("windows")) {
            if (browserName.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", "./WebDriver/chromedriver.exe");
                driver = new ChromeDriver();
            } else {
                System.setProperty("webdriver.gecko.driver", "./WebDriver/geckodriver.exe");
                driver = new FirefoxDriver();
            }
        } else if (OsName.equalsIgnoreCase("mac")) {
            if (browserName.equalsIgnoreCase("chrome")) {
                System.setProperty("webdriver.chrome.driver", "./WebDriver/chromedriver");
                driver = new ChromeDriver();
            } else {
                System.setProperty("webdriver.gecko.driver", "./WebDriver/geckodriver");
                driver = new FirefoxDriver();
            }
        }

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