package demo.pageObject;

import org.openqa.selenium.WebDriver;

public class ParentPage {

    public WebDriver driver;

    public ParentPage(){

    }

    public ParentPage(WebDriver driver) {
        this.driver = driver;
    }
}
