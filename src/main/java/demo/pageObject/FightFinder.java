package demo.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class FightFinder {

    public static void selectFlight(WebDriver driver) {

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

        List<WebElement> serviceClass = driver.findElements(By.name("servClass"));
        serviceClass.get(1).click();
        String _serviceClass = serviceClass.get(1).getAttribute("value");//getText() do not work

        Select airline = new Select(driver.findElement(By.name("airline")));
        airline.selectByIndex(1);
        String _airline = airline.getOptions().get(1).getText();

        driver.findElement(By.name("findFlights")).click();

    }

}
