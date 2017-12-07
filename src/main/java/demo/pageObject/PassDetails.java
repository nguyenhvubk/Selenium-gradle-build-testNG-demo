package demo.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PassDetails {

    public static void inPutInfor (WebDriver driver){

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

    }

}
