package demo.pageObject;

import demo.TicketInfor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PassDetails extends ParentPage {

    WebElement passFirstName = driver.findElement(By.name("passFirst0"));
    WebElement passLastName = driver.findElement(By.name("passLast0"));
    WebElement creditNum = driver.findElement(By.name("creditnumber"));

    WebElement billAddress = driver.findElement(By.name("billAddress1"));
    WebElement billCity = driver.findElement(By.name("billCity"));
    WebElement billState = driver.findElement(By.name("billState"));
    WebElement billPostal = driver.findElement(By.name("billZip"));

    WebElement delAddress = driver.findElement(By.name("delAddress1"));
    WebElement delCity = driver.findElement(By.name("delCity"));
    WebElement delState = driver.findElement(By.name("delState"));
    WebElement delPostal = driver.findElement(By.name("delZip"));

    public PassDetails(WebDriver driver) {
        super(driver);
    }

    public String[] inPutInfor (TicketInfor data){

        String[] out = new String[4];

        String outFlightPrice = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[2]/td/table/tbody/tr[3]/td[3]/font")).getText();
        String inFlightPrice = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[2]/td/table/tbody/tr[6]/td[3]/font")).getText();
        out[0] = outFlightPrice;
        out[1] = inFlightPrice;

        passFirstName.sendKeys("Vinz");
        passLastName.sendKeys("Nguyen");
        creditNum.sendKeys("0123456789");

        billAddress.clear();
        billCity.clear();
        billPostal.clear();
        billState.clear();
        delAddress.clear();
        delCity.clear();
        delState.clear();
        delPostal.clear();

        billAddress.sendKeys(data.getBillAddress());
        billCity.sendKeys(data.getBillCity());
        billPostal.sendKeys(data.getBillPostal());
        billState.sendKeys(data.getBillState());

        delAddress.sendKeys(data.getDelAddress());
        delCity.sendKeys(data.getDelCity());
        delPostal.sendKeys(data.getDelPostal());
        delState.sendKeys(data.getDelState());

        String tax = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[2]/td/table/tbody/tr[8]/td[2]/font")).getText();
        String totalPrice = driver.findElement(By.xpath("/html/body/div/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[5]/td/form/table/tbody/tr[2]/td/table/tbody/tr[9]/td[2]/font/b")).getText();
        out[2] = tax;
        out[3] = totalPrice;

        driver.findElement(By.name("buyFlights")).click();

        return out;
    }

}
