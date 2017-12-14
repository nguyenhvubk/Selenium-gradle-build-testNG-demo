package demo;

import demo.pageObject.*;
import demo.Utilities.Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Month;

public class BookingTest {

    public WebDriver driver;
    public String CSVPath = "./TestData/Book1.csv";

    @DataProvider(name = "TestData")
    public Object[][] getTestData() throws Exception{

        Object[][] out = Utilities.ParseTestDataFromCSV(CSVPath);
        return out;

    }

    @Test(dataProvider = "TestData")
    public void TicketInforCheck(TicketInfor inputData, Boolean temp) throws Exception {

        //home page check
        Assert.assertEquals(driver.getCurrentUrl(),"http://newtours.demoaut.com/");

        //Log In
        HomePage homePage = new HomePage(driver);
        homePage.validLogIn(inputData);
        Utilities.waitForLoad(driver, "findFlights");

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
        BookedPage bookedPage = new BookedPage(driver);
        result = bookedPage.CollectResult();

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

    }

    @AfterMethod
    public void afterMethod() throws Exception {
        driver.close();
    }

}