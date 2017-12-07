package demo.pageObject;

import demo.TicketInfor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class FlightFinder {

    public static TicketInfor selectFlight(WebDriver driver, TicketInfor data) {

        TicketInfor out = new TicketInfor();

        List<WebElement> tripType = driver.findElements(By.name("tripType"));
        tripType.get(new Integer(data.getFlightType())).click();

        Select passCount = new Select(driver.findElement(By.name("passCount")));
        passCount.selectByIndex(new Integer(data.getPassCountSelect()));
        String _passCount = passCount.getOptions().get(new Integer(data.getPassCountSelect())).getText().trim();
        out.setPassCountSelect(_passCount);

        Select fromPort = new Select(driver.findElement(By.name("fromPort")));
        fromPort.selectByIndex(new Integer(data.getOutFlightPortSelect()));
        String _fromPort = fromPort.getOptions().get(new Integer(data.getOutFlightPortSelect())).getText().trim();
        out.setOutFlightPortSelect(_fromPort);

        Select fromMonth = new Select(driver.findElement(By.name("fromMonth")));
        fromMonth.selectByIndex(new Integer(data.getOutFlightMonth()));
        String _fromMonth = fromMonth.getOptions().get(new Integer(data.getOutFlightMonth())).getText().trim();
        out.setOutFlightMonth(_fromMonth);

        Select fromDay = new Select(driver.findElement(By.name("fromDay")));
        fromDay.selectByIndex(new Integer(data.getOutFlightDate()));
        String _fromDay = fromDay.getOptions().get(new Integer(data.getOutFlightDate())).getText().trim();
        out.setOutFlightDate(_fromDay);

        Select toPort = new Select(driver.findElement(By.name("toPort")));
        toPort.selectByIndex(new Integer(data.getInFlightPortSelect()));
        String _toPort = toPort.getOptions().get(new Integer(data.getInFlightPortSelect())).getText().trim();
        out.setInFlightPortSelect(_toPort);

        Select toMonth = new Select(driver.findElement(By.name("toMonth")));
        toMonth.selectByIndex(new Integer(data.getInFlightMonth()));
        String _toMonth = toMonth.getOptions().get(new Integer(data.getInFlightMonth())).getText().trim();
        out.setInFlightMonth(_toMonth);

        Select toDay = new Select(driver.findElement(By.name("toDay")));
        toDay.selectByIndex(new Integer(data.getInFlightDate()));
        String _toDay = toDay.getOptions().get(new Integer(data.getInFlightDate())).getText().trim();
        out.setInFlightDate(_toDay);

        List<WebElement> serviceClass = driver.findElements(By.name("servClass"));
        serviceClass.get(new Integer(data.getFlightClassSelect())).click();
        String _serviceClass = serviceClass.get(new Integer(data.getFlightClassSelect())).getAttribute("value");//getText() do not work
        out.setFlightClassSelect(_serviceClass);

        Select airline = new Select(driver.findElement(By.name("airline")));
        airline.selectByIndex(new Integer(data.getAirline()));
        String _airline = airline.getOptions().get(new Integer(data.getAirline())).getText().trim();
        out.setAirline(_airline);

        driver.findElement(By.name("findFlights")).click();

        return out;

    }

}
