package demo;

import demo.pageObject.FlightFinder;
import demo.pageObject.HomePage;
import demo.pageObject.PassDetails;
import demo.pageObject.SelectFlight;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Month;
import java.util.Properties;

public class BookingTest {


    public WebDriver driver;
    public Properties prop = new Properties();
    public InputStream input = null;
    public TicketInfor inputData = new TicketInfor();

    private void waitForLoad(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 0);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("findFlights")));
    }

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

        out.setPassCount(driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/table/tbody/tr[5]/td/table/tbody/tr[7]/td/font")).getText().substring(0,1));
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

        //home page check
        Assert.assertEquals(driver.getCurrentUrl(),"http://newtours.demoaut.com/");

        //Log In
        HomePage homePage = new HomePage(driver);
        homePage.validLogIn(inputData);
        waitForLoad(driver);

        //login check
        Assert.assertEquals(driver.getCurrentUrl().contains("mercuryreservation"), true);

        //Flight Finder
        FlightFinder flightFinderPage = new FlightFinder(driver);
        TicketInfor flightFinder = flightFinderPage.selectFlight(inputData);

        //Select Flight
        SelectFlight selectFlightPage = new SelectFlight(driver);
        String[] selectFlight = selectFlightPage.selectFlight(inputData);

        //Passenger Details
        PassDetails passDetailsPage = new PassDetails(driver);
        String[] passDetails = passDetailsPage.inPutInfor(inputData);

        //Cast Ticket Information
        TicketInfor expect = new TicketInfor();
        TicketInfor result = new TicketInfor();

        String outDate = Month.valueOf(flightFinder.getOutFlightMonth().toUpperCase()).getValue() + "/" + flightFinder.getOutFlightDate() + "/2017";
        expect.setOutFlightPort(flightFinder.getOutFlightPortSelect() + " to " + flightFinder.getInFlightPortSelect());
        expect.setOutFlightPrice("$" + passDetails[0] + " each");
        expect.setOutFlightName(outDate + " @ " + selectFlight[1] + " w/ " + selectFlight[0]);
        expect.setOutFlightClass(flightFinder.getFlightClassSelect());

        String inDate = Month.valueOf(flightFinder.getInFlightMonth().toUpperCase()).getValue() + "/" + flightFinder.getInFlightDate() + "/2017";
        expect.setInFlightPort(flightFinder.getInFlightPortSelect() + " to " + flightFinder.getOutFlightPortSelect());
        expect.setInFlightName(inDate + " @ " + selectFlight[3] + " w/ " + selectFlight[2]);
        expect.setInFlightPrice("$" + passDetails[1] + " each");
        expect.setInFlightClass(flightFinder.getFlightClassSelect());

        expect.setPassCount(flightFinder.getPassCountSelect());
        expect.setBillAdd(inputData.getBillAddress() + "\n\n" + inputData.getBillCity() + ", " + inputData.getBillState() + ", " + inputData.getBillPostal());
        expect.setDelAdd(inputData.getDelAddress() + "\n\n" + inputData.getDelCity() + ", " + inputData.getDelState() + ", " + inputData.getDelPostal());

        expect.setTax(passDetails[2] + " USD");
        expect.setTotalPrice(passDetails[3] + " USD");

        //Collect flight infor
        result = CollectFlightInfor();
        Assert.assertEquals(result.toString(), expect.toString());

    }

    @BeforeMethod
    @Parameters({"browserName" , "osName"})
    public void beforeMethod(@Optional("chrome") String browserName,@Optional("mac") String OsName) throws Exception {

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

            Integer passCount = new Integer(inputData.getPassCountSelect()) + 1;
            String[][] passList = new String[passCount][2];
            for (int i = 0; i <= new Integer(inputData.getPassCountSelect()); i++){
                passList[i][0] = prop.getProperty("firstName" + i);
                passList[i][1] = prop.getProperty("lastName" + i);
            }
            inputData.setPassList(passList);

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