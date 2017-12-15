package demo.Utilities;

import demo.TicketInfor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utilities {

    public static void waitForLoad(WebDriver driver,String elementType, String presence) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        if (elementType == "name") {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.name(presence)));
        } else if (elementType == "xpath"){
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(presence)));
        }
    }

    public static Object[][] ParseTestDataFromCSV (String CSVPath) throws Exception{

        List<List<String>> listData = new ArrayList<>();
        Scanner scanner = new Scanner(new File(CSVPath));
        //CSV handle
        //skip header
        if (scanner.hasNext()){
            scanner.nextLine();
        }
        while (scanner.hasNext()) {
            List<String> line = CSVUtil.parseLine(scanner.nextLine());
            //System.out.println(line);
            listData.add(line);
        }
        scanner.close();

        Object[][] out = new Object[listData.size()][2];
        for (Integer i = 0; i < listData.size(); i++){

            TicketInfor dt = new TicketInfor();
            dt.setFlightType(listData.get(i).get(0));
            dt.setPassCountSelect(listData.get(i).get(1));
            dt.setOutFlightPortSelect(listData.get(i).get(2));
            dt.setOutFlightMonth(listData.get(i).get(3));
            dt.setOutFlightDate(listData.get(i).get(4));
            dt.setInFlightPortSelect(listData.get(i).get(5));
            dt.setInFlightMonth(listData.get(i).get(6));
            dt.setInFlightDate(listData.get(i).get(7));
            dt.setFlightClassSelect(listData.get(i).get(8));
            dt.setAirline(listData.get(i).get(9));
            dt.setOutFlightSelect(listData.get(i).get(10));
            dt.setInFlightSelect(listData.get(i).get(11));
            dt.setBillAddress(listData.get(i).get(12));
            dt.setBillCity(listData.get(i).get(13));
            dt.setBillState(listData.get(i).get(14));
            dt.setBillPostal(listData.get(i).get(15));
            dt.setDelAddress(listData.get(i).get(16));
            dt.setDelCity(listData.get(i).get(17));
            dt.setDelState(listData.get(i).get(18));
            dt.setDelPostal(listData.get(i).get(19));

            dt.setUserName(listData.get(i).get(20));
            dt.setPassWord(listData.get(i).get(21));
            dt.setCreditNum(listData.get(i).get(22));

            Integer passCount = new Integer(dt.getPassCountSelect()) + 1;
            String[][] passList = new String[passCount][2];
            for (int j = 0; j <= new Integer(dt.getPassCountSelect()); j++){
                passList[j][0] = listData.get(i).get(23 + j*2);
                passList[j][1] = listData.get(i).get(24 + j*2);
            }
            dt.setPassList(passList);

            out[i][0] = dt;
            out[i][1] = true;
        }


        return out;
    }

}
