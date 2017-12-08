package demo.pageObject;

import demo.TicketInfor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SelectFlight extends ParentPage{

    public SelectFlight(WebDriver driver) {
        super(driver);
    }

    public static TicketInfor selectFlight (WebDriver driver, TicketInfor data){

        TicketInfor out = new TicketInfor();

        List<WebElement> outFlight = driver.findElements(By.name("outFlight"));
        outFlight.get(new Integer(data.getOutFlightSelect())).click();

        String _outFlightName = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table[1]/tbody/tr[3]/td[2]/font/b")).getText();
        String _outFlightTime = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table[1]/tbody/tr[3]/td[3]/font")).getText();

        List<WebElement> inFlight = driver.findElements(By.name("inFlight"));
        inFlight.get(new Integer(data.getInFlightSelect())).click();

        String _inFlightName = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table[2]/tbody/tr[3]/td[2]/font/b")).getText();
        String _inFlightTime = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table[2]/tbody/tr[3]/td[3]/font")).getText();

        driver.findElement(By.name("reserveFlights")).click();

        return out;

    }

}
