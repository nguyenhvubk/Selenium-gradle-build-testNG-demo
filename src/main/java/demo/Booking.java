package demo;

import demo.pageObject.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Month;
import java.util.List;

public class Booking {

    void waitForLoad(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("findFlights")));
    }

    public TicketInfor booking(WebDriver driver, TicketInfor data){

        //home page check
        Assert.assertEquals(driver.getCurrentUrl(),"http://newtours.demoaut.com/");

        //Log In
        HomePage homePage = new HomePage(driver);
        homePage.validLogIn(data);
        waitForLoad(driver);

        //login check
        Assert.assertEquals(driver.getCurrentUrl().contains("mercuryreservation"), true);

        //Flight Finder
        FlightFinder flightFinderPage = new FlightFinder(driver);
        TicketInfor flightFinder = flightFinderPage.selectFlight(data);
        
        //Select Flight
        SelectFlight selectFlightPage = new SelectFlight(driver);
        String[] selectFlight = selectFlightPage.selectFlight(data);

        //Passenger Details
        PassDetails passDetailsPage = new PassDetails(driver);
        String[] passDetails = passDetailsPage.inPutInfor(data);

        //Cast Ticket Information
        TicketInfor ticket = new TicketInfor();

        String outDate = Month.valueOf(flightFinder.getOutFlightMonth().toUpperCase()).getValue() + "/" + flightFinder.getOutFlightDate() + "/2017";
        ticket.setOutFlightPort(flightFinder.getOutFlightPortSelect() + " to " + flightFinder.getInFlightPortSelect());
        ticket.setOutFlightPrice("$" + passDetails[0] + " each");
        ticket.setOutFlightName(outDate + " @ " + selectFlight[1] + " w/ " + selectFlight[0]);
        ticket.setOutFlightClass(flightFinder.getFlightClassSelect());

        String inDate = Month.valueOf(flightFinder.getInFlightMonth().toUpperCase()).getValue() + "/" + flightFinder.getInFlightDate() + "/2017";
        ticket.setInFlightPort(flightFinder.getInFlightPortSelect() + " to " + flightFinder.getOutFlightPortSelect());
        ticket.setInFlightName(inDate + " @ " + selectFlight[3] + " w/ " + selectFlight[2]);
        ticket.setInFlightPrice("$" + passDetails[1] + " each");
        ticket.setInFlightClass(flightFinder.getFlightClassSelect());

        ticket.setPassCount(flightFinder.getPassCountSelect() + " passenger");
        ticket.setBillAdd(data.getBillAddress() + "\n\n" + data.getBillCity() + ", " + data.getBillState() + ", " + data.getBillPostal());
        ticket.setDelAdd(data.getDelAddress() + "\n\n" + data.getDelCity() + ", " + data.getDelState() + ", " + data.getDelPostal());

        ticket.setTax(passDetails[2] + " USD");
        ticket.setTotalPrice(passDetails[3] + " USD");

        return ticket;

    }

}
