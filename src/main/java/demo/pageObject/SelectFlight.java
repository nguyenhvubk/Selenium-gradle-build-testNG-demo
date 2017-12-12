package demo.pageObject;

import demo.TicketInfor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SelectFlight extends ParentPage{

    List<WebElement> outFlight = driver.findElements(By.name("outFlight"));
    List<WebElement> inFlight = driver.findElements(By.name("inFlight"));

    public SelectFlight(WebDriver driver) {
        super(driver);
    }

    public String[] selectFlight (TicketInfor data){

        String[] out = new String[4];

        Integer outFlightIndex = 3 + 2*(new Integer(data.getOutFlightSelect()));
        Integer inFlightIndex = 3 + 2*(new Integer(data.getInFlightSelect()));

        outFlight.get(new Integer(data.getOutFlightSelect())).click();
        String _outFlightName = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table[1]/tbody/tr[" + outFlightIndex + "]/td[2]/font/b")).getText();
        String _outFlightTime = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table[1]/tbody/tr[" + outFlightIndex + "]/td[3]/font")).getText();

        inFlight.get(new Integer(data.getInFlightSelect())).click();
        String _inFlightName = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table[2]/tbody/tr[" + inFlightIndex + "]/td[2]/font/b")).getText();
        String _inFlightTime = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table[2]/tbody/tr[" + inFlightIndex + "]/td[3]/font")).getText();

        driver.findElement(By.name("reserveFlights")).click();

        out[0] = _outFlightName;
        out[1] = _outFlightTime;
        out[2] = _inFlightName;
        out[3] = _inFlightTime;

        return out;

    }

}
