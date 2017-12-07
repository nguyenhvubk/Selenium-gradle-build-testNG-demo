package demo;

import demo.pageObject.HomePage;
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

    public TicketInfor booking(WebDriver driver){

        Assert.assertEquals(driver.getCurrentUrl(),"http://newtours.demoaut.com/");

        //log gin
        HomePage.logIn(driver);

        waitForLoad(driver);

        System.out.println(driver.getCurrentUrl());
        Assert.assertEquals(driver.getCurrentUrl().contains("mercuryreservation"), true);

        //flight finder
        ////flight details
        List<WebElement> tripType = driver.findElements(By.name("tripType"));
        tripType.get(0).click();

        Select passCount = new Select(driver.findElement(By.name("passCount")));
        passCount.selectByIndex(0);
        String _passCount = passCount.getOptions().get(0).getText().trim();

        Select fromPort = new Select(driver.findElement(By.name("fromPort")));
        fromPort.selectByIndex(1);
        String _fromPort = fromPort.getOptions().get(1).getText();

        Select fromMonth = new Select(driver.findElement(By.name("fromMonth")));
        fromMonth.selectByIndex(1);
        String _fromMonth = fromMonth.getOptions().get(1).getText();

        Select fromDay = new Select(driver.findElement(By.name("fromDay")));
        fromDay.selectByIndex(1);
        String _fromDay = fromDay.getOptions().get(1).getText();

        Select toPort = new Select(driver.findElement(By.name("toPort")));
        toPort.selectByIndex(2);
        String _toPort = toPort.getOptions().get(2).getText();

        Select toMonth = new Select(driver.findElement(By.name("toMonth")));
        toMonth.selectByIndex(1);
        String _toMonth = toMonth.getOptions().get(1).getText();

        Select toDay = new Select(driver.findElement(By.name("toDay")));
        toDay.selectByIndex(24);
        String _toDay = toDay.getOptions().get(24).getText();

        ////Preferences
        List<WebElement> serviceClass = driver.findElements(By.name("servClass"));
        serviceClass.get(1).click();
        String _serviceClass = serviceClass.get(1).getAttribute("value");//getText() do not work

        Select airline = new Select(driver.findElement(By.name("airline")));
        airline.selectByIndex(1);
        String _airline = airline.getOptions().get(1).getText();

        driver.findElement(By.name("findFlights")).click();

        //Select Flight
        List<WebElement> outFlight = driver.findElements(By.name("outFlight"));
        outFlight.get(0).click();

        String _outFlightName = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table[1]/tbody/tr[3]/td[2]/font/b")).getText();
        String _outFlightTime = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table[1]/tbody/tr[3]/td[3]/font")).getText();

        List<WebElement> inFlight = driver.findElements(By.name("inFlight"));
        inFlight.get(0).click();

        String _inFlightName = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table[2]/tbody/tr[3]/td[2]/font/b")).getText();
        String _inFlightTime = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table[2]/tbody/tr[3]/td[3]/font")).getText();

        driver.findElement(By.name("reserveFlights")).click();

        //Passenger Details

        String _outFlightPrice = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[2]/td/table/tbody/tr[3]/td[3]/font")).getText();
        String _inFlightPrice = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[2]/td/table/tbody/tr[6]/td[3]/font")).getText();

        WebElement passFirstName = driver.findElement(By.name("passFirst0"));
        passFirstName.sendKeys("Vinz");

        WebElement passLastName = driver.findElement(By.name("passLast0"));
        passLastName.sendKeys("Nguyen");

        WebElement creditNum = driver.findElement(By.name("creditnumber"));
        creditNum.sendKeys("0123456789");

        String _billAddress = driver.findElement(By.name("billAddress1")).getAttribute("value");
        String _billCity = driver.findElement(By.name("billCity")).getAttribute("value");
        String _billState = driver.findElement(By.name("billState")).getAttribute("value");
        String _billPostal = driver.findElement(By.name("billZip")).getAttribute("value");

        String _delAddress = driver.findElement(By.name("delAddress1")).getAttribute("value");
        String _delCity = driver.findElement(By.name("delCity")).getAttribute("value");
        String _delState = driver.findElement(By.name("delState")).getAttribute("value");
        String _delPostal = driver.findElement(By.name("delZip")).getAttribute("value");

        String _tax = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[2]/td/table/tbody/tr[8]/td[2]/font")).getText();
        String _totalPrice = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[2]/td/table/tbody/tr[9]/td[2]/font/b")).getText();

        driver.findElement(By.name("buyFlights")).click();

        //Cast Ticket Information
        TicketInfor ticket = new TicketInfor();

        String outDate = Month.valueOf(_fromMonth.toUpperCase()).getValue() + "/" + _fromDay + "/2017";
        ticket.setOutFlightPort(_fromPort + " to " + _toPort);
        ticket.setOutFlightPrice("$" + _outFlightPrice + " each");
        ticket.setOutFlightName(outDate + " @ " + _outFlightTime + " w/ " + _outFlightName);
        ticket.setOutFlightClass(_serviceClass);

        String inDate = Month.valueOf(_toMonth.toUpperCase()).getValue() + "/" + _toDay + "/2017";
        ticket.setInFlightPort(_toPort + " to " + _fromPort);
        ticket.setInFlightName(inDate + " @ " + _inFlightTime + " w/ " + _inFlightName);
        ticket.setInFlightPrice("$" + _inFlightPrice + " each");
        ticket.setInFlightClass(_serviceClass);

        ticket.setPassCount(_passCount + " passenger");
        ticket.setBillAdd(_billAddress + "\n\n" + _billCity + ", " + _billState + ", " + _billPostal);
        ticket.setDelAdd(_delAddress + "\n\n" + _delCity + ", " + _delState + ", " + _delPostal);

        ticket.setTax(_tax + " USD");
        ticket.setTotalPrice(_totalPrice + " USD");

        return ticket;

    }

}
