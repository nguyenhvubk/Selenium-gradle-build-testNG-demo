package demo.pageObject;

import demo.TicketInfor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BookedPage extends ParentPage {

    public BookedPage(WebDriver driver) {
        super(driver);
    }

    public TicketInfor CollectResult () {

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

}
